/*
 * Student : Kristina Upadhaya
 * Class   : CS 2336.501
 * Due Date: 04/22/2018
 * Semester Project 2
 * Purpose: This program will allow you to chat in chatbot with others regarding to weather and trends in twitter. It uses json and rest appi.

*/

import java.io.IOException;
import org.jibble.pircbot.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.ConsoleHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import twitter4j.Location;
import twitter4j.ResponseList;
import twitter4j.Trends;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationBuilder;
import org.json.JSONArray;
import org.json.JSONObject;
import org.omg.CORBA.TypeCodePackage.*;
import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.TwitterApi;
import org.scribe.model.*;
import org.scribe.oauth.*;

public class MyBot extends PircBot {
	
	//Constructor
	public MyBot() {
		this.setName("KRISTINA2336"); // this is name of chat bot that will try to use when it joins an IRC server
	}

	//method that checks for weather, temparature, temp, humidity, wind, usage, time, and trend enter by the user and if no match found return no match
	public String checkKeyword(String key) {

		if (key.contains("weather")) {
			return "weather";
		} else if (key.contains("temperature") || key.contains("temp")) {
			return "main";
		} else if (key.contains("humidity")) {
			return "humidity";
		} else if (key.contains("wind")) {
			return "wind";
		} else if (key.contains("usage")) {
			return "usage";
		} else if (key.contains("time")) {
			return "time";
		} else if (key.contains("trend")) {
			return "trend";
		} else
			return "nomatch";
	}
    //this method checks if the key is true or false
	public boolean checkK(String key) {

		if (key.contains("weather")) {
			return true;
		} else if (key.contains("temperature") || key.contains("temp")) {
			return true;
		} else if (key.contains("humidity")) {
			return true;
		} else if (key.contains("wind")) {
			return true;
		} else if (key.contains("trend")) {
			return true;
		} else if (key.contains("usage")) {
			return true;
		} else if (key.contains("time")) {
			return true;
		} else
			return false;
	}

	//check all the input enter by the user and do accordingly
	public void onMessage(String channel, String sender, String login, String hostman, String message) {

		String check = message;
		// check for keywords that we support
		check = check.toLowerCase();
		String match = checkKeyword(message);
		boolean ch = checkK(message);
		if (match != "nomatch" && ch == true) {
			if (message.equalsIgnoreCase("usage")) {
				
				String msg = "\t Commands: trend/weather/temperature/wind/humidity: 1. command 2. command <cityname> 3. command <zipcode> 4. What is the <command> in <city/zipcode>";
				sendMessage(channel, sender + " " + msg);
				return;
			}
			if (message.equalsIgnoreCase("time")) {
				String time = new java.util.Date().toString();
				sendMessage(channel, sender + ": The time is now " + time);
				return;
			}

			if (match.equals("nomatch")) {
				sendMessage(channel, sender + ": Invalid Request");

			}

			if (message.matches("\\d")) {

				boolean hasInt = message.matches("\\d{5}");
				boolean hasw = message.matches("\\d{7}");
				if (hasInt == false || hasw == false) {
					sendMessage(channel, sender + ": Invalid request" + ".");

				} else {

					int zipW = (Integer.parseInt(check.replaceAll("[\\D]", "")));

					String zipcodeW = Integer.toString(zipW);
					String strZ = "";

					if (hasInt) {
						try {
							strZ = mainly(zipcodeW, match, true);
						} catch (IOException e) {
							e.printStackTrace();
						}
						match = match.toUpperCase();
						match = match + " : ";
						sendMessage(channel, sender + " " + strZ);
					} else {
						strZ = twitterJSON(zipcodeW);
						sendMessage(channel, sender + " " + strZ);
					}
				}
			}
			// check if no numbers then
			else {
				String strCA[] = message.split("\\s+");
				String strCity = "";
				String strW = "";
				if (strCA.length == 1) {
					strCity = "dallas";
					strW = "dallas";
				} else {

					strCity = strCA[strCA.length - 1];
					strCity = strCity.replaceAll("\\?", "");
					strW = strCity;
				}

				String strjson = "";
				try {
					if (match == "trend") {
						String strZ = twitterJSON(strW);
						sendMessage(channel, sender + " " + strZ);
					} else {
						strjson = mainly(strCity, match, false);
					}
				} catch (IOException e) {

					e.printStackTrace();

				}
				match = match.toUpperCase();
				match = match + " : ";
				sendMessage(channel, sender + " " + strjson);

			}

		}

	}

