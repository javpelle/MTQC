/**
 * This code is part of MTQC.
 * 
 * Copyright (c) 2020 Javier Pellejero, Luis Aguirre.
 * 
 * This code is licensed under the MIT License. You may obtain a copy 
 * of this license in the LICENSE file in the root directory of this source tree 
 * or at https://github.com/javpelle/MTQC/blob/master/LICENSE.
 */

package model.files;

import java.io.File;

/**
 * Represents a file obtained from applying a test to a mutant file.
 * 
 * @author Javier & Luis
 *
 */
public class TestFile {

	/**
	 * Name of associated mutant
	 */
	private String mutantName;

	/**
	 * Test identifier
	 */
	private int idTest;

	/**
	 * Path which contains the file
	 */
	private String path;

	/**
	 * File name without extension
	 */
	private String fileName;

	/**
	 * Complete name of the file (includes extension)
	 */
	private String completeName;

	/**
	 * Constructor for class
	 * 
	 * @param mutantName Name of the mutant
	 * @param idTest     Test identifier
	 * @param path       Path for the file
	 * @param fileName   Complete name of the file(with extension)
	 */
	public TestFile(String mutantName, int idTest, String path, String fileName) {
		this.mutantName = mutantName;
		this.idTest = idTest;
		this.path = path;
		this.fileName = fileName.substring(0, fileName.length() - 3);
		completeName = fileName;
	}

	/**
	 * Get the name of the file
	 * 
	 * @return String with the name of the file of this test
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * Get the relative path of the file
	 * 
	 * @return String with the path and name of the file
	 */
	public String getCompletePath() {
		return path + File.separator + completeName;
	}

	/**
	 * Get the name for the mutant associated to this object
	 * 
	 * @return String with the name of the mutant
	 */
	public String getMutantName() {
		return mutantName;
	}

	/**
	 * Get the test identifier
	 * 
	 * @return Integer which identifies a test
	 */
	public int getIdTest() {
		return idTest;
	}
}
