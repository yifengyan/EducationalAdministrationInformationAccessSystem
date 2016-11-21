package ujs.edu.lou.table;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

public class HttpUtil {
 
	public static CookieStore cookieStore;	
	public static String getUrl(String url, DefaultHttpClient client,
			String setHeader) throws IOException {
		HttpGet request = new HttpGet(url);
		request.setHeader("Referer", setHeader);
		
		if(cookieStore!=null)
			client.setCookieStore(cookieStore);
		HttpResponse response = client.execute(request);
		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			cookieStore = ((AbstractHttpClient) client).getCookieStore();
			return EntityUtils.toString(response.getEntity());
		} else {
			return "";
		}
	}
	public static String postUrl(String url, List<NameValuePair> params,
			DefaultHttpClient client, String setHeader)
			throws ClientProtocolException, IOException {

		HttpPost request = new HttpPost(url);
		
		
		request.setEntity(new UrlEncodedFormEntity(params,HTTP.UTF_8));
		if(setHeader!="")
		request.setHeader("Referer", setHeader);
		   if(cookieStore!=null)
			client.setCookieStore(cookieStore);
		HttpResponse response = client.execute(request);
		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			cookieStore = ((AbstractHttpClient) client).getCookieStore();
			return EntityUtils.toString(response.getEntity());
		} 
		else if (response.getStatusLine().getStatusCode() == 200) {
            // ½âÎöÊý¾Ý
            HttpEntity resEntity = response.getEntity();
            cookieStore = ((AbstractHttpClient) client).getCookieStore();
            return EntityUtils.toString(resEntity);     
        }
		else {
			cookieStore = ((AbstractHttpClient) client).getCookieStore();
			return EntityUtils.toString(response.getEntity());
		}
	}

}
