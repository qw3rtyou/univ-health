package univHealth;

import java.util.Scanner;

public class AnaerobicExercise extends Exercise implements Manageable {
	protected String part;

	@Override
	public String toString() {
		String buf = "";
		buf += "이름 : " + name + "(무산소)";
		buf += "\nMETs(운동강도) : " + mets + "\n";
		return buf;
	}

	public void read(Scanner scan) {
		String line = scan.nextLine();
		String[] parts = line.split(" ");
		name = parts[1];
		mets = Double.parseDouble(parts[2]);
		part = parts[3];
	}

	@Override
	public boolean matches(String kwd) {
		return false;
	}
}