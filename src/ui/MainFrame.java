package ui;

import health.ExerciseMgr;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    public MainFrame() {
        ImageIcon startImage = new ImageIcon("data/main/title2.jpg");
        JButton startButton = new JButton(startImage);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            //    GUIMain.startGUI(); // "시작하기" 버튼을 누르면 GUIMain 화면을 시작
                dispose();
            }
        });

        add(startButton); // "시작하기" 버튼을 프레임에 추가

        setSize(340, 800); // 프레임의 크기 설정
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 닫기 버튼을 누르면 프로그램이 종료되도록 설정
    }

//    public static void main(String[] args) {
//        ExerciseMgr exerciseMgr = ExerciseMgr.getInstance();
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new MainFrame().setVisible(true); // 메인 프레임을 보이도록 설정
//            }
//
//        });
//    }
}
