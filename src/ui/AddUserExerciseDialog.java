package ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import health.DailyInfo;
import health.Date;
import health.Exercise;
import health.ExerciseMgr;
import health.User;
import health.UserExercise;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class AddUserExerciseDialog extends javax.swing.JDialog {
	JDatePickerImpl datePicker;
	private static final long serialVersionUID = 1L;
	JTextField dateField;
	JComboBox<String> nameBox;
	JTextField sizeField;
	JButton addFoodButton;
	User user;

	public AddUserExerciseDialog(User user) {
		this.user = user;
		setup();
	}

	public void setup() {
		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put ("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model);
		datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());

		setTitle("운동추가");
		setLayout(new GridLayout(16, 2));

		sizeField = new JTextField();

		addFoodButton = new JButton("운동 추가");

		nameBox = new JComboBox<>();

		JRadioButton aerobicButton = new JRadioButton("유산소");
		JRadioButton anaerobicButton = new JRadioButton("무산소");
		ButtonGroup group = new ButtonGroup();
		group.add(aerobicButton);
		group.add(anaerobicButton);

		aerobicButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nameBox.removeAllItems();
				for (Exercise exercise : ExerciseMgr.getInstance().getExercisesByType("유산소")) {
					nameBox.addItem(exercise.getName());
				}
			}
		});

		anaerobicButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nameBox.removeAllItems();
				for (Exercise exercise : ExerciseMgr.getInstance().getExercisesByType("무산소")) {
					nameBox.addItem(exercise.getName());
				}
			}
		});

		add(new JLabel("날짜"));
		add(datePicker);
		add(new JLabel("운동 타입"));
		add(aerobicButton);
		add(anaerobicButton);
		add(new JLabel("운동이름"));
		add(nameBox);
		add(new JLabel("운동 시간(분)"));
		add(sizeField);

		JPanel buttonPanel = new JPanel(new BorderLayout());
		buttonPanel.add(addFoodButton, BorderLayout.CENTER);
		add(new JLabel(""));
		add(buttonPanel);

		addFoodButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				java.util.Date selectedDate = (java.util.Date) datePicker.getModel().getValue();
				Date date = new Date(selectedDate.getYear()+1900, selectedDate.getMonth()+1, selectedDate.getDate());
				Exercise exercise = ExerciseMgr.getInstance().find((String) nameBox.getSelectedItem());
				double size = Double.parseDouble(sizeField.getText());

				UserExercise userExercise = new UserExercise(exercise, user.weight, (int) size, date);

				if (user.isDailyExerciseExist(date))
					user.findDaily(date).userExerciseMgr.add(userExercise);
				else {
					DailyInfo dailyInfo = new DailyInfo(date);
					dailyInfo.userExerciseMgr.add(userExercise);
					user.dailyInfos.add(dailyInfo);
				}

				dispose();
			}
		});
	}
}