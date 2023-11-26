package ar.com.tdm.weather.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CallHttp {

	private final static Logger log = LoggerFactory.getLogger(CallHttp.class);

	public static final int TIMEOUT = 10000;

	
	public static String llamadoHttpGet(URL url) throws IOException {

		String inputLine;
		StringBuffer response = new StringBuffer();
		HttpURLConnection con = null;

		con = (HttpURLConnection) url.openConnection();

		con.setConnectTimeout(TIMEOUT);
		con.setReadTimeout(TIMEOUT);

		con.setRequestMethod("GET");
		con.setRequestProperty("Accept", "application/json");

		int responseCode = con.getResponseCode();
		
		if (responseCode == 200) {

			BufferedReader in;
			in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			return response.toString();
		} else {
			log.error("Error " + url.toString() + " - Code: " + responseCode);
			return con.getContent().toString();
		}
	}
	

	public static String llamadoHttpPost(URL url, String body) throws IOException {

        String inputLine;
        StringBuffer response = new StringBuffer();
        HttpURLConnection con = null;

        con = (HttpURLConnection) url.openConnection();

        con.setConnectTimeout(TIMEOUT);
        con.setReadTimeout(TIMEOUT);
        con.setRequestMethod("POST");
        con.setDoOutput(true);
        con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

        OutputStream os = con.getOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");

        osw.write(body);
        osw.flush();
        osw.close();

        con.connect();

        int responseCode = con.getResponseCode();
        if (responseCode == 200) {
            BufferedReader in;
            in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response.toString();
        } else {
            return con.getContent().toString();
        }
    }
}
