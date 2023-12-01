package ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class FoodTopPanel extends JPanel {
	
	JTextField kwdTextField = new JTextField("", 20);
	JTextField edit;
	
	void setupTopPane(TableSelectionDemo tableDemo) {
		JPanel topPane = new JPanel();
		
		/*
		JButton detail = new JButton("상세보기");
		topPane.add(detail, BorderLayout.LINE_START);
		*/
		
		topPane.add(kwdTextField, BorderLayout.CENTER);
		
		JButton search = new JButton("검색");
		topPane.add(search, BorderLayout.LINE_END);
		add(topPane, BorderLayout.PAGE_START);
		
		JButton addFood = new JButton("음식추가");
		topPane.add(addFood, BorderLayout.SOUTH);
		
		/*
		detail.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equals("상세보기")) {
					tableDemo.showDetail();
				}
			}
		});
		*/
		
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equals("검색")) {
					tableDemo.loadData(kwdTextField.getText());
				}
			}
		});
		
		addFood.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equals("음식추가")) {
					tableDemo.addFood();
		
				}
			}
		});
	}

}
