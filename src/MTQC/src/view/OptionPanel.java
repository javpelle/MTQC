package view;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import model.configuration.Option;

public class OptionPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea content;
	private Option o;

	public OptionPanel(Option o) {
		setLayout(new BorderLayout());
		this.o = o;
		setBorder(BorderFactory.createTitledBorder(o.getOptionInfo()));
		this.content = new JTextArea(o.getOption());
		add(this.content, BorderLayout.CENTER);
	}

	public String getContent() {
		return content.getText();
	}
	
	public void apply() {
		o.setOption(content.getText());
	}

}
