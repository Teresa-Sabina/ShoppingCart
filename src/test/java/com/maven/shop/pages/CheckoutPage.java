/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.maven.shop.pages;

/**
 *
 * @author lourdestssantiago
 */

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage {
    private WebDriver driver;

    // Constructor to initialize the driver and the PageFactory
    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Method to fill in the checkout form
    public void fillCheckoutForm(String email, String phone, String address, String city, String state, String zip, String country,
                                  String cardholderName, String cardNumber, String expiry, String cvv) {
        List<WebElement> secondShadowElements = com.maven.shop.utils.PageFactory.initializeElements("shop-checkout"); 
        for (int i = 0; i < secondShadowElements.size(); i++) {
            WebElement element = secondShadowElements.get(i);
            if(element.getTagName().equals("shop-input") && element.getText().equals("Email")){
                 WebElement element1 = secondShadowElements.get(++i);
                 if(element1.getTagName().equals("input")){
                     element1.sendKeys(email);
                 }
            }
            if(element.getTagName().equals("shop-input") && element.getText().equals("Phone Number")){
                 WebElement element1 = secondShadowElements.get(++i);
                 if(element1.getTagName().equals("input")){
                     element1.sendKeys(phone);
                 }
            }
            if(element.getTagName().equals("shop-input") && element.getText().equals("Address")){
                 WebElement element1 = secondShadowElements.get(++i);
                 if(element1.getTagName().equals("input")){
                     element1.sendKeys(address);
                 }
            }
            if(element.getTagName().equals("shop-input") && element.getText().equals("City")){
                 WebElement element1 = secondShadowElements.get(++i);
                 if(element1.getTagName().equals("input")){
                     element1.sendKeys(city);
                 }
            }
            if(element.getTagName().equals("shop-input") && element.getText().equals("State/Province")){
                 WebElement element1 = secondShadowElements.get(++i);
                 if(element1.getTagName().equals("input")){
                     element1.sendKeys(state);
                 }
            }
            if(element.getTagName().equals("shop-input") && element.getText().equals("Zip/Postal Code")){
                 WebElement element1 = secondShadowElements.get(++i);
                 if(element1.getTagName().equals("input")){
                     element1.sendKeys(zip);
                 }
            }
            if(element.getTagName().equals("shop-input") && element.getText().equals("Cardholder Name")){
                 WebElement element1 = secondShadowElements.get(++i);
                 if(element1.getTagName().equals("input")){
                     element1.sendKeys(cardholderName);
                 }
            }
            if(element.getTagName().equals("shop-input") && element.getText().equals("Card Number")){
                 WebElement element1 = secondShadowElements.get(++i);
                 if(element1.getTagName().equals("input")){
                     element1.sendKeys(cardNumber);
                 }
            }
            if(element.getTagName().equals("shop-input") && element.getText().equals("CVV")){
                 WebElement element1 = secondShadowElements.get(++i);
                 if(element1.getTagName().equals("input")){
                     element1.sendKeys(cvv);
                 }
            }
            if(element.getTagName().equals("option") && element.getText().equals(country)){
                element.click();
            }
            String[] parts = expiry.split(" ");
            String month = parts[0];
            String year = parts[1];
            if(element.getTagName().equals("option") && element.getText().equals(month)){
                element.click();
            }
            if(element.getTagName().equals("option") && element.getText().equals(year)){
                element.click();
            }
        }        


    }

    // Method to click the Place Order button
    public void clickPlaceOrder() {
       List<WebElement> secondShadowElements = com.maven.shop.utils.PageFactory.initializeElements("shop-checkout"); 
        for (int i = 0; i < secondShadowElements.size(); i++) {
            WebElement element = secondShadowElements.get(i);
            if(element.getTagName().equals("input") && element.getAttribute("type").equals("button") && element.getAttribute("value").equals("Place Order")){
                 element.click();
                 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            }
        }        
    }
}
