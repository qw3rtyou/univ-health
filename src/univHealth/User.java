package univHealth;

import java.util.ArrayList;
import java.util.Collections;

public class User {
	private String name;
	private double height;
	private double weight;
	private String gender;
	private int goal; // 목표체중
	ArrayList<DailyInfo> dailyInfos = new ArrayList<>();

	public User(String name, double height, double weight, String gender, int goal) {
		this.name = name;
		this.height = height;
		this.weight = weight;
		this.gender = gender;
		this.goal = goal;
	}

	DailyInfo findDaily(int year, int month, int day) {
		Date date = new Date(year, month, day);
		for (DailyInfo dailyInfo : dailyInfos) {
			if (dailyInfo.getDate().equals(date))
				return dailyInfo;
		}
		return null;
	}

	@Override
	public String toString() {
		String buf = "===기본정보===\n이름 : " + name + "\n키 : " + height + "\n몸무게 : " + weight + "\n성별 : " + gender
				+ "\n목표 몸무게 : " + goal + "\n";
		for (DailyInfo dailyResult : dailyInfos) {
			buf += dailyResult;
		}
		return buf;
	}

	public ArrayList<DailyInfo> getdailyInfos() {
		return dailyInfos;
	}

	public void setdailyInfos(ArrayList<DailyInfo> dailyInfos) {
		this.dailyInfos = dailyInfos;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getGoal() {
		return goal;
	}

	public void setGoal(int goal) {
		this.goal = goal;
	}

	public void addDaily(DailyInfo dailyInfo) {
		dailyInfos.add(dailyInfo);
		Collections.sort(dailyInfos);
	}
}
