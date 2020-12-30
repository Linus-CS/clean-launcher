package de.linus.advancedGui.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonReader {

	private JsonReader() {
	}

	public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
		InputStream is = new URL(url).openStream();
		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			JSONObject json = new JSONObject(jsonText);
			return json;
		} finally {
			is.close();
		}
	}

	private static String readAll(BufferedReader reader) throws IOException {
		StringBuilder builder = new StringBuilder();
		int cp = -1;
		boolean flag = false;
		boolean first = true;
		while ((cp = reader.read()) != -1) {
			if(first) flag = cp == 91 ? true : false;
			first = false;
			builder.append((char) cp);
		}
		String output = builder.toString();
		return flag ? output.substring(1, builder.toString().length() - 1) : output;
	}
}
