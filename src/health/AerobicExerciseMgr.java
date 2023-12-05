package health;

import facade.DataEngineImpl;

public class AerobicExerciseMgr extends DataEngineImpl<Exercise> {
	private static AerobicExerciseMgr mgr = null;
	private String headers[] = {"타입", "운동", "강도", "부위"};

	private AerobicExerciseMgr() { setLabels(headers); }

	public static AerobicExerciseMgr getInstance() {
		if (mgr == null) {
			mgr = new AerobicExerciseMgr();
		}
		return mgr;
	}
}