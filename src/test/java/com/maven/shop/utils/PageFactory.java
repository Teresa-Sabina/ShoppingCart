/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.maven.shop.utils;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 *
 * @author lourdestssantiago
 */
public class PageFactory {
     private static WebDriver driver;


    public PageFactory(WebDriver driver) {
        this.driver = driver;
        org.openqa.selenium.support.PageFactory.initElements(driver, this);
    }
       // Custom method to initialize elements
    public static List initializeElements(String secondShadow) {
        
            WebElement firstShadowHost = driver.findElement(By.cssSelector("shop-app")); // Replace with the actual selector

            // Access the first shadow root
            List<WebElement> firstShadowElements = (List<WebElement>) ((JavascriptExecutor) driver).executeScript(
                "const shadowRoot1 = arguments[0].shadowRoot; " +
                "return shadowRoot1 ? Array.from(shadowRoot1.querySelectorAll('*')) : [];", 
                firstShadowHost);

            // Assume we need to get the second shadow host from the first shadow root
            // You may need to modify the selector based on the structure of your HTML
            WebElement secondShadowHost = firstShadowElements.stream()
                    .filter(element -> element.getTagName().equals(secondShadow)) // Replace with the actual tag name or criteria
                    .findFirst()
                    .orElse(null);

            if (secondShadowHost != null) {
                // Access the second shadow root
                List<WebElement> secondShadowElements = (List<WebElement>) ((JavascriptExecutor) driver).executeScript(
                    "const shadowRoot2 = arguments[0].shadowRoot; " +
                    "return shadowRoot2 ? Array.from(shadowRoot2.querySelectorAll('*')) : [];", 
                   // "return Array.from(arguments[0].querySelectorAll(arguments[1]));",
                    secondShadowHost);

                // Check if any elements were found in the second shadow root
                if (secondShadowElements.isEmpty()) {
                    System.out.println("No elements found in the second private shadow DOM.");
                    return null;
                } else {
                    // Print the found elements
                    return secondShadowElements;
                }
            } else {
                System.out.println("Second shadow host not found.");
                return null;
            }

    }

    public static List fetchthirdShadowElements(List<WebElement> firstShadowElements,String thirdShadow,int n) {
            WebElement secondShadowHost = null;
        if(n==1){
            secondShadowHost = firstShadowElements.stream()
                    .filter(element -> element.getTagName().equals(thirdShadow)) // Replace with the actual tag name or criteria
                    .findFirst()
                    .orElse(null);
        }else{
            secondShadowHost = firstShadowElements.stream()
                    .filter(element -> element.getTagName().equals(thirdShadow))
                    .skip(n-1)
                    .findFirst()
                    .orElse(null);            
        }
            if (secondShadowHost != null) {
                // Access the second shadow root
                List<WebElement> secondShadowElements = (List<WebElement>) ((JavascriptExecutor) driver).executeScript(
                    "const shadowRoot2 = arguments[0].shadowRoot; " +
                    "return shadowRoot2 ? Array.from(shadowRoot2.querySelectorAll('*')) : [];", 
                   // "return Array.from(arguments[0].querySelectorAll(arguments[1]));",
                    secondShadowHost);

                // Check if any elements were found in the second shadow root
                if (secondShadowElements.isEmpty()) {
                    System.out.println("No elements found in the third private shadow DOM.");
                    return null;
                } else {
                    // Print the found elements
                    return secondShadowElements;
                }
            } else {
                System.out.println("Third shadow host not found.");
                return null;
            }
    
    }
}
