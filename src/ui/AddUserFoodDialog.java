package ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import health.DailyInfo;
import health.Date;
import health.Food;
import health.FoodMgr;
import health.User;
import health.UserFood;
import health.UserMgr;

public class AddUserFoodDialog extends javax.swing.JDialog {
	private static final long serialVersionUID = 1L;
	JTextField dateField;
	JTextField nameField;
	JTextField sizeField;
	JButton addFoodButton;
	User user;

	public AddUserFoodDialog(User user) {
		this.user = user;
	}

	public void setup() {

		setTitle("음식추가");
		setLayout(new GridLayout(6, 2));

		dateField = new JTextField();
		nameField = new JTextField();
		sizeField = new JTextField();

		addFoodButton = new JButton("음식 추가");

		add(new JLabel("날짜"));
		add(dateField);
		add(new JLabel("음식이름"));
		add(nameField);
		add(new JLabel("먹은 양(g)"));
		add(sizeField);
		add(addFoodButton,BorderLayout.SOUTH);

		addFoodButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] tmp = dateField.getText().split(" ");
				Date date = new Date(Integer.parseInt(tmp[0]), Integer.parseInt(tmp[1]), Integer.parseInt(tmp[2]));
				Food food = FoodMgr.getInstance().find(nameField.getText());
				double size = Double.parseDouble(sizeField.getText());

				UserFood userFood = new UserFood(food, (int) size, date);

				if (user.isDailyFoodExist(date))
					user.findDaily(date).userFoodMgr.add(userFood);
				else {
					DailyInfo dailyInfo = new DailyInfo(date);
					dailyInfo.userFoodMgr.add(userFood);
					user.dailyInfos.add(dailyInfo);
				}

				dispose();
			}
		});
	}

}
