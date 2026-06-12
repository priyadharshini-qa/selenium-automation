package com.saucedemo.pages;

import com.saucedemo.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;

public class CheckoutFinal extends BasePage {

    // --- Locators ---
    private By checkoutContainer    = By.id("checkout_summary_container");
    private By pageTitle            = By.className("title");

    // Order summary item locators
    private By cartItems            = By.className("cart_item");
    private By cartItemNames        = By.className("inventory_item_name");
    private By cartItemPrices       = By.className("inventory_item_price");
    private By cartItemQuantities   = By.className("cart_quantity");
    private By cartItemDescriptions = By.className("inventory_item_desc");

    // Price summary locators
    private By subtotalLabel        = By.className("summary_subtotal_label");
    private By taxLabel             = By.className("summary_tax_label");
    private By totalLabel           = By.className("summary_total_label");
    private By paymentInfoValue     = By.className("summary_value_label");
    private By shippingInfoValue    = By.cssSelector(".summary_shipping_label + .summary_value_label");

    // Payment & shipping info
    private By paymentInfoLabel     = By.cssSelector("[data-test='payment-info-value']");
    private By shippingInfoLabel    = By.cssSelector("[data-test='shipping-info-value']");

    // Action buttons
    private By finishBtn            = By.cssSelector("[data-test='finish']");
    private By cancelBtn            = By.cssSelector("[data-test='cancel']");

    // Order confirmation (after finish)
    private By confirmationContainer = By.id("checkout_complete_container");
    private By confirmationHeader    = By.className("complete-header");
    private By confirmationText      = By.className("complete-text");
    private By backHomeBtn           = By.cssSelector("[data-test='back-to-products']");

    // --- Constructor ---
    public CheckoutFinal() {
        super();
    }

    // --- Actions ---

    public void clickFinish() {
        click(finishBtn);
    }

    public void clickCancel() {
        click(cancelBtn);
    }

    public void clickBackToHome() {
        click(backHomeBtn);
    }

    // --- Order Items ---

    public int getOrderItemCount() {
        return driver.findElements(cartItems).size();
    }

    public String getItemNameByIndex(int index) {
        return driver.findElements(cartItemNames).get(index).getText().trim();
    }

    public String getItemPriceByIndex(int index) {
        return driver.findElements(cartItemPrices).get(index).getText().trim();
    }

    public String getItemQuantityByIndex(int index) {
        return driver.findElements(cartItemQuantities).get(index).getText().trim();
    }

    public String getItemDescriptionByIndex(int index) {
        return driver.findElements(cartItemDescriptions).get(index).getText().trim();
    }

    public boolean isItemPresentInOrder(String productName) {
        List<WebElement> names = driver.findElements(cartItemNames);
        for (WebElement name : names) {
            if (name.getText().trim().equalsIgnoreCase(productName)) {
                return true;
            }
        }
        return false;
    }

    // --- Price Summary ---

    // Returns e.g. "Item total: $29.99"
    public String getSubtotal() {
        return getText(subtotalLabel);
    }

    // Returns e.g. "Tax: $2.40"
    public String getTax() {
        return getText(taxLabel);
    }

    // Returns e.g. "Total: $32.39"
    public String getTotal() {
        return getText(totalLabel);
    }

    // Extracts numeric value from subtotal label e.g. 29.99
    public double getSubtotalValue() {
        return parsePriceFromLabel(getSubtotal());
    }

    // Extracts numeric value from tax label
    public double getTaxValue() {
        return parsePriceFromLabel(getTax());
    }

    // Extracts numeric value from total label
    public double getTotalValue() {
        return parsePriceFromLabel(getTotal());
    }

    // Parses "$32.39" or "Total: $32.39" → 32.39
    private double parsePriceFromLabel(String labelText) {
        try {
            return Double.parseDouble(labelText.replaceAll("[^0-9.]", ""));
        } catch (Exception e) {
            return 0.0;
        }
    }

    // --- Payment & Shipping Info ---

    public String getPaymentInfo() {
        return getText(paymentInfoLabel);
    }

    public String getShippingInfo() {
        return getText(shippingInfoLabel);
    }

    // --- State Checks ---

    public boolean isCheckoutFinalPageDisplayed() {
        return isDisplayed(checkoutContainer);
    }

    public String getPageTitle() {
        return getText(pageTitle);
    }

    public boolean isFinishButtonVisible() {
        return isDisplayed(finishBtn);
    }

    public boolean isCancelButtonVisible() {
        return isDisplayed(cancelBtn);
    }

    // --- Order Confirmation (checkout-complete.html) ---

    public boolean isOrderConfirmed() {
        return isDisplayed(confirmationContainer);
    }

    public String getConfirmationHeader() {
        return getText(confirmationHeader);
    }

    public String getConfirmationText() {
        return getText(confirmationText);
    }

    public boolean isBackHomeButtonVisible() {
        return isDisplayed(backHomeBtn);
    }
}