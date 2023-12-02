package health;

import facade.DataEngineImpl;

public class FoodMgr extends DataEngineImpl<Food>{
	
	private static FoodMgr mgr = null;
	
	private FoodMgr() { setLabels(headers); }
	
	public static FoodMgr getInstance() {
		if (mgr == null) 
			mgr = new FoodMgr();
		return mgr;
	}
	
	String[] headers = {"음식", "탄수화물", "단백질", "지방"};
}
