package univHealthLegacy;

public class UserFood {
	private int size;
	private int cal;
	Food food;

	public UserFood(Food food, int size) {
		this.food = food;
		this.size = size;
		this.cal = (int) (food.getCalRatio() * size);
	}

	@Override
	public String toString() {
		return "음식 이름 : " + food.getName() + "\n먹은 양 : " + size + "g\n칼로리 : " + cal + "cal\n";
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getCal() {
		return cal;
	}

	public void setCal(int cal) {
		this.cal = cal;
	}

	public Food getFood() {
		return food;
	}

	public void setFood(Food food) {
		this.food = food;
	}
}
