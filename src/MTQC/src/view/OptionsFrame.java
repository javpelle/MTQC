package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.configuration.Option;

public class OptionsFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ArrayList<OptionPanel> optionPanelList;

	public OptionsFrame(Option[] options) {
		super("Configuration");
		setSize(new Dimension(640, 360));
		setLayout(new BorderLayout());
		
		optionPanelList = new ArrayList<OptionPanel>();

		centerPanel(options);
		southPanel();
		setVisible(true);
	}

	private void centerPanel(Option[] options) {
		JPanel center = new JPanel();
		add(center, BorderLayout.NORTH);
		center.setLayout(new GridLayout(options.length, 1));
		for (Option o : options) {
			OptionPanel optionPanel = new OptionPanel(o);
			center.add(optionPanel);
			optionPanelList.add(optionPanel);
		}
	}

	private void southPanel() {
		JPanel south = new JPanel();
		add(south, BorderLayout.SOUTH);
		JButton apply = new JButton("Apply");
		JButton accept = new JButton("Accept");
		apply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				applyConfiguration();
			}
		});
		accept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				applyConfiguration();
				close();
			}
		});
		south.add(apply);
		south.add(accept);
	}

	private void applyConfiguration() {
		for (OptionPanel o : optionPanelList) {
			o.apply();
		}
	}

	private void close() {
		setVisible(false);
		dispose();
	}

}
