package univHealth;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/*
GUI 구현

루틴이라는 개념을 어떻게 사용할 것인지 고민
루틴 추천 알고리즘(가장 최근 운동 부위 제외 후 빈도 수 가장 낮은 부위 운동 추천 예정) + 중량 추천
음식 추천 알로리즘(목표 중량, 가장 최근 섭취 음식 기반 추천 예정)
하루일과 수정, 삭제 기능

후순위
개발 마무리 후 필요없는 getter setter 제거
접근제어자 체크
입력값 검증 or trycatch 적용
사용자 정의 운동 추가 기능 구현 
사용자 정의 음식 추가 기능 구현
저장 기능 구현(사용자 정의 기능이 추가되면 기본 데이터도 수정 가능)
추천 알고리즘 개선
*/

/*
 * 처음에 중량, 몇 세트, 한 횟수 등을 고려하여 칼로리 계산을 하자는 의견이 있었는데,
 * 검색해보니 해당 값들을 이용해 칼로리 계산을 하는건 현실적으로 매우 어렵다고 함
 * 만약 위와 같은 값들로 칼로리 계산을 하려고 하면
 * 1. 위의 요인들을 고려한 계산식이 필요
 * 2. 해당 계산식을 활용하여 다양한 운동했을 때 나온 칼로리 데이터가 필요
 * 둘 다 검색해도 없어서 그냥 시간 당 대략적인 칼로리 소모로 계획 변경
 */

public class Main {
	static Manager userManager = new Manager();
	static Manager foodManager = new Manager();
	static Manager exerciseManager = new Manager();
	static User currentUser;

	final String FILE_PATH_USER = "user_data.txt";
	final String FILE_PATH_FOOD = "food_data.txt";
	final String FILE_PATH_EXERCISE = "exercise_data.txt";
	final String FILE_PATH_USERFOOD = "user_food_data.txt";
	final String FILE_PATH_USEREXERCISE = "user_exercise_data.txt";

	public static void main(String[] args) {
		Main main = new Main();
		main.start();
	}

	void test() {
		System.out.println("\n\n\n\n\n1. 모든 파일음식 출력");
		foodManager.printAll();

		System.out.println("\n\n\n\n\n2. 모든 파일운동 출력");
		exerciseManager.printAll();

		System.out.println("\n\n\n\n\n3. 모든 파일유저 출력");
		userManager.printAll();

		System.out.println("\n\n\n\n\n4. 현재 유저 출력");
		System.out.println(currentUser);
	}

	void init() {
		userManager.readAll(FILE_PATH_USER, new Factory() {

			@Override
			public Manageable create(Scanner scanner) {
				return new User();
			}
		});
		foodManager.readAll(FILE_PATH_FOOD, new Factory() {
			@Override
			public Manageable create(Scanner scanner) {
				return new Food();
			}
		});

		exerciseManager.readAll(FILE_PATH_EXERCISE, new Factory() {
			@Override
			public Manageable create(Scanner scanner) {
				if (scanner.next().equals("무산소")) {
					return new AnaerobicExercise();
				}
				return new AerobicExercise();
			}
		});

		// 두 개 파일도 Manager를 통해 관리하려면, Manager를 관리하는 인터페이스가 필요함(별개의 Manager가 필요)
		loadDailyFoodFromFile(FILE_PATH_USERFOOD);
		loadDailyExerciseFromFile(FILE_PATH_USEREXERCISE);
	}

	void start() {
		init();

		Scanner scanner = new Scanner(System.in);
		currentUser = new User();//

		while (true) {
			menu();
			switch (scanner.nextInt()) {
			case 0: // 추후 사용법이나 관리자 매뉴 등으로 바뀔 예정
				test();
				break;

			case 1:
				userLoop(scanner);
				break;

			case 2:
				if (currentUser.getName().contentEquals("아직 설정되지 않음"))
					System.out.println("사용자 설정을 해야 함!");
				else
					commitLoop(scanner);
				break;

			case 3:
				if (currentUser.getName().contentEquals("아직 설정되지 않음"))
					System.out.println("사용자 설정을 해야 함!");
				else
					recommend();
				break;

			case 4:
				saveCurrentState();
				break;

			case 5:
				scanner.close();
				System.out.println("프로그램 정상 종료...");
				return;

			default:
				System.out.println("유효하지 않은 입력값");
				break;
			}
		}
	}

