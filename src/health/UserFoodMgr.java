package health;

import facade.DataEngineImplFoodList;

public class UserFoodMgr extends DataEngineImplFoodList<Exercise> {
	private static final long serialVersionUID = 1L;
	private String headers[] = { "날짜", "음식명", "먹은 양(g)", "칼로리" };

	public UserFoodMgr() {
		setLabels(headers);
	}

	@Override
	public void addElement(UserFood a) {
	}

	@Override
	protected UserFood createItem(String[] uiTexts) {
		return null;
	}
}
