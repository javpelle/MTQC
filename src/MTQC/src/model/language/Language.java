/**
 * This code is part of MTQC.
 * 
 * Copyright (c) 2020 Javier Pellejero, Luis Aguirre.
 * 
 * This code is licensed under the MIT License. You may obtain a copy 
 * of this license in the LICENSE file in the root directory of this source tree 
 * or at https://github.com/javpelle/MTQC/blob/master/LICENSE.
 */

package model.language;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import model.files.TestFile;
import model.mutant.Mutant;
import model.mutantoperator.MutantOperator;
import model.testing.Testing;
import model.testresult.TestResult;

/**
 * This class is used to generate all test files, run them, and collect the
 * results.
 * 
 * @author Javier & Luis
 *
 */
public abstract class Language {

	/**
	 * Directory used for all related python stuff.
	 */
	protected static final String path = "python";

	/**
	 * Main python script name
	 */
	protected static final String main = "main_mtqc.py";
	/**
	 * Token used to distinguish between internal prints, and method return print.
	 */
	protected static final String keyStart = "_mtqc_s";
	/**
	 * Token used to distinguish between internal prints, and method return print.
	 */
	protected static final String keyEnd = "_mtqc_e";

	/**
	 * Notify progress to Model.
	 */
	protected NotifyListener listener;

	/**
	 * Runs all posible combinations of test and mutants
	 * 
	 * @param mutantList List which contains all mutants.
	 * @param testSuit   List of all test to be used.
	 * @param test       Type of test to be used.
	 * @param method     Name of method to be tested.
	 * @param timeLimit  Limit of time each test can run.
	 * @return All test results gathered during execution.
	 */
	public ArrayList<ArrayList<TestResult>> run(ArrayList<Mutant> mutantList, ArrayList<String> testSuit, Testing test,
			String method, double timeLimit, NotifyListener listener) {
		this.listener = listener;
		ArrayList<ArrayList<TestResult>> ret;
		ArrayList<ArrayList<TestFile>> files = generateFiles(mutantList, testSuit, method);
		generatePythonScript(files, test, timeLimit);
		listener.notify("Files generated. Running...\n");
		ret = runMain(files, test);
		//deleteFiles(files);
		return ret;
	}

	/**
	 * Deletes all temporal files
	 * 
	 * @param files
	 */
	protected void deleteFiles(ArrayList<ArrayList<TestFile>> files) {
		for (ArrayList<TestFile> list : files) {
			for (TestFile t : list) {
				deleteFile(t.getCompletePath());
			}
		}
		deleteFile(path + File.separator + main);
	}

