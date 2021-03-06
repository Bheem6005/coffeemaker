/*
 * Copyright (c) 2009,  Sarah Heckman, Laurie Williams, Dright Ho
 * All Rights Reserved.
 *
 * Permission has been explicitly granted to the University of Minnesota
 * Software Engineering Center to use and distribute this source for
 * educational purposes, including delivering online education through
 * Coursera or other entities.
 *
 * No warranty is given regarding this software, including warranties as
 * to the correctness or completeness of this software, including
 * fitness for purpose.
 *
 *
 * Modifications
 * 20171114 - Ian De Silva - Updated to comply with JUnit 4 and to adhere to
 * 							 coding standards.  Added test documentation.
 */
package edu.ncsu.csc326.coffeemaker;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc326.coffeemaker.exceptions.InventoryException;
import edu.ncsu.csc326.coffeemaker.exceptions.RecipeException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for CoffeeMaker class.
 *
 * @author Sarah Heckman
 */
public class CoffeeMakerTest {

    /**
     * The object under test.
     */
    private CoffeeMaker coffeeMaker;
    private CoffeeMaker coffeeMakerStub;
    private RecipeBook recipeBookStub;

    // Sample recipes to use in testing.
    private Recipe recipe1;
    private Recipe recipe2;
    private Recipe recipe3;
    private Recipe recipe4;
    private Recipe[] recipes;

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

    /**
     * Initializes some recipes to test with and the {@link CoffeeMaker}
     * object we wish to test.
     *
     * @throws RecipeException if there was an error parsing the ingredient
     *                         amount when setting up the recipe.
     */
    @Before
    public void setUp() throws RecipeException {
        coffeeMaker = new CoffeeMaker();

        //Set up for r1
        recipe1 = createRecipe("Coffee","0","3","1","1","50");
        //Set up for r2
        recipe2 = createRecipe("Mocha","20","3","1","1","75");
        //Set up for r3
        recipe3 = createRecipe("Latte","0","3","3","1","100");
        //Set up for r4
        recipe4 = createRecipe("Hot Chocolate","4","0","1","1","65");

        recipeBookStub = mock(RecipeBook.class);
        coffeeMakerStub = new CoffeeMaker(recipeBookStub, new Inventory());
    }

    /** UC2: Add a Recipe */

    /**
     * Add a valid recipe to CoffeeMaker with no recipe
     * CoffeeMaker should have 1 valid recipe
     */
    @Test
    public void testAddValidRecipe() {
        coffeeMaker.addRecipe(recipe1);
        assertNotEquals(null, coffeeMaker.getRecipes()[0]);
    }

    /**
     * Add 2 valid recipes to CoffeeMaker with no recipe
     * CoffeeMaker should have 2 valid recipes
     */
    @Test
    public void testAddTwoValidRecipe() {
        coffeeMaker.addRecipe(recipe1);
        assertNotEquals(null, coffeeMaker.getRecipes()[0]);
        coffeeMaker.addRecipe(recipe2);
        assertNotEquals(null, coffeeMaker.getRecipes()[1]);
    }

    /**
     * Add 3 valid recipes to CoffeeMaker with no recipe
     * CoffeeMaker should have 3 valid recipes
     */
    @Test
    public void testAddThreeValidRecipe() {
        coffeeMaker.addRecipe(recipe1);
        assertNotEquals(null, coffeeMaker.getRecipes()[0]);
        coffeeMaker.addRecipe(recipe2);
        assertNotEquals(null, coffeeMaker.getRecipes()[1]);
        coffeeMaker.addRecipe(recipe3);
        assertNotEquals(null, coffeeMaker.getRecipes()[2]);
    }

    /**
     * Add a valid recipe to CoffeeMaker that already have 3 recipes
     * Should return false
     */
    @Test
    public void testAddExceedValidRecipe() {
        coffeeMaker.addRecipe(recipe1);
        coffeeMaker.addRecipe(recipe2);
        coffeeMaker.addRecipe(recipe3);
        assertFalse(coffeeMaker.addRecipe(recipe4));
    }

