package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import health.User;

@SuppressWarnings("serial")
public class UserRecommandDialog extends JDialog {

    public UserRecommandDialog(User user) {
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // 마진 추가

        JLabel exerciseLabel = new JLabel(user.exerciseRecommend());
        exerciseLabel.setFont(new Font("", Font.BOLD, 12)); // 폰트 변경
        exerciseLabel.setForeground(Color.black); // 텍스트 색상 변경
        exerciseLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // 패딩 추가
        contentPanel.add(exerciseLabel, BorderLayout.SOUTH);

        JLabel foodLabel = new JLabel(user.foodRecommend());
        foodLabel.setFont(new Font("", Font.BOLD, 12));
        foodLabel.setForeground(Color.black); 
        foodLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); 
        contentPanel.add(foodLabel, BorderLayout.NORTH);

        add(contentPanel);
        pack(); 
        setLocationRelativeTo(null); 
        setVisible(true);
    }
}