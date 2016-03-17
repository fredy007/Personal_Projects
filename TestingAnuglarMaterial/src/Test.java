import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import com.google.gson.Gson;


public class Test {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
	    String google = "http://ajax.googleapis.com/ajax/services/search/web?v=1.0&q=";
	    String search = "Bill Gates";
	    String charset = "UTF-8";

	    URL url = new URL(google + URLEncoder.encode(search, charset));
	    Reader reader = new InputStreamReader(url.openStream(), charset);
	    GoogleResults results = new Gson().fromJson(reader, GoogleResults.class);	    
	    
	    
	    System.out.println("Search size: " + results.getResponseData().getResults().size());
	    
	    int i = 0;
	    while (i < results.getResponseData().getResults().size())
	    {
	    	
		    System.out.println(results.getResponseData().getResults().get(i).getTitle());
		    System.out.println(results.getResponseData().getResults().get(i).getUrl());
		    System.out.println("---------------------------------------------------------");
		    System.out.println(" ");
		    System.out.println(" ");
		    
		    i++;
		    
		    if(i == 10) 
		    	break;
	    	
	    }
	    
	
	  
	    
	    
	    

	}

}
