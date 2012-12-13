import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import data.reader;
public class main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFileChooser fileopen = new JFileChooser();
	    FileFilter filter = new FileNameExtensionFilter("c files", "c");
	    fileopen.addChoosableFileFilter(filter);

	    int ret = fileopen.showDialog(null, "Open file");

	    if (ret == JFileChooser.APPROVE_OPTION) {
	      File file = fileopen.getSelectedFile();
	      System.out.println(file);
	      reader.readIn(file.toString());
	    }
		//String URL = "Zweiproduktproblem.txt";		
	}

}
