/**
 * This code is part of MTQC.
 * 
 * Copyright (c) 2020 Javier Pellejero, Luis Aguirre.
 * 
 * This code is licensed under the MIT License. You may obtain a copy 
 * of this license in the LICENSE file in the root directory of this source tree 
 * or at https://github.com/javpelle/MTQC/blob/master/LICENSE.
 */

package view.tools;

import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JList;

import model.mutant.Mutant;

/**
 * Extension of JList used to show list of mutants.
 * 
 * @author Javier & Luis
 *
 */
public class JMutantList extends JList<Mutant> {

	private static final long serialVersionUID = 1L;

	private DefaultListModel<Mutant> model;

	/**
	 * Empty constructor.
	 */
	public JMutantList() {
		super(new DefaultListModel<Mutant>());
		model = (DefaultListModel<Mutant>) getModel();
	}

	/**
	 * Adds mutants to the list.
	 * 
	 * @param mutants List of new mutants.
	 */
	public void setMutants(ArrayList<Mutant> mutants) {
		for (int i = 0; i < mutants.size(); i++) {
			model.addElement(mutants.get(i));
		}
	}

	/**
	 * Clears the list of mutants.
	 */
	public void clear() {
		model.clear();
	}

	/**
	 * Gets a mutant
	 * 
	 * @param index Index for the wanted mutant.
	 * @return The selected mutant.
	 */
	public Mutant getMutant(int index) {
		return model.get(index);
	}

}
