package health;

public class Date implements Comparable<Date> {

	int year;
	int month;
	int day;

	public Date(int year, int month, int day) {
		this.year = year;
		this.month = month;
		this.day = day;
	}

	@Override
	public int compareTo(Date o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String toString() {
		return "" + year + "/" + month + "/" + day;
	}
}
