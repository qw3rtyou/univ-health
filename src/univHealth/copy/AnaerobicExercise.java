package univHealth.copy;

public class AnaerobicExercise extends Exercise {
	protected String part;

	public AnaerobicExercise(String name, String type, Double mets, String part) {
		super(name, type, mets);
		this.part = part;
	}

	@Override
	public String toString() {
		return super.toString()+"부위 : "+part+"\n";
	}
}