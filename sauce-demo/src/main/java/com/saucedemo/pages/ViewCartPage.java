package com.saucedemo.pages;

import com.saucedemo.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;

public class ViewCartPage extends BasePage {

    // --- Locators ---
    private By cartContainer        = By.id("cart_contents_container");
    private By cartItems            = By.className("cart_item");
    private By cartItemNames        = By.className("inventory_item_name");
    private By cartItemPrices       = By.className("inventory_item_price");
    private By cartItemQuantities   = By.className("cart_quantity");
    private By cartItemDescriptions = By.className("inventory_item_desc");
    private By continueShoppingBtn  = By.cssSelector("[data-test='continue-shopping']");
    private By checkoutBtn          = By.cssSelector("[data-test='checkout']");
    private By cartBadge            = By.className("shopping_cart_badge");
    private By pageTitle            = By.className("title");

    // Dynamic — remove button for a specific item by product name
    private By removeBtn(String productName) {
        return By.cssSelector("[data-test='remove-"
            + productName.toLowerCase().replace(" ", "-") + "']");
    }

    // --- Constructor ---
    public ViewCartPage() {
        super();
    }

    // --- Actions ---

    public void clickContinueShopping() {
        click(continueShoppingBtn);
    }

    public void clickCheckout() {
        click(checkoutBtn);
    }

    public void removeItemFromCart(String productName) {
        click(removeBtn(productName));
    }

    // --- State Checks ---

    public boolean isCartPageDisplayed() {
        return isDisplayed(cartContainer);
    }

    public String getPageTitle() {
        return getText(pageTitle);
    }

    // Total number of items currently in cart
    public int getCartItemCount() {
        return driver.findElements(cartItems).size();
    }

    // Cart badge count (top-right icon); returns 0 if badge not visible
    public int getCartBadgeCount() {
        try {
            return Integer.parseInt(getText(cartBadge));
        } catch (Exception e) {
            return 0;
        }
    }

    public boolean isCartBadgeVisible() {
        return isDisplayed(cartBadge);
    }

    // Check if a specific product is present in the cart by name
    public boolean isItemPresentInCart(String productName) {
        List<WebElement> names = driver.findElements(cartItemNames);
        for (WebElement name : names) {
            if (name.getText().trim().equalsIgnoreCase(productName)) {
                return true;
            }
        }
        return false;
    }

    // Get item name by index (0-based)
    public String getItemNameByIndex(int index) {
        return driver.findElements(cartItemNames).get(index).getText().trim();
    }

    // Get item price by index (0-based)
    public String getItemPriceByIndex(int index) {
        return driver.findElements(cartItemPrices).get(index).getText().trim();
    }

    // Get item quantity by index (0-based)
    public String getItemQuantityByIndex(int index) {
        return driver.findElements(cartItemQuantities).get(index).getText().trim();
    }

    // Get item description by index (0-based)
    public String getItemDescriptionByIndex(int index) {
        return driver.findElements(cartItemDescriptions).get(index).getText().trim();
    }

    // Check if remove button is visible for a specific product
    public boolean isRemoveButtonVisible(String productName) {
        return isDisplayed(removeBtn(productName));
    }

    // Check if cart is empty (no items present)
    public boolean isCartEmpty() {
        return driver.findElements(cartItems).isEmpty();
    }
}