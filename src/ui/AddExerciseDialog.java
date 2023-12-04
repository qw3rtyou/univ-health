package ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import health.Exercise;
import health.ExerciseMgr;

public class AddExerciseDialog extends javax.swing.JDialog {
	private static final long serialVersionUID = 1L;
	JTextField nameField;
	JTextField typeField;
	JTextField metsField;
	JTextField partField;
	JButton addExerciseButton;

	public void setup() {

		setTitle("운동추가");
		setLayout(new GridLayout(5, 2));

		nameField = new JTextField();
		typeField = new JTextField();
		metsField = new JTextField();
		partField = new JTextField();
		addExerciseButton = new JButton("운동 추가");

		add(new JLabel("이름:"));
		add(nameField);
		add(new JLabel("유/무산소"));
		add(typeField);
		add(new JLabel("METs"));
		add(metsField);
		add(new JLabel("부위"));
		add(partField);
		add(addExerciseButton);

		addExerciseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = nameField.getText();
				String type = typeField.getText();
				Double mets = Double.parseDouble(metsField.getText());
				String part = partField.getText();

				Exercise exercise = new Exercise(name, type, mets, part);
				ExerciseMgr.getInstance().addElement(exercise);
				/*
				 * nameField.setText(""); carbField.setText("");; proteinField.setText("");
				 * fatField.setText("");
				 */
				dispose();
			}
		});
	}

}
