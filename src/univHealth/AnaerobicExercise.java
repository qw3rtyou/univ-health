package univHealth;

public class AnaerobicExercise extends Exercise {
	protected String part;

	public AnaerobicExercise(String name, String type, int caloriesBurnedPerMinute, String part) {
		super(name, type, caloriesBurnedPerMinute);
		this.part = part;
	}

}