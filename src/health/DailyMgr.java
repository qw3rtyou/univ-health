package health;

import facade.DataEngineImpl;

public class DailyMgr extends DataEngineImpl<Exercise> {
	private String headers[] = { "타입", "운동", "강도", "부위" };
}
