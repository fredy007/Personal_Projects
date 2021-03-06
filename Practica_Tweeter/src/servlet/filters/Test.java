package servlet.filters;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;


@RestController
@RequestMapping("/test")
public class Test {
	@RequestMapping(value = "/{busqueda}", method = RequestMethod.GET, produces ="text/plain;charset=utf-8")
	public String test(@PathVariable String busqueda) {
        try {
            // TODO add your handling code here:
            String consumerKeyStr = "Y8NvcZqWjUvNR0wfict2rSKmx";
            String consumerSecretStr = "A3K8YqVjLpTN5sSbk9MJ8DmiwIxRapLmyhmZcCau55sqzPjA1y";
            String accessTokenStr = "566064066-BMF8JBt2JI7c4KBWEDtxRqPN2rLNxwKcUoykzoTR";
            String accessTokenSecretStr = "wo4LnwlsYYfbYkGixN0CS3NxlYfXxbxwl0gWfpQTIKas4";
            
            String c = "";
            
            c = busqueda;
            
            OAuthConsumer oAuthConsumer = new CommonsHttpOAuthConsumer(consumerKeyStr, consumerSecretStr);
            oAuthConsumer.setTokenWithSecret(accessTokenStr, accessTokenSecretStr);
            
            HttpGet httpGet = new HttpGet("https://api.twitter.com/1.1/search/tweets.json?q=" + c+ "&result_type=popular&count=5");
            
            oAuthConsumer.sign(httpGet);
            
            DefaultHttpClient client = new DefaultHttpClient();
            HttpResponse response = client.execute(httpGet);
            int statusCode = response.getStatusLine().getStatusCode();
	    	System.out.println(statusCode + " : " + response.getStatusLine().getReasonPhrase());
	    	BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
	    	String templine="";
	    	String line="";
            
            StringBuilder sb = new StringBuilder();
       
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
                
                JSONObject json = new JSONObject(sb.toString());
                
                JSONArray jArray = json.getJSONArray("statuses");
                
                String LT = "";
                
			for (int i = 0; i < 5; i++) {
				//System.out.print(((JSONObject)((JSONObject)jArray.get(i)).get("user")).get("screen_name") + "\t");
				LT += "[" +((JSONObject)jArray.get(i)).get("text") + "] \n" ;
				
			}
            
            String thisIP = InetAddress.getLocalHost().getHostAddress();
            
            return "[" + thisIP + "] : " + LT;
            
            
            
        } catch (Exception ex) {
            
        	
        	return ex.getMessage();
           // System.out.println(ex.getMessage());
        } 
	}
}