/**
 * This code is part of MTQC.
 * 
 * Copyright (c) 2020 Javier Pellejero, Luis Aguirre.
 * 
 * This code is licensed under the MIT License. You may obtain a copy 
 * of this license in the LICENSE file in the root directory of this source tree 
 * or at https://github.com/javpelle/MTQC/blob/master/LICENSE.
 */

package view.mutantgeneratorview;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import view.tools.JTableCheck;

/**
 * File selection field on the MutantsGenerator tab.
 * 
 * @author Javier & Luis.
 *
 */
public class Files extends JPanel {

	private static final long serialVersionUID = 1L;

	private final static Object[] column = { "", "File" };

	private JPanel center;

	private JTableCheck<String> table;

	private JTextArea path;

	private JButton all;

	private JButton none;

	private JButton newPath;

	private JButton selectPath;

	/**
	 * Constructor for the class.
	 * 
	 * @param listener Listener for the change of path.
	 */
	public Files(NewPathListener listener) {
		setLayout(new BorderLayout());

		createCenterPanel();

		createSouthPanel(listener);

	}

	/**
	 * Method which creates the center panel.
	 */
	private void createCenterPanel() {
		center = new JPanel();
		center.setLayout(new BorderLayout());
		table = new JTableCheck<String>(column);
		all = new JButton("All");
		none = new JButton("None");
		JPanel centerSouth = new JPanel();
		centerSouth.add(all);
		centerSouth.add(none);
		center.add(new JScrollPane(table), BorderLayout.CENTER);
		center.add(centerSouth, BorderLayout.SOUTH);
		add(center, BorderLayout.CENTER);

		all.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.setAllTrue();
			}
		});

		none.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.setAllFalse();
			}
		});
	}

	/**
	 * Creates the south panel.
	 * 
	 * @param listener Listener for the change of path.
	 */
	private void createSouthPanel(NewPathListener listener) {
		JPanel south = new JPanel();
		south.setBorder(BorderFactory.createTitledBorder("Current path"));
		south.setLayout(new BorderLayout());

		path = new JTextArea(System.getProperty("user.dir"));
		selectPath = new JButton("Select Path");
		newPath = new JButton("Update Path");

		JPanel aux = new JPanel();
		aux.add(newPath);

		south.add(new JScrollPane(path), BorderLayout.CENTER);
		south.add(selectPath, BorderLayout.EAST);
		south.add(aux, BorderLayout.SOUTH);
		add(south, BorderLayout.SOUTH);

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

		newPath.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listener.updatePath(path.getText());
			}
		});

		selectPath.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fileChooser.setCurrentDirectory(new File(path.getText()));
				int result = fileChooser.showDialog(null, "Seleccionar");
				if (result == JFileChooser.APPROVE_OPTION) {
					path.setText(fileChooser.getSelectedFile().getAbsolutePath());
				}
			}
		});
	}

	/**
	 * Updates the files in the new path.
	 * 
	 * @param files List of files in the new path.
	 */
	public void updatePath(ArrayList<String> files) {
		table.clear();
		for (int i = 0; i < files.size(); ++i) {
			table.addRow(new Object[] { false, files.get(i) });
		}
	}

	/**
	 * Interface for the listener on path change.
	 * 
	 * @author Javier & Luis
	 *
	 */
	public interface NewPathListener {
		public void updatePath(String path);
	}

	/**
	 * Used to get the selected files.
	 * 
	 * @return A list with the name of selected files.
	 */
	public ArrayList<String> getSelectedFiles() {
		return table.getTrueRows();
	}

}
