package health;

import java.util.Scanner;

import facade.UIData;
import mgr.Manageable;

public class UserFood implements Manageable, UIData{
	int size;
	int cal;
	Food food;

	public UserFood(Food food, int size) {
		this.food = food;
		this.size = size;
		this.cal = (int) (food.getCalRatio() * size);
	}

	@Override
	public String toString() {
		return "음식 이름 : " + food.name + "\n먹은 양 : " + size + "g\n칼로리 : " + cal + "cal\n";
	}

	@Override
	public void read(Scanner scan) {
	}

	@Override
	public void print() {
	}

	@Override
	public boolean matches(String kwd) {
		return kwd.equals(food.name);
	}

	@Override
	public void set(Object[] uitexts) {
	}

	@Override
	public String[] getUiTexts() {
		String[] texts = new String[3];
		texts[0] = food.name;
		texts[1] = ""+cal;
		texts[2] = ""+size;
		return texts;
	}
}
