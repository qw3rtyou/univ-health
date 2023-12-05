package health;

import facade.DataEngineImpl;

public class ExerciseMgr extends DataEngineImpl<Exercise> {
	private static ExerciseMgr mgr = null;
	private String headers[] = { "타입", "운동", "강도", "부위" };

	private ExerciseMgr() {
		setLabels(headers);
	}

	public static ExerciseMgr getInstance() {
		if (mgr == null) {
			mgr = new ExerciseMgr();
		}
		return mgr;
	}
}