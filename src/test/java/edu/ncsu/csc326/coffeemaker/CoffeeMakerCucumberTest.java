package edu.ncsu.csc326.coffeemaker;

import edu.ncsu.csc326.coffeemaker.exceptions.InventoryException;
import edu.ncsu.csc326.coffeemaker.exceptions.RecipeException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.*;

public class CoffeeMakerCucumberTest {
    private CoffeeMaker coffeeMaker;

    private Recipe recipe1;
    private Recipe recipe2;
    private Recipe recipe3;

    private int recipe_index;
    private int money;

    private static Recipe createRecipe(String name, String amtChocolate, String amtCoffee, String amtMilk, String amtSugar, String price) throws RecipeException {
        Recipe recipe = new Recipe();
        recipe.setName(name);
        recipe.setAmtChocolate(amtChocolate);
        recipe.setAmtCoffee(amtCoffee);
        recipe.setAmtMilk(amtMilk);
        recipe.setAmtSugar(amtSugar);
        recipe.setPrice(price);

        return recipe;
    }

    @Given("the coffee maker has no recipe")
    public void theCoffeeMakerHasNoRecipe() {
        coffeeMaker = new CoffeeMaker();
    }

    @When("I add Mocha recipe to coffee maker")
    public void iAddMochaRecipeToCoffeeMaker() throws RecipeException {
        recipe1 = createRecipe("Mocha", "20", "3", "1", "1", "75");
        coffeeMaker.addRecipe(recipe1);
    }

    @Then("the coffee maker should have Mocha recipe")
    public void theCoffeeMakerShouldHaveMochaRecipe() {
        assertNotEquals(null, coffeeMaker.getRecipes()[0]);
        assertEquals("Mocha", coffeeMaker.getRecipes()[0].getName());
        assertEquals(20, coffeeMaker.getRecipes()[0].getAmtChocolate());
        assertEquals(3, coffeeMaker.getRecipes()[0].getAmtCoffee());
        assertEquals(1, coffeeMaker.getRecipes()[0].getAmtMilk());
        assertEquals(1, coffeeMaker.getRecipes()[0].getAmtSugar());
        assertEquals(75, coffeeMaker.getRecipes()[0].getPrice());
    }

    @Given("the coffee maker has Mocha recipe")
    public void theCoffeeMakerHasMochaRecipe() throws RecipeException {
        coffeeMaker = new CoffeeMaker();
        recipe1 = createRecipe("Mocha", "20", "3", "1", "1", "75");
        coffeeMaker.addRecipe(recipe1);
    }

    @When("I delete that Mocha recipe")
    public void iDeleteThatMochaRecipe() {
        coffeeMaker.deleteRecipe(0);
    }

    @Then("that Mocha recipe should be null")
    public void thatMochaRecipeShouldBeNull() {
        assertNull(coffeeMaker.getRecipes()[0]);
    }

    @When("I edit that Mocha recipe price to {int}")
    public void iEditThatMochaRecipePriceTo(int arg0) throws RecipeException {
        Recipe new_mocha = createRecipe("Mocha", "20", "3", "1", "1", String.valueOf(arg0));
        coffeeMaker.editRecipe(0, new_mocha);
    }

    @Then("that Mocha recipe price should be changed to {int}")
    public void thatMochaRecipePriceShouldBeChangedTo(int arg0) {
        assertEquals(arg0, coffeeMaker.getRecipes()[0].getPrice());
    }

    @Given("the default inventory has 15 coffee")
    public void theDefaultInventoryHasCoffee() {
        coffeeMaker = new CoffeeMaker();
    }

    @When("I add {int} coffee to inventory")
    public void iAddCoffeeToInventory(int arg0) throws InventoryException {
        coffeeMaker.addInventory(String.valueOf(arg0), "0", "0", "0");
    }

