package ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import health.User;

@SuppressWarnings("serial")
public class UserFoodTopPanel extends JPanel {
	User user;

	public UserFoodTopPanel(User user) {
		this.user = user;
	}

	void setupTopPane(TableSelectionDemo tableDemo) {
		JPanel topPane = new JPanel();

		JButton addFood = new JButton("음식추가");
		topPane.add(addFood, BorderLayout.SOUTH);

		addFood.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equals("음식추가")) {
					tableDemo.addUserFood(user);
				}
			}
		});
	}
}