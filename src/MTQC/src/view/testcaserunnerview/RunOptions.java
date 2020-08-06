/**
 * This code is part of MTQC.
 * 
 * Copyright (c) 2020 Javier Pellejero, Luis Aguirre.
 * 
 * This code is licensed under the MIT License. You may obtain a copy 
 * of this license in the LICENSE file in the root directory of this source tree 
 * or at https://github.com/javpelle/MTQC/blob/master/LICENSE.
 */

package view.testcaserunnerview;

import java.awt.GridLayout;

import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import model.testing.Testing;
import view.tools.TextField;

/**
 * Panel with all the different paraments for running the tests.
 * 
 * @author Javier & Luis.
 *
 */
public class RunOptions extends JPanel {

	private static final long serialVersionUID = 1L;

	private JComboBox<String> files;

	private JComboBox<String> methods;

	private JSpinner spinner;

	private JComboBox<Testing> testType;

	private JSpinner shots;

	/**
	 * Constructor for the class.
	 * 
	 * @param listenerCombo Listener for the selected file field.
	 */
	public RunOptions(FileComboListener fileComboListener) {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		fileCombo(fileComboListener);
		methodCombo(fileComboListener);
		timeLimit();
		testType(fileComboListener);
	}

	/**
	 * Generates the file field.
	 * 
	 * @param listener Listener for this field.
	 */
	private void fileCombo(FileComboListener listener) {
		JPanel aux = new JPanel();
		aux.setLayout(new GridLayout(1, 2));
		aux.add(new TextField("File:"));
		files = new JComboBox<String>();
		files.addPopupMenuListener(new PopupMenuListener() {

			public void popupMenuCanceled(PopupMenuEvent arg0) {
			}

			public void popupMenuWillBecomeInvisible(PopupMenuEvent arg0) {
				if (files.getSelectedItem() != null) {
					listener.refreshMethods((String) files.getSelectedItem());
					listener.updateTesting(getTestType(), getShots(), getMethodName());
				}
			}

			public void popupMenuWillBecomeVisible(PopupMenuEvent arg0) {
				listener.refreshPath();
			}
		});
		aux.add(files);

		add(aux);
	}

	/**
	 * Generates the method field.
	 */
	private void methodCombo(FileComboListener listener) {
		JPanel aux = new JPanel();
		aux.setLayout(new GridLayout(1, 2));
		aux.add(new TextField("Method:"));
		methods = new JComboBox<String>();
		methods.addPopupMenuListener(new PopupMenuListener() {

			public void popupMenuCanceled(PopupMenuEvent arg0) {
			}

			public void popupMenuWillBecomeInvisible(PopupMenuEvent arg0) {
				listener.updateTesting(getTestType(), getShots(), getMethodName());
			}

			public void popupMenuWillBecomeVisible(PopupMenuEvent arg0) {
				
			}
		});
		aux.add(methods);
		add(aux);
	}

	/**
	 * Generates the timeLimit field.
	 */
	private void timeLimit() {
		JPanel aux = new JPanel();
		aux.setLayout(new GridLayout(1, 2));
		aux.add(new TextField("Time Limit (seconds):"));
		spinner = new JSpinner(new SpinnerNumberModel(3.0, 0.1, 100.0, 0.1));
		aux.add(spinner);
		add(aux);
	}

	/**
	 * Generates the TestType field.
	 */
	public void testType(FileComboListener listener) {
		JPanel aux1 = new JPanel();
		aux1.setLayout(new GridLayout(1, 2));
		aux1.add(new TextField("Testing Type:"));
		testType = new JComboBox<Testing>();
		aux1.add(testType);
		JPanel aux2 = new JPanel();
		aux2.setLayout(new GridLayout(1, 2));
		aux2.add(new TextField("Shots:"));
		shots = new JSpinner(new SpinnerNumberModel(10, 1, 10000, 1));
		shots.setEnabled(false);
		shots.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent arg0) {
				listener.updateTesting(getTestType(), getShots(), getMethodName());
			}
		
		
		});
		aux2.add(shots);
		testType.addPopupMenuListener(new PopupMenuListener() {

			public void popupMenuCanceled(PopupMenuEvent arg0) {
			}

			public void popupMenuWillBecomeInvisible(PopupMenuEvent arg0) {
				if (testType.getSelectedIndex() == 0) {
					shots.setEnabled(false);
				} else {
					shots.setEnabled(true);
				}
				listener.updateTesting(getTestType(), getShots(), getMethodName());
			}

			public void popupMenuWillBecomeVisible(PopupMenuEvent arg0) {
			}
		});
		add(aux1);
		add(aux2);
	}

	/**
	 * Refresh file field.
	 * 
	 * @param files List of new files.
	 */
	public void refreshFileCombo(ArrayList<String> files) {
		this.files.removeAllItems();
		this.files.addItem(null);
		for (int i = 0; i < files.size(); i++) {
			this.files.addItem(files.get(i));
		}
	}

	/**
	 * Updates method field.
	 * 
	 * @param fileMethods List of methods declared in the selected file.
	 */
	public void updateFileMethods(ArrayList<String> fileMethods) {
		this.methods.removeAllItems();
		for (int i = 0; i < fileMethods.size(); i++) {
			this.methods.addItem(fileMethods.get(i));
		}

	}

	/**
	 * Interface Listener for file selection.
	 * 
	 * @author Javier & Luis
	 *
	 */
	public interface FileComboListener {
		public void refreshPath();

		public void refreshMethods(String fileName);
		
		public void updateTesting(Testing testing, int shots, String methodName);
	}

	/**
	 * Gets the type of test selected.
	 * 
	 * @return Selected test type.
	 */
	public Testing getTestType() {
		return (Testing) testType.getSelectedItem();
	}

	/**
	 * Gets the number of shots.
	 * 
	 * @return Number of shots.
	 */
	public int getShots() {
		return (int) shots.getValue();
	}

	/**
	 * Sets the different type of test available.
	 * 
	 * @param tests List with all the available test types.
	 */
	public void setTests(Testing[] tests) {
		testType.removeAllItems();
		for (Testing t : tests) {
			testType.addItem(t);
		}
	}

	/**
	 * Gets the time limit for a test to run.
	 * 
	 * @return Time limit.
	 */
	public double getTimeLimit() {
		return (double) spinner.getValue();
	}

	/**
	 * Gets the name of the method wanted to be tested.
	 * 
	 * @return Name of the method.
	 */
	public String getMethodName() {
		if (methods.getSelectedItem() == null) {
			return null;
		}
		return methods.getSelectedItem().toString();
	}

	/**
	 * Gets the name of the selected file.
	 * 
	 * @return Name of the selected file.
	 */
	public String getFileName() {
		if (files.getSelectedItem() == null) {
			return null;
		}
		return files.getSelectedItem().toString();
	}

}
