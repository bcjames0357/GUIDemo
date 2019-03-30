/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guidemo;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

/**
 *
 * @author brandon
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML private Label pizzaOrderLabel;
    @FXML private CheckBox pepperoniCheckBox;
    @FXML private CheckBox pineappleCheckBox;
    @FXML private CheckBox baconCheckBox;
    
    @FXML private ChoiceBox choiceBox;
    @FXML private Label choiceBoxLabel;
    
    public void choiceBoxButtonPushed()
    {
        choiceBoxLabel.setText("My favorite fruit is:\n"+choiceBox.getValue().toString());
    }
    
    public void pizzaOrderButtonPushed ()
    {
        String order = "Toppings are:";
        
        if (pepperoniCheckBox.isSelected())
            order += "\npepperoni";
        if (pineappleCheckBox.isSelected())
            order += "\npineapple";
        if (baconCheckBox.isSelected())
            order += "\nbacon";
        
        this.pizzaOrderLabel.setText(order);
    }
    //@FXML
    //private void handleButtonAction(ActionEvent event) {
    //    System.out.println("You clicked me!");
        //label.setText("Hello World!");
    //}
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pizzaOrderLabel.setText("");
        
        choiceBoxLabel.setText("");
        choiceBox.getItems().add("apples");
        choiceBox.getItems().add("bananas");
        choiceBox.getItems().addAll("oranges", "pears", "strawberries");
        choiceBox.setValue("apples");
    }    
    
}
