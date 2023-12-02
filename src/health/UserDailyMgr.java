package health;

import facade.DataEngineImplExerciseList;

public class UserDailyMgr extends DataEngineImplExerciseList<UserExercise> {
	private static final long serialVersionUID = 1L;

	public UserDailyMgr() {
		setLabels(headers);
	}

	private String headers[] = { "날짜", "활동명" };

	@Override
	public void addElement(UserExercise a) {
		add(a);
	}

	@Override
	protected UserExercise createItem(String[] uiTexts) {
		return null;
	}
}