package model.configuration;

public class Properties {
	
	private static String pythonPath;
	
	private static Option[] options = {new PythonPathOption()};
	
	public static String getPythonPath() {
		return pythonPath;
	}
	
	public static void setPythonPath(String pythonPath) {
		Properties.pythonPath = pythonPath;
	}
	
	public static Option[] getOptions() {
		return options;
	}

}
