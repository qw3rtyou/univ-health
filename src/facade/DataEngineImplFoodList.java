package facade;

import java.util.ArrayList;
import java.util.List;

import health.UserFood;
import mgr.Manageable;

public abstract class DataEngineImplFoodList<T extends Manageable> extends ArrayList<UserFood> implements IDataEngine<UserFood> {
	private static final long serialVersionUID = 1L;
	private String[] labels;

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
    	UserFood newItem = createItem(uiTexts);
        this.add(newItem);
    }

    protected abstract UserFood createItem(String[] uiTexts);

    @Override
    public List<UserFood> search(String kwd) {
        List<UserFood> results = new ArrayList<>();
        for (UserFood item : this) {
            if (item.toString().contains(kwd) || kwd == null) {
                results.add(item);
            }
        }
        return results;
    }
    @Override
    public void update(String[] editTexts) {
        for (UserFood item : this) {
            if (item.toString().equals(editTexts[0])) {
                item.set(editTexts);
                break;
            }
        }
    }
    @Override
    public void remove(String kwd) {
        this.removeIf(item -> item.toString().equals(kwd));
    }

    @Override
    public UserFood get(int index) {
        return super.get(index);
    }
}