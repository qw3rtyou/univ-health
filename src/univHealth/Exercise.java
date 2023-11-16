package univHealth;

public class Exercise {
	protected String name;
	protected Double mets; // 운동강도

	public Exercise(String name, String type, Double mets) {
		this.name = name;
		this.mets = mets;
	}

	public Exercise() { // 하위 개체에서 필요로 함
	}

	@Override
	public String toString() {
		String buf = "";
		buf += "이름 : " + name;
		buf += "\nMETs(운동강도) : " + mets + "\n";
		return buf;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getMets() {
		return mets;
	}

	public void setMets(Double mets) {
		this.mets = mets;
	}
}
