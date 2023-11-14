package univHealth;

public class Exercise {
	protected String name;
	protected String type;
	protected int caloriesBurnedPerMinute;

	public Exercise(String name, String type, int caloriesBurnedPerMinute) {
		this.name = name;
		this.type = type;
		this.caloriesBurnedPerMinute = caloriesBurnedPerMinute;
	}

	public int calculateCaloriesBurned(int duration) {
		return duration * caloriesBurnedPerMinute;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getCaloriesBurnedPerMinute() {
		return caloriesBurnedPerMinute;
	}

	public void setCaloriesBurnedPerMinute(int caloriesBurnedPerMinute) {
		this.caloriesBurnedPerMinute = caloriesBurnedPerMinute;
	}
}
