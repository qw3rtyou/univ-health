package health;

import facade.DataEngineImpl;

public class ExerciseMgr extends DataEngineImpl<Exercise>{
	private static ExerciseMgr mgr = null;
	
	private ExerciseMgr() { setLabels(headers); }
	
	public static ExerciseMgr getInstance() {
		if (mgr == null)
			mgr = new ExerciseMgr();
		return mgr;
	}
	
	private String headers[] = {"타입", "운동", "강도", "부위"};
	
}
