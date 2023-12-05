package health;

import java.util.ArrayList;
import java.util.Scanner;

import facade.UIData;
import mgr.Manageable;

public class User implements Manageable, UIData {

	public String name;
	public double height;
	public double weight;
	public String gender;
	public int goal;
	public ArrayList<DailyInfo> dailyInfos = new ArrayList<>();
	private String profileImagePath;
	public User(String name, double height, double weight, String gender, int goal) {
		this.name = name;
		this.height = height;
		this.weight = weight;
		this.gender = gender;
		this.goal = goal;
	}

	public User() {
	}

	@Override
	public void set(Object[] uitexts) {
		// TODO Auto-generated method stub
	}
	@Override
	public String[] getUiTexts() {
		// TODO Auto-generated method stub
		String[] texts = new String[5];
		texts[0] = name;
		texts[1] = "" + height;
		texts[2] = gender;
		texts[3] = "" + weight;
		texts[4] = "" + goal;
		return texts;
	}

	@Override
	public void read(Scanner scan) {
		// TODO Auto-generated method stub
		name = scan.next();
		height = scan.nextDouble();
		weight = scan.nextDouble();
		gender = scan.next();
		goal = scan.nextInt();

	}

	@Override
	public void print() {
		// TODO Auto-generated method stub
		System.out.format("이름 %s 키 %.1f 체중 %.1f 성별 %s 목표 %d", name, height, weight, gender, goal);
		System.out.println();

	}

	@Override
	public boolean matches(String kwd) {
		// TODO Auto-generated method stub
		if (name.contains(kwd))
			return true;
		return false;
	}

	public DailyInfo findDaily(Date date) {
		for (DailyInfo dailyInfo : dailyInfos) {
			if (dailyInfo.getDate().equals(date))
				return dailyInfo;
		}
		return null;
	}

	String foodRecommend() {
		DailyInfo dailyInfo = getCurrentDailyInfo();

		int recommendedDailyCal = (int) weight * 30;
		int metabolism = (int) (recommendedDailyCal * 0.4);
		int curCal = dailyInfo.getDailyCalInput();

		boolean isGoalLoss = weight > goal;
		String buf = "";

		if (isGoalLoss) {
			if ((recommendedDailyCal - metabolism) > curCal) {
				buf += "목표치보다 " + (recommendedDailyCal - metabolism - curCal);
				buf += "만큼 덜 먹었음\n";
				buf += "쌀밥,감자,우유,콩나물국,김치찌개,순두부찌개,김밥,초밥,닭볶음탕,맥주\n";
			} else {
				buf += "목표치보다 " + (curCal - recommendedDailyCal + metabolism);
				buf += "만큼 더 먹었음\n";
				buf += "샐러드,닭가슴살,계란,두부,브로콜리,토마토,오이,상추,연어,닭다리\n";
			}
		} else {
			if ((recommendedDailyCal - metabolism) > curCal) {
				buf += "목표치보다 " + (recommendedDailyCal - metabolism - curCal);
				buf += "만큼 덜 먹었음\n";
				buf += "빵,피자,햄버거,스파게티,비빔밥,라면,갈비,제육볶음,돈까스,파스타\n";
			} else {
				buf += "목표치보다 " + (curCal - recommendedDailyCal + metabolism);
				buf += "만큼 더 먹었음\n";
				buf += "쌀밥,감자,우유,콩나물국,김치찌개,순두부찌개,김밥,초밥,닭볶음탕,맥주\n";
			}
		}

		return buf;
	}

//	String exerciseRecommend() {
//		HashMap<String, Integer> partCount = new HashMap<>();
//		partCount.put("상체", 0);
//		partCount.put("하체", 0);
//		partCount.put("등", 0);
//		partCount.put("기슴", 0);
//		partCount.put("어깨", 0);
//
//		for (UserExercise userExercise : getCurrentDailyInfo().getExercises()) {
//			if (userExercise.getExercise() instanceof AnaerobicExercise) {
//				AnaerobicExercise anaerobicExercise = (AnaerobicExercise) userExercise.getExercise();
//				String part = anaerobicExercise.part;
//				partCount.put(part, partCount.getOrDefault(part, 0) + 1);
//			}
//		}
//		System.out.println(partCount);
//
//		String leastUsedPart = null;
//		int minCount = Integer.MAX_VALUE;
//		for (Map.Entry<String, Integer> entry : partCount.entrySet()) {
//			if (entry.getValue() < minCount) {
//				minCount = entry.getValue();
//				leastUsedPart = entry.getKey();
//			}
//		}
//
//		if (leastUsedPart != null) {
//			return "무산소 운동 정보가 없습니다.";
//		}
//		return "추천 부위 : " + leastUsedPart;
//	}

	public Boolean isDailyExist(Date date) {
		for (DailyInfo dailyInfo : dailyInfos) {
			if (dailyInfo.getDate().equals(date))
				return true;
		}
		return false;
	}

	public Boolean isDailyFoodExist(Date date) {
		for (DailyInfo dailyInfo : dailyInfos) {
			if (dailyInfo.getDate().equals(date) && !dailyInfo.userFoodMgr.isEmpty())
				return true;
		}
		return false;
	}

	public Boolean isDailyExerciseExist(Date date) {
		for (DailyInfo dailyInfo : dailyInfos) {
			if (dailyInfo.getDate().equals(date) && !dailyInfo.userExerciseMgr.isEmpty())
				return true;
		}
		return false;
	}

	@Override
	public String toString() {
		String buf = "\n이름 : " + name + "\n키 : " + height + "\n몸무게 : " + weight + "\n성별 : " + gender + "\n목표 몸무게 : "
				+ goal + "\n";
		for (DailyInfo dailyResult : dailyInfos) {
			buf += dailyResult.toString();
		}
		return buf;
	}

	public DailyInfo getCurrentDailyInfo() { // 가장 최근 일과 반환
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
			userFoods = dailyInfo.userFoodMgr;
			buf += name + " " + dailyInfo.getDate().toString() + " " + userFoods.size() + "\n";
			for (UserFood userFood : userFoods) {
				buf += userFood.food.name + " " + userFood.size + "\n";
			}
		}

		return buf;
	}

	public String toStringforExerciseFile() {
		name = name.trim();
		String buf = "";
		ArrayList<UserExercise> userExercises;
		for (DailyInfo dailyInfo : dailyInfos) {
			userExercises = dailyInfo.userExerciseMgr;
			buf += name + " " + dailyInfo.getDate().toString() + " " + userExercises.size() + "\n";
			for (UserExercise userExercise : userExercises) {
				buf += userExercise.exercise.name + " " + userExercise.duration + "\n";
			}
		}

		return buf;
	}
	public void setProfileImagePath(String profileImagePath) {

		this.profileImagePath = profileImagePath;
	}
	public String getProfileImagePath() {
		return profileImagePath;
	}
}
