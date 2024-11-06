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
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.maven.shop.utils.PageFactory;
import java.util.HashMap;

public class ProductPage {
    WebDriver driver;
    private static PageFactory pageFactory;
    public static HashMap<String, String> map = new HashMap<>();
    
    public ProductPage(WebDriver driver, PageFactory pageFactory) {
        this.driver = driver;
        this.pageFactory = pageFactory;
        //PageFactory.initElements(driver, this);
    }


    // Example method to interact with the element
    public void selectSize(String size) {
       List<WebElement> secondShadowElements = PageFactory.initializeElements("shop-detail"); 
       for (WebElement element : secondShadowElements) {
           // System.out.println("Found element in second shadow DOM: " + element.getTagName());
            if(element.getTagName().equals("option") && element.getText().equals(size)){
                element.click();
                System.out.println("Element selected"+size);
            }
            if(element.getTagName().equals("div") && element.getAttribute("class").equals("price")){
                map.put(element.getText(), size);
            }
        }
    }
    public void setQuantity(String quantity) {
       List<WebElement> secondShadowElements = PageFactory.initializeElements("shop-detail");
       for (WebElement element : secondShadowElements) {
            //System.out.println("Found element in second shadow DOM: " + element.getTagName());
            if(element.getTagName().equals("option") && element.getText().equals(quantity)){
                element.click();
                System.out.println("Element selected"+quantity);
            }
        }
    }

    public void addToCart() {
       List<WebElement> secondShadowElements = PageFactory.initializeElements("shop-detail");
       for (WebElement element : secondShadowElements) {
           // System.out.println("Found element in second shadow DOM: " + element.getTagName());
            if(element.getTagName().equals("button")){
                element.click();
                System.out.println("Element selected Add toCart");
                // fetch cart-modal
                List<WebElement> secondShadowElements2 = PageFactory.initializeElements("shop-cart-modal");
                for (WebElement element2 : secondShadowElements2) {
                 //   System.out.println("Found element in second shadow DOM: " + element2.getTagName());
                    if(element2.getTagName().equals("a") && element2.getAttribute("href").equals("https://shop.polymer-project.org/cart")){
                        element2.click();
                        System.out.println("Element selected View Cart");
                    }
                }
            }
        }
    }
}

