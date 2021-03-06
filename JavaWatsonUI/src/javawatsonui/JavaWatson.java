/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javawatsonui;

import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifiedImages;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifyOptions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;


public class JavaWatson {
	
	public static HashSet<String> database = null;
	
	public static HashSet<String> createDatabase(String filepath) throws IOException{
		
		File database_file = new File(filepath);
		InputStream is = new FileInputStream(database_file);
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		
		database = new HashSet<>();
		
		String line = br.readLine();
		while (line != null) {
			database.add(line.toLowerCase());
			line = br.readLine();
		}
		return database;
	}
	
	public static Boolean classInDatabase(String className) {
		return database.contains(className.toLowerCase());
	}
	
	public static HashSet<String> checkInventory(String db_filepath, String img_filepath) throws IOException{
		
		VisualRecognition service = new VisualRecognition(
                VisualRecognition.VERSION_DATE_2016_05_20);
        service.setApiKey("8d7aced8efa9ce11cca985d203dce5989cc20148");
        service.setEndPoint("https://gateway-a.watsonplatform.net/visual-recognition/api");
        
        createDatabase(db_filepath);
        HashSet<String> food_in_fridge = new HashSet<>();

        InputStream imagesStream = new FileInputStream(img_filepath);
        ClassifyOptions classifyOptions = new ClassifyOptions.Builder()
                .imagesFile(imagesStream)
                .imagesFilename("tester image")
                .parameters("{\"classifier_ids\": [\"default\"], \"threshold\": 0.5}")
                .build();
        ClassifiedImages result = service.classify(classifyOptions).execute();
        
        int no_of_images = result.getImages().size();
        for (int j=0;j<no_of_images;j++) {
        	int no_of_classes = result.getImages().get(j).getClassifiers().get(0).getClasses().size();
            for (int i=0;i<no_of_classes;i++) {
            	String new_class_name = result.getImages().get(j).getClassifiers().get(0).getClasses().get(i).getClassName();
            	if (classInDatabase(new_class_name)) {
            		food_in_fridge.add(new_class_name);
            	}
            }
        }
        
        return food_in_fridge;
    }

   
    public static void main(String[] args) throws IOException {
    	//HashSet<String> inventory = checkInventory("food_database.csv","../../foods.zip");
    	//System.out.print(inventory);
    }
}

