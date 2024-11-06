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
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.maven.shop.utils.PageFactory;
import org.openqa.selenium.support.ui.Duration;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    WebDriver driver;
    ShoppingCartSteps shoppingCart;
    private com.maven.shop.utils.PageFactory pageFactory;
    

    public HomePage(WebDriver driver,PageFactory pageFactory) {
        this.driver = driver;
        this.pageFactory = pageFactory;
        //PageFactory.initElements(driver, this);
    }

    public void goToCategory(String category) {
        if(category.equals("Men's Outerwear")) {
            String href = shoppingCart.baseURI+"/list/mens_outerwear";
            System.out.println("Href is: " + href);
            driver.get(href);
            
        } else {
            String href = shoppingCart.baseURI+"/list/ladies_outerwear";
            System.out.println("Href is: " + href);
            driver.get(href);
            
        }
    }

}
