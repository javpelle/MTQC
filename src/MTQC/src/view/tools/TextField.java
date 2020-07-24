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

import javax.swing.JTextField;

/**
 * Extension on JTextFiled used to forbid text edition by user.
 * 
 * @author Javier & Luis.
 *
 */
public class TextField extends JTextField {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for the class.
	 * 
	 * @param s String of the text needed to be showed.
	 */
	public TextField(String s) {
		super(s);
		setEditable(false);
	}

}
