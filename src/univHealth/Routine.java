package univHealth;

import java.util.ArrayList;

public class Routine {
	private ArrayList<UserExercise> exercises = new ArrayList<>();

	public int getTotalCal() {
		int totalCal = 0;
		for (UserExercise userExercise : exercises) {
			totalCal += userExercise.caloriesBurned;
		}
		return totalCal;
	}
}