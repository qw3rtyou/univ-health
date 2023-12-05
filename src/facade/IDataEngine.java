package facade;

import java.util.List;
import mgr.Manageable;

public interface IDataEngine<T extends Manageable> {
	int getColumnCount();
	String[] getColumnNames();
	List<T> search(String kwd);
	void addNewItem(String[] uiTexts);
	void update(String[] uiTexts);
	void remove(String kwd);
	UIData get(int index);
	void addElement(T a);
}
