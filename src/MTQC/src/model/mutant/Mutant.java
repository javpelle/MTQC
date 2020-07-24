/**
 * This code is part of MTQC.
 * 
 * Copyright (c) 2020 Javier Pellejero, Luis Aguirre.
 * 
 * This code is licensed under the MIT License. You may obtain a copy 
 * of this license in the LICENSE file in the root directory of this source tree 
 * or at https://github.com/javpelle/MTQC/blob/master/LICENSE.
 */

package model.mutant;

import java.io.File;

/**
 * Represents a mutated file.
 * 
 * @author Javier & Luis
 *
 */
public class Mutant {

	/**
	 * Mutant name.
	 */
	private String name;

	/**
	 * Original file path.
	 */
	private String originalFilePath;

	/**
	 * Original file name.
	 */
	private String originalFileName;

	/**
	 * Mutant file path.
	 */
	private String mutantFilePath;

	/**
	 * Mutant file name.
	 */
	private String mutantFileName;

	/**
	 * Line where file was been modified.
	 */
	private int lineChanged;

	/**
	 * Class Constructor
	 * 
	 * @param name         Mutant Name.
	 * @param originalFile Original File Path.
	 * @param mutantFile   Mutant File Path.
	 * @param lineChanged  Line where file was been modified.
	 */
	public Mutant(String name, String originalFilePath, String originalFileName, String mutantFilePath,
			String mutantFileName, int lineChanged) {
		this.name = name;
		this.originalFilePath = originalFilePath;
		this.originalFileName = originalFileName;
		this.mutantFilePath = mutantFilePath;
		this.mutantFileName = mutantFileName;
		this.lineChanged = lineChanged;
	}

	/**
	 * Returns mutant name.
	 * 
	 * @return Mutant Name String.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns original File Path.
	 * 
	 * @return original File Path String.
	 */
	public String getOriginalCompletePath() {
		return originalFilePath + File.separator + originalFileName;
	}

	public String getOriginalName() {
		return originalFileName;
	}

	/**
	 * Returns mutant File Path.
	 * 
	 * @return mutant File Path String.
	 */
	public String getMutantCompletePath() {
		return mutantFilePath + File.separator + mutantFileName;
	}

	public String getMutantFileName() {
		return mutantFileName;
	}

	/**
	 * Returns line changed.
	 * 
	 * @return line changed integer.
	 */
	public int getLineChanged() {
		return lineChanged;
	}

	public String toString() {
		return name;
	}

}
