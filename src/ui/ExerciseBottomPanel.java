package ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ExerciseBottomPanel extends JPanel {

	void setupBottomPane(TableSelectionDemo tableDemo) {

		JPanel topPane = new JPanel();

		JButton addExercise = new JButton("추가");
		topPane.add(addExercise, BorderLayout.SOUTH);
		add(topPane, BorderLayout.PAGE_START);

		addExercise.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equals("유저 추가")) {
					tableDemo.addExercise();
				}
			}
		});
	}
}