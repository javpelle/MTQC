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
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.mutantoperator.MutantOperator;
import view.mutantgeneratorview.Files.NewPathListener;
import view.tools.LogArea;

/**
 * Tab used to generate mutants given a file and a list of mutant operators.
 * 
 * @author Javier & Luis
 *
 */
public class MutantsGenerator extends JPanel {

	private static final long serialVersionUID = 1L;

	private JButton generate;

	private Operators operators;

	private Files files;

	private LogArea log;

	/**
	 * Constructor for the class.
	 * 
	 * @param listener       Listener on path change.
	 * @param listenGenerate Listener on generate mutant button.
	 */
	public MutantsGenerator(NewPathListener listener, NewGenerateListener listenGenerate) {
		setLayout(new BorderLayout());

		files = new Files(listener);
		operators = new Operators();
		log = new LogArea();

		JPanel west = new JPanel(new GridLayout(2, 1));
		JPanel westAux = new JPanel(new BorderLayout());
		westAux.setBorder(BorderFactory.createTitledBorder("Log"));
		westAux.add(new JScrollPane(log));
		west.add(files);
		west.add(westAux);
		add(west, BorderLayout.WEST);

		add(operators, BorderLayout.EAST);

		generate = new JButton("Generate");
		generate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listenGenerate.generate(files.getSelectedFiles(), operators.getSelectedOperators());
			}
		});
		JPanel south = new JPanel();
		south.add(generate);
		add(south, BorderLayout.SOUTH);
	}

	/**
	 * Updates the view with the files in the new path.
	 * 
	 * @param fileList List of files in the new path.
	 */
	public void updatePath(ArrayList<String> fileList) {
		files.updatePath(fileList);
	}

	/**
	 * Updates the view with the operators available for a language.
	 * 
	 * @param mutantOperatorList List of new operators.
	 */
	public void updateOperators(MutantOperator[] mutantOperatorList) {
		operators.updateOperators(mutantOperatorList);
	}

	/**
	 * Interface Listener for generate button.
	 * 
	 * @author Javier & Luis.
	 *
	 */
	public interface NewGenerateListener {
		public void generate(ArrayList<String> files, ArrayList<MutantOperator> operators);
	}

	/**
	 * Notifies a message to the log screen.
	 * 
	 * @param msg Message to be showed.
	 */
	public void notify(String msg) {
		log.setMessage(msg);
	}

}
