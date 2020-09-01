package model.json;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

public class JSON {

	private HashMap<Integer, String> results;

	public JSON(String str) {
		String json = readFile(str);
		json = json.replaceAll("\\s+", "");
		json = json.substring(1, json.length() - 2);

		String[] parts = json.split("},");
		results = new HashMap<Integer, String>();
		for (String s : parts) {
			String[] parts2 = s.split(":\\{");
			results.put(Integer.valueOf(parts2[0].substring(1, parts2[0].length() - 1)), parts2[1]);
		}
	}

	public HashMap<String, Long> getCounts(int key) {
		String aux = results.get(key);
		HashMap<String, Long> counts = new HashMap<String, Long>();
		String[] parts = aux.split(",");
		for (String s : parts) {
			String[] parts2 = s.split(":");
			counts.put(parts2[0].substring(1, parts2[0].length() - 1), Long.valueOf(parts2[1]));
		}
		return counts;
	}

	private String readFile(String file) {
		String aux = "";
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = reader.readLine();
			while (line != null) {
				aux += line;
				line = reader.readLine();
			}
			reader.close();
		} catch (Exception e) {

		}
		return aux;
	}

}
