/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.maven.shop.pages;

/**
 *
 * @author lourdestssantiago
 */

import com.maven.shop.stepDefinitions.ShoppingCartSteps;
import java.util.ArrayList;
import java.util.HashMap;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.junit.Assert;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;

public class CartPage {
    WebDriver driver;
    ShoppingCartSteps shoppingCart;
    // Elements for items in the cart
   

    // Elements for navigating the cart and shop
    WebElement viewCartButton;

    @FindBy(xpath = "//a[contains(text(),'Shop')]")
    WebElement shopButton;

    @FindBy(xpath = "//a[contains(text(),'Basket')]")
    WebElement viewBasketButton;

    // Button to proceed to checkout
    @FindBy(xpath = "//button[contains(text(),'Checkout')]")
    WebElement checkoutButton;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
    // Method to view cart
    public void viewCart() {
        int n=0;
            List<WebElement> secondShadowElements = com.maven.shop.utils.PageFactory.initializeElements("shop-cart"); 
            for (WebElement element : secondShadowElements) {
                 if(element.getTagName().equals("shop-cart-item") ){ 
                     n++;
                     List<WebElement> thirdShadowElements = com.maven.shop.utils.PageFactory.fetchthirdShadowElements(secondShadowElements,"shop-cart-item",n);
                     for (WebElement element1 : thirdShadowElements) {
                     }
                 }
            }        
    }

    // Method to navigate back to shop
    public void goBackToShop() {
            List<WebElement> secondShadowElements = com.maven.shop.utils.PageFactory.initializeElements("app-header"); 
            for (WebElement element : secondShadowElements) {
                if(element.getTagName().equals("a")){
                    element.click();
                }
            }
    }

    // Method to view basket
    public void viewBasket() {        
        if(checkSizeAndPrice()){System.out.println("Confirmed the size and price are correct with the one selected");}
        else {System.out.println("Confirmed the size and price are not correct with the one selected");}
        if(checkTotal()){System.out.println("Confirmed total");}
        else {System.out.println("Total is not matching. Please Recheck the items");}
    }

    // Method to verify item in the cart with its size and quantity
    public void verifyItem(String itemName, String size, String quantity) {
        boolean itemFound = false;
         List<WebElement> cartItems= new ArrayList<>();
             List<WebElement> secondShadowElements = com.maven.shop.utils.PageFactory.initializeElements("shop-cart"); 
            try{
                //count the no of shop-cart-item
                int n=0;
             for (WebElement element : secondShadowElements) {
                 if(element.getTagName().equals("shop-cart-item") ){  
                     n++;
                     List<WebElement> thirdShadowElements = com.maven.shop.utils.PageFactory.fetchthirdShadowElements(secondShadowElements,"shop-cart-item",n);
                     for (WebElement element1 : thirdShadowElements) {
                         cartItems.add(element1);
                     }
                 }
            }
            }catch(StaleElementReferenceException e) {
                System.out.println("Stale element detected; refreshing elements and retrying.");
                // Retry logic: Refresh the element and attempt again if necessary
                secondShadowElements = com.maven.shop.utils.PageFactory.initializeElements("shop-cart");
                //count the no of shop-cart-item
                int n=0;
             for (WebElement element : secondShadowElements) {
                 if(element.getTagName().equals("shop-cart-item") ){   
                     n++;
                     List<WebElement> thirdShadowElements = com.maven.shop.utils.PageFactory.fetchthirdShadowElements(secondShadowElements,"shop-cart-item",n);
                     for (WebElement element1 : thirdShadowElements) {
                          System.out.println("Element Tag : "+element1.getTagName() + " Text "+element1.getText());
                          cartItems.add(element1);
                     }
                 }
             }
            }
       for (WebElement item : cartItems ) {
            if (item.getTagName().equals("div") && item.getText().contains(itemName) && item.getText().contains(size) && item.getText().contains(quantity)) {
                itemFound = true;
                System.out.println("Item " + itemName + " with Size " + size + " and Quantity " + quantity + " was found in the cart."+ itemFound);
                break;
            }
        }
        Assert.assertTrue("Item " + itemName + " with Size " + size + " and Quantity " + quantity + " was not found in the cart.", itemFound);
    }

