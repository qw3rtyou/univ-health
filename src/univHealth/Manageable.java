package univHealth;

import java.util.Scanner;

interface Manageable {

	public void read(Scanner scan);
	//public void print();	toString으로 대체
	boolean matches(String kwd);
}
