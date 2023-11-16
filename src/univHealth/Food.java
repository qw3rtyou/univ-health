package univHealth;

import java.util.Scanner;

public class Food implements Manageable {
	private String name;
	private Nutrition nutrition;

	@Override
	public String toString() {
		return "음식 이름 : " + name + "\n" + nutrition.toString();
	}

	Double getCalRatio() {
		return nutrition.getCalRatio();
	}

	@Override
	public void read(Scanner scan) {
		String line = scan.nextLine();
		String[] parts = line.split(" ");
		name = parts[0];
		double carbs = Double.parseDouble(parts[1]);
		double protein = Double.parseDouble(parts[2]);
		double fat = Double.parseDouble(parts[3]);
		nutrition = new Nutrition(carbs, protein, fat);
	}

	@Override
	public boolean matches(String kwd) {
		return kwd.equals(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Nutrition getNutrition() {
		return nutrition;
	}

	public void setNutrition(Nutrition nutrition) {
		this.nutrition = nutrition;
	}
}