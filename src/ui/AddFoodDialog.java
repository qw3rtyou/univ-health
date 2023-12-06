package ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import health.Food;
import health.FoodMgr;

public class AddFoodDialog extends javax.swing.JDialog {
	private static final long serialVersionUID = 1L;
	JTextField nameField;
	JTextField carbField;
	JTextField proteinField;
	JTextField fatField;
	JButton addFoodButton;

	public void setup() {


		setTitle("음식추가");
		setLayout(new GridLayout(5, 2));

		nameField = new JTextField();
		carbField = new JTextField();
		proteinField = new JTextField();
		fatField = new JTextField();
		addFoodButton = new JButton("음식 추가");

		add(new JLabel("이름:"));
		add(nameField);
		add(new JLabel("탄수화물"));
		add(carbField);
		add(new JLabel("단백질"));
		add(proteinField);
		add(new JLabel("지방"));
		add(fatField);
		add(addFoodButton);

		addFoodButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = nameField.getText();
				String carb = carbField.getText();
				String prot = proteinField.getText();
				String fat = fatField.getText();

				Food food = new Food(name, carb, prot, fat);
				FoodMgr.getInstance().addElement(food);
				dispose();
			}
		});
	}

}
