package ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import health.User;
import health.UserMgr;

public class AddUserInfo extends javax.swing.JDialog {
	private static final long serialVersionUID = 1L;
	JTextField nameField;
	JTextField heightField;
	JTextField weightField;
	JTextField genderField;
	JTextField goalField;
	JButton addUserButton;
	
	public void setup() {
		
		
		setTitle("음식추가");
		setLayout(new GridLayout(6, 2));
		
		nameField = new JTextField();
		heightField = new JTextField();
		weightField = new JTextField();
		genderField = new JTextField();
		goalField = new JTextField();
		
		addUserButton = new JButton("유저 추가");
		
		add(new JLabel("이름")); 		add(nameField);
		add(new JLabel("키")); 		add(heightField);
		add(new JLabel("성별"));		add(genderField);
		add(new JLabel("몸무게"));	add(weightField);
		add(new JLabel("목표체중"));	add(goalField);
		add(addUserButton);
		
		addUserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = nameField.getText();
				double height = Double.parseDouble(heightField.getText());
				double weight = Double.parseDouble(weightField.getText());
				String gender  = genderField.getText();
				int goal = Integer.parseInt(goalField.getText());
				
				User user = new User(name, height, weight, gender, goal);
				UserMgr.getInstance().addElement(user);
				/*nameField.setText("");
				heightField.setText("");;
				weightField.setText("");
				genderField.setText("");
				goalField.setText("");;*/
				dispose();
				
			}
		});
	}	
	
	

}
