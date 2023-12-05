package health;

import java.io.File;
import java.util.Scanner;

import facade.DataEngineImpl;
import java.util.ArrayList;
import java.util.List;

public class FoodMgr extends DataEngineImpl<Food>{
	private List<Food> foods;
	private static FoodMgr mgr = null;

	private FoodMgr() {
		setLabels(headers);
		foods = new ArrayList<>();
		readAll("data/food_data.txt");  // 음식 데이터 파일
	}


	public static FoodMgr getInstance() {
		if (mgr == null) 
			mgr = new FoodMgr();
		return mgr;
	}
	public List<Food> getFoods() {
		return foods;
	}
	String[] headers = {"음식", "탄수화물", "단백질", "지방"};
	private void readAll(String filename) {
		Scanner file = openFile(filename);
		while (file.hasNext()) {
			String name = file.next();
			String carbohydrate = file.next();
			String protein = file.next();
			String fat = file.next();
			foods.add(new Food(name, carbohydrate, protein, fat));
		}
		file.close();
	}
	public Scanner openFile(String filename) {
		Scanner filein = null;
		try {
			filein = new Scanner(new File(filename));
		} catch (Exception e) {
			System.out.println(filename + ": 파일 없음");
			System.exit(0);
		}
		return filein;
	}
}