	/**
	 * Delets a file
	 * 
	 * @param file Name of the file to be deleted.
	 */
	protected void deleteFile(String file) {
		try {
			File f = new File(file); // file to be delete
			f.delete();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Runs main python script
	 * 
	 * @param files All files to be runned.
	 * @param test  Type of test.
	 * @return Results obtained from execution.
	 */
	protected ArrayList<ArrayList<TestResult>> runMain(ArrayList<ArrayList<TestFile>> files, Testing test) {
		try {
			Process p = Runtime.getRuntime().exec(pythonCall(path, main));
			BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
			return generateResults(in, files, test);
		} catch (IOException e) {

		}
		return null;
	}

	/**
	 * Dinamically creates the main python script.
	 * 
	 * @param files     All files needed to be exectued.
	 * @param test      Type of test.
	 * @param timeLimit Maximum time each file can run for.
	 */
	protected void generatePythonScript(ArrayList<ArrayList<TestFile>> files, Testing test, double timeLimit) {
		String script = generateImportLanguage();
		script += System.lineSeparator();
		script += "from tools import run_shots" + System.lineSeparator();
		for (ArrayList<TestFile> list : files) {
			for (TestFile t : list) {
				script += "import " + t.getFileName() + System.lineSeparator();
			}
		}
		script += System.lineSeparator();
		script += "if __name__ == '__main__':" + System.lineSeparator();
		for (ArrayList<TestFile> list : files) {
			for (TestFile t : list) {
				script += "\trun_shots(" + getMethodCall(t.getFileName()) + ", " + String.valueOf(timeLimit) + ", "
						+ String.valueOf(test.getShots()) + isQStateTest(test) + ")" + System.lineSeparator();
			}
		}
		writeFile(path + File.separator + main, script);
	}

	/**
	 * Checks if we are running a Probability Test on QSharp.
	 * 
	 * @param test Type of test.
	 * @return True if we are running a Probability Test on QSharp. False in other
	 *         case.
	 */
	protected abstract String isQStateTest(Testing test);

	/**
	 * Used to get the syntaxes used to import files for each language.
	 * 
	 * @return String which the correct syntaxes for importing a file.
	 */
	protected abstract String generateImportLanguage();

	/**
	 * "Glues" each mutant with an inicialization (Test)
	 * 
	 * @param mutantList List of mutants to be tested.
	 * @param testSuit   Lists of tests.
	 * @param method     Name of method to be tested.
	 * @return A list of all posible combinations of Mutants and Tests.
	 */
	protected ArrayList<ArrayList<TestFile>> generateFiles(ArrayList<Mutant> mutantList, ArrayList<String> testSuit,
			String method) {
		ArrayList<ArrayList<TestFile>> files = new ArrayList<ArrayList<TestFile>>();

		for (int i = 0; i < testSuit.size(); i++) {
			ArrayList<TestFile> aux = new ArrayList<TestFile>();
			aux.add(generateFile(mutantList.get(0).getOriginalCompletePath(), mutantList.get(0).getOriginalName(),
					testSuit.get(i), i, method, "original"));
			for (Mutant m : mutantList) {
				aux.add(generateFile(m.getMutantCompletePath(), m.getMutantFileName(), testSuit.get(i), i, method,
						m.getName()));
			}
			files.add(aux);
		}
		return files;
	}

	/**
	 * Dinamically generets a file given a mutant and a test.
	 * 
	 * @param completePath Complete path for the mutant.
	 * @param fileName     Name of file which contains the mutant.
	 * @param test         Test to be implemented.
	 * @param id_test      Test identifier.
	 * @param method       Name of method to be tested.
	 * @param mutantName   Name of the mutant.
	 * @return A TestFile which represents the "association" betweern a particular
	 *         mutant and a particular test.
	 */
	protected abstract TestFile generateFile(String completePath, String fileName, String test, int id_test,
			String method, String mutantName);

	/**
	 * Used to get the syntaxes used to call a method for each language.
	 * 
	 * @return String which the correct syntaxes for calling a method.
	 */
	protected abstract String getMethodCall(String file);

	/**
	 * Writes in a file.
	 * 
	 * @param fileName Name of the file where we need to write.
	 * @param content  Text we want to write on the file.
	 */
	protected void writeFile(String fileName, String content) {
		try {
			File file = new File(fileName);
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fileWriter = new FileWriter(file);
			fileWriter.write(content);
			fileWriter.close();
		} catch (Exception e) {
		}
	}

	/**
	 * Gets the correct shell commands for each OS.
	 * 
	 * @param path Directory where main python script is.
	 * @param file Name of the main python script.
	 * @return List of commands to be executed.
	 */
	protected String[] pythonCall(String path, String file) {
		if (System.getProperty("os.name").startsWith("Windows")) {
			// We are running this software in Windows OS
			// QSharp python script must run from the same path as the methods
			// it calls cuz
			// ...?
			return new String[] { "cmd.exe", "/c", "cd", path, "&&", "python", file, "&&", "cd", ".." };
		} else {
			return new String[] { "/bin/bash", "-c", "cd", path, "&&", "python", file, "&&", "cd", ".." };
		}
	}

	/**
	 * Reads the content from a file.
	 * 
	 * @param fileName Name of the file to be readed.
	 * @return The content of the file in the form of a String.
	 */
	protected String readFile(String fileName) {
		File originalFile = new File(fileName);
		String file = "";
		BufferedReader reader = null;

		try {
			reader = new BufferedReader(new FileReader(originalFile));
			String line = reader.readLine();

			while (line != null) {
				file = file + line + System.lineSeparator();
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {

		}
		return file;
	}

	/**
	 * Generates all the results from the execution.
	 * 
	 * @param in    Reader used to get the results from standard output.
	 * @param files List of all TestFiles where we will save the results.
	 * @param test  Type of test.
	 * @return List of all TestFiles for this execution.
	 */
	protected ArrayList<ArrayList<TestResult>> generateResults(BufferedReader in, ArrayList<ArrayList<TestFile>> files,
			Testing test) {
		ArrayList<ArrayList<TestResult>> results = new ArrayList<ArrayList<TestResult>>();
		for (ArrayList<TestFile> list : files) {
			ArrayList<TestResult> aux = new ArrayList<TestResult>();
			for (int t = 0; t < list.size(); t++) {
				TestResult tr = test.newTestResult(list.get(t).getMutantName(), list.get(t).getIdTest());
				for (int i = 0; i < test.getShots(); i++) {
					tr.setResult(readLine(in));
				}
				tr.make();
				aux.add(tr);
			}
			results.add(aux);
			listener.notify("Test number " + (list.get(0).getIdTest() + 1) + " finished\n");
		}
		return results;
	}

	/**
	 * Method used to only read the lines wanted from execution.
	 * 
	 * @param in Reader used to read from standard output.
	 * @return A line from stout in the form of a String.
	 */
	protected String readLine(BufferedReader in) {
		String line = null;
		try {
			do {
				line = in.readLine();
			} while (line != null && !line.startsWith(keyStart));

			String aux = in.readLine();
			while (aux != null && !aux.startsWith(keyEnd)) {
				line += aux;
				aux = in.readLine();
			}
		} catch (IOException e) {
			return "Error";
		}
		return line.substring(keyStart.length() + 1, line.length());
	}

	/**
	 * 
	 * @return List of Mutant Operators for a specific language
	 */
	public abstract MutantOperator[] getMutantOperators();

	/**
	 * Interface Notify Listener.
	 */
	public interface NotifyListener {
		public void notify(String msg);
	}

	/**
	 * 
	 * @return Input example String.
	 */
	public abstract String getInputExample(Testing testing, int shots, String methodName);

	/**
	 * @return language name String.
	 */
	public abstract String toString();

	/**
	 * 
	 * @return language extension String.
	 */
	public abstract String getExtension();

	/**
	 * Verify if a match is correct.
	 * 
	 * @param mutantOperator mutant operator.
	 * @param line           line integer.
	 * @param offset         position where match was found.
	 * @param searchWord     search word String.
	 * @return
	 */
	public abstract boolean verifyMatch(MutantOperator mutantOperator, String line, int offset, String searchWord);

	/**
	 * 
	 * @return Start method Token language String.
	 */
	public abstract String getStartMethodToken();

	/**
	 * 
	 * @return End method Token language String.
	 */
	public abstract String getEndMethodToken();
}
