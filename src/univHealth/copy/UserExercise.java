package univHealth.copy;

public class UserExercise {
	Exercise exercise;
	Double weight;
	int duration;
	int caloriesBurned; // 칼로리 소모량 (칼로리) = METs × 체중(kg) × 시간(분)

	public UserExercise(Exercise exercise, Double weight, int duration) {
		this.exercise = exercise;
		this.weight = weight;
		this.duration = duration;
		this.caloriesBurned = (int) (exercise.mets * weight * duration);
	}

	@Override
	public String toString() {
		return exercise.toString() + "운동시간 : " + duration + "\n총 칼로리 소모 : " + caloriesBurned + "\n";
	}

	public Exercise getExercise() {
		return exercise;
	}

	public void setExercise(Exercise exercise) {
		this.exercise = exercise;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weights) {
		this.weight = weights;
	}

	public int getCaloriesBurned() {
		return caloriesBurned;
	}

	public void setCaloriesBurned(int caloriesBurned) {
		this.caloriesBurned = caloriesBurned;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}
}
