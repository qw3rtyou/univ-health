package ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import health.DailyInfo;
import health.Date;
import health.Exercise;
import health.ExerciseMgr;
import health.User;
import health.UserExercise;

public class AddUserExerciseDialog extends javax.swing.JDialog {
	private static final long serialVersionUID = 1L;
	JTextField dateField;
	JTextField nameField;
	JTextField sizeField;
	JButton addFoodButton;
	User user;

	public AddUserExerciseDialog(User user) {
		this.user = user;
	}

	public void setup() {

		setTitle("운동추가");
		setLayout(new GridLayout(6, 2));

		dateField = new JTextField();
		nameField = new JTextField();
		sizeField = new JTextField();

		addFoodButton = new JButton("운동 추가");

		add(new JLabel("날짜"));
		add(dateField);
		add(new JLabel("운동이름"));
		add(nameField);	
		add(new JLabel("운동 시간"));
		add(sizeField);
		add(addFoodButton);

		addFoodButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] tmp = dateField.getText().split(" ");
				Date date = new Date(Integer.parseInt(tmp[0]), Integer.parseInt(tmp[1]), Integer.parseInt(tmp[2]));
				Exercise exercise = ExerciseMgr.getInstance().find(nameField.getText());
				double size = Double.parseDouble(sizeField.getText());

				UserExercise userExercise = new UserExercise(exercise, user.weight, (int) size, date);

				if (user.isDailyExerciseExist(date))
					user.findDaily(date).userExerciseMgr.add(userExercise);
				else {
					DailyInfo dailyInfo = new DailyInfo(date);
					user.dailyInfos.add(dailyInfo);
				}

				dispose();
			}
		});
	}

}
