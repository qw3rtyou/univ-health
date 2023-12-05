package health;

import java.util.Scanner;

import facade.UIData;
import mgr.Manageable;

public class Food implements Manageable, UIData {

	String name;
	String carbRatio;
	String proteinRatio;
	String fatRatio;

	public Food(String name, String carbRatio, String proteinRatio, String fatRatio) {
		this.name = name;
		this.carbRatio = carbRatio;
		this.proteinRatio = proteinRatio;
		this.fatRatio = fatRatio;
	}

	public Food() {
	}

	@Override
	public void set(Object[] uiTexts) {
		// TODO Auto-generated method stub

	}

	Double getCalRatio() {
		return (Double.parseDouble(carbRatio) * 4 + Double.parseDouble(proteinRatio) * 4
				+ Double.parseDouble(fatRatio) * 9) / 100;
	}

	@Override
	public String[] getUiTexts() {
		// TODO Auto-generated method stub
		String[] texts = new String[4];
		texts[0] = name;
		texts[1] = carbRatio;
		texts[2] = proteinRatio;
		texts[3] = fatRatio;
		return texts;
	}

	@Override
	public void read(Scanner scan) {
		// TODO Auto-generated method stub
		name = scan.next();
		carbRatio = scan.next();
		proteinRatio = scan.next();
		fatRatio = scan.next();

	}

	@Override
	public void print() {
		// TODO Auto-generated method stub
		System.out.format("%s %s %s %s", name, carbRatio, proteinRatio, fatRatio);
		System.out.println();
	}

	@Override
	public boolean matches(String kwd) {
		// TODO Auto-generated method stub
		if (name.contains(kwd))
			return true;
		return false;
	}

	public String toStringforFile() {
		name = name.trim();
		String buf = "";
		buf += name + " " + carbRatio + " " + proteinRatio + " " + fatRatio + "\n";
		return buf;
	}
}
