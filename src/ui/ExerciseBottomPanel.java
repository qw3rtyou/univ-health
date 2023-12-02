package ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class ExerciseBottomPanel extends JPanel {

	JTextField kwdTextField = new JTextField("", 20);
	JTextField edit;

	void setupBottomPane(TableSelectionDemo tableDemo) {

		JPanel bottomPane = new JPanel();

		bottomPane.add(kwdTextField, BorderLayout.CENTER);

		JButton search = new JButton("검색");
		bottomPane.add(search, BorderLayout.LINE_END);

		JButton addExercise = new JButton("운동추가");
		bottomPane.add(addExercise, BorderLayout.SOUTH);
		add(bottomPane, BorderLayout.PAGE_START);

		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equals("검색")) {
					tableDemo.loadData(kwdTextField.getText());
				}
			}
		});

		addExercise.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equals("운동 추가")) {
					tableDemo.addExercise();
				}
			}
		});
	}
}