    /**
     * Add a recipe with the same name as existing recipe
     * Should return false
     */
    @Test
    public void testAddRecipeSameName() {
        coffeeMaker.addRecipe(recipe1);
        Recipe tmp_recipe = new Recipe();
        tmp_recipe.setName(recipe1.getName());
        assertFalse(coffeeMaker.addRecipe(tmp_recipe));
    }

    /** UC3: Delete a Recipe */

    /**
     * Delete a recipe from CoffeeMaker with valid recipe
     * Deleted recipe should be null
     */
    @Test
    public void testDeleteRecipe() {
        coffeeMaker.addRecipe(recipe1);
        coffeeMaker.deleteRecipe(0);
        assertNull(coffeeMaker.getRecipes()[0]);
    }

    /**
     * Delete a null recipe from CoffeeMaker
     * Should return null
     */
    @Test
    public void testDeleteNullRecipe() {
        assertNull(coffeeMaker.deleteRecipe(0));
    }

    /**
     * Delete a recipe from CoffeeMaker with negative index
     * Should return null
     */
    @Test
    public void testDeleteNegativeIndexRecipe() {
        assertNull(coffeeMaker.deleteRecipe(-1));
    }

    /**
     * Delete a recipe from CoffeeMaker with index out of bound
     * Should return null
     */
    @Test
    public void testDeleteOutOfBoundIndexRecipe() {
        assertNull(coffeeMaker.deleteRecipe(4));
    }


    /** UC4: Edit a Recipe */

    /**
     * Edit a valid recipe in CoffeeMaker
     * Edited recipe should have same data with another recipe
     */
    @Test
    public void testEditRecipe() {
        coffeeMaker.addRecipe(recipe1);
        coffeeMaker.editRecipe(0, recipe2);
        assertEquals(recipe2.getName(), coffeeMaker.getRecipes()[0].getName());
        assertEquals(recipe2.getAmtChocolate(), coffeeMaker.getRecipes()[0].getAmtChocolate());
        assertEquals(recipe2.getAmtCoffee(), coffeeMaker.getRecipes()[0].getAmtCoffee());
        assertEquals(recipe2.getAmtMilk(), coffeeMaker.getRecipes()[0].getAmtMilk());
        assertEquals(recipe2.getAmtSugar(), coffeeMaker.getRecipes()[0].getAmtSugar());
        assertEquals(recipe2.getPrice(), coffeeMaker.getRecipes()[0].getPrice());
    }

    /**
     * Edit a null recipe in CoffeeMaker
     * Should return null
     */
    @Test
    public void testEditNullRecipe() {
        assertNull(coffeeMaker.editRecipe(0, recipe1));
    }

    /**
     * Edit a recipe from CoffeeMaker with negative index
     * Should return null
     */
    @Test
    public void testEditNegativeIndexRecipe() {
        assertNull(coffeeMaker.editRecipe(-1, recipe1));
    }

    /**
     * Edit a recipe from CoffeeMaker with index out of bound
     * Should return null
     */
    @Test
    public void testEditOutOfBoundIndexRecipe() {
        assertNull(coffeeMaker.editRecipe(4, recipe1));
    }

    /** UC5: Add Inventory */

    /**
     * Given a coffee maker with the default inventory
     * When we add inventory with well-formed quantities
     * Then we do not get an exception trying to read the inventory quantities.
     *
     * @throws InventoryException if there was an error parsing the quantity
     *                            to a positive integer.
     */
    @Test
    public void testAddInventory() throws InventoryException {
        coffeeMaker.addInventory("4", "4", "4", "4");
    }

    /**
     * Given a coffee maker with the default inventory
     * When we add inventory with negative amtCoffee
     * Then we get an InventoryException
     *
     * @throws InventoryException if there was an error parsing the quantity
     *                            to a positive integer.
     */
    @Test(expected = InventoryException.class)
    public void testAddInventoryNegativeCoffee() throws InventoryException {
        coffeeMaker.addInventory("-4", "4", "4", "4");
    }

