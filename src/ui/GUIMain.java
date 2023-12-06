package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import health.AerobicExerciseMgr;
import health.AnaerobicExerciseMgr;
import health.ExerciseMgr;
import health.FoodMgr;
import health.Health;
import health.UserMgr;

public class GUIMain {

	static UserMgr userMgr;

	private static GUIMain main = null;

	private GUIMain() {
	}

	public static GUIMain getInstance() {	//static 추가
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

		jtab.add("사용자", userPane);
		jtab.add("음식", foodPane);
		jtab.add("운동", exercisePane);

		frame.setPreferredSize(new Dimension(900,800));
		frame.getContentPane().add(jtab);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		frame.addWindowListener(new WindowAdapter() {	// 훅 메소드 받기 - 종료시 state 저장
			public void windowClosing(WindowEvent e) {
				Health.getInstance().saveCurrentState();
			}
		});
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
		aerobicPane.add(aerobicTable, BorderLayout.CENTER);

		// '무산소' 탭 설정
		anaerobicTable.tableTitle = "anaerobic";
		anaerobicTable.addComponentsToPane(AnaerobicExerciseMgr.getInstance());
		anaerobicPane.add(anaerobicTable, BorderLayout.CENTER);

		// '전체' 탭 설정
		TableSelectionDemo totalTable = new TableSelectionDemo();
		JPanel totalPane = new JPanel(new BorderLayout());
		totalTable.tableTitle = "total";
		totalTable.addComponentsToPane(ExerciseMgr.getInstance()); // 이 부분은 유산소와 무산소 운동 데이터를 합친 결과를 가져오는 것으로 수정해야 합니다.
		totalPane.add(totalTable, BorderLayout.CENTER);
		
		exerciseTab.add("전체",totalPane);
		exerciseTab.add("유산소", aerobicPane);
		exerciseTab.add("무산소", anaerobicPane);

		exerciseTable.tableTitle = "exercise";
		exerciseTable.addComponentsToPane(ExerciseMgr.getInstance());
		exerciseBottom.setupBottomPane(totalTable);
		exercisePane.add(exerciseBottom, BorderLayout.NORTH);
		exercisePane.add(exerciseTab, BorderLayout.CENTER);
	}

	public static void startGUI() {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				GUIMain.getInstance().createAndShowGUI();
			}
		});
	}

//	public static void main(String[] args) {
//		startGUI();
//	}
}
