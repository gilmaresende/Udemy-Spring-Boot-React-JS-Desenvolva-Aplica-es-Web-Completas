package app;

import java.io.File;
import java.util.Scanner;

public class Pastas {	

	public static void main(String[] args) {
		Scanner cs = new Scanner(System.in);
		System.out.println("Enter a folder path: ");
		String strPath = cs.nextLine();

		File path = new File(strPath);

		File[] foldes = path.listFiles(File::isDirectory);

		System.out.println("Pastas");
		for (File folder : foldes) {
			System.out.println(folder);

		}
		
		File[] files = path.listFiles(File::isFile);

		System.out.println("Files");
		for (File file : files) {
			System.out.println(file);

		}
		
		boolean sucess = new File(strPath+ "\\subDid").mkdir();
		
		System.out.println(sucess);

		cs.close();
	}

}
