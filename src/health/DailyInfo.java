package health;

import java.util.ArrayList;

public class DailyInfo implements Comparable<DailyInfo> {
	private ArrayList<UserFood> eatenFoods = new ArrayList<>();
//	private ArrayList<UserExercise> exercises = new ArrayList<>();
	UserFoodMgr userFoodMgr=new UserFoodMgr();
	UserExerciseMgr userExerciseMgr=new UserExerciseMgr();
	
	private Date date;

	public DailyInfo(Date date) {
		this.setDate(date);
	}

	@Override
	public String toString() {
		String buf = date.toString();
		buf += "\n-------------------\n";
		if (eatenFoods.size() != 0)
			buf += "칼로리 - 기록있음\n";

		if (userExerciseMgr.size() != 0)
			buf += "운동 - 기록있음\n";

		return buf;
	}

	String toStringDetail() {
		String buf = date.toString();
		buf += "\n-------------------\n";
		buf += "===칼로리정보===\n";
		for (UserFood eatenFood : eatenFoods) {
			buf += eatenFood.toString();
		}

		buf += "===운동정보===\n";
		for (UserExercise exercise : userExerciseMgr) {
			buf += exercise.toString();
		}

		return buf;
	}

	int getDailyCalInput() {
		int sum = 0;
		for (UserFood userFood : eatenFoods) {
			sum += userFood.cal;
		}
		return sum;
	}

	int getDailyCalOuput() {
		int sum = 0;
		for (UserExercise userExercise : userExerciseMgr) {
			sum += userExercise.caloriesBurned;
		}
		return sum;
	}

	public ArrayList<UserFood> getEatenFoods() {
		return eatenFoods;
	}

	public void setEatenFoods(ArrayList<UserFood> eatenFoods) {
		this.eatenFoods = eatenFoods;
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
		userExerciseMgr.add(exercise);
	}

	@Override
	public int compareTo(DailyInfo other) {
		return this.date.compareTo(other.date);
	}
}