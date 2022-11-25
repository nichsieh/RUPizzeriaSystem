package project4.rupizzeria;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Controller for the ChicagoPizzaView.fxml
 */
public class ChicagoPizzaController {

    /**
     * Choice box for the type of pizzas
     */
    @FXML
    ChoiceBox pizzaTypeBox;

    /**
     * observable list for the pizzatypesBox
     */
    ObservableList<String> pizzaTypes = FXCollections.observableArrayList("Build Your Own", "BBQ Chicken", "Deluxe", "Meatzza");

    /**
     * Check Box for all the toppings
     */
    @FXML
    CheckBox sausage, pepperoni, greenPepper, onion, mushroom, bbqChicken, provolone, cheddar, beef, ham, cheese, broccoli, olive;

    /**
     * Radio Button for the sizes
     */
    @FXML
    RadioButton small, medium, large;

    /**
     * Text Area to set texts
     */
    @FXML
    TextArea crustType, total, messages;

    /**
     * image view for the image
     */
    @FXML
    ImageView pizzaImage;

    /**
     * max toppings allowed
     */
    private static final int MAXTOPPINGS = 7;

    /**
     * initiates the pizza object
     */
    private Pizza pizza;

    /**
     * ArrayList of toppings
     */
    static ArrayList<Topping> toppings = new ArrayList<>();

    /**
     * String for the type of pizza
     */
    String type;

    /**
     * Size of the pizza
     */
    Size size;

    /**
     * Crust of the pizza
     */
    Crust crust;

    /**
     * price of the pizza
     */
    double price = 0;

    /**
     * toppingPrice of the pizza
     */
    double toppingPrice = 0;

    /**
     * initialize the choice box with the pizza types
     */
    @FXML
    private void initialize(){
        // populate the choice box
        pizzaTypeBox.setItems(pizzaTypes);
        // when the box is pressed it gets the information selected
        pizzaTypeBox.setOnAction(e -> setType(pizzaTypeBox));
    }

    /**
     * called when the choice box is selected
     * @param pizzaTypeBox pizzaTypeBox is a choiceBox
     */
    private void setType(ChoiceBox pizzaTypeBox){
        messages.setText("");
        type = pizzaTypeBox.getValue().toString();

        setPizzaImage();

        // every time a type is changed, the crust is automatically changed with it
        crust = getCrust();
        crustType.setText(Crust.crustInfo(crust));

        // when we set a price we also get the size. When we select the size we also get the price.
        setPrice();

        resetToppings();
        if (!type.equalsIgnoreCase("Build Your Own")){
            setToppings();
        } else{
            getToppingsForBYO();
        }
    }

