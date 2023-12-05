package ui;

import health.User;
import health.UserMgr;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserEditDialog extends JDialog {
    private JTextField nameField, heightField, genderField, weightField, targetWeightField;
    private User user;

    public UserEditDialog(User user) {
        this.user = user;
        setLayout(new GridLayout(6, 2));

        nameField = new JTextField(user.getName());
        heightField = new JTextField(String.valueOf(user.getHeight()));
        genderField = new JTextField(user.getGender());
        weightField = new JTextField(String.valueOf(user.getWeight()));
        targetWeightField = new JTextField(String.valueOf(user.getTargetWeight()));

        add(new JLabel("이름: "));
        add(nameField);
        add(new JLabel("키: "));
        add(heightField);
        add(new JLabel("성별: "));
        add(genderField);
        add(new JLabel("몸무게: "));
        add(weightField);
        add(new JLabel("목표체중: "));
        add(targetWeightField);

        JButton saveButton = new JButton("저장");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                user.setName(nameField.getText());
                user.setHeight(Double.parseDouble(heightField.getText()));
                user.setGender(genderField.getText());
                user.setWeight(Double.parseDouble(weightField.getText()));
                user.setTargetWeight(Double.parseDouble(targetWeightField.getText()));

                UserMgr.getInstance().updateUser(user);

                dispose();
            }
        });

        add(new JLabel(""));
        add(saveButton);
    }
}