	void menu() {
		System.out.println("===Health!!===");
		System.out.println("현재 사용자 : " + currentUser.getName());
		System.out.println("1. 사용자 메뉴");
		System.out.println("2. 기록 메뉴");
		System.out.println("3. 루틴&식단 추천받기");
		System.out.println("4. 저장하기");
		System.out.println("5. 종료");
	}

	void userMenu() {
		System.out.println("===사용자 매뉴===");
		System.out.println("현재 사용자 : " + currentUser.getName());
		System.out.println("1. 현재 계정 변경");
		System.out.println("2. 사용자 등록");
		System.out.println("3. 사용자 정보 수정");
		System.out.println("4. 사용자 삭제");
		System.out.println("5. 사용자 정보 출력");
		System.out.println("6. 돌아가기");
	}

	void commitMenu() {
		System.out.println("===기록 매뉴===");
		System.out.println("현재 사용자 : " + currentUser.getName());
		System.out.println(currentUser);
		System.out.println("1. 기록 입력하기");
		System.out.println("2. 오늘(최근) 기록 열람");
		System.out.println("3. 나의 모든 기록 열람");
		System.out.println("4. 특정 날짜 기록 열람");
		System.out.println("5. 돌아가기");
	}

	void userLoop(Scanner scanner) {
		User user;
		String name;
		Double height;
		Double weight;
		String gender;
		int goal;

		while (true) {
			userMenu();
			switch (scanner.nextInt()) {
			case 1:
				System.out.print("사용자 이름 : ");
				user = (User) userManager.find(scanner.next());
				if (user == null)
					System.out.println("사용자를 찾을 수 없습니다");
				else
					currentUser = user;
				break;

			case 2:
				System.out.print("사용자 이름 : ");
				name = scanner.next();
				System.out.print("사용자 키 : ");
				height = scanner.nextDouble();
				System.out.print("사용자 몸무게 : ");
				weight = scanner.nextDouble();
				System.out.print("사용자 성별 : ");
				gender = scanner.next();
				System.out.print("사용자 목표 체중 : ");
				goal = scanner.nextInt();
				userManager.mList.add(new User(name, height, weight, gender, goal));
				System.out.println("계정 생성 성공");
				break;

			case 3:
				System.out.print("사용자 이름 : ");
				user = (User) userManager.find(scanner.next());
				if (user == null)
					System.out.println("사용자를 찾을 수 없습니다");
				else {
					System.out.println(user);
					System.out.print("사용자 이름 : ");
					user.setName(scanner.next());
					System.out.print("사용자 키 : ");
					user.setHeight(scanner.nextDouble());
					System.out.print("사용자 몸무게 : ");
					user.setWeight(scanner.nextDouble());
					System.out.print("사용자 성별 : ");
					user.setGender(scanner.next());
					System.out.print("사용자 목표 체중 : ");
					user.setGoal(scanner.nextInt());
					System.out.println("계정 수정 성공");
				}
				break;
			case 4:
				System.out.print("사용자 이름 : ");
				user = (User) userManager.find(scanner.next());
				if (user == null)
					System.out.println("사용자를 찾을 수 없습니다");
				else
					userManager.mList.remove(user);
				break;

			case 5:
				System.out.println(currentUser);
				break;

			case 6:
				return;

			default:
				System.out.println("유효하지 않은 입력값");
				break;
			}
		}
	}

