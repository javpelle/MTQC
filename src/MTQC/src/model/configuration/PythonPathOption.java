package model.configuration;

public class PythonPathOption implements Option {

	@Override
	public String getOptionInfo() {
		return "Python Path";
	}

	@Override
	public String getOption() {
		return Properties.getPythonPath();
	}

	@Override
	public void setOption(String option) {
		Properties.setPythonPath(option);		
	}

}
