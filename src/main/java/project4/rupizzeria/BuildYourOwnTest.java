package project4.rupizzeria;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * test class for the Build Your Own class
 */
public class BuildYourOwnTest {
    /**
     * test method for the price method from BuildYourOwnPrice
     */
    @Test
    public void price() {
        double DELTA = 1e-2;

        BuildYourOwn smallPizza = new BuildYourOwn();
        smallPizza.setSize(Size.small);
        double price = smallPizza.price();
        double expectedPrice = 8.99;
        assertEquals(expectedPrice, price, DELTA);

        smallPizza.addTopping(Topping.sausage);
        price = smallPizza.price();
        expectedPrice = 8.99 + 1.59;
        assertEquals(expectedPrice, price, DELTA);

        smallPizza.addTopping(Topping.bbqChicken);
        smallPizza.addTopping(Topping.beef);
        price = smallPizza.price();
        expectedPrice = 8.99 + (1.59*3);
        assertEquals(expectedPrice, price, DELTA);

        BuildYourOwn mediumPizza = new BuildYourOwn();
        mediumPizza.setSize(Size.medium);
        price = mediumPizza.price();
        expectedPrice = 10.99;
        assertEquals(expectedPrice, price, DELTA);

        mediumPizza.addTopping(Topping.sausage);
        price = mediumPizza.price();
        expectedPrice = 10.99 + 1.59;
        assertEquals(expectedPrice, price, DELTA);

        mediumPizza.addTopping(Topping.bbqChicken);
        mediumPizza.addTopping(Topping.beef);
        price = mediumPizza.price();
        expectedPrice = 10.99 + (1.59*3);
        assertEquals(expectedPrice, price, DELTA);

        BuildYourOwn largePizza = new BuildYourOwn();
        largePizza.setSize(Size.large);
        price = largePizza.price();
        expectedPrice = 12.99;
        assertEquals(expectedPrice, price, DELTA);

        largePizza.addTopping(Topping.sausage);
        price = largePizza.price();
        expectedPrice = 12.99 + 1.59;
        assertEquals(expectedPrice, price, DELTA);

        largePizza.addTopping(Topping.bbqChicken);
        largePizza.addTopping(Topping.beef);
        price = largePizza.price();
        expectedPrice = 12.99 + (1.59*3);
        assertEquals(expectedPrice, price, DELTA);
    }
}