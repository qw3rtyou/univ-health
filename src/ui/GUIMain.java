package ui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import facade.DataEngineImpl;
import health.AerobicExerciseMgr;
import health.AnaerobicExerciseMgr;
import health.FoodMgr;
import health.UserMgr;

public class GUIMain {

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
		frame.setLocationRelativeTo(jtab);
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
		JTabbedPane exerciseTab = new JTabbedPane();

		TableSelectionDemo aerobicTable = new TableSelectionDemo();
		TableSelectionDemo anaerobicTable = new TableSelectionDemo();
		JPanel aerobicPane = new JPanel(new BorderLayout());
		JPanel anaerobicPane = new JPanel(new BorderLayout());

		// '유산소' 탭 설정
		aerobicTable.tableTitle = "aerobic";
		aerobicTable.addComponentsToPane(AerobicExerciseMgr.getInstance());
		// 이 부분을 ExerciseMgr.getInstance().getAerobicExercises()
		// 로 수정하려고 했는데, 오류 발생
		aerobicPane.add(aerobicTable, BorderLayout.CENTER);

		// '무산소' 탭 설정
		anaerobicTable.tableTitle = "anaerobic";
		anaerobicTable.addComponentsToPane(AnaerobicExerciseMgr.getInstance());
		// 이 부분을 ExerciseMgr.getInstance().getAnAerobicExercises()
		// 로 수정하려고 했는데, 오류 발생
		anaerobicPane.add(anaerobicTable, BorderLayout.CENTER);

		exerciseTab.add("유산소", aerobicPane);
		exerciseTab.add("무산소", anaerobicPane);

		exercisePane.add(exerciseTab, BorderLayout.CENTER);
	}
	
	
	public static void startGUI() {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() { GUIMain.getInstance().createAndShowGUI(); }
		});
	}
	
	DataEngineImpl<?> engine = FoodMgr.getInstance();
	public static void main(String[] args) { startGUI(); }
}
