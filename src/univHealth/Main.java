package univHealth;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static ArrayList<Food> foods;
	public static ArrayList<User> users;
	public static ArrayList<Exercise> exercises;
	User currentUser;

	public static void main(String[] args) {
		Main main = new Main();
		main.start();
	}

	void start() {
		users = loadUsersFromFile("user_data.txt");
		foods = loadFoodsFromFile("food_data.txt");
		loadDailyFoodFromFile("user_food_data.txt");
		exercises = loadExercisesFromFile("exercise_data.txt");
		// loadDailyRoutineFromFile("user_routine_data.txt");

		Scanner scanner = new Scanner(System.in);
		currentUser = new User("아직 설정되지 않음", 0, 0, "NaN", 0);

		while (true) {
			menu();
			switch (scanner.nextInt()) {
			case 1:
				userLoop(scanner);
				break;
			case 2:
				if (currentUser.getName().contentEquals("아직 설정되지 않음"))
					System.out.println("사용자 설정을 해야 함!");
				else
					inputDaily(scanner);
				break;
			case 3:
				if (currentUser.getName().contentEquals("아직 설정되지 않음"))
					System.out.println("사용자 설정을 해야 함!");
				else
					System.out.println("구현중!");
				break;
			case 4:
				if (currentUser.getName().contentEquals("아직 설정되지 않음"))
					System.out.println("사용자 설정을 해야 함!");
				else
					System.out.println("구현중!");
				break;
			case 5:
				// saveCurrentState();
				break;
			case 6:
				scanner.close();
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
		System.out.println("2. 일일 루틴 & 음식 입력하기");
		System.out.println("3. 최근 칼로리 확인");
		System.out.println("3. 나의 이전 기록 보기");
		System.out.println("4. 루틴 추천받기");
		System.out.println("5. 저장하기");
		System.out.println("6. 나가기");
	}

	void userMenu() {
		System.out.println("===사용자 매뉴===");
		System.out.println("현재 사용자 : " + currentUser.getName());
		System.out.println("1. 현재 계정 변경");
		System.out.println("2. 사용자 등록");
		System.out.println("3. 사용자 정보 수정");
		System.out.println("4. 사용자 삭제");
		System.out.println("5. 돌아가기");
		System.out.println("6. 사용자 정보 출력");
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
				System.out.println("사용자 이름 : ");
				user = findUserByName(scanner.next());
				if (user == null)
					System.out.println("사용자를 찾을 수 없습니다");
				else
					currentUser = user;
				break;

			case 2:
				System.out.println("사용자 이름 : ");
				name = scanner.next();
				System.out.println("사용자 키 : ");
				height = scanner.nextDouble();
				System.out.println("사용자 몸무게 : ");
				weight = scanner.nextDouble();
				System.out.println("사용자 성별 : ");
				gender = scanner.next();
				System.out.println("사용자 목표 체중 : ");
				goal = scanner.nextInt();
				users.add(new User(name, height, weight, gender, goal));
				System.out.println("계정 생성 성공");
				break;

			case 3:
				System.out.println("사용자 이름 : ");
				user = findUserByName(scanner.next());
				if (user == null)
					System.out.println("사용자를 찾을 수 없습니다");
				else {
					System.out.println(user);
					System.out.println("사용자 이름 : ");
					user.setName(scanner.next());
					System.out.println("사용자 키 : ");
					user.setHeight(scanner.nextDouble());
					System.out.println("사용자 몸무게 : ");
					user.setWeight(scanner.nextDouble());
					System.out.println("사용자 성별 : ");
					user.setGender(scanner.next());
					System.out.println("사용자 목표 체중 : ");
					user.setGoal(scanner.nextInt());
					System.out.println("계정 수정 성공");
				}
				break;
			case 4:
				System.out.println("사용자 이름 : ");
				user = findUserByName(scanner.next());
				if (user == null)
					System.out.println("사용자를 찾을 수 없습니다");
				else
					users.remove(user);
				break;

			case 5:
				return;

			case 6:
				System.out.println(currentUser);
				break;

			default:
				System.out.println("유효하지 않은 입력값");
				break;
			}
		}
	}

	void inputDaily(Scanner scanner) {
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

		for (DailyInfo dailyInfo : currentUser.getdailyInfos()) {
			if (dailyInfo.getDate().equals(date)) {
				System.out.println("이미 해당 날짜에 기록된 내용이 있음");
				return;
			}
		}

		DailyInfo newDailyInfo=new DailyInfo(year, month, day);
		UserFood newFood;
		UserExercise newExercise;

		System.out.print("식사 횟수 : ");
		num = scanner.nextInt();
		for (int i = 0; i < num; i++) {
			System.out.print(i + "번째 음식명 : ");
			foodName = scanner.next();
			System.out.print("먹은 양(g) : ");
			amount = scanner.nextInt();
			newFood=new UserFood(findFoodByName(foodName), amount);
			newDailyInfo.addFoodEaten(newFood);
		}

		System.out.print("운동 종류 개수 : ");
		num = scanner.nextInt();
		for (int i = 0; i < num; i++) {
			System.out.print(i + "번째 운동 이름 : ");
			exerciseName = scanner.next();
			System.out.print("운동 시간 : ");
			time = scanner.nextInt();
			newExercise=new UserExercise(findExerciseByName(exerciseName), time);
			newDailyInfo.addExerciseDone(newExercise);
		}
		
		currentUser.addDaily(newDailyInfo);
	}

	static User findUserByName(String kwd) {
		for (User user : users) {
			if (user.getName().contentEquals(kwd))
				return user;
		}
		return null;
	}

	static Food findFoodByName(String kwd) {
		for (Food food : foods) {
			if (food.getName().contentEquals(kwd))
				return food;
		}
		return null;
	}
	
	static Exercise findExerciseByName(String kwd) {
		for (Exercise exercise : exercises) {
			if (exercise.getName().contentEquals(kwd))
				return exercise;
		}
		return null;
	}

	public static ArrayList<Food> loadFoodsFromFile(String filename) {
		ArrayList<Food> foods = new ArrayList<>();
		try (Scanner scanner = new Scanner(new File(filename))) {
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] parts = line.split(" ");
				String name = parts[0];
				double carbs = Double.parseDouble(parts[1]);
				double protein = Double.parseDouble(parts[2]);
				double fat = Double.parseDouble(parts[3]);
				foods.add(new Food(name, new Nutrition(carbs, protein, fat)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return foods;
	}

	public static ArrayList<User> loadUsersFromFile(String filename) {
		ArrayList<User> users = new ArrayList<>();
		try (Scanner scanner = new Scanner(new File(filename))) {
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] parts = line.split(" ");
				String name = parts[0];
				double height = Double.parseDouble(parts[1]);
				double weight = Double.parseDouble(parts[2]);
				String gender = parts[3];
				int goal = Integer.parseInt(parts[4]);
				users.add(new User(name, height, weight, gender, goal));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}

	public static void loadDailyFoodFromFile(String filename) {
		try (Scanner scanner = new Scanner(new File(filename))) {
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] parts = line.split(" ");
				String name = parts[0];
				int year = Integer.parseInt(parts[1]);
				int month = Integer.parseInt(parts[2]);
				int day = Integer.parseInt(parts[3]);
				int num = Integer.parseInt(parts[4]);
				User user = findUserByName(name);
				user.dailyInfos.add(new DailyInfo(year, month, day));

				for (int i = 0; i < num; i++) {
					line = scanner.nextLine();
					parts = line.split(" ");
					String foodName = parts[0];
					int foodsize = Integer.parseInt(parts[1]);
					Food food = findFoodByName(foodName);
					user.findDaily(year, month, day).addFoodEaten(new UserFood(food, foodsize));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static ArrayList<Exercise> loadExercisesFromFile(String filename) {
		ArrayList<Exercise> exercises = new ArrayList<>();
		try (Scanner scanner = new Scanner(new File(filename))) {
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] parts = line.split(" ");
				String type = parts[0];
				String name = parts[1];
				int cal = Integer.parseInt(parts[2]);
				String part;
				Exercise exercise;

				if (type.contentEquals("무")) {
					part = parts[3];
					exercise = new AnaerobicExercise(name, type, cal, part);
				} else
					exercise = new AerobicExercise(name, type, cal);

				exercises.add(exercise);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return exercises;
	}

}