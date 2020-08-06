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

	public HashMap<String, Integer> getCounts(int key) {
		String aux = results.get(key);
		HashMap<String, Integer> counts = new HashMap<String, Integer>();
		String[] parts = aux.split(",");
		for (String s : parts) {
			String[] parts2 = s.split(":");
			counts.put(parts2[0].substring(1, parts2[0].length() - 1), Integer.valueOf(parts2[1]));
		}
		return counts;
	}

	public HashMap<Integer, Double> getStatevector(int key) {
		String aux = results.get(key);
		HashMap<Integer, Double> statevector = new HashMap<Integer, Double>();
		String[] parts = aux.split(",");
		for (String s : parts) {
			String[] parts2 = s.split(":");
			statevector.put(Integer.valueOf(parts2[0].substring(1, parts2[0].length() - 1)), Double.valueOf(parts2[1]));
		}
		return statevector;
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

	public static void main(String[] args) {
		new JSON("python\\data.json");
	}

}
