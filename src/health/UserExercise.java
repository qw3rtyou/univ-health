package health;

import java.util.Scanner;

import facade.UIData;
import mgr.Manageable;

public class UserExercise implements Manageable, UIData {
	Exercise exercise;
	Double weight;
	int duration;
	int caloriesBurned;

	String date;

	public UserExercise(Exercise exercise, Double weight, int duration, Date date) {
		this.exercise = exercise;
		this.weight = weight;
		this.duration = duration;
		this.caloriesBurned = (int) (exercise.mets * weight * duration);
		this.date = date.toString();
	}

	@Override
	public String toString() {
		return exercise.toString() + "운동시간(분) : " + duration + "\n총 칼로리 소모 : " + caloriesBurned + "\n";
	}

	@Override
	public void read(Scanner scan) {
	}

	@Override
	public void print() {
	}

	@Override
	public boolean matches(String kwd) {
		return kwd.equals(exercise.name);
	}

	@Override
	public void set(Object[] uitexts) {
	}

	@Override
	public String[] getUiTexts() {
		String[] texts = new String[5];
		texts[0] = date.toString();
		texts[1] = exercise.name;
		texts[2] = "" + weight;
		texts[3] = "" + duration;
		texts[4] = "" + caloriesBurned;
		return texts;
	}

	public String getName() {
		return this.exercise.name;
	}
}
