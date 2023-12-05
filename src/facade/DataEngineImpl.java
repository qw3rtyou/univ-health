package facade;

import java.util.List;
import mgr.Manageable;
import mgr.Manager;

public abstract class DataEngineImpl<T extends Manageable> extends Manager<T> 
									implements IDataEngine<T> {
	String[] labels = null;
	public void setLabels(String[] labels) {
		this.labels = labels;
	}
	@Override 
	public int getColumnCount() {
		return labels.length;
	}
	public String[] getColumnNames() {		
		return labels;
	}
	@Override
	public void addNewItem(String[] uiTexts) {
		// TODO Auto-generated method stub	
	}
	@Override
	public List<T> search(String kwd) {
		if (kwd == null)
			return mList;
		return findAll(kwd);
	}
	@Override
	public void update(String[] editTexts) {
		for (T item : mList) {
			if (item.getName().equals(editTexts[0])) {
				((UIData)item).set(editTexts);
				break;
			}
		}
	}
	@Override
	public void remove(String kwd) {
		// TODO Auto-generated method stub
		Manageable s = find(kwd);
		mList.remove(s);
	}
	@Override
	public UIData get(int index) {
		// TODO Auto-generated method stub
		return (UIData)mList.get(index);
	}
	public List<T> getData() {
		return mList;
	}
	public void addElement(T a) {
		mList.add(a);
	}
}
