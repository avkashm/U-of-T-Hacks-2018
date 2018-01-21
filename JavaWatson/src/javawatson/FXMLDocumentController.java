/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javawatson;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.util.HashMap;

/**
 *
 * @author KhaledKhalil
 */
public class FXMLDocumentController implements Initializable {
    
    public HashMap<String,String> Title2Url;

    @FXML
    private Button button;

    @FXML
    private Label label;

    @FXML
    private TextField searchBar;

    @FXML
    private Button inventoryButton;

    @FXML
    private ListView searchResults;

    @FXML
    private void search(ActionEvent event) throws IOException {
        System.out.println("You clicked me!");
        switch (searchBar.getText()) {
            case "Fish Tacos":
                ObservableList results = FXCollections.observableArrayList("Mexican Fish Taco Recipes", "Fish Tacos", "Crunchy Fish Tacos", "Fish Tacos Ultimo", "Quick Fish Tacos");
                searchResults.setItems(results);
            case "Chicken Teriyaki":
                
        }

    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Title2Url = new HashMap<>();
        Title2Url.put("Mexican Fish Taco Recipes", "http://allrecipes.com/recipes/16562/world-cuisine/latin-american/mexican/main-dishes/tacos/fish/");
        Title2Url.put("Fish Tacos", "http://allrecipes.com/recipe/53729/fish-tacos/");
        Title2Url.put("Crunchy Fish Tacos", "http://allrecipes.com/recipe/228902/crunchy-fish-tacos/");
        Title2Url.put("Fish Tacos Ultimo", "http://allrecipes.com/recipe/221935/fish-tacos-ultimo/");
        Title2Url.put("Quick Fish Tacos", "http://allrecipes.com/recipe/221936/quick-fish-tacos/");

    }

    @FXML
    private void handleMouseClick(javafx.scene.input.MouseEvent event) {
        String URL = Title2Url.get(searchResults.getSelectionModel().getSelectedItem().toString());
        System.out.println(URL);
        
    }

}
