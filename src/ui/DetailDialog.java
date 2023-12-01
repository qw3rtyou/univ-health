package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import health.UserMgr;

public class DetailDialog extends javax.swing.JDialog {
	private static final long serialVersionUID = 1L;
	String[] userDetails;
	JLabel details[] = new JLabel[5];

	JPanel userFoodPane;
	TableSelectionDemo userFoodTable = new TableSelectionDemo();
	JPanel userExercisePane;
	TableSelectionDemo userExerciseTable = new TableSelectionDemo();

	DetailDialog(String[] texts) {
		super(GUIMain.frame);
		userDetails = texts;
	}

	void setup() {
		JTabbedPane jtab = new JTabbedPane();
		userFoodPane = new JPanel(new BorderLayout());
		userFoodTable.tableTitle = "userfood";
		userFoodTable.addComponentsToPane(UserMgr.getInstance());
		userFoodPane.add(userFoodTable, BorderLayout.CENTER);
		jtab.add("음식정보", userFoodPane);
		
		userExercisePane = new JPanel(new BorderLayout());
		userExerciseTable.tableTitle = "userexercise";
		userExerciseTable.addComponentsToPane(UserMgr.getInstance());
		userExercisePane.add(userExerciseTable, BorderLayout.CENTER);
		jtab.add("운동정보", userExercisePane);

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
		pane.add(lpane, BorderLayout.CENTER);
		pane.add(photo, BorderLayout.LINE_END);

		pane.add(jtab, BorderLayout.NORTH); // JTabbedPane를 pane에 추가

		this.setMinimumSize(new Dimension(400, 150));
		setContentPane(pane);
		setLocationRelativeTo(pane);
	}
}
