package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import health.DailyInfo;
import health.User;
import health.UserExercise;
import health.UserExerciseMgr;
import health.UserFood;
import health.UserFoodMgr;
import health.UserMgr;

public class UserDetailDialog extends javax.swing.JDialog {
	private static final long serialVersionUID = 1L;
	String[] userDetails;
	JLabel details[] = new JLabel[5];

	JPanel userFoodPane;
	TableSelectionDemo userFoodTable = new TableSelectionDemo();
	JPanel userExercisePane;
	TableSelectionDemo userExerciseTable = new TableSelectionDemo();
	UserFoodTopPanel userFoodTopPanel;

	UserDetailDialog(String[] texts) {
		super(GUIMain.frame);
		userDetails = texts;
	}

//	private JPanel foodPane;
//	TableSelectionDemo foodTable = new TableSelectionDemo();
//	FoodTopPanel foodTop = new FoodTopPanel();
//
//	private void setupFoodPane() {
//		foodPane = new JPanel(new BorderLayout());
//		foodTable.tableTitle = "food";
//		foodTable.addComponentsToPane(FoodMgr.getInstance());
//		foodTop.setupTopPane(foodTable);
//		foodPane.add(foodTop, BorderLayout.NORTH);
//		foodPane.add(foodTable, BorderLayout.CENTER);
//	}

	void setup() {
		User user = UserMgr.getInstance().find(userDetails[0]);

		JTabbedPane jtab = new JTabbedPane();
		UserExerciseMgr tmpExerciseMgr = new UserExerciseMgr();
		UserFoodMgr tmpUserFoodMgr = new UserFoodMgr();

		for (DailyInfo dailyInfo : user.dailyInfos) {
			for (UserExercise userExercise : dailyInfo.userExerciseMgr) {
				tmpExerciseMgr.add(userExercise);
			}
			for (UserFood userfood : dailyInfo.userFoodMgr) {
				tmpUserFoodMgr.add(userfood);
			}
		}

		userExercisePane = new JPanel(new BorderLayout());
		userExerciseTable.tableTitle = "userexercise";
		userExerciseTable.addComponentsToPane(tmpExerciseMgr);
		userExercisePane.add(userExerciseTable, BorderLayout.CENTER);
		jtab.add("운동정보", userExercisePane);

		userFoodPane = new JPanel(new BorderLayout());
		userFoodTable.tableTitle = "userfood";
		userFoodTable.addComponentsToPane(tmpUserFoodMgr);
		userFoodPane.add(userFoodTable, BorderLayout.CENTER);
		jtab.add("음식정보", userFoodPane);
		

		JPanel BottomPane = new JPanel();

		JButton addFoodButton = new JButton("음식추가");
		BottomPane.add(addFoodButton, BorderLayout.EAST);

		addFoodButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equals("음식추가")) {
					userFoodTable.addUserFood(user);
				}
			}
		});

		JButton addExerciseButton = new JButton("운동추가");
		BottomPane.add(addExerciseButton, BorderLayout.WEST);

		addExerciseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equals("운동추가")) {
					userExerciseTable.addUserExercise(user);
				}
			}
		});
		
		setTitle("유저정보");
		JPanel pane = new JPanel(new BorderLayout());
		JPanel lpane = new JPanel(new GridLayout(5, 1));
		JLabel photo = new JLabel("photo");
		photo.setOpaque(true);
		photo.setPreferredSize(new Dimension(150, 150));
		photo.setBackground(Color.yellow);
		details[0] = new JLabel("이름: " + userDetails[0]);
		details[1] = new JLabel("키: " + userDetails[1]);
		details[2] = new JLabel("성별: " + userDetails[2]);
		details[3] = new JLabel("몸무게: " + userDetails[3]);
		details[4] = new JLabel("목표체중: " + userDetails[4]);
		lpane.add(details[0]);
		lpane.add(details[1]);
		lpane.add(details[2]);
		lpane.add(details[3]);
		lpane.add(details[4]);
		
		pane.add(BottomPane, BorderLayout.SOUTH);
		pane.add(lpane, BorderLayout.CENTER);
		pane.add(photo, BorderLayout.LINE_END);
		pane.add(jtab, BorderLayout.NORTH);

		this.setMinimumSize(new Dimension(400, 150));
		setContentPane(pane);
		setLocationRelativeTo(pane);
	}
}
