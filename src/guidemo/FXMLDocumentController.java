/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guidemo;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

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
    
    @FXML private Label comboBoxLabel;
    @FXML private ComboBox comboBox;
    
    @FXML private Label radioButtonLabel;
    @FXML private RadioButton phpRadioButton;
    @FXML private RadioButton javaRadioButton;
    @FXML private RadioButton cSharpRadioButton;
    @FXML private RadioButton cPlusPlusRadioButton;
    private ToggleGroup favLangToggleGroup;
    
    @FXML private ListView listView;
    @FXML private TextArea golfTextArea; 
    
    public void changeScreenButtonPushed(ActionEvent event) throws IOException
    {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("ExampleOfTableView.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
        
    }
    
    
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
    
    public void comboBoxWasUpdated()
    {
        this.comboBoxLabel.setText("Course selected: \n" + comboBox.getValue().toString());
    }
    
    public void radioButtonChanged()
    {
        if (this.favLangToggleGroup.getSelectedToggle().equals(this.cPlusPlusRadioButton))
            radioButtonLabel.setText("The selected item is C++");
        if (this.favLangToggleGroup.getSelectedToggle().equals(this.javaRadioButton))
            radioButtonLabel.setText("The selected item is Java");
        if (this.favLangToggleGroup.getSelectedToggle().equals(this.cSharpRadioButton))
            radioButtonLabel.setText("The selected item is C#");
        if (this.favLangToggleGroup.getSelectedToggle().equals(this.phpRadioButton))
            radioButtonLabel.setText("The selected item is PHP");
    }
    
    public void listViewButtonPushed()
    {
        String textAreaString = "";
        
        ObservableList listOfItems = listView.getSelectionModel().getSelectedItems();
        
        for(Object item : listOfItems)
        {
            textAreaString += String.format("%s%n", (String) item);
        }
        
        this.golfTextArea.setText(textAreaString);
        
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pizzaOrderLabel.setText("");
        
        choiceBoxLabel.setText("");
        choiceBox.getItems().add("apples");
        choiceBox.getItems().add("bananas");
        choiceBox.getItems().addAll("oranges", "pears", "strawberries");
        choiceBox.setValue("apples");
        
        comboBox.getItems().add("COMP1030");
        comboBox.getItems().addAll("COMP1008","MGMT2003","MGMT2010");
        comboBoxLabel.setText("");
        
        radioButtonLabel.setText("");
        favLangToggleGroup = new ToggleGroup();
        this.cPlusPlusRadioButton.setToggleGroup(favLangToggleGroup);
        this.phpRadioButton.setToggleGroup(favLangToggleGroup);
        this.javaRadioButton.setToggleGroup(favLangToggleGroup);
        this.cSharpRadioButton.setToggleGroup(favLangToggleGroup);
        
        listView.getItems().addAll("Golf Balls","Wedges","Irons","Tees","Driver","Putter");
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }    
    
}
