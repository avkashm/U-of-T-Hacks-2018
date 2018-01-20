/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javawatson;

import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifiedImages;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifyOptions;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 *
 * @author KhaledKhalil
 */
public class JavaWatson {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here
        VisualRecognition service = new VisualRecognition(
                VisualRecognition.VERSION_DATE_2016_05_20
        );
        service.setApiKey("8d7aced8efa9ce11cca985d203dce5989cc20148");

        InputStream imagesStream = new FileInputStream("../../Downloads/WatsomImages/Banana.jpg");
        ClassifyOptions classifyOptions = new ClassifyOptions.Builder()
                .imagesFile(imagesStream)
                .imagesFilename("fruitbowl.jpg")
                .parameters("{\"classifier_ids\": [\"default\"], \"threshold\": 0.6}")
  .build();
        ClassifiedImages result = service.classify(classifyOptions).execute();
        //System.out.println(result);
        
        System.out.println(result.getImages().get(0).getClassifiers().get(0).getClasses().get(0).getTypeHierarchy());
        }
    }

