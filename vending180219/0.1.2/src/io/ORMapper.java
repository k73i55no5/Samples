package vending180219.io;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public abstract class ORMapper {

	private static final String PATH = "logcard.txt";

	public List<String> readAllLines() {
		try {
			return Files.readAllLines(Paths.get(PATH), StandardCharsets.UTF_8);
		} catch (IOException e) { e.printStackTrace(); }
		return null;
	}

	public PrintWriter newPrintWriter() {
		try {
			return new PrintWriter(Files.newBufferedWriter(Paths.get(PATH), StandardCharsets.UTF_8));
		} catch (IOException e) { e.printStackTrace(); }
		return null;
	}
}
