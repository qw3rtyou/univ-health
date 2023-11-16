package univHealth;

import java.util.ArrayList;

public class DailyInfo implements Comparable<DailyInfo> {
	private ArrayList<UserFood> eatenFoods = new ArrayList<>();
	private ArrayList<UserExercise> exercises = new ArrayList<>();
	private Date date;

	public DailyInfo(Date date) {
		this.setDate(date);
	}

	@Override
	public String toString() {
		String buf = date.toString();
		buf += "\n-------------------\n";
		buf += "===칼로리정보===\n";
		for (UserFood eatenFood : eatenFoods) {
			buf += eatenFood.toString();
		}

		buf += "===운동정보===\n";
		for (UserExercise exercise : exercises) {
			buf += exercise.toString();
		}

		return buf;
	}

	int getDailyCalInput() {
		int sum = 0;
		for (UserFood userFood : eatenFoods) {
			sum += userFood.getCal();
		}
		return sum;
	}

	int getDailyCalOuput() {
		int sum = 0;
		for (UserExercise userExercise : exercises) {
			userExercise.getCaloriesBurned();
		}
		return sum;
	}

	public ArrayList<UserFood> getEatenFoods() {
		return eatenFoods;
	}

	public void setEatenFoods(ArrayList<UserFood> eatenFoods) {
		this.eatenFoods = eatenFoods;
	}

	public ArrayList<UserExercise> getExercises() {
		return exercises;
	}

	public void setExercises(ArrayList<UserExercise> exercises) {
		this.exercises = exercises;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void addFoodEaten(UserFood eatenFood) {
		eatenFoods.add(eatenFood);
	}

	public void addExerciseDone(UserExercise exercise) {
		exercises.add(exercise);
	}

	@Override
	public int compareTo(DailyInfo other) {
		return this.date.compareTo(other.date);
	}
}