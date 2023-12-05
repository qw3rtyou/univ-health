package health;

import mgr.Manageable;

import java.util.ArrayList;
import java.util.Scanner;

import facade.UIData;

public class Exercise implements Manageable, UIData {
	String type;
	String name;
	Double mets;
	String part;

	public Exercise() {
	}


	public String getType() {
		return type;
	}

	public Exercise(String type, String name, Double mets, String part) {

		this.type = type;
		this.name = name;
		this.mets = mets;
		this.part = part;
	}

	@Override
	public void read(Scanner scan) {
		// TODO Auto-generated method stub
		type = scan.next();
		if (type.equals("유산소")) {
			name = scan.next();
			mets = scan.nextDouble();
			part = "";
		}
		if (type.equals("무산소")) {
			name = scan.next();
			mets = scan.nextDouble();
			part = scan.next();
		}

	}

	@Override
	public void print() {
		// TODO Auto-generated method stub
		System.out.format("[%s] %S - Mets(운동강도) : %.4f %s \n", type, name, mets, part);
		System.out.println();
	}

	@Override
	public boolean matches(String kwd) {
		// TODO Auto-generated method stub
		if (name.contains(kwd))
			return true;
		if (kwd.equals("무산소") && type.equals("무산소"))
			return true;
		if (kwd.equals("유산소") && type.equals("유산소"))
			return true;
		return false;
	}

	@Override
	public void set(Object[] uiTexts) {
		// TODO Auto-generated method stub

	}

	@Override
	public String[] getUiTexts() {
		// TODO Auto-generated method stub
		String[] texts = new String[4];
		texts[0] = type;
		texts[1] = name;
		texts[2] = "" + mets;
		texts[3] = part;
		return texts;
	}
	public String getName() {
		return name;
	}

	public String toStringforFile() {
		name = name.trim();
		String buf = "";
		if (type.equals("유산소")) {
			buf += type + " " + name + " " + mets + "\n";
		} else {
			buf += type + " " + name + " " + mets + " " + part + "\n";
		}
		return buf;
	}

}
