package ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;


@SuppressWarnings("serial")
public class UserTopPanel extends JPanel {


	JTextField kwdTextField = new JTextField("검색어 입력", 20);
	
void setupTopPane(TableSelectionDemo tableDemo) {
		
		JPanel topPane = new JPanel();
		JButton detail = new JButton("유저정보");
		topPane.add(detail, BorderLayout.LINE_START);
		
		topPane.add(kwdTextField, BorderLayout.CENTER);
		JButton search = new JButton("검색");
		topPane.add(search , BorderLayout.LINE_END);
				
		JButton regist = new JButton("유저 추가");
		topPane.add(regist, BorderLayout.SOUTH);
		add(topPane, BorderLayout.SOUTH);
		
		detail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equals("유저정보")) {
					tableDemo.showDetail();				}
			}
		});
		
		search.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (e.getActionCommand().equals("검색")) {
        			tableDemo.loadData(kwdTextField.getText());
            	}
           }
        });
		
		regist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equals("유저 추가")) {
					tableDemo.addUser();
				}
			}
		});
		
	}
}
