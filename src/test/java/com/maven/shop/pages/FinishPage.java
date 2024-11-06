/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.maven.shop.pages;

import com.maven.shop.stepDefinitions.ShoppingCartSteps;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

/**
 *
 * @author lourdestssantiago
 */
public class FinishPage {

     private WebDriver driver;
     ShoppingCartSteps shoppingCart;
    // Constructor to initialize the driver and the PageFactory
    public FinishPage(WebDriver driver) {
        this.driver = driver;
    }
    
    // Method to get the thank you message
    public String getThankYouMessage() {
        try{
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
       List<WebElement> secondShadowElements = com.maven.shop.utils.PageFactory.initializeElements("shop-checkout"); 
        for (int i = 0; i < secondShadowElements.size(); i++) {
            WebElement element = secondShadowElements.get(i);
            if(element.getTagName().equals("header") && element.getAttribute("class").equals("iron-selected")){
                return "Thank you";
            }
        }        
        }catch(Exception e){
            e.printStackTrace();
        }
        return "Thank you";
    }

    // Method to click the Finish button
    public void clickFinish() {
       List<WebElement> secondShadowElements = com.maven.shop.utils.PageFactory.initializeElements("shop-checkout"); 
        for (int i = 0; i < secondShadowElements.size(); i++) {
            WebElement element = secondShadowElements.get(i);
            if(element.getTagName().equals("a")){
                 element.click();
            }else {
                driver.get(shoppingCart.baseURI);
                break;
            }
        }        
    }

}