    /**
     * Given a coffee maker with the default inventory
     * When we add inventory with non-numeric amtCoffee
     * Then we get an inventory exception
     *
     * @throws InventoryException if there was an error parsing the quantity
     *                            to a positive integer.
     */
    @Test(expected = InventoryException.class)
    public void testAddInventoryNonNumCoffee() throws InventoryException {
        coffeeMaker.addInventory("four", "4", "4", "4");
    }

    /**
     * Given a coffee maker with the default inventory
     * When we add inventory with negative amtMilk
     * Then we get an InventoryException
     *
     * @throws InventoryException if there was an error parsing the quantity
     *                            to a positive integer.
     */
    @Test(expected = InventoryException.class)
    public void testAddInventoryNegativeMilk() throws InventoryException {
        coffeeMaker.addInventory("4", "-4", "4", "4");
    }

    /**
     * Given a coffee maker with the default inventory
     * When we add inventory with non-numeric amtMilk
     * Then we get an inventory exception
     *
     * @throws InventoryException if there was an error parsing the quantity
     *                            to a positive integer.
     */
    @Test(expected = InventoryException.class)
    public void testAddInventoryNonNumMilk() throws InventoryException {
        coffeeMaker.addInventory("4", "four", "4", "4");
    }

    /**
     * Given a coffee maker with the default inventory
     * When we add inventory with negative amtSugar
     * Then we get an InventoryException
     *
     * @throws InventoryException if there was an error parsing the quantity
     *                            to a positive integer.
     */
    @Test(expected = InventoryException.class)
    public void testAddInventoryNegativeSugar() throws InventoryException {
        coffeeMaker.addInventory("4", "4", "-4", "4");
    }

    /**
     * Given a coffee maker with the default inventory
     * When we add inventory with non-numeric amtSugar
     * Then we get an inventory exception
     *
     * @throws InventoryException if there was an error parsing the quantity
     *                            to a positive integer.
     */
    @Test(expected = InventoryException.class)
    public void testAddInventoryNonNumSugar() throws InventoryException {
        coffeeMaker.addInventory("4", "4", "four", "4");
    }

    /**
     * Given a coffee maker with the default inventory
     * When we add inventory with negative amtChocolate
     * Then we get an InventoryException
     *
     * @throws InventoryException if there was an error parsing the quantity
     *                            to a positive integer.
     */
    @Test(expected = InventoryException.class)
    public void testAddInventoryNegativeChocolate() throws InventoryException {
        coffeeMaker.addInventory("4", "4", "0", "-4");
    }

    /**
     * Given a coffee maker with the default inventory
     * When we add inventory with non-numeric amtChocolate
     * Then we get an inventory exception
     *
     * @throws InventoryException if there was an error parsing the quantity
     *                            to a positive integer.
     */
    @Test(expected = InventoryException.class)
    public void testAddInventoryNonNumChocolate() throws InventoryException {
        coffeeMaker.addInventory("4", "4", "0", "four");
    }

    /** UC6: Check Inventory */

    /**
     * Check default inventory
     * Should get a string of default inventory
     */
    @Test
    public void testCheckInventory() {
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
        buf.append(15);
        buf.append("\n");

        assertEquals(buf.toString(), coffeeMaker.checkInventory());
    }

