package health;

import facade.DataEngineImplExerciseList;

public class UserExerciseMgr extends DataEngineImplExerciseList<UserExercise> {
	private static final long serialVersionUID = 1L;

	public UserExerciseMgr() {
		setLabels(headers);
	}

	private String headers[] = { "운동 날짜","운동명", "중량", "시간", "칼로리 소모", };

	@Override
	public void addElement(UserExercise a) {
		add(a);
	}

	@Override
	protected UserExercise createItem(String[] uiTexts) {
		return null;
	}
}
