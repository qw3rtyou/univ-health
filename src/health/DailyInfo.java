package health;

public class DailyInfo implements Comparable<DailyInfo> {
	public UserFoodMgr userFoodMgr=new UserFoodMgr();
	public UserExerciseMgr userExerciseMgr=new UserExerciseMgr();
	
	private Date date;

	public DailyInfo(Date date) {
		this.setDate(date);
	}

	int getDailyCalInput() {
		int sum = 0;
		for (UserFood userFood : userFoodMgr) {
			sum += userFood.cal;
		}
		return sum;
	}

	int getDailyCalOutput() {
		int sum = 0;
		for (UserExercise userExercise : userExerciseMgr) {
			sum += userExercise.caloriesBurned;
		}
		return sum;
	}
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

//	public void addFoodEaten(UserFood eatenFood) {
//		userFoodMgr.add(eatenFood);
//	}

//	public void addExerciseDone(UserExercise exercise) {
//		userExerciseMgr.add(exercise);
//	}

	@Override
	public int compareTo(DailyInfo other) {
		return this.date.compareTo(other.date);
	}
}