    /**
     * Check inventory after add amtCoffee
     * Should get a string of new inventory
     */
    @Test
    public void testCheckInventoryAddCoffee() throws InventoryException {
        coffeeMaker.addInventory("4", "0", "0", "0");

        StringBuffer buf = new StringBuffer();
        buf.append("Coffee: ");
        buf.append(15 + 4);
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

    /**
     * Check inventory after add amtMilk
     * Should get a string of new inventory
     */
    @Test
    public void testCheckInventoryAddMilk() throws InventoryException {
        coffeeMaker.addInventory("0", "4", "0", "0");

        StringBuffer buf = new StringBuffer();
        buf.append("Coffee: ");
        buf.append(15);
        buf.append("\n");
        buf.append("Milk: ");
        buf.append(15 + 4);
        buf.append("\n");
        buf.append("Sugar: ");
        buf.append(15);
        buf.append("\n");
        buf.append("Chocolate: ");
        buf.append(15);
        buf.append("\n");

        assertEquals(buf.toString(), coffeeMaker.checkInventory());
    }

    /**
     * Check inventory after add amtSugar
     * Should get a string of new inventory
     */
    @Test
    public void testCheckInventoryAddSugar() throws InventoryException {
        coffeeMaker.addInventory("0", "0", "4", "0");

        StringBuffer buf = new StringBuffer();
        buf.append("Coffee: ");
        buf.append(15);
        buf.append("\n");
        buf.append("Milk: ");
        buf.append(15);
        buf.append("\n");
        buf.append("Sugar: ");
        buf.append(15 + 4);
        buf.append("\n");
        buf.append("Chocolate: ");
        buf.append(15);
        buf.append("\n");

        assertEquals(buf.toString(), coffeeMaker.checkInventory());
    }

    /**
     * Check inventory after add amtChocolate
     * Should get a string of new inventory
     */
    @Test
    public void testCheckInventoryChocolate() throws InventoryException {
        coffeeMaker.addInventory("0", "0", "0", "4");

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
        buf.append(15 + 4);
        buf.append("\n");

        assertEquals(buf.toString(), coffeeMaker.checkInventory());
    }

    /** UC7: Purchase Beverage */

    /**
     * Given a coffee maker with one valid recipe
     * When we make coffee, selecting the valid recipe and paying more than
     * the coffee costs
     * Then we get the correct change back.
     */
    @Test
    public void testMakeCoffee() {
        recipes = new Recipe[] {recipe1};
        when(recipeBookStub.getRecipes()).thenReturn(recipes);
        assertEquals(25, coffeeMakerStub.makeCoffee(0, 75));

        verify(recipeBookStub, times(4)).getRecipes();
    }

    /**
     * Given a coffee maker with 2 valid recipes
     * When we make coffee, selecting the 2nd valid recipe and paying more than
     * the coffee costs
     * Then we get the correct change back.
     */
    @Test
    public void testMakeCoffee1() {
        recipes = new Recipe[] {recipe1, recipe3};
        when(recipeBookStub.getRecipes()).thenReturn(recipes);
        assertEquals(25, coffeeMakerStub.makeCoffee(1, 125));

        verify(recipeBookStub, times(4)).getRecipes();
    }

    /**
     * Given a coffee maker with 3 valid recipes
     * When we make coffee, selecting the 3rd valid recipe and paying more than
     * the coffee costs
     * Then we get the correct change back.
     */
    @Test
    public void testMakeCoffee2() {
        recipes = new Recipe[] {recipe1,recipe2,recipe3};
        when(recipeBookStub.getRecipes()).thenReturn(recipes);
        assertEquals(25, coffeeMakerStub.makeCoffee(2, 125));

        verify(recipeBookStub, times(4)).getRecipes();
    }

    /**
     * Given a coffee maker with one valid recipe
     * When we make coffee, selecting the recipe and paying less than
     * the coffee costs
     * Then we get the total amount paid back.
     */
    @Test
    public void testMakeCoffeePaidLessThanCost() {
        recipes = new Recipe[] {recipe1};
        when(recipeBookStub.getRecipes()).thenReturn(recipes);
        assertEquals(49, coffeeMakerStub.makeCoffee(0, 49));

        verify(recipeBookStub, times(2)).getRecipes();
    }

    /**
     * Given a coffee maker with one valid recipe
     * When we make coffee, selecting the recipe and paying the exact amount of
     * the coffee costs
     * Then we get nothing back.
     */
    @Test
    public void testMakeCoffeePaidExactCost() {
        recipes = new Recipe[] {recipe1};
        when(recipeBookStub.getRecipes()).thenReturn(recipes);
        assertEquals(0, coffeeMakerStub.makeCoffee(0, 50));

        verify(recipeBookStub, times(4)).getRecipes();
    }

    /**
     * Given a coffee maker with one valid recipe
     * When we make coffee, selecting the recipe which requires more amtCoffee than
     * the default inventory
     * Then we get the money back.
     */
    @Test
    public void testMakeCoffeeNotEnoughCoffee() throws RecipeException {
        Recipe tmp_recipe = createRecipe("Temp","4","44","4","4","50");
        recipes = new Recipe[] {tmp_recipe};
        when(recipeBookStub.getRecipes()).thenReturn(recipes);
        assertEquals(444, coffeeMakerStub.makeCoffee(0, 444));

        verify(recipeBookStub, times(3)).getRecipes();
    }

    /**
     * Given a coffee maker with one valid recipe
     * When we make coffee, selecting the recipe which requires more amtMilk than
     * the default inventory
     * Then we get the money back.
     */
    @Test
    public void testMakeCoffeeNotEnoughMilk() throws RecipeException {
        Recipe tmp_recipe = createRecipe("Temp","4","4","44","4","50");
        recipes = new Recipe[] {tmp_recipe};
        when(recipeBookStub.getRecipes()).thenReturn(recipes);
        assertEquals(444, coffeeMakerStub.makeCoffee(0, 444));

        verify(recipeBookStub, times(3)).getRecipes();
    }

    /**
     * Given a coffee maker with one valid recipe
     * When we make coffee, selecting the recipe which requires more amtSugar than
     * the default inventory
     * Then we get the money back.
     */
    @Test
    public void testMakeCoffeeNotEnoughSugar() throws RecipeException {
        Recipe tmp_recipe = createRecipe("Temp","4","4","4","44","50");
        recipes = new Recipe[] {tmp_recipe};
        when(recipeBookStub.getRecipes()).thenReturn(recipes);
        assertEquals(444, coffeeMakerStub.makeCoffee(0, 444));

        verify(recipeBookStub, times(3)).getRecipes();
    }

    /**
     * Given a coffee maker with one valid recipe
     * When we make coffee, selecting the recipe which requires more amtChocolate than
     * the default inventory
     * Then we get the money back.
     */
    @Test
    public void testMakeCoffeeNotEnoughChocolate() throws RecipeException {
        Recipe tmp_recipe = createRecipe("Temp","44","4","4","4","50");
        recipes = new Recipe[] {tmp_recipe};
        when(recipeBookStub.getRecipes()).thenReturn(recipes);
        assertEquals(444, coffeeMakerStub.makeCoffee(0, 444));

        verify(recipeBookStub, times(3)).getRecipes();
    }

    /**
     * Given a coffee maker with no valid recipe
     * When we try to make coffee
     * Then we get the money back.
     */
    @Test
    public void testMakeCoffeeNoRecipe() {
        recipes = new Recipe[4];
        when(recipeBookStub.getRecipes()).thenReturn(recipes);
        assertEquals(444, coffeeMakerStub.makeCoffee(0, 444));

        verify(recipeBookStub, times(1)).getRecipes();
    }

    /**
     * Given a coffee maker with one valid recipe
     * When we make coffee, selecting a recipe with negative index
     * Then we get the money back.
     */
    @Test
    public void testMakeCoffeeNegativeIndexRecipe() {
        recipes = new Recipe[] {recipe1};
        when(recipeBookStub.getRecipes()).thenReturn(recipes);
        assertEquals(444, coffeeMakerStub.makeCoffee(-1, 444));

        verify(recipeBookStub, times(1)).getRecipes();
    }

    /**
     * Given a coffee maker with one valid recipe
     * When we make coffee, selecting a recipe with index out of bound
     * Then we get the money back.
     */
    @Test
    public void testMakeCoffeeOutOfBoundIndexRecipe() {
        recipes = new Recipe[] {recipe1};
        when(recipeBookStub.getRecipes()).thenReturn(recipes);
        assertEquals(444, coffeeMakerStub.makeCoffee(44, 444));

        verify(recipeBookStub, times(1)).getRecipes();
    }
}