    // Method to change quantity of a specified item in the cart
    public void changeQuantity(String itemName, String newQuantity) {
         List<WebElement> secondShadowElements = com.maven.shop.utils.PageFactory.initializeElements("shop-cart"); 
         int n=0; boolean itemToChanged= false;
             for (WebElement element : secondShadowElements) {
                 if(element.getTagName().equals("shop-cart-item") ){  
                     n++;
                     List<WebElement> thirdShadowElements = com.maven.shop.utils.PageFactory.fetchthirdShadowElements(secondShadowElements,"shop-cart-item",n);
                     boolean itemFound = false;
                     for (WebElement element1 : thirdShadowElements) {
                         //System.out.println("Found element in third shadow DOM: " + element1.getTagName());
                         if(element1.getTagName().equals("a") && element1.getAttribute("title").equals(itemName)){
                             itemFound = true; itemToChanged = true;
                         }
                         if(itemFound && element1.getTagName().equals("option") && !element1.isSelected() && element1.getText().equals(newQuantity)){
                             element1.click();
                             System.out.println("Element option Changed");
                             if(checkTotal()){System.out.println("Confirmed New total");}
                             else {System.out.println("Total is not matching. Please Recheck the items");}
                             break;
                         }
                     }
                     
                 }
                 if(itemToChanged){
                     break;
                 }
             }
  
    }

    // Method to check Total
    private boolean checkTotal(){
        Map<String,String> priceNoMap = new HashMap<>();
        String quantity = null;
        float total = 0;float itemTotal = 0;
         List<WebElement> secondShadowElements = com.maven.shop.utils.PageFactory.initializeElements("shop-cart"); 
         int n=0; boolean itemToGot= false;
             for (WebElement element : secondShadowElements) {
                 if(element.getTagName().equals("shop-cart-item") ){  
                     n++;
                     List<WebElement> thirdShadowElements = com.maven.shop.utils.PageFactory.fetchthirdShadowElements(secondShadowElements,"shop-cart-item",n);
                     boolean itemFound = false;
                     for (WebElement element1 : thirdShadowElements) {
                         if(itemFound && element1.getTagName().equals("div") && element1.getAttribute("class").equals("price")){                             
                             priceNoMap.put(quantity, element1.getText());
                         }
                         if(element1.getTagName().equals("option") && element1.isSelected()){
                             itemFound = true; itemToGot = true;
                             quantity = element1.getText();
                         }
                     }   
                 }
                 if(element.getTagName().equals("span") && element.getAttribute("class").equals("subtotal")){
                     total = Float.parseFloat(element.getText().replace("$", ""));
                     System.out.println("Total in Cart "+total);
                 }
             }
                 if(itemToGot){
                     
                     for (Map.Entry<String, String> entry : priceNoMap.entrySet()) {
                       String key = entry.getKey();
                       String value = entry.getValue();
                       int quantities = Integer.parseInt(key);
                       float price = Float.parseFloat(value.replace("$", ""));
                       itemTotal = itemTotal + (quantities*price);                       
                    }
                     System.out.println("Total sum of items:" + itemTotal);
                     Assert.assertTrue("Total in Basket doesn't match the Sum of item Total", total == itemTotal);
                 }
                 return total == itemTotal;
        
    }
    
    // Method to proceed to checkout
    public void proceedToCheckout() {
        int n=0;
            List<WebElement> secondShadowElements = com.maven.shop.utils.PageFactory.initializeElements("shop-cart"); 
            for (WebElement element : secondShadowElements) {
                 if(element.getTagName().equals("a") && element.getAttribute("href").contains("/checkout")){ 
                     element.click();
                 }
            }
        
        
        
    }

    private boolean checkSizeAndPrice() {
      boolean allSizePriceMatch = false;
      String size = null;  
         List<WebElement> secondShadowElements = com.maven.shop.utils.PageFactory.initializeElements("shop-cart"); 
         int n=0; boolean itemToGot= false;
             for (WebElement element : secondShadowElements) {
                 if(element.getTagName().equals("shop-cart-item") ){  
                     n++;
                     List<WebElement> thirdShadowElements = com.maven.shop.utils.PageFactory.fetchthirdShadowElements(secondShadowElements,"shop-cart-item",n);
                     boolean itemFound = false;
                     for (WebElement element1 : thirdShadowElements) {
                         if(itemFound && element1.getTagName().equals("div") && element1.getAttribute("class").equals("price")){
                             if (ProductPage.map.containsKey(element1.getText()) && ProductPage.map.containsValue(size)) {
                                    System.out.println(element1.getText() +" and "+size +" are present in the map.");
                             } else {
                                     System.out.println(element1.getText() +" and "+size +" are not present in the map.");
                             }
                         }
                         if(element1.getTagName().equals("span")){
                             itemFound = true; itemToGot = true;
                             size = element1.getText();                           
                         }
                     }
                     if(itemToGot){allSizePriceMatch = true;} 
                 }
      
             }
      return allSizePriceMatch;  
        
    }
}
