/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javawatson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javawatson.JavaWatson;

/**
 *
 * @author KhaledKhalil
 */
public class Scraper {
	
	public static HashSet<String> getIngredients(String url_recipe) throws UnsupportedEncodingException, IOException{
		
		String website = "https://choppingboard.recipes/api/v0/recipe?";
        String key = "key=4c299f3ea9a20640b815dd2f2a7c9631&q=";
        String link = website + key + url_recipe;
        
        HashSet<String> ingredientList = new HashSet<>();
        
        URL url = new URL(link);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"))) {
            for (String line; (line = reader.readLine()) != null;) {
                String[] splitted = line.split(":");

                for (int x = 0; x < splitted.length; x++) {
                    if (splitted[x].contains("ingredients")) {
//                        System.out.println(splitted[x]);
//                        System.out.println(splitted[x + 1]);
                        String ingredients = splitted[x + 1];
                        String[] ingredientsSplit = ingredients.split(",");

                        Pattern p = Pattern.compile("\"([^\"]*)\"");
                        Matcher m = p.matcher(ingredients);
                        while (m.find()) {
                            ingredientList.add(m.group(1));
                        }
                    }
                }
            }
        }
        return ingredientList;

	}
	
	public static HashSet<String> ingredientsNeeded(String recipe_url, String db_filepath, String img_filepath) throws UnsupportedEncodingException, IOException{
		
		HashSet<String> ingNeeded = getIngredients(recipe_url);
		HashSet<String> allIngredients = getIngredients(recipe_url);
		HashSet<String> inventory = JavaWatson.checkInventory(db_filepath, img_filepath);
		
		for (String ingredient : allIngredients) {
			for (String inventory_item : inventory) {
				if (ingredient.contains(inventory_item)){
					ingNeeded.remove(ingredient);
				}
				if (ingredient.equals("instructions")) {
					ingNeeded.remove(ingredient);
				}
			}
		}
		return ingNeeded;
	}

    public static void main(String[] args) throws IOException {
    	for (String ing : ingredientsNeeded("http://allrecipes.com/recipe/53729/fish-tacos/","food_database.csv","../../foods.zip")) {
    		System.out.println(ing);
    	}
    }
}
