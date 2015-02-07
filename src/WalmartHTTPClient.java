import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;



public class WalmartHTTPClient {
	private HttpClient client;
	private HttpGet request;
	Map<String,String> m = new HashMap<String,String>();
	WalmartHTTPClient() {
		RequestConfig globalConfig = RequestConfig.custom()
		        .setCookieSpec(CookieSpecs.IGNORE_COOKIES)
		        .build();
		client = HttpClients.custom()
				.setDefaultRequestConfig(globalConfig)
		        .build();
		m.put("Host","www.walmart.com");
		m.put("Connection","close");
		m.put("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		m.put("Accept-Language", "en-US,en;q=0.8");
		m.put("User-Agent","TextScrapperForBrightEdgeTest");
	}
	void addHeaders()
	{
		for(Map.Entry<String, String> entry : m.entrySet()){
			request.addHeader(entry.getKey(),entry.getValue());
		}
	}
	void setUrl(String url){
		request = new HttpGet(url);
	}
	String getData()
	{
		HttpResponse response=null;
		
		try {
			 response = client.execute(request);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			System.out.println("Error in the client protocol");
			System.exit(1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error in the client IO");
			System.exit(1);
		}
		
		if(response.getStatusLine().getStatusCode() == 200){
			BufferedReader rd=null;
			try {
				rd = new BufferedReader(
						new InputStreamReader(response.getEntity().getContent()));
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
				System.out.println("Error in the client due to Illegal state");
				System.exit(1);
			} catch (IOException e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
				System.out.println("Error in the client");
				System.exit(1);
			}
			StringBuilder result = new StringBuilder();
			String line = "";
			try {
				while ((line = rd.readLine()) != null) {
					result.append(line);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Error in the client IO");
				System.exit(1);
			}
			return result.toString();
			
		}
		return null;
	}
}