    @Then("the inventory should have {int} coffee now")
    public void theInventoryShouldHaveCoffeeNow(int arg0) {
        StringBuffer buf = new StringBuffer();
        buf.append("Coffee: ");
        buf.append(arg0);
        buf.append("\n");
        buf.append("Milk: ");
        buf.append(15);
        buf.append("\n");
        buf.append("Sugar: ");
        buf.append(15);
        buf.append("\n");
        buf.append("Chocolate: ");
        buf.append(15);
        buf.append("\n");

        assertEquals(buf.toString(), coffeeMaker.checkInventory());
    }

    @Given("the default inventory has 15 milk")
    public void theDefaultInventoryHasMilk() {
        coffeeMaker = new CoffeeMaker();
    }

    @When("I add {int} milk to inventory")
    public void iAddMilkToInventory(int arg0) throws InventoryException {
        coffeeMaker.addInventory("0", String.valueOf(arg0), "0", "0");
    }

    @Then("the inventory should have {int} milk now")
    public void theInventoryShouldHaveMilkNow(int arg0) {
        StringBuffer buf = new StringBuffer();
        buf.append("Coffee: ");
        buf.append(15);
        buf.append("\n");
        buf.append("Milk: ");
        buf.append(arg0);
        buf.append("\n");
        buf.append("Sugar: ");
        buf.append(15);
        buf.append("\n");
        buf.append("Chocolate: ");
        buf.append(15);
        buf.append("\n");

        assertEquals(buf.toString(), coffeeMaker.checkInventory());
    }

    @Given("the default inventory has 15 sugar")
    public void theDefaultInventoryHasSugar() {
        coffeeMaker = new CoffeeMaker();
    }

    @When("I add {int} sugar to inventory")
    public void iAddSugarToInventory(int arg0) throws InventoryException {
        coffeeMaker.addInventory("0", "0", String.valueOf(arg0), "0");
    }

    @Then("the inventory should have {int} sugar now")
    public void theInventoryShouldHaveSugarNow(int arg0) {
        StringBuffer buf = new StringBuffer();
        buf.append("Coffee: ");
        buf.append(15);
        buf.append("\n");
        buf.append("Milk: ");
        buf.append(15);
        buf.append("\n");
        buf.append("Sugar: ");
        buf.append(arg0);
        buf.append("\n");
        buf.append("Chocolate: ");
        buf.append(15);
        buf.append("\n");

        assertEquals(buf.toString(), coffeeMaker.checkInventory());
    }

    @Given("the default inventory has 15 chocolate")
    public void theDefaultInventoryHasChocolate() {
        coffeeMaker = new CoffeeMaker();
    }

    @When("I add {int} chocolate to inventory")
    public void iAddChocolateToInventory(int arg0) throws InventoryException {
        coffeeMaker.addInventory("0", "0", "0", String.valueOf(arg0));
    }

    @Then("the inventory should have {int} chocolate now")
    public void theInventoryShouldHaveChocolateNow(int arg0) {
        StringBuffer buf = new StringBuffer();
        buf.append("Coffee: ");
        buf.append(15);
        buf.append("\n");
        buf.append("Milk: ");
        buf.append(15);
        buf.append("\n");
        buf.append("Sugar: ");
        buf.append(15);
        buf.append("\n");
        buf.append("Chocolate: ");
        buf.append(arg0);
        buf.append("\n");

        assertEquals(buf.toString(), coffeeMaker.checkInventory());
    }

    @Given("the coffee maker is started")
    public void theCoffeeMakerIsStarted() throws RecipeException {
        coffeeMaker = new CoffeeMaker();
        recipe1 = createRecipe("Coffee", "0", "3", "1", "1", "50");
        recipe2 = createRecipe("Mocha", "20", "3", "1", "1", "75");
        recipe3 = createRecipe("Latte", "0", "3", "3", "1", "100");

        coffeeMaker.addRecipe(recipe1);
        coffeeMaker.addRecipe(recipe2);
        coffeeMaker.addRecipe(recipe3);
    }

    @When("the customer purchase Coffee paying {int}")
    public void theCustomerPurchaseCoffeePaying(int arg0) {
        recipe_index = 0;
        money = arg0;
    }

