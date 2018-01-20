/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javawatson;

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
	
	private static HashSet<String> database = null;
	
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

   
    public static void main(String[] args) throws IOException {
    	
        VisualRecognition service = new VisualRecognition(
                VisualRecognition.VERSION_DATE_2016_05_20);
        service.setApiKey("8d7aced8efa9ce11cca985d203dce5989cc20148");
        
        createDatabase("food_database.csv");

        InputStream imagesStream = new FileInputStream("../../corn.jpg");
        ClassifyOptions classifyOptions = new ClassifyOptions.Builder()
                .imagesFile(imagesStream)
                .imagesFilename("tester image")
                .parameters("{\"classifier_ids\": [\"default\"], \"threshold\": 0.5}")
                .build();
        ClassifiedImages result = service.classify(classifyOptions).execute();
        
        ArrayList<String> classnames = new ArrayList<>();
        int no_of_classes = result.getImages().get(0).getClassifiers().get(0).getClasses().size();
        for (int i=0;i<no_of_classes;i++) {
        	String new_class_name = result.getImages().get(0).getClassifiers().get(0).getClasses().get(i).getClassName();
        	if (classInDatabase(new_class_name)) {
        		classnames.add(new_class_name);
        	}
        }
        System.out.println(classnames);
        }
    }

