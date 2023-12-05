package ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FoodTopPanel extends JPanel {

	JTextField kwdTextField = new JTextField("검색어 입력", 20);
	JTextField edit;

	void setupTopPane(TableSelectionDemo tableDemo) {
		JPanel topPane = new JPanel();
		topPane.add(kwdTextField, BorderLayout.CENTER);

		JButton search = new JButton("검색");
		topPane.add(search, BorderLayout.LINE_END);
		add(topPane, BorderLayout.PAGE_START);

		JButton addFood = new JButton("음식추가");
		topPane.add(addFood, BorderLayout.SOUTH);

		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equals("검색")) {
					tableDemo.loadData(kwdTextField.getText());
				}
			}
		});

		addFood.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equals("음식추가")) {
					tableDemo.addFood();

				}
			}
		});
	}

}
