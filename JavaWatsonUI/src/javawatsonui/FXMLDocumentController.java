/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javawatsonui;

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
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import javafx.scene.image.ImageView;
import static javawatsonui.Scraper.ingredientsNeeded;

/**
 *
 * @author KhaledKhalil
 */
public class FXMLDocumentController implements Initializable {
    
    public HashMap<String,String> Title2Url;

    @FXML
    private ImageView image;
    
    @FXML
    private Button button;

    @FXML
    private TextField searchBar;
    
    @FXML
    private ListView inglist;
    
    @FXML
    private ListView searchResults;

    @FXML
    private void search(ActionEvent event) throws IOException {
        System.out.println("You clicked me!");
        ObservableList results;
        System.out.println(searchBar.getText());
        switch (searchBar.getText()) {
            case "Fish Tacos":
                results = FXCollections.observableArrayList("Anaheim Fish Tacos Recipe", "Fish Tacos", "Crunchy Fish Tacos", "Fish Tacos Ultimo", "Quick Fish Tacos");
                searchResults.setItems(results);
                break;
            case "Chicken Teriyaki":
                results = FXCollections.observableArrayList("Oven Baked Chicken Teriyaki","Easy Grilled Chicken Teriyaki","Chef John's Chicken Teriyaki","Microwave Chicken Teriyaki","Sweet Onion and Pineapple Chicken Teriyaki");
                searchResults.setItems(results);
                break;
            case "Stir Fry":
                results = FXCollections.observableArrayList("Flavorful Beef Stir-Fry","Shrimp Stirfry","Chicken Stir-Fry","Stir-Fry Chicken and Broccoli", "Ginger Veggie Stir-Fry");
                searchResults.setItems(results);
                break;
                
        }

    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Title2Url = new HashMap<>();
        Title2Url.put("Anaheim Fish Tacos Recipe", "http://allrecipes.com/recipe/53604/anaheim-fish-tacos/");
        Title2Url.put("Fish Tacos", "http://allrecipes.com/recipe/53729/fish-tacos/");
        Title2Url.put("Crunchy Fish Tacos", "http://allrecipes.com/recipe/228902/crunchy-fish-tacos/");
        Title2Url.put("Fish Tacos Ultimo", "http://allrecipes.com/recipe/221935/fish-tacos-ultimo/");
        Title2Url.put("Quick Fish Tacos", "http://allrecipes.com/recipe/221936/quick-fish-tacos/");
        
        Title2Url.put("Oven Baked Chicken Teriyaki","http://allrecipes.com/recipe/228872/oven-baked-chicken-teriyaki/");
        Title2Url.put("Easy Grilled Chicken Teriyaki","http://allrecipes.com/recipe/17784/easy-grilled-chicken-teriyaki/");
        Title2Url.put("Chef John's Chicken Teriyaki","http://allrecipes.com/recipe/237927/chef-johns-chicken-teriyaki/");
        Title2Url.put("Microwave Chicken Teriyaki","http://allrecipes.com/recipe/139017/microwave-chicken-teriyaki/");
        Title2Url.put("Sweet Onion and Pineapple Chicken Teriyaki","http://allrecipes.com/recipe/239013/sweet-onion-and-pineapple-chicken-teriyaki/");
        
        Title2Url.put("Flavorful Beef Stir-Fry","http://allrecipes.com/recipe/223389/flavorful-beef-stir-fry/");
        Title2Url.put("Shrimp Stirfry","http://allrecipes.com/recipe/231376/shrimp-stirfry/");
        Title2Url.put("Chicken Stir-Fry","http://allrecipes.com/recipe/223382/chicken-stir-fry/");
        Title2Url.put("Stir-Fry Chicken and Broccoli","http://allrecipes.com/recipe/231230/stir-fry-chicken-and-broccoli/");
        Title2Url.put("Ginger Veggie Stir-Fry", "http://allrecipes.com/recipe/24712/ginger-veggie-stir-fry/");
    }

    @FXML
    private void handleMouseClick(javafx.scene.input.MouseEvent event) throws IOException {
        String URL = Title2Url.get(searchResults.getSelectionModel().getSelectedItem().toString());
        System.out.println(URL);
        HashSet<String> ing = ingredientsNeeded(URL, "food_database.csv", "foods.zip");
        ObservableList stuffForList = FXCollections.observableArrayList(ing.toArray());
        inglist.setItems(stuffForList);
    }

}
