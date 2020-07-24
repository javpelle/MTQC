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

import javax.swing.JTextArea;

/**
 * Log screen used to display messages.
 * 
 * @author Javier & Luis
 *
 */
public class LogArea extends JTextArea {

	private static final long serialVersionUID = 1L;

	/**
	 * Empty constructor.
	 */
	public LogArea() {
		setEditable(false);
	}

	/**
	 * Displays a message.
	 * 
	 * @param msg Message.
	 */
	public void setMessage(String msg) {
		append(msg);
	}

	/**
	 * Cleans the log.
	 */
	public void clear() {
		setText("");
	}

}
