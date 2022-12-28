package app;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class ProgramScanner {

	public static void main(String[] args) {
		File file = new File("c:\\tmp\\in.txt");

		Scanner sc = null;

		try {
			sc = new Scanner(file);
			while (sc.hasNextLine()) {
				System.out.println(sc.nextLine());
			}

		} catch (IOException e) {
			// TODO: handle exception
		} finally {
			if (sc != null) {
				sc.close();
			}
		}
	}

}
