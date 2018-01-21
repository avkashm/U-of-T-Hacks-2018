package javawatson;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import com.google.gson.Gson;

public class googleSearchAPI {
	
	public static void main(String[] args) throws IOException{
		
		String google_base = "http://www.google.ca/search?q=";
		String query = "SOME INPUT TEXT";
		String recipe = "recipe";
		String ascii = "UTF-8";
		
		URL url = new URL(google_base+URLEncoder.encode(query, ascii)+recipe);
		Reader reader = new InputStreamReader(url.openStream(),ascii);
		GoogleResult search_result = new Gson().fromJson(reader, GoogleResult.class);
		
		int no_of_results = 5;
		
		for (int i=0;i<no_of_results;i++) {
			System.out.println("Title: "+result.getResponseData().getResult().get(i).getTitle());
			System.out.println("URL: "+result.getResponseData().getResult().get(i).getUrl());
		}
		
		
		
	}

}
