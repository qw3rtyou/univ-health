package facade;

import java.util.ArrayList;
import java.util.List;

import health.UserExercise;
import mgr.Manageable;

public abstract class DataEngineImplExerciseList<T extends Manageable> extends ArrayList<UserExercise> implements IDataEngine<UserExercise> {
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
    	UserExercise newItem = createItem(uiTexts);
        this.add(newItem);
    }

    protected abstract UserExercise createItem(String[] uiTexts);

    @Override
    public List<UserExercise> search(String kwd) {
        List<UserExercise> results = new ArrayList<>();
        for (UserExercise item : this) {
            if (item.toString().contains(kwd) || kwd == null) {
                results.add(item);
            }
        }
        return results;
    }

    @Override
    public void update(String[] editTexts) {
        for (UserExercise item : this) {
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
    public UserExercise get(int index) {
        return super.get(index);
    }
}