package univHealth;

public class Nutrition {
	private double carbRatio;
	private double proteinRatio;
	private double fatRatio;

	public Nutrition(double carbRatio, double proteinRatio, double fatRatio) {
		this.carbRatio = carbRatio;
		this.proteinRatio = proteinRatio;
		this.fatRatio = fatRatio;
	}

	public double getCarbRatio() {
		return carbRatio;
	}

	public void setCarbRatio(double carbRatio) {
		this.carbRatio = carbRatio;
	}

	public double getProteinRatio() {
		return proteinRatio;
	}

	public void setProteinRatio(double proteinRatio) {
		this.proteinRatio = proteinRatio;
	}

	public double getFatRatio() {
		return fatRatio;
	}

	public void setFatRatio(double fatRatio) {
		this.fatRatio = fatRatio;
	}

	@Override
	public String toString() {
		return "탄당지 비율 정보 \n탄수화물 : "+carbRatio+"\n단백질 : "+proteinRatio+"\n지방 : "+fatRatio+"\n";
	}
}
