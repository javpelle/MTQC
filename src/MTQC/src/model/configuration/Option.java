package model.configuration;

public interface Option {
	
	/**
	 * Returns option description
	 * @return Option description.
	 */
	public String getOptionInfo();
	
	/**
	 * Return current option value.
	 * @return Current option value.
	 */
	public String getOption();
	
	/**
	 * Set a option value.
	 * @param option Option value.
	 */
	public void setOption(String option);
}
