package health;

import java.io.File;
import java.util.Scanner;

import mgr.Factory;
import ui.GUIMain;

public class Health {
	private static Health health = null;

	private Health() {
	}

	public Health getInstance() {
		if (health == null)
			health = new Health();
		return health;
	}

	Scanner scan = new Scanner(System.in);

	public void test() { // 삭제
		for (User user : UserMgr.getInstance().mList) {
			for (DailyInfo dailyInfo : user.dailyInfos) {
				for (UserExercise userExercise : dailyInfo.userExerciseMgr) {
					System.out.println(userExercise);
				}
//				for (UserFood userFood : dailyInfo.userFoodMgr) {
//					System.out.println(userExercise);
//				}
			}
		}
	}

	public void run() {
		ExerciseMgr.getInstance().readAll("exercise_data.txt", new Factory<Exercise>() {
			public Exercise create() {
				return new Exercise();
			}
		});
		FoodMgr.getInstance().readAll("food_data.txt", new Factory<Food>() {
			public Food create() {
				return new Food();
			}
		});
		UserMgr.getInstance().readAll("user_data.txt", new Factory<User>() {
			public User create() {
				return new User();
			}
		});
		loadDailyExerciseFromFile("user_exercise_data.txt");
		loadDailyFoodFromFile("user_food_data.txt");
	}

	public static void main(String[] args) {
		Health health = new Health();
		health.run();
		health.test(); // 삭제
		GUIMain.startGUI();
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
				User user = (User) UserMgr.getInstance().find(name);
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
					Food food = (Food) FoodMgr.getInstance().find(foodName);
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
				User user = (User) UserMgr.getInstance().find(name);
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
					Exercise exercise = (Exercise) ExerciseMgr.getInstance().find(exerciseName);
					user.findDaily(date).userExerciseMgr.add(new UserExercise(exercise, user.weight, duration, date));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
