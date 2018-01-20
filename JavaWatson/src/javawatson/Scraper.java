/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javawatson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author KhaledKhalil
 */
public class Scraper {

    public static void main(String[] args) throws IOException {
        String website = "https://choppingboard.recipes/api/v0/recipe?";
        String key = "key=4c299f3ea9a20640b815dd2f2a7c9631&q=";
        String recipe = "http://allrecipes.com/recipe/53729/fish-tacos/?internalSource=streams&referringId=16562&referringContentType=recipe%20hub&clickId=st_trending_b";
        String link = website + key + recipe;
        URL url = new URL(link);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"))) {
            for (String line; (line = reader.readLine()) != null;) {
                String[] splitted = line.split(":");

                for (int x = 0; x < splitted.length; x++) {
                    if (splitted[x].contains("ingredients")) {
                        System.out.println(splitted[x]);
                        System.out.println(splitted[x + 1]);
                        String ingredients = splitted[x + 1];
                        String[] ingredientsSplit = ingredients.split(",");

                        Pattern p = Pattern.compile("\"([^\"]*)\"");
                        Matcher m = p.matcher(ingredients);
                        while (m.find()) {
                            System.out.println(m.group(1));
                        }
                    }
                }
            }
        }

    }
}
