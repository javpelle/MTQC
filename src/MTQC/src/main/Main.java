/**
 * This code is part of MTQC.
 * 
 * Copyright (c) 2020 Javier Pellejero, Luis Aguirre.
 * 
 * This code is licensed under the MIT License. You may obtain a copy 
 * of this license in the LICENSE file in the root directory of this source tree 
 * or at https://github.com/javpelle/MTQC/blob/master/LICENSE.
 */

package main;

import control.Controller;
import model.Model;
import view.UI;

/**
 * Main method for program. Runs Model, Controller and UI.
 * 
 * @author Javier & Luis
 *
 */
public class Main {

	/**
	 * Main method. Runs View, Controller and Model modules.
	 * 
	 * @param args Not used
	 */
	public static void main(String[] args) {
		Model model = new Model();
		Controller c = new Controller(model);
		new UI(c, model);
		c.start();
	}

}
