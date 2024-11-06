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
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductMenuPage {
    WebDriver driver;
    ShoppingCartSteps shoppingCart;
    
    // Locate the category element using @FindBy
    @FindBy(css = "a[href='/detail/mens_outerwear/Men+s+Tech+Shell+Full-Zip']")
    private WebElement mensOutwearLink;

    @FindBy(css = "a[href='/detail/ladies_outerwear']")
    private WebElement ladiesOutwearLink;

    public ProductMenuPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this); // Initialize elements using PageFactory
    }

    // Method to select Men's Outwear
    public void selectMensOutwear(String item,int itemNo) {
        try
        {
            //mensOutwearLink.click();
            List<String> allItems = new ArrayList<String>();
            List<WebElement> secondShadowElements = com.maven.shop.utils.PageFactory.initializeElements("shop-list"); 
            for (WebElement element : secondShadowElements) {
                //System.out.println("Found element in second shadow DOM: " + element.getTagName());
                 if(element.getTagName().equals("a") ){
                           String link  = element.getText();
                           allItems.add(link);
                           if(allItems.size() == itemNo && allItems.get(itemNo-1).contains(item)){
                               element.click();
                               System.out.println("Element selected . "+item);
                           }
                            
                           // element.click();
                        }
            }

        }catch(Exception e){
            e.printStackTrace();;
            String href = shoppingCart.baseURI+"/detail/mens_outerwear/Men+s+Tech+Shell+Full-Zip";
            System.out.println("Href is: " + href);
            driver.get(href);            
        }
    }

    // Method to select Ladies Outwear
    public void selectLadiesOutwear(String item,int itemNo) {        
        try
        {
//            ladiesOutwearLink.click();
            List<String> allItems = new ArrayList<String>();
            List<WebElement> secondShadowElements = com.maven.shop.utils.PageFactory.initializeElements("shop-list"); 
            for (WebElement element : secondShadowElements) {
                //System.out.println("Found element in second shadow DOM: " + element.getTagName());
                 if(element.getTagName().equals("a") ){
                           String link  = element.getText();
                           allItems.add(link);
                           if(allItems.size() == itemNo && allItems.get(itemNo-1).contains(item)){
                               element.click();
                               System.out.println("Element selected . "+ item);
                           }
                            
                           // element.click();
                        }
            }

        }catch(Exception e){
            e.printStackTrace();
            String href = shoppingCart.baseURI+"/detail/mens_outerwear/Ladies+Modern+Stretch+Full+Zip";
            System.out.println("Href is: " + href);
            driver.get(href);            
        }
    }
    
    // If the category name is dynamic, we can create a more generic method:
    public void selectCategory(String categoryName) {
        WebElement category = driver.findElement(By.linkText(categoryName));
        category.click();
    }
}
