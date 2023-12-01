package health;

import facade.DataEngineImpl;

public class UserMgr extends DataEngineImpl<User> {
	private static UserMgr mgr = null;
	
	private UserMgr() { setLabels(headers); }
	public static UserMgr getInstance() {
		if (mgr == null)
			mgr = new UserMgr();
		return mgr;
	}
	
	String[] headers = { "이름", "키", "성별", "몸무게", "목표체중" };

	public void addUser(User user) { UserMgr.mgr.mList.add(user); }
	
	public void addNewItem(String[] editTexts) {
		User u = new User();
		u.set(editTexts);
		mList.add(u);
		System.out.println("add : " + u);
	}
	
}
