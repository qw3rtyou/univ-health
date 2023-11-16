package univHealthLegacy;

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
	public String toString() {
		return "" + year + " " + month + " " + day;
	}

	@Override
	public boolean equals(Object obj) {
		Date date = (Date) obj;
		return date.year == year && date.month == month && date.day == day;
	}

	@Override
	public int compareTo(Date other) {
		if (this.year != other.year) {
			return this.year - other.year;
		}
		if (this.month != other.month) {
			return this.month - other.month;
		}
		return this.day - other.day;
	}
}