import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class HC2018 {
    private static final String FILE_NAME = null; // TODO mudar
    private static final String new_line = System.getProperty("line.separator");

    public void readFile(File file) throws FileNotFoundException {
	Scanner scn = new Scanner(file);
	for (int line_index = 0; scn.hasNextLine(); line_index++) {
	    // TODO
	}

	scn.close();
    }

    public void escreveFicheiro() throws IOException {
	String s = "";

	// TODO

	File file = new File("resultado_" + FILE_NAME + ".in");
	file.createNewFile();
	try {
	    BufferedWriter out = new BufferedWriter(new FileWriter(file.getAbsolutePath()));
	    out.write(s);
	    out.close();
	} catch (Exception e) {
	    System.out.println(e);
	}
    }

    public static void main(String[] args) {

    }
}
