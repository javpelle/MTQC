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
 * Empty list type exception.
 * 
 * @author Javier & Luis
 *
 */
public class EmptyListException extends Exception {

	/**
	 * Unique serial version identifier
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for this exception
	 * 
	 * @param list Name of the empty list
	 */
	public EmptyListException(String list) {
		super(list + " can not be an empty list.\n");
	}

}