package health;

import facade.DataEngineImpl;

public class AnaerobicExerciseMgr extends DataEngineImpl<Exercise> {
	private static AnaerobicExerciseMgr mgr = null;
	private String headers[] = { "타입", "운동", "강도", "부위", "사진" };

	private AnaerobicExerciseMgr() {
		setLabels(headers);
	}

	public static AnaerobicExerciseMgr getInstance() {
		if (mgr == null) {
			mgr = new AnaerobicExerciseMgr();
		}
		return mgr;
	}
}