	public Twitter twitter() {
		ConfigurationBuilder cBuilder = new ConfigurationBuilder();     //Builder

		//API credentials of Twitter 
		cBuilder.setDebugEnabled(true).setOAuthConsumerKey("UusAn3krxo7Cwh6fCUODbDqla")            //consumer key
				.setOAuthConsumerSecret("uw2EMJGCGWcSUYImeJJpCKtre9goQovUWAxh2UiNU0XfmXhu4Z")      //consumer secret
				.setOAuthAccessToken("987369275651436550-BvusryhtqLiXJpw7QKUeH6A9xyW4ui4")         //token
				.setOAuthAccessTokenSecret("YHMCcbTsKP6MOnvSA3XPqyt8Dq4HW7dZxec5e89lWqNyc");       //token secret

		TwitterFactory tFactory = new TwitterFactory(cBuilder.build());      //creatig an object

		Twitter t = tFactory.getInstance();
		return t;
	}

	public String twitterJSON(String strARG) {

		Twitter t = twitter();

		FileReader in = null; 
		//try/catch exception
		try {
			in = new FileReader("woeidW.txt");    //read file
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		BufferedReader bufRead = new BufferedReader(in);
		StringBuffer sb = new StringBuffer();
		String city = "";
		String woeid = "";
		int counter = 0;
		String info[] = null;
		int wid = 0;

		try {
			while ((woeid = bufRead.readLine()) != null) {

				woeid = woeid.toLowerCase();
				strARG = strARG.toLowerCase();

				if (woeid.contains(strARG)) {
					String[] lineArr = woeid.split(",");

					wid = (Integer.parseInt(woeid.replaceAll("[\\D]", "")));

				}

			}
			if (wid == 0) {
				wid = 2388929;
				strARG = "USA";
			}
			String widdd = "" + wid;

		} catch (IOException e1) {
			e1.printStackTrace();
		}

		Trends trends = null;
		try {
			trends = t.getPlaceTrends(wid);
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		String strl = "";
		for (int i = 0; i < trends.getTrends().length; i++)

		{

			strl += (trends.getTrends()[i].getName());
		}
		strl = " The trending topics in " + strARG + " are " + strl;

		return strl;

	}

	public String mainly(String strng, String key, boolean isZip) throws IOException {
		String urlStr = "";
		if (isZip == false) {
			String base = "http://api.openweathermap.org/data/2.5/weather?q=";
			
			urlStr = base + strng + "&appid=22efa56c77e2a50e1b1d41c64f710408";
		} else {
			String base = "http://api.openweathermap.org/data/2.5/weather?zip=";
			
			urlStr = base + strng + ",us&appid=22efa56c77e2a50e1b1d41c64f710408";
		}

		StringBuilder strn = new StringBuilder();

		URL url = new URL(urlStr);

		URLConnection urlc = (URLConnection) url.openConnection();

		urlc.setDoOutput(true);
		urlc.setAllowUserInteraction(false);

		BufferedReader br = new BufferedReader(new InputStreamReader(urlc.getInputStream()));
		String l = null;
		while ((l = br.readLine()) != null) {
			strn.append(l);
		}
		br.close();
		String s = strn.toString();

		String sp = parser(strng, s, key);

		return sp;

	}

	public String parser(String strng, String sjason, String key) {

		JSONObject jobj = new JSONObject(sjason);

		key = key.toLowerCase();

		if (key.equals("main")) {

			JSONObject ggg = jobj.getJSONObject("main");
			Double ddd = ggg.getDouble("temp");
			Double dddn = ggg.getDouble("temp_min");
			Double dddx = ggg.getDouble("temp_max");
			return "" + ddd + " minimum :" + dddn + " maximum: " + dddx;

		} else if (key.equals("weather")) {
			JSONArray aaa = jobj.getJSONArray("weather");

			String sss = "";
			for (int i = 0; i < aaa.length(); i++) {
				JSONObject c = aaa.getJSONObject(i);
				sss = c.getString("description");

			}
			JSONObject ggg = jobj.getJSONObject("main");
			Double ddd = ggg.getDouble("temp");
			JSONObject gggh = jobj.getJSONObject("wind");
			Double dddh = gggh.getDouble("speed");
			JSONObject gggm = jobj.getJSONObject("main");
			Double dddm = gggm.getDouble("humidity");

			return "" + strng + "," + sss + " temp " + ddd + " wind " + dddh + " humidity " + dddm;

		} else if (key.equals("wind")) {
			JSONObject ggg = jobj.getJSONObject("wind");
			Double ddd = ggg.getDouble("speed");

			return "" + ddd + " ";

		} else if (key.equals("humidity")) {
			JSONObject ggg = jobj.getJSONObject("main");
			Double ddd = ggg.getDouble("humidity");

			return "" + ddd + " ";

		} else
			return "";

	}

}
