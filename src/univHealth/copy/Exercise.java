package univHealth.copy;

public class Exercise {
	protected String name;
	protected String type;
	protected Double mets;	//운동강도

	public Exercise(String name, String type, Double mets) {
		this.name = name;
		this.type = type;
		this.mets = mets;
	}

	@Override
	public String toString() {
		String buf="";
		buf+="이름 : "+name+"("+type+")";
		buf+="\nMETs(운동강도) : "+mets+"\n";
		return buf;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Double getMets() {
		return mets;
	}

	public void setMets(Double mets) {
		this.mets = mets;
	}
}
