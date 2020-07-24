/**
 * This code is part of MTQC.
 * 
 * Copyright (c) 2020 Javier Pellejero, Luis Aguirre.
 * 
 * This code is licensed under the MIT License. You may obtain a copy 
 * of this license in the LICENSE file in the root directory of this source tree 
 * or at https://github.com/javpelle/MTQC/blob/master/LICENSE.
 */

package exception;

/**
 * Null String type exception.
 * 
 * @author Javier & Luis
 *
 */
public class NullStringException extends Exception {

	/**
	 * Unique serial version identifier
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for this exeption
	 * 
	 * @param str Name of the non selected field
	 */
	public NullStringException(String str) {
		super("Chose a " + str + ".\n");
	}

}