    /**
     * set image for the imageview
     */
    private void setPizzaImage(){
        if (type.equalsIgnoreCase("Deluxe")){
            Image deluxeImage = new Image(getClass().getResourceAsStream("DeluxeChicagoPizza.png"));
            pizzaImage.setImage(deluxeImage);
        } else if (type.equalsIgnoreCase("BBQ Chicken")){
            Image bbqChickenImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("BBQChickenChicagoPizza.png")));
            pizzaImage.setImage(bbqChickenImage);
        } else if (type.equalsIgnoreCase("Meatzza")){
            Image meatzzaImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("MeatzzaChicagoPizza.png")));
            pizzaImage.setImage(meatzzaImage);
        } else if (type.equalsIgnoreCase("Build Your Own")){
            Image byoImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("1-ChicagoPizza.png")));
            pizzaImage.setImage(byoImage);
        }
    }

    /**
     * get the size for the pizza
     * @return Size from the size class
     */
    private Size getSize(){
        if (small.isSelected()){
            size = Size.small;
        } else if (medium.isSelected()){
            size = Size.medium;
        } else if (large.isSelected()){
            size = Size.large;
        } else {
            size = null;
        }
        return size;
    }

    /**
     * get the crust method
     * @return crust from the crust class
     */
    private Crust getCrust(){
        return Crust.getCrust(type, "Chicago");
    }

    /**
     * set the price for the pizza
     */
    public void setPrice(){
        getSize();
        if (type != null && size != null){
            if (type.equalsIgnoreCase("Deluxe")){
                if (size.equals(Size.small)){
                    price = 14.99;
                } else if (size.equals(Size.medium)){
                    price = 16.99;
                } else if (size.equals(Size.large)){
                    price = 18.99;
                }
            } else if (type.equalsIgnoreCase("BBQ Chicken")){
                if (size.equals(Size.small)){
                    price = 13.99;
                } else if (size.equals(Size.medium)){
                    price = 15.99;
                } else if (size.equals(Size.large)){
                    price = 17.99;
                }
            } else if (type.equalsIgnoreCase("Meatzza")){
                if (size.equals(Size.small)){
                    price = 15.99;
                } else if (size.equals(Size.medium)){
                    price = 17.99;
                } else if (size.equals(Size.large)){
                    price = 19.99;
                }
            } else if (type.equalsIgnoreCase("Build Your Own")){
                if (size.equals(Size.small)){
                    price = 8.99;
                } else if (size.equals(Size.medium)){
                    price = 10.99;
                } else if (size.equals(Size.large)){
                    price = 12.99;
                }
            }
            if (!type.equalsIgnoreCase("Build Your Own")){
                total.setText(String.valueOf(price));
            } else {
                total.setText(String.valueOf(price + toppingPrice));
            }
        }
    }

    /**
     * update the price for the build your own pizza
     */
    private void updatePrice(){
        toppingPrice = toppings.size() * 1.59;
        total.setText(String.format("%.2f", price + toppingPrice));
    }

    /**
     * select the boxes for which toppings are in the deluxe, meatzza or bbqchicken pizza.
     * @param toppings arraylist of topping
    **/
    private void setToppingBoxes(ArrayList<Topping> toppings){
        for (Topping topping : toppings){
            if (topping.equals(Topping.sausage))
                sausage.setSelected(true);
            else if (topping.equals(Topping.pepperoni))
                pepperoni.setSelected(true);
            else if (topping.equals(Topping.greenPepper))
                greenPepper.setSelected(true);
            else if (topping.equals(Topping.onion))
                onion.setSelected(true);
            else if (topping.equals(Topping.mushroom))
                mushroom.setSelected(true);
            else if (topping.equals(Topping.bbqChicken))
                bbqChicken.setSelected(true);
            else if (topping.equals(Topping.provolone))
                provolone.setSelected(true);
            else if (topping.equals(Topping.cheddar))
                cheddar.setSelected(true);
            else if (topping.equals(Topping.beef))
                beef.setSelected(true);
            else if (topping.equals(Topping.ham))
                ham.setSelected(true);
            else if (topping.equals(Topping.cheese))
                cheese.setSelected(true);
            else if (topping.equals(Topping.broccoli))
                broccoli.setSelected(true);
            else if (topping.equals(Topping.olive))
                olive.setSelected(true);
        }

        makeToppingsUnavailable();
    }

    /**
     * get the toppings for the deluxe, meatzza or bbqchicken pizza.
     */
    private void setToppings(){
        if (type.equalsIgnoreCase("Deluxe")) {
            toppings = Topping.getToppings("Deluxe");
        } else if (type.equalsIgnoreCase("BBQ Chicken")) {
            toppings = Topping.getToppings("BBQ Chicken");
        } else if (type.equalsIgnoreCase("Meatzza")) {
            toppings = Topping.getToppings("Meatzza");
        }

        setToppingBoxes(toppings);
    }

    /**
     * get the toppings for the build your own pizza
     */
    public void getToppingsForBYO(){
        if (type != null ){
            if (toppings.size() != 0){
                toppings.removeAll(toppings.subList(0, toppings.size()));
            }
            if (type.equalsIgnoreCase("Build Your Own")){
                if (sausage.isSelected())
                    toppings.add(Topping.sausage);
                if (pepperoni.isSelected())
                    toppings.add(Topping.pepperoni);
                if (greenPepper.isSelected())
                    toppings.add(Topping.greenPepper);
                if (onion.isSelected())
                    toppings.add(Topping.onion);
                if (mushroom.isSelected())
                    toppings.add(Topping.mushroom);
                if (bbqChicken.isSelected())
                    toppings.add(Topping.bbqChicken);
                if (provolone.isSelected())
                    toppings.add(Topping.provolone);
                if (cheddar.isSelected())
                    toppings.add(Topping.cheddar);
                if (beef.isSelected())
                    toppings.add(Topping.beef);
                if (ham.isSelected())
                    toppings.add(Topping.ham);
                if (cheese.isSelected())
                    toppings.add(Topping.cheese);
                if (broccoli.isSelected())
                    toppings.add(Topping.broccoli);
                if (olive.isSelected())
                    toppings.add(Topping.olive);

                if (toppings.size() > MAXTOPPINGS - 1){
                    makeToppingsUnavailable();
                } else {
                    makeToppingAvailable();
                }

                updatePrice();
            }
        }
    }

    /**
     * reset the checkboxes and the toppings list.
     */
    private void resetToppings(){
        if (toppings.size() != 0){
            toppings.removeAll(toppings.subList(0, toppings.size()));
        }
        sausage.setSelected(false);
        sausage.setDisable(false);
        pepperoni.setSelected(false);
        pepperoni.setDisable(false);
        greenPepper.setSelected(false);
        greenPepper.setDisable(false);
        onion.setSelected(false);
        onion.setDisable(false);
        mushroom.setSelected(false);
        mushroom.setDisable(false);
        bbqChicken.setSelected(false);
        bbqChicken.setDisable(false);
        provolone.setSelected(false);
        provolone.setDisable(false);
        cheddar.setSelected(false);
        cheddar.setDisable(false);
        beef.setSelected(false);
        beef.setDisable(false);
        ham.setSelected(false);
        ham.setDisable(false);
        cheese.setSelected(false);
        cheese.setDisable(false);
        broccoli.setSelected(false);
        broccoli.setDisable(false);
        olive.setSelected(false);
        olive.setDisable(false);
    }

    /**
     * disable all checkboxes that are not selected
     */
    private void makeToppingsUnavailable(){
        if (!sausage.isSelected())
            sausage.setDisable(true);
        if (!pepperoni.isSelected())
            pepperoni.setDisable(true);
        if (!greenPepper.isSelected())
            greenPepper.setDisable(true);
        if (!onion.isSelected())
            onion.setDisable(true);
        if (!mushroom.isSelected())
            mushroom.setDisable(true);
        if (!bbqChicken.isSelected())
            bbqChicken.setDisable(true);
        if (!provolone.isSelected())
            provolone.setDisable(true);
        if (!cheddar.isSelected())
            cheddar.setDisable(true);
        if (!beef.isSelected())
            beef.setDisable(true);
        if (!ham.isSelected())
            ham.setDisable(true);
        if (!cheese.isSelected())
            cheese.setDisable(true);
        if (!broccoli.isSelected())
            broccoli.setDisable(true);
        if (!olive.isSelected())
            olive.setDisable(true);
    }

    /**
     * make all the topping checkboxes available
     */
    private void makeToppingAvailable(){
        sausage.setDisable(false);
        pepperoni.setDisable(false);
        greenPepper.setDisable(false);
        onion.setDisable(false);
        mushroom.setDisable(false);
        bbqChicken.setDisable(false);
        provolone.setDisable(false);
        cheddar.setDisable(false);
        beef.setDisable(false);
        ham.setDisable(false);
        cheese.setDisable(false);
        broccoli.setDisable(false);
        olive.setDisable(false);
    }

    /**
     * error message when adding a pizza to a current order
     * @return String - the error message
     */
    private String errorMessages(){
        if (type.equalsIgnoreCase(""))
            return "Choose your pizza type";
        if (getSize() == null) // getSize returns "" if not a small, medium or large
            return "Choose your pizza size";
        if (toppings.size() <= 0)
            return "Choose your toppings";
        return "";
    }

    /**
     * called when user presses the "Add to current order" button
     */
    public void addToCurrentOrder(){
        // check if all fields are not blank
        String error = errorMessages();

        if (error.isBlank()){
            ChicagoPizza chicagoPizza = new ChicagoPizza();
            // type is selected automatically when the user uses the dropdown box
            if (type.equalsIgnoreCase("Build Your Own")){
                pizza = chicagoPizza.createBuildYourOwn();
            } else if (type.equalsIgnoreCase("Deluxe")) {
                pizza = chicagoPizza.createDeluxe();
            } else if (type.equalsIgnoreCase("BBQ Chicken")) {
                pizza = chicagoPizza.createBBQChicken();
            } else if (type.equalsIgnoreCase("Meatzza")) {
                pizza = chicagoPizza.createMeatzza();
            }

            pizza.setType("Chicago");
            pizza.setSize(size);
            pizza.setCrust(crust);

            if (type.equalsIgnoreCase("Build Your Own")){
                for (Topping topping : toppings)
                    pizza.addTopping(topping);
            }

            if (MainViewController.currOrder.add(pizza)){
                messages.setText(size.toString().toUpperCase() + " Chicago "+ type + " style pizza \n has been added to current order");
            } else {
                messages.setText(size.toString().toUpperCase() + " Chicago " + type + " pizza could not be added");
            }

        } else {
            messages.setText(error);
        }
    }

    /**
     * Cancel chicago pizza order. It goes back to main menu
     * @param event actionEvent
     * @throws IOException for any exceptions
     */
    public void backToMainView(ActionEvent event) throws IOException {
        Stage stage;
        Scene scene;
        Parent root;
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainView.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("RU Pizzeria");
        stage.setScene(scene);
        stage.show();
    }
}
