package univHealth;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Manager {
	ArrayList<Manageable> mList = new ArrayList<>();

	Scanner openFile(String filename) {
		Scanner filein = null;
		try {
			filein = new Scanner(new File(filename));
		} catch (IOException e) {
			System.out.println("파일 입력 오류");
			System.exit(0);
		}
		return filein;
	}

	public void readAll(String filename, Factory factory) {
		Scanner filein = openFile(filename);
		Manageable m = null;
		while (filein.hasNext()) {
			m = factory.create(filein);
			m.read(filein);
			mList.add(m);
		}
		filein.close();
	}

	void printAll() {	
		for (Manageable m : mList) {
			System.out.println(m.toString());
		}
	}

	void search(Scanner scan) {
		String kwd = null;
		while (true) {
			System.out.print(">> ");
			kwd = scan.next();
			if (kwd.equals("end"))
				break;
			for (Manageable m : mList) {
				if (m.matches(kwd))
					System.out.println(m.toString());
			}
		}
	}

	public Manageable find(String kwd) {
		for (Manageable m : mList) {
			if (m.matches(kwd))
				return m;
		}
		return null;
	}
	
	public ArrayList<Manageable> getmList() {
		return mList;
	}
}
