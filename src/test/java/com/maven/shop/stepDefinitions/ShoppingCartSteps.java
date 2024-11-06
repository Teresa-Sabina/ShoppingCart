/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.maven.shop.stepDefinitions;

/**
 *
 * @author lourdestssantiago
 */
import com.maven.shop.pages.CartPage;
import com.maven.shop.pages.CheckoutPage;
import com.maven.shop.pages.FinishPage;
import com.maven.shop.pages.HomePage;
import com.maven.shop.pages.ProductMenuPage;
import com.maven.shop.pages.ProductPage;
import com.maven.shop.utils.ExcelUtils;
import com.maven.shop.utils.PageFactory;
import com.maven.shop.utils.TestBase;
import io.cucumber.java.en.*;
import org.junit.Assert;

public class ShoppingCartSteps  {

    private HomePage homePage;
    private ProductMenuPage productMenuPage;
    private ProductPage productPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;
    private FinishPage finishPage;
    private TestBase testBase;
    private PageFactory pageFactory;
    public static String baseURI = "https://shop.polymer-project.org";
    
    public ShoppingCartSteps() {
        this.testBase = new TestBase();
        this.pageFactory = new PageFactory(testBase.getDriver());
        this.homePage = new HomePage(testBase.getDriver(),pageFactory);
        this.productMenuPage = new ProductMenuPage(testBase.getDriver());
        this.productPage = new ProductPage(testBase.getDriver(),pageFactory);
        this.cartPage = new CartPage(testBase.getDriver());
        this.checkoutPage = new CheckoutPage(testBase.getDriver()); 
        this.finishPage = new FinishPage(testBase.getDriver());
    }

    @Given("I am on the Home Page")
    public void iAmOnTheHomePage() {
        testBase.getDriver().get(baseURI);
    }

    @When("I add Men's Outwear with Size XL and Quantity 2 to cart")
    public void addMensOutwearToCart() {
        homePage.goToCategory("Men's Outerwear");
        productMenuPage.selectMensOutwear("Green Flex Fleece Zip Hoodie",3);
        productPage.selectSize("XL");
        productPage.setQuantity("2");
        productPage.addToCart();
    }

    @When("I view cart to confirm the item is added successfully")
    public void viewCartAndConfirmItem() {
        cartPage.viewCart();
        cartPage.verifyItem("Green Flex Fleece Zip Hoodie", "XL", "2");
        
    }

    @When("I click on Shop to go back to Home Page")
    public void goBackToHomePage() {
        cartPage.goBackToShop();
    }

    @When("I add Ladies Outwear with Size XS and Quantity 3 to cart")
    public void addLadiesOutwearToCart() {
        homePage.goToCategory("Ladies Outerwear");
        productMenuPage.selectLadiesOutwear("Ladies Voyage Fleece Jacket",3);
        productPage.selectSize("XS");
        productPage.setQuantity("3");
        productPage.addToCart();
    }

    @When("I verify items in the basket")
    public void verifyItemsInBasket() {
        cartPage.viewBasket();
        cartPage.verifyItem("Green Flex Fleece Zip Hoodie", "XL", "2");
        cartPage.verifyItem("Ladies Voyage Fleece Jacket", "XS", "3");
    }

    @When("I change Ladies Outwear quantity to 1")
    public void changeLadiesOutwearQuantity() {
        cartPage.changeQuantity("Ladies Voyage Fleece Jacket", "1");
    }


    @When("I complete checkout with the following data")
    public void completeCheckout() throws Exception {
        cartPage.proceedToCheckout();
        ExcelUtils excelUtils = new ExcelUtils("src/test/resources/data.xlsx");
        checkoutPage.fillCheckoutForm(
            excelUtils.getData("Account", "Email"),
            excelUtils.getData("Account", "Phone"),
            excelUtils.getData("Shipping", "Address"),
            excelUtils.getData("Shipping", "City"),
            excelUtils.getData("Shipping", "State"),
            excelUtils.getData("Shipping", "Zip"),
            excelUtils.getData("Shipping", "Country"),
            excelUtils.getData("Payment", "Cardholder Name"),
            excelUtils.getData("Payment", "Card Number"),
            excelUtils.getData("Payment", "Expiry"),
            excelUtils.getData("Payment", "CVV")
        );
       
    }
    
    @Then("I place the order and confirm Thank You message")
    public void placeOrderAndConfirm() {
        checkoutPage.clickPlaceOrder();
        String message = finishPage.getThankYouMessage();
        Assert.assertTrue(message.contains("Thank you")); 
        finishPage.clickFinish();
    }
}

