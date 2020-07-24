/**
 * This code is part of MTQC.
 * 
 * Copyright (c) 2020 Javier Pellejero, Luis Aguirre.
 * 
 * This code is licensed under the MIT License. You may obtain a copy 
 * of this license in the LICENSE file in the root directory of this source tree 
 * or at https://github.com/javpelle/MTQC/blob/master/LICENSE.
 */

package view.mutantsviewer;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.FileReader;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import model.mutant.Mutant;
import view.tools.TextField;

/**
 * Shows the content of file and a mutant.
 * 
 * @author Javier & Luis
 *
 */
public class FileArea extends JPanel {

	private static final long serialVersionUID = 1L;

	private JTextArea originalArea;

	private JTextArea mutantArea;

	private TextField line;

	/**
	 * Empty constructor for the class.
	 */
	public FileArea() {
		setLayout(new BorderLayout());

		createNorthPanel();

		JPanel center = new JPanel();
		center.setLayout(new GridLayout(2, 1));
		center.add(createOriginal());
		center.add(createMutant());
		add(center, BorderLayout.CENTER);

	}

	/**
	 * Creates north panel of the view.
	 */
	private void createNorthPanel() {
		line = new TextField("Line...");
		add(line, BorderLayout.NORTH);
	}

	/**
	 * Creates the panel which shows the content in the original file.
	 * 
	 * @return JPanel for the original file.
	 */
	private JPanel createOriginal() {
		JPanel original = new JPanel();
		original.setLayout(new BorderLayout());
		originalArea = new JTextArea();
		originalArea.setEditable(false);
		original.setBorder(BorderFactory.createTitledBorder("Original"));
		original.add(new JScrollPane(originalArea), BorderLayout.CENTER);
		return original;
	}

	/**
	 * Creates the panel which shows the content in the mutant file.
	 * 
	 * @return JPanel for the mutant file.
	 */
	private JPanel createMutant() {
		JPanel mutant = new JPanel();
		mutant.setLayout(new BorderLayout());
		mutantArea = new JTextArea();
		mutantArea.setEditable(false);
		mutant.setBorder(BorderFactory.createTitledBorder("Mutant"));
		mutant.add(new JScrollPane(mutantArea), BorderLayout.CENTER);
		return mutant;
	}

	/**
	 * Updates the content in a mutant file.
	 * 
	 * @param mutant Mutant to be showed.
	 */
	public void updateMutant(Mutant mutant) {
		line.setText("Line " + mutant.getLineChanged());
		originalArea.setText(readFile(mutant.getOriginalCompletePath()));
		mutantArea.setText(readFile(mutant.getMutantCompletePath()));
	}

	/**
	 * Read the content of a file.
	 * 
	 * @param file Name of the file it needs to read.
	 * @return String with the content of the file.
	 */
	private String readFile(String file) {
		int lineCounter = 1;
		String aux = "";
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = reader.readLine();

			while (line != null) {
				aux = aux + lineCounter + "\t" + line + System.lineSeparator();
				line = reader.readLine();
				++lineCounter;
			}
			reader.close();
		} catch (Exception e) {
			JFrame error = new JFrame();
			JOptionPane.showMessageDialog(error, e.getMessage());
		}
		return aux;
	}

}
