//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.PrintStream;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.net.URLConnection;
//import java.text.ParseException;
////import org.hibernate.MappingException;
//import java.util.Scanner;
//import java.util.logging.ConsoleHandler;
//
//import com.google.gson.JsonElement;
//
//import com.google.gson.JsonObject;
//import com.google.gson.JsonParseException;
//import com.google.gson.JsonParser;
//
//import org.json.JSONObject;
//import org.omg.CORBA.TypeCodePackage.*;
//
////22efa56c77e2a50e1b1d41c64f710408
//public class restAPI {
//
//	// String s="";
//	public String mainly(String strng) throws IOException {
//
//		String base = "http://api.openweathermap.org/data/2.5/weather?q=";
////		Scanner sc = new Scanner(System.in);
////		String city = sc.nextLine();
//		String urlStr = base + strng+ "&appid=22efa56c77e2a50e1b1d41c64f710408";
//
//		StringBuilder strn = new StringBuilder();
//
//		URL url = new URL(urlStr);
//
//		URLConnection urlc = (URLConnection) url.openConnection();
//
//		// urlc.setRequestProperty("Content-Type", "application/json");
//		// Msj("connecting: " + url.toString());
//
//		urlc.setDoOutput(true);
//		urlc.setAllowUserInteraction(false);
//
//		BufferedReader br = new BufferedReader(new InputStreamReader(urlc.getInputStream()));
//		String l = null;
//		while ((l = br.readLine()) != null) {
//			strn.append(l);
//		}
//		br.close();
//		String s = strn.toString();
//		//System.out.println(s);
//		
//		String sp = parser(s);
//
//		return sp;
//		
//
//	}
//
//	public String parser(String sjason) {
//		
//		JSONObject jobj =new JSONObject(sjason);
//		
//		
//		JSONObject ggg= jobj.getJSONObject("coord");
//		Double ddd=ggg.getDouble("lon");
//		
//		//System.out.println("\n\n "+ddd);
//		
//		//JSONObject newjob=jobj.getJSONArray("coord").getJSONObject(0);
//		
//		//System.out.println(newjob.getString("weather"));
//
//		return ""+ddd;
//
//	}
//	
//
//	
//}
