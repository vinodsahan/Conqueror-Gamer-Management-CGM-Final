package com.example.cgm;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class API {
    private static final String GET_URL = "https://marcconrad.com/uob/smile/api.php";
    private static final String USER_AGENT = "Mozilla/5.0";

    public static JSONObject sendGET() throws IOException, JSONException {
        URL obj = new URL(GET_URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        int responsCode = getResponseCode(con);
        System.out.println("GET Response code :: " + responsCode);
        if (responsCode == HttpURLConnection.HTTP_OK){
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer responceString = new StringBuffer();

            while ((inputLine = in.readLine()) != null){
                responceString.append(inputLine);
            }
            in.close();

            JSONObject responceJosn = new JSONObject(responceString.toString());
            System.out.println(responceJosn.toString());
            return responceJosn;
        }else {
            System.out.println("GET reponce not worked");
        }
        return null;
    }

    private static int getResponseCode(HttpURLConnection con) throws IOException{
        return con.getResponseCode();
    }
}
