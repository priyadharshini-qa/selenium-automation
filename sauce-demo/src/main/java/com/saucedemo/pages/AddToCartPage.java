package com.saucedemo.pages;

import com.saucedemo.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;

public class AddToCartPage extends BasePage {

    // --- Locators ---
    private By inventoryItems      = By.className("inventory_item");
    private By cartBadge           = By.className("shopping_cart_badge");
    private By cartLink            = By.className("shopping_cart_link");
    private By addToCartButtons    = By.cssSelector("[data-test^='add-to-cart']");
    private By productNames        = By.className("inventory_item_name");
    private By productPrices       = By.className("inventory_item_price");

    // Dynamic locators by product name
    private By addToCartBtn(String productName) {
        return By.cssSelector("[data-test='add-to-cart-"
            + productName.toLowerCase().replace(" ", "-") + "']");
    }

    private By removeBtn(String productName) {
        return By.cssSelector("[data-test='remove-"
            + productName.toLowerCase().replace(" ", "-") + "']");
    }

    // --- Constructor ---
    public AddToCartPage() {
        super();
    }

    // --- Actions ---

    public void addToCart(String productName) {
        click(addToCartBtn(productName));
    }

    public void addFirstProductToCart() {
        List<WebElement> buttons = driver.findElements(addToCartButtons);
        if (!buttons.isEmpty()) {
            waitForClickable(buttons.get(0)).click();
        }
    }

    public void addAllProductsToCart() {
        List<WebElement> buttons = driver.findElements(addToCartButtons);
        for (WebElement button : buttons) {
            waitForClickable(button).click();
        }
    }

    public void removeFromCart(String productName) {
        click(removeBtn(productName));
    }

    public void goToCart() {
        click(cartLink);
    }

    // --- State Checks ---

    public int getCartCount() {
        try {
            return Integer.parseInt(getText(cartBadge));
        } catch (Exception e) {
            return 0;
        }
    }

    public boolean isCartBadgeVisible() {
        return isDisplayed(cartBadge);
    }

    public boolean isAddToCartButtonVisible(String productName) {
        return isDisplayed(addToCartBtn(productName));
    }

    public boolean isRemoveButtonVisible(String productName) {
        return isDisplayed(removeBtn(productName));
    }

    public int getTotalProductCount() {
        return driver.findElements(productNames).size();
    }

    public String getProductNameByIndex(int index) {
        return driver.findElements(productNames).get(index).getText().trim();
    }

    public String getProductPriceByIndex(int index) {
        return driver.findElements(productPrices).get(index).getText().trim();
    }
}