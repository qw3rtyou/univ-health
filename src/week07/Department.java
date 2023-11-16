package week07;

import java.util.Scanner;

public class Department implements Factory {
	Scanner scan = new Scanner(System.in);
	Manager studentMgr = new Manager();
	static Manager LecMgr = new Manager();
	//static ArrayList<Lecture> lectureList = new ArrayList<>(); // 왜 static?

	void run() {
		LecMgr.readAll("lecture.txt", () -> new Lecture()); // 람다함수
//		LecMgr.readAll("lecture.txt", new Factory() {
//			@Override
//			public Manageable create() {
//				return new Student();
//			}
//		});
		// LecMgr.readAll("lecture.txt", new StFactory()); //훅킹메소드
		LecMgr.printAll();
		studentMgr.readAll("student.txt", this);
		studentMgr.readAll("student.txt", new StFactory() {
		});
		studentMgr.printAll();
		studentMgr.search(scan);
	}

	@Override
	public Manageable create() {
		return new Student();
	}

	abstract class StFactory implements Factory {
		public Manageable create() {
			return new Lecture();
		}
	};

	public static void main(String args[]) {
		Department my = new Department();
		my.run();
	}
}