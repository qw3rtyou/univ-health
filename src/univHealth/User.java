package univHealth;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class User implements Manageable {
	private String name;
	private double height;
	private double weight;
	private String gender;
	private int goal; // 목표체중
	ArrayList<DailyInfo> dailyInfos = new ArrayList<>();
	Manager dailyManager = new Manager();

	public User(String name, double height, double weight, String gender, int goal) { // 생성자 오버로딩 - 직접 객체 생성 시 사용
		this.name = name;
		this.height = height;
		this.weight = weight;
		this.gender = gender;
		this.goal = goal;
	}

	public User() { // 생성자 오버로딩 - Fatory create용
		// 비어있어야함
	}

	DailyInfo findDaily(Date date) {
		for (DailyInfo dailyInfo : dailyInfos) {
			if (dailyInfo.getDate().equals(date))
				return dailyInfo;
		}
		return null;
	}

	void foodRecommend() { // 추천 알고리즘 다소 빈약함
		DailyInfo dailyInfo = getCurrentDailyInfo();

		int recommendedDailyCal = (int) weight * 30; // 하루 권장 섭취량은 30*몸무게
		int metabolism = (int) (recommendedDailyCal * 0.4);// 기초대사량은 일반적으로 권장 섭취량의 0.4배
		int curCal = dailyInfo.getDailyCalInput(); // 일반적으로 하루에 600~700kcal 정도 먹음

		boolean isGoalLosing = weight > goal;

		if (isGoalLosing) {// 감량이 목적이라면
			if ((recommendedDailyCal - metabolism) % 0.9 > curCal) {
				System.out.println("좋은 칼로리 섭취량!");
			} else if ((recommendedDailyCal - metabolism) % 1.1 > curCal) {
				System.out.println("적당한 칼로리 섭취량");
			} else {
				System.out.println("칼로리 섭취를 줄여야 합니다!");
			}
		} else {// 증량이 목적이라면
			if ((recommendedDailyCal - metabolism) % 0.9 > curCal) {
				System.out.println("칼로리 섭취를 늘려야 합니다!");
			} else if ((recommendedDailyCal - metabolism) % 1.1 > curCal) {
				System.out.println("적당한 칼로리 섭취량");
			} else {
				System.out.println("좋은 칼로리 섭취량!");
			}
		}
	}

	void exerciseRecommend() {
		HashMap<String, Integer> partCount = new HashMap<>();
		partCount.put("하체", 0);
		partCount.put("어깨", 0);
		partCount.put("등", 0);
		partCount.put("상체", 0);
		partCount.put("가슴", 0);

		for (UserExercise userExercise : getCurrentDailyInfo().getExercises()) {
			if (userExercise.getExercise() instanceof AnaerobicExercise) {
				AnaerobicExercise anaerobicExercise = (AnaerobicExercise) userExercise.getExercise();
				String part = anaerobicExercise.part;
				partCount.put(part, partCount.getOrDefault(part, 0) + 1);
			}
		}

		String leastUsedPart = null;
		int minCount = Integer.MAX_VALUE;
		for (Map.Entry<String, Integer> entry : partCount.entrySet()) {
			if (entry.getValue() < minCount) {
				minCount = entry.getValue();
				leastUsedPart = entry.getKey();
			}
		}

		if (leastUsedPart != null) {
			System.out.println("추천 부위 : " + leastUsedPart);
		} else {
			System.out.println("무산소 운동 정보가 없습니다.");
		}
	}

	Boolean isDailyExist(Date date) {
		for (DailyInfo dailyInfo : dailyInfos) {
			if (dailyInfo.getDate().equals(date))
				return true;
		}
		return false;
	}

	Boolean isDailyFoodExist(Date date) {
		for (DailyInfo dailyInfo : dailyInfos) {
			if (dailyInfo.getDate().equals(date) && !dailyInfo.getEatenFoods().isEmpty())
				return true;
		}
		return false;
	}

	Boolean isDailyExerciseExist(Date date) {
		for (DailyInfo dailyInfo : dailyInfos) {
			if (dailyInfo.getDate().equals(date) && !dailyInfo.getExercises().isEmpty())
				return true;
		}
		return false;
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

	DailyInfo getCurrentDailyInfo() { // 가장 최근 일과 반환
		int size = dailyInfos.size();
		return dailyInfos.get(size - 1);
	}

	public String toStringforUserFile() {
		return name + " " + height + " " + weight + " " + gender + " " + goal;
	}

	public String toStringforFoodFile() {
		String buf = "";
		ArrayList<UserFood> userFoods;
		for (DailyInfo dailyInfo : dailyInfos) {
			userFoods = dailyInfo.getEatenFoods();
			buf += name + " " + dailyInfo.getDate().toString() + " " + userFoods.size() + "\n";
			for (UserFood userFood : userFoods) {
				buf += userFood.getFood().getName() + " " + userFood.getSize() + "\n";
			}
		}

		return buf;
	}

	public String toStringforExerciseFile() {
		name = name.trim();
		String buf = "";
		ArrayList<UserExercise> userExercises;
		for (DailyInfo dailyInfo : dailyInfos) {
			userExercises = dailyInfo.getExercises();
			buf += name + " " + dailyInfo.getDate().toString() + " " + userExercises.size() + "\n";
			for (UserExercise userExercise : userExercises) {
				buf += userExercise.getExercise().getName() + " " + userExercise.getDuration() + "\n";
			}
		}

		return buf;
	}

	@Override
	public void read(Scanner scan) {
		String line = scan.nextLine();
		String[] parts = line.split(" ");
		name = parts[0];
		height = Double.parseDouble(parts[1]);
		weight = Double.parseDouble(parts[2]);
		gender = parts[3];
		goal = Integer.parseInt(parts[4]);
	}

	@Override
	public boolean matches(String kwd) {
		return kwd.equals(name);
	}

	public ArrayList<DailyInfo> getDailyInfos() {
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
