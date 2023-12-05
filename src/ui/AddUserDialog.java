package ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import health.User;
import health.UserMgr;

public class AddUserDialog extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField nameField;
	private JTextField heightField;
	private JTextField weightField;
	private JTextField genderField;
	private JTextField goalField;
	private JButton addUserButton;
	private JLabel ImageField;
	private String profileImagePath;

	public void setup() {
		setTitle("유저 추가");
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		nameField = new JTextField();
		heightField = new JTextField();
		weightField = new JTextField();
		genderField = new JTextField();
		goalField = new JTextField();

		addUserButton = new JButton("유저 추가");
		ImageField = new JLabel();

		ImageField.setPreferredSize(new Dimension(300, 200));

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		add(new JLabel("이름"), c);
		c.gridx = 1;
		add(nameField, c);
		c.gridx = 0;
		c.gridy++;
		add(new JLabel("키"), c);
		c.gridx = 1;
		add(heightField, c);
		c.gridx = 0;
		c.gridy++;
		add(new JLabel("성별"), c);
		c.gridx = 1;
		add(genderField, c);
		c.gridx = 0;
		c.gridy++;
		add(new JLabel("몸무게"), c);
		c.gridx = 1;
		add(weightField, c);
		c.gridx = 0;
		c.gridy++;
		add(new JLabel("목표체중"), c);
		c.gridx = 1;
		add(goalField, c);
		c.gridx = 0;
		c.gridy++;
		add(new JLabel("프로필 사진"), c);
		c.gridx = 1;
		add(ImageField, c);
		c.gridx = 0;
		c.gridy++;
		add(addUserButton, c);



		addUserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = nameField.getText();
				double height = Double.parseDouble(heightField.getText());
				double weight = Double.parseDouble(weightField.getText());
				String gender = genderField.getText();
				int goal = Integer.parseInt(goalField.getText());

				User user = new User(name, height, weight, gender, goal);
				user.setProfileImagePath(profileImagePath);
				UserMgr.getInstance().addElement(user);
				dispose();
			}
		});
		ImageField.setPreferredSize(new Dimension(250, 250));
		JButton selectImageButton = new JButton("프로필 사진 선택");
		selectImageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				int result = fileChooser.showOpenDialog(AddUserDialog.this);
				if (result == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					profileImagePath = selectedFile.getAbsolutePath();
					ImageIcon imageIcon = new ImageIcon(profileImagePath);
					Image image = imageIcon.getImage();
					Image scaledImage = image.getScaledInstance(ImageField.getWidth(),
							ImageField.getHeight(), Image.SCALE_SMOOTH);
					ImageField.setIcon(new ImageIcon(scaledImage));
					ImageField.setIcon(imageIcon);
				}
			}
		});
		c.gridx = 1;
		add(selectImageButton, c);

		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args) {
		new AddUserDialog();
	}

}
