package univHealth.copy;

public class Food {
	private String name;
	private Nutrition nutrition;

	public Food(String name, Nutrition nutrition) {
		this.name = name;
		this.nutrition = nutrition;
	}

	@Override
	public String toString() {
		return "음식 이름 : " + name + "\n" + nutrition.toString();
	}

	Double getCalRatio() {
		return nutrition.getCalRatio();
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