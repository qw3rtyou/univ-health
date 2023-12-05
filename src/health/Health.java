package health;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Scanner;

import mgr.Factory;
import mgr.Manageable;
import ui.GUIMain;

public class Health {
	final static String DATA_DIRECTORY = "data/";
	final static String FILE_PATH_USER = DATA_DIRECTORY + "user_data.txt";
	final static String FILE_PATH_FOOD = DATA_DIRECTORY + "food_data.txt";
	final static String FILE_PATH_EXERCISE = DATA_DIRECTORY + "exercise_data.txt";
	final static String FILE_PATH_USERFOOD = DATA_DIRECTORY + "user_food_data.txt";
	final static String FILE_PATH_USEREXERCISE = DATA_DIRECTORY + "user_exercise_data.txt";

	private static Health health = null;

	private Health() {
	}

	public static Health getInstance() {
		if (health == null)
			health = new Health();
		return health;
	}

	public void test() { // 삭제
		for (User user : UserMgr.getInstance().mList) {
			for (DailyInfo dailyInfo : user.dailyInfos) {
				for (UserExercise userExercise : dailyInfo.userExerciseMgr) {
					System.out.println(userExercise);
				}
				for (UserFood userFood : dailyInfo.userFoodMgr) {
					System.out.println(userFood);
				}
			}
		}
	}

	public void run() {
		ExerciseMgr.getInstance().readAll(FILE_PATH_EXERCISE, new Factory<Exercise>() {
			public Exercise create() {
				return new Exercise();
			}
		});
		FoodMgr.getInstance().readAll(FILE_PATH_FOOD, new Factory<Food>() {
			public Food create() {
				return new Food();
			}
		});
		UserMgr.getInstance().readAll(FILE_PATH_USER, new Factory<User>() {
			public User create() {
				return new User();
			}
		});
		loadDailyExerciseFromFile(FILE_PATH_USEREXERCISE);
		loadDailyFoodFromFile(FILE_PATH_USERFOOD);

		AerobicExerciseMgr.getInstance().readAll(FILE_PATH_EXERCISE, new Factory<Exercise>() {
			public Exercise create() {
				return new Exercise();
			}
		});
		for (Exercise exercise : AerobicExerciseMgr.getInstance().findAll("무산소")) {
			AerobicExerciseMgr.getInstance().mList.remove(exercise);
		}

		AnaerobicExerciseMgr.getInstance().readAll(FILE_PATH_EXERCISE, new Factory<Exercise>() {
			public Exercise create() {
				return new Exercise();
			}
		});
		for (Exercise exercise : AnaerobicExerciseMgr.getInstance().findAll("유산소")) {
			AnaerobicExerciseMgr.getInstance().mList.remove(exercise);
		}

	}

	public static void main(String[] args) {
		Health health = new Health();
		Runtime.getRuntime().addShutdownHook(new Thread(() -> health.saveCurrentState()));
		health.run();
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
					user.findDaily(date).userFoodMgr.add(new UserFood(food, foodsize, date));
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
					Collections.sort(user.dailyInfos);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void saveCurrentState() {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH_USER))) {
			for (Manageable user : UserMgr.getInstance().mList) {
				writer.write(((User) user).toStringforUserFile());
				writer.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH_USERFOOD))) {
			for (Manageable user : UserMgr.getInstance().mList) {
				writer.write(((User) user).toStringforFoodFile());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH_USEREXERCISE))) {
			for (Manageable user : UserMgr.getInstance().mList) {
				writer.write(((User) user).toStringforExerciseFile());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH_FOOD))) {
			for (Manageable food : FoodMgr.getInstance().mList) {
				writer.write(((Food) food).toStringforFile());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH_EXERCISE))) {
			for (Manageable exercise : ExerciseMgr.getInstance().mList) {
				writer.write(((Exercise) exercise).toStringforFile());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
