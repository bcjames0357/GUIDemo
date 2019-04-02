/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guidemo;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author brandon
 */
public class ExampleOfTableViewController implements Initializable {
    
    @FXML private TableView<Person> tableView;
    @FXML private TableColumn<Person, String> firstNameColumn;
    @FXML private TableColumn<Person, String> lastNameColumn;
    @FXML private TableColumn<Person, LocalDate> birthdayColumn;
    
    @FXML private TextField firstNameTextField;
    @FXML private TextField lastNameTextField;
    @FXML private DatePicker birthdayDatePicker;
    
    @FXML private Button detailedPersonViewButton;
    
    public void changeFirstNameCellEvent(CellEditEvent edditedCell)
    {
        Person personSelected = tableView.getSelectionModel().getSelectedItem();
        personSelected.setFirstName(edditedCell.getNewValue().toString());
        
    }
    
    public void changeLastNameCellEvent(CellEditEvent edditedCell)
    {
        Person personSelected = tableView.getSelectionModel().getSelectedItem();
        personSelected.setLastName(edditedCell.getNewValue().toString());
        
    }
    
    public void changeScreenButtonPushed(ActionEvent event) throws IOException
    {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
        
    }
    
    public void userClickedOnTable()
    {
        this.detailedPersonViewButton.setDisable(false);
    }
    
    //Method will pass selected Person object to the detailed view
    public void changeSceneToDetailedPersonView(ActionEvent event) throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("PersonView.fxml"));
        Parent tableViewParent = loader.load();
        
        Scene tableViewScene = new Scene(tableViewParent);
        
        PersonViewController controller = loader.getController();
        controller.initData(tableView.getSelectionModel().getSelectedItem());
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
        
    }
    
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("lastName"));
        birthdayColumn.setCellValueFactory(new PropertyValueFactory<Person, LocalDate>("birthday"));
        
        tableView.setItems(getPeople());
        
        tableView.setEditable(true);
        firstNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        lastNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        
        this.detailedPersonViewButton.setDisable(true);
    }    
    
    public void deleteButtonPushed()
    {
        ObservableList<Person> selectedRows, allPeople;
        allPeople = tableView.getItems();
        
        selectedRows = tableView.getSelectionModel().getSelectedItems();
        
        for (Person person: selectedRows)
        {
            allPeople.remove(person);
        }
    }
    
    public ObservableList<Person> getPeople()
    {
        ObservableList<Person> people = FXCollections.observableArrayList();
        people.add(new Person("Frank","Sinatra",LocalDate.of(1915,Month.DECEMBER, 12)));
        people.add(new Person("Rebecca","Fergusson",LocalDate.of(1986,Month.JULY, 21)));
        people.add(new Person("Mr.","T",LocalDate.of(1952,Month.MAY, 21)));
        
        return people;        
    }
    
    public void newPersonButtonPushed()
    {
        Person newPerson = new Person(firstNameTextField.getText(),
                                      lastNameTextField.getText(),
                                      birthdayDatePicker.getValue());
        
        //Get all the items from the table  as a list, then add the new person
        //to the list
        tableView.getItems().add(newPerson);
    }
    
    
    
}