	void commitLoop(Scanner scanner) {
		commitMenu();
		while (true) {
			switch (scanner.nextInt()) {

			case 1:
				inputCommit(scanner);
				break;

			case 2:
				System.out.println(currentUser.getCurrentDailyInfo());
				break;

			case 3:
				System.out.println(currentUser.toStringDetail());
				break;

			case 4:
				Date keyDate = new Date(scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
				System.out.println(currentUser.findDaily(keyDate).toStringDetail());
				break;

			case 5:
				return;

			default:
				break;
			}
		}
	}

	void inputCommit(Scanner scanner) {
		System.out.println("현재 사용자로 입력을 시작합니다.(맞으면 y 입력)");

		if (!scanner.next().contentEquals("y"))
			return;

		int num;
		int year;
		int month;
		int day;
		String foodName;
		int amount;
		String exerciseName;
		int time;

		System.out.println("===일일 입력===");
		System.out.print("기록날짜(yyyy mm dd) : ");

		year = scanner.nextInt();
		month = scanner.nextInt();
		day = scanner.nextInt();
		Date date = new Date(year, month, day);

		for (DailyInfo dailyInfo : currentUser.getDailyInfos()) {
			if (dailyInfo.getDate().equals(date)) {
				System.out.println("이미 해당 날짜에 기록된 내용이 있음");
				return;
			}
		}

		DailyInfo newDailyInfo = new DailyInfo(new Date(year, month, day));
		UserFood newFood;
		UserExercise newExercise;

		System.out.print("식사 횟수 : ");
		num = scanner.nextInt();
		for (int i = 0; i < num; i++) {
			System.out.print(i + 1 + "번째 음식명 : ");
			foodName = scanner.next();
			Food food = (Food) foodManager.find(foodName);
			if (food == null) {
				i--;
				System.out.println("미등록 음식 - 유사한 다른 음식을 선택해 주세요");
				continue;
			}

			System.out.print("먹은 양(g) : ");
			amount = scanner.nextInt();
			newFood = new UserFood(food, amount);
			newDailyInfo.addFoodEaten(newFood);
		}

		System.out.print("운동 종류 개수 : ");
		num = scanner.nextInt();
		for (int i = 0; i < num; i++) {
			System.out.print(i + 1 + "번째 운동 이름 : ");
			exerciseName = scanner.next();
			Exercise exercise = (Exercise) exerciseManager.find(exerciseName);
			if (exercise == null) {
				i--;
				System.out.println("미등록 운동 - 유사한 다른 운동을 선택해 주세요");
				continue;
			}

			System.out.print("운동 시간 : ");
			time = scanner.nextInt();
			newExercise = new UserExercise(exercise, currentUser.getWeight(), time);
			newDailyInfo.addExerciseDone(newExercise);
		}

		currentUser.addDaily(newDailyInfo);
		System.out.println("입력 성공!");
	}

	void recommend() {
		DailyInfo dailyInfo = currentUser.getCurrentDailyInfo();
		System.out.println("가장 최근 운동, 식단 정보");
		System.out.println(dailyInfo);

		currentUser.foodRecommend();
		currentUser.exerciseRecommend();

	}

	void saveCurrentState() {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH_USER))) {
			for (Manageable user : userManager.getmList()) {
				writer.write(((User) user).toStringforUserFile());
				writer.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH_USERFOOD))) {
			for (Manageable user : userManager.getmList()) {
				writer.write(((User) user).toStringforFoodFile());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH_USEREXERCISE))) {
			for (Manageable user : userManager.getmList()) {
				writer.write(((User) user).toStringforExerciseFile());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void loadDailyFoodFromFile(String filename) {
		try (Scanner scanner = new Scanner(new File(filename))) {
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] parts = line.split(" ");
				String name = parts[0];
				int year = Integer.parseInt(parts[1]);
				int month = Integer.parseInt(parts[2]);
				int day = Integer.parseInt(parts[3]);
				int num = Integer.parseInt(parts[4]);
				User user = (User) userManager.find(name);
				Date date = new Date(year, month, day);

				if (user.isDailyFoodExist(date)) {
					System.out.println("초기 구성 데이터 무결성 오류!\n해당 날짜의 식단 정보가 이미 존재합니다.(" + date + ")");
					return;
				}
				user.dailyInfos.add(new DailyInfo(date));

				for (int i = 0; i < num; i++) {
					line = scanner.nextLine();
					parts = line.split(" ");
					String foodName = parts[0];
					int foodsize = Integer.parseInt(parts[1]);
					Food food = (Food) foodManager.find(foodName);
					user.findDaily(date).addFoodEaten(new UserFood(food, foodsize));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void loadDailyExerciseFromFile(String filename) {
		try (Scanner scanner = new Scanner(new File(filename))) {
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] parts = line.split(" ");
				String name = parts[0];
				int year = Integer.parseInt(parts[1]);
				int month = Integer.parseInt(parts[2]);
				int day = Integer.parseInt(parts[3]);
				int num = Integer.parseInt(parts[4]);
				User user = (User) userManager.find(name);
				Date date = new Date(year, month, day);

				if (user.isDailyExerciseExist(date)) {
					System.out.println("초기 구성 데이터 무결성 오류!\n해당 날짜의 운동 정보가 이미 존재합니다.(" + date + ")");
					return;
				}

				if (!user.isDailyExist(date))
					user.dailyInfos.add(new DailyInfo(date));

				for (int i = 0; i < num; i++) {
					line = scanner.nextLine();
					parts = line.split(" ");
					String exerciseName = parts[0];
					int duration = Integer.parseInt(parts[1]);
					Exercise exercise = (Exercise) exerciseManager.find(exerciseName);
					user.findDaily(date).addExerciseDone(new UserExercise(exercise, user.getWeight(), duration));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}