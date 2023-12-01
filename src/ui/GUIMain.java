package ui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import facade.DataEngineImpl;
import health.ExerciseMgr;
import health.FoodMgr;
import health.UserMgr;

public class GUIMain {
	
	/*
	 *	유저마다 유저운동, 음식 불러오기
	 *  데이터 추가시 바로 새로고침
	 * 	루틴 추천, 유저 프로필사진 추가
	 * 	디자인
	 * 	데이터 쓰기 구현 논의
	 */
	
	static UserMgr userMgr;
	
	private static GUIMain main = null;
	private GUIMain() {
	}
	public static GUIMain getInstance() {
		if (main == null)
			main = new GUIMain();
		return main;
	}
	
	static JFrame frame = new JFrame("건강관리");
	
	private void createAndShowGUI() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTabbedPane jtab = new JTabbedPane();
		
		setupUserPane();
		setupFoodPane();
		setupExercisePane();

		jtab.add("사용자" , userPane);
		jtab.add("음식", foodPane);
		jtab.add("운동", exercisePane);
		
		frame.getContentPane().add(jtab);
		frame.pack();
		frame.setLocationRelativeTo(jtab); // 화면 중앙에 표시
		frame.setVisible(true);
		
	}
	
	
	private JPanel userPane;
	TableSelectionDemo userTable = new TableSelectionDemo();
	UserTopPanel userTop = new UserTopPanel();
	
	private void setupUserPane() {
		userPane = new JPanel(new BorderLayout());
		userTable.tableTitle = "user";
		userTable.addComponentsToPane(UserMgr.getInstance());
		userTop.setupTopPane(userTable);
		userPane.add(userTop, BorderLayout.NORTH);
		userPane.add(userTable, BorderLayout.CENTER);
		
	}
	
	private JPanel foodPane;
	TableSelectionDemo foodTable = new TableSelectionDemo();
	FoodTopPanel foodTop = new FoodTopPanel();
	
	private void setupFoodPane() {
		foodPane = new JPanel(new BorderLayout());
		foodTable.tableTitle = "food";
		foodTable.addComponentsToPane(FoodMgr.getInstance());
		foodTop.setupTopPane(foodTable);
		foodPane.add(foodTop, BorderLayout.NORTH);
		foodPane.add(foodTable, BorderLayout.CENTER);
	}
	
	private JPanel exercisePane;
	TableSelectionDemo exerciseTable = new TableSelectionDemo();
	ExerciseBottomPanel exerciseBottom = new ExerciseBottomPanel();
	
	private void setupExercisePane() {
		exercisePane = new JPanel(new BorderLayout());
		exerciseTable.tableTitle = "exercise";
		exerciseTable.addComponentsToPane(ExerciseMgr.getInstance());
		exerciseBottom.setupBottomPane(exerciseTable);
		exercisePane.add(exerciseBottom, BorderLayout.SOUTH);
		exercisePane.add(exerciseTable, BorderLayout.CENTER);
	}
	
	
	public static void startGUI() {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() { GUIMain.getInstance().createAndShowGUI(); }
		});
	}
	
	DataEngineImpl<?> engine = FoodMgr.getInstance();
	public static void main(String[] args) { startGUI(); }
}