    @Then("the customer should get the Coffee and {int} change back")
    public void theCustomerShouldGetTheCoffeeAndChangeBack(int arg0) {
        assertEquals(arg0, coffeeMaker.makeCoffee(recipe_index, money));
    }

    @When("the customer purchase Mocha paying {int}")
    public void theCustomerPurchaseMochaPaying(int arg0) {
        recipe_index = 1;
        money = arg0;
    }

    @Then("the customer should get the Mocha and get money change back because chocolate is not enough")
    public void theCustomerShouldGetTheMochaAndChangeBack() {
        assertEquals(money, coffeeMaker.makeCoffee(recipe_index, money));
    }

    @When("the customer purchase Latte paying {int}")
    public void theCustomerPurchaseLattePaying(int arg0) {
        recipe_index = 2;
        money = arg0;
    }

    @Then("the customer should get the Latte and {int} change")
    public void theCustomerShouldGetTheLatteAndNoChange(int arg0) {
        assertEquals(arg0, coffeeMaker.makeCoffee(recipe_index, money));
    }

    @Then("the customer should not get the Mocha and get the money back")
    public void theCustomerShouldNotGetTheMochaAndGetTheMoneyBack() {
        assertEquals(money, coffeeMaker.makeCoffee(recipe_index, money));
    }

    @Given("the coffee maker is started with new recipe that require {int} coffee")
    public void theCoffeeMakerIsStartedWithNewRecipeThatRequireCoffee(int arg0) throws RecipeException {
        coffeeMaker = new CoffeeMaker();
        recipe1 = createRecipe("New Beverage", "0", String.valueOf(arg0), "0", "0", "10");

        coffeeMaker.addRecipe(recipe1);
    }

    @When("the customer purchase beverage paying {int} but coffee in the inventory is not enough")
    public void theCustomerPurchaseBeveragePayingButCoffeeInTheInventoryIsNotEnough(int arg0) {
        money = arg0;
    }

    @Then("the customer should not get the beverage and get the money back")
    public void theCustomerShouldNotGetTheBeverageAndGetTheMoneyBack() {
        assertEquals(money, coffeeMaker.makeCoffee(0, money));
    }

    @Given("the coffee maker is started with new recipe that require {int} milk")
    public void theCoffeeMakerIsStartedWithNewRecipeThatRequireMilk(int arg0) throws RecipeException {
        coffeeMaker = new CoffeeMaker();
        recipe1 = createRecipe("New Beverage", "0", "0", String.valueOf(arg0), "0", "10");

        coffeeMaker.addRecipe(recipe1);
    }

    @When("the customer purchase beverage paying {int} but milk in the inventory is not enough")
    public void theCustomerPurchaseBeveragePayingButMilkInTheInventoryIsNotEnough(int arg0) {
        money = arg0;
    }

    @Given("the coffee maker is started with new recipe that require {int} sugar")
    public void theCoffeeMakerIsStartedWithNewRecipeThatRequireSugar(int arg0) throws RecipeException {
        coffeeMaker = new CoffeeMaker();
        recipe1 = createRecipe("New Beverage", "0", "0", "0", String.valueOf(arg0), "10");

        coffeeMaker.addRecipe(recipe1);
    }

    @When("the customer purchase beverage paying {int} but sugar in the inventory is not enough")
    public void theCustomerPurchaseBeveragePayingButSugarInTheInventoryIsNotEnough(int arg0) {
        money = arg0;
    }

    @Given("the coffee maker is started with new recipe that require {int} chocolate")
    public void theCoffeeMakerIsStartedWithNewRecipeThatRequireChocolate(int arg0) throws RecipeException {
        coffeeMaker = new CoffeeMaker();
        recipe1 = createRecipe("New Beverage", String.valueOf(arg0), "0", "0", "0", "10");

        coffeeMaker.addRecipe(recipe1);
    }

    @When("the customer purchase beverage paying {int} but chocolate in the inventory is not enough")
    public void theCustomerPurchaseBeveragePayingButChocolateInTheInventoryIsNotEnough(int arg0) {
        money = arg0;
    }
}
