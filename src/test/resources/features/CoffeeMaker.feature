Feature: Make a coffee with coffee maker
  A customer can purchase beverage using coffee maker.

  Scenario: Add a recipe
    Given the coffee maker has no recipe
    When I add Mocha recipe to coffee maker
    Then the coffee maker should have Mocha recipe

  Scenario: Delete a recipe
    Given the coffee maker has Mocha recipe
    When I delete that Mocha recipe
    Then that Mocha recipe should be null

  Scenario: Edit a recipe
    Given the coffee maker has Mocha recipe
    When I edit that Mocha recipe price to 150
    Then that Mocha recipe price should be changed to 150

  Scenario: Add coffee in inventory
    Given the default inventory has 15 coffee
    When I add 5 coffee to inventory
    Then the inventory should have 20 coffee now

  Scenario: Add milk in inventory
    Given the default inventory has 15 milk
    When I add 5 milk to inventory
    Then the inventory should have 20 milk now

  Scenario: Add sugar in inventory
    Given the default inventory has 15 sugar
    When I add 5 sugar to inventory
    Then the inventory should have 20 sugar now

  Scenario: Add chocolate in inventory
    Given the default inventory has 15 chocolate
    When I add 5 chocolate to inventory
    Then the inventory should have 20 chocolate now

  Scenario: Customer purchase a Coffee
    Given the coffee maker is started
    When the customer purchase Coffee paying 100
    Then the customer should get the Coffee and 50 change back

  Scenario: Customer purchase a Mocha
    Given the coffee maker is started
    When the customer purchase Mocha paying 100
    Then the customer should get the Mocha and get money change back because chocolate is not enough

  Scenario: Customer purchase a Latte
    Given the coffee maker is started
    When the customer purchase Latte paying 100
    Then the customer should get the Latte and 0 change

  Scenario: Customer purchase a beverage with not enough money
    Given the coffee maker is started
    When the customer purchase Mocha paying 25
    Then the customer should not get the Mocha and get the money back

  Scenario: Customer purchase a beverage with not enough coffee
    Given the coffee maker is started with new recipe that require 20 coffee
    When the customer purchase beverage paying 50 but coffee in the inventory is not enough
    Then the customer should not get the beverage and get the money back

  Scenario: Customer purchase a beverage with not enough milk
    Given the coffee maker is started with new recipe that require 20 milk
    When the customer purchase beverage paying 50 but milk in the inventory is not enough
    Then the customer should not get the beverage and get the money back

  Scenario: Customer purchase a beverage with not enough sugar
    Given the coffee maker is started with new recipe that require 20 sugar
    When the customer purchase beverage paying 50 but sugar in the inventory is not enough
    Then the customer should not get the beverage and get the money back

  Scenario: Customer purchase a beverage with not enough chocolate
    Given the coffee maker is started with new recipe that require 20 chocolate
    When the customer purchase beverage paying 50 but chocolate in the inventory is not enough
    Then the customer should not get the beverage and get the money back



