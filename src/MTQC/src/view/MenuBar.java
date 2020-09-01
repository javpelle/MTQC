/**
 * This code is part of MTQC.
 * 
 * Copyright (c) 2020 Javier Pellejero, Luis Aguirre.
 * 
 * This code is licensed under the MIT License. You may obtain a copy 
 * of this license in the LICENSE file in the root directory of this source tree 
 * or at https://github.com/javpelle/MTQC/blob/master/LICENSE.
 */

package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;

import model.configuration.Option;
import model.language.ELanguage;

/**
 * Typical menu bar of the UI.
 * 
 * @author luisl
 *
 */
public class MenuBar extends JMenuBar {
	/**
	 * Unique identifier.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * URL for the readme of the repository.
	 */

	private static final String URL_Readme = "https://github.com/javpelle/TFGInformatica/blob/master/README.md";

	/**
	 * Constructor for the class.
	 * 
	 * @param listener      Listener for the language change field.
	 * @param resetListener Listener for the reset button.
	 */
	public MenuBar(LanguageListener listener, ResetListener resetListener, OptionsListener optionListener) {
		JMenu menu1 = new JMenu("File");
		add(menu1);
		JMenuItem reset = new JMenuItem("Reset");
		menu1.add(reset);
		reset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				resetListener.reset();
			}
		});

		JMenu menu2 = new JMenu("Language");
		ButtonGroup language = new ButtonGroup();

		JRadioButtonMenuItem qiskit = new JRadioButtonMenuItem("Qiskit");
		qiskit.setSelected(true);
		JRadioButtonMenuItem qsharp = new JRadioButtonMenuItem("Q#");
		qsharp.setSelected(false);

		menu2.add(qiskit);
		menu2.add(qsharp);

		language.add(qiskit);
		language.add(qsharp);

		// If new Language is added, it is necessary replicate structure code.
		qiskit.addItemListener((e) -> {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				listener.languageChosen(ELanguage.QISKIT);
			}
		});

		qsharp.addItemListener((e) -> {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				listener.languageChosen(ELanguage.QSHARP);
			}
		});

		add(menu2);

		JMenu menu3 = new JMenu("Options");
		add(menu3);
		JMenuItem pythonPath = new JMenuItem("Configure");
		menu3.add(pythonPath);
		pythonPath.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new OptionsFrame(optionListener.getOptions());				
			}
		});

		JMenu menu4 = new JMenu("Help");
		add(menu4);
		JMenuItem help = new JMenuItem("Help Contents");
		menu4.add(help);
		help.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					java.awt.Desktop.getDesktop().browse(new URI(URL_Readme));
				} catch (IOException | URISyntaxException ex) {
					ex.printStackTrace();
				}
			}
		});

		JMenuItem freeSW = new JMenuItem("Free Software Libraries");
		menu4.add(freeSW);
		freeSW.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String msg = "FUNCTION TIMEOUT\r\n"
						+ "Copyright (c) 2016, 2017 Tim Savannah All Rights Reserved\r\n\r\n"
						+ "https://github.com/kata198/func_timeout\r\n\r\n"
						+ "Licensed under the Lesser GNU Public License Version 3, LGPLv3.\r\n"
						+ "You should have recieved a copy of this with the source distribution as\r\n"
						+ "LICENSE, otherwise it is available at\r\n\r\n"
						+ "https://github.com/kata198/func_timeout/LICENSE";
				JFrame info = new JFrame();
				JOptionPane.showMessageDialog(info, msg);
			}
		});
	}

	/**
	 * Interface for the listener on language change.
	 * 
	 * @author Javier & Luis
	 *
	 */
	public interface LanguageListener {
		public void languageChosen(ELanguage qiskit);
	}

	/**
	 * Interface for the listener on reset button.
	 * 
	 * @author Javier & Luis.
	 *
	 */
	public interface ResetListener {
		public void reset();
	}
	
	public interface OptionsListener {
		public Option[] getOptions();
	}

}