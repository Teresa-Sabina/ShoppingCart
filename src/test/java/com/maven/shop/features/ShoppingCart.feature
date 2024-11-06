Feature: Shopping Cart
  Scenario: Add items to cart and complete checkout
    Given I am on the Home Page
    When I add Men's Outwear with Size XL and Quantity 2 to cart
    And I view cart to confirm the item is added successfully
    When I click on Shop to go back to Home Page
    And I add Ladies Outwear with Size XS and Quantity 3 to cart
    And I verify items in the basket
    When I change Ladies Outwear quantity to 1
    And I complete checkout with the following data
    Then I place the order and confirm Thank You message
