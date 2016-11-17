package net.shadowfacts.encode2png;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author shadowfacts
 */
public class Main {

	public static void main(String... args) throws IOException {
		String mode = "";
		boolean interactive = false;
		String input = "";
		String output = "";

		for (String s : args) {
			if (s.startsWith("-m")) {
				mode = s.split("=")[1].toLowerCase();
			} else if (s.startsWith("-I")) {
				interactive = Boolean.parseBoolean(s.split("=")[1]);
			} else if (s.startsWith("-i")) {
				input = s.split("=")[1];
			} else if (s.startsWith("-o")) {
				output = s.split("=")[1];
			}
		}

		if (interactive) {
			Scanner scanner = new Scanner(System.in);
			System.out.print("Input: ");
			input = scanner.nextLine();
			System.out.print("Output: ");
			output = scanner.nextLine();
		}

		switch (mode) {
			case "encode":
				Encode2PNG.encode(new File(input), new File(output));
				break;
			case "decode":
				Encode2PNG.decode(new File(input), new File(output));
				break;
			default:
				System.err.println("Unknown mode " + mode);
				break;
		}
	}

}
