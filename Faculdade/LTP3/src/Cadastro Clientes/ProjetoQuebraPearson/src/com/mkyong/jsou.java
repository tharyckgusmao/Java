package com.mkyong;
 
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
 


 
public class jsou {
 
  private String cookies;
  private HttpClient client = HttpClientBuilder.create().build();
  private final String USER_AGENT = "Mozilla/5.0";
 
  public static void main(String[] args) throws Exception {
 
	String url = "http://fumec.bv3.digitalpages.com.br/users/sign_in";
	String gmail = "http://fumec.bv3.digitalpages.com.br/users/publications/9789351524489/pages/2";
 
	// make sure cookies is turn on
	CookieHandler.setDefault(new CookieManager());
 
	jsou  http = new jsou ();
 
	String page = http.GetPageContent(url);
 
	List<NameValuePair> postParams =  http.getFormParams(page, "224731971","220718");
 
	http.sendPost(url, postParams);
	
	String result = http.GetPageContent(gmail);
	
	System.out.println(result);
 
	System.out.println("Done");
  }
 
  private void sendPost(String url, List<NameValuePair> postParams) 
        throws Exception {
 
	HttpPost post = new HttpPost(url);
 
	// add header
	post.setHeader("Host", "fumec.bv3.digitalpages.com.br");
	post.setHeader("User-Agent", USER_AGENT);
	post.setHeader("Accept", 
             "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
	post.setHeader("Accept-Language", "pt-BR,pt;q=0.8,en-US;q=0.5,en;q=0.3");
	post.setHeader("Cookie", getCookies());
	post.setHeader("Connection", "keep-alive");
	post.setHeader("Referer", "http://fumec.bv3.digitalpages.com.br/users/sign_in");
	post.setHeader("Content-Type", "application/x-www-form-urlencoded");
 
	post.setEntity(new UrlEncodedFormEntity(postParams));
 
	HttpResponse response = client.execute(post);
 
	int responseCode = response.getStatusLine().getStatusCode();
 
	System.out.println("\nSending 'POST' request to URL : " + url);
	System.out.println("Post parameters : " + postParams);
	System.out.println("Response Code : " + responseCode);
 
	BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
 
	StringBuffer result = new StringBuffer();
	String line = "";
	while ((line = rd.readLine()) != null) {
		result.append(line);
	}
 
	// System.out.println(result.toString());
 
  }
 
  private String GetPageContent(String url) throws Exception {
 
	HttpGet request = new HttpGet(url);
 
	request.setHeader("User-Agent", USER_AGENT);
	request.setHeader("Accept",
		"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
	request.setHeader("Accept-Language", "pt-BR,pt;q=0.8,en-US;q=0.5,en;q=0.3");
 
	HttpResponse response = client.execute(request);
	int responseCode = response.getStatusLine().getStatusCode();
 
	System.out.println("\nSending 'GET' request to URL : " + url);
	System.out.println("Response Code : " + responseCode);
 
	BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));
 
	StringBuffer result = new StringBuffer();
	String line = "";
	while ((line = rd.readLine()) != null) {
		result.append(line);
	}
 
	// set cookies
	setCookies(response.getFirstHeader("Set-Cookie") == null ? "" : 
                     response.getFirstHeader("Set-Cookie").toString());
 
	return result.toString();
 
  }
 
  public List<NameValuePair> getFormParams(
             String html, String username, String password)
			throws UnsupportedEncodingException {
 
	System.out.println("Extracting form's data...");
 

	List<NameValuePair> paramList = new ArrayList<NameValuePair>();

	paramList.add(new BasicNameValuePair("login", username));
	paramList.add(new BasicNameValuePair("password", password));
	paramList.add(new BasicNameValuePair("remember_me", "0"));
 
	
 
	return paramList;
  }
 
  public String getCookies() {
	return cookies;
  }
 
  public void setCookies(String cookies) {
	this.cookies = cookies;
  }
 
}