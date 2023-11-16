package univHealth;

import java.util.Scanner;

public class AerobicExercise extends Exercise implements Manageable {
	@Override
	public String toString() {
		String buf = "";
		buf += "이름 : " + name + "(유산소)";
		buf += "\nMETs(운동강도) : " + mets + "\n";
		return buf;
	}

	@Override
	public void read(Scanner scan) {
		String line = scan.nextLine();
		String[] parts = line.split(" ");
		name = parts[1];
		mets = Double.parseDouble(parts[2]);
	}

	@Override
	public boolean matches(String kwd) {
		return kwd.equals(name);
	}
}