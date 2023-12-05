package ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import health.DailyInfo;
import health.Date;
import health.Food;
import health.FoodMgr;
import health.User;
import health.UserFood;
import health.UserMgr;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class AddUserFoodDialog extends javax.swing.JDialog {
	JDatePickerImpl datePicker;
	private static final long serialVersionUID = 1L;
	JTextField dateField;
	JComboBox<String> nameBox;
	JTextField sizeField;
	JButton addFoodButton;
	User user;

	public AddUserFoodDialog(User user) {
		this.user = user;
	}

	public void setup() {
		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put ("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model);
		datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());

		setTitle("음식추가");
		setLayout(new GridLayout(7, 2));

		dateField = new JTextField();
		nameBox = new JComboBox<>();
		for (Food food : FoodMgr.getInstance().getFoods()) {
			nameBox.addItem(food.getName());
		}
		sizeField = new JTextField();

		addFoodButton = new JButton("음식 추가");

		add(new JLabel("날짜"));
		add(datePicker);
		add(new JLabel("음식이름"));
		add(nameBox);
		add(new JLabel("먹은 양(g)"));
		add(sizeField);
		add(addFoodButton,BorderLayout.SOUTH);

		addFoodButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				java.util.Date selectedDate = (java.util.Date) datePicker.getModel().getValue();
				Date date = new Date(selectedDate.getYear()+1900, selectedDate.getMonth()+1, selectedDate.getDate());
				Food food = FoodMgr.getInstance().find((String) nameBox.getSelectedItem());
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