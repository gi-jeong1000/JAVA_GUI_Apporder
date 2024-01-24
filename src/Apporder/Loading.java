package Apporder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class LoadingFrame extends JFrame {
    ImageIcon imageIcon = new ImageIcon("Images/Loading.gif");

    LoadingFrame() { //생성자 
        this.setTitle("Loading...Please Wait");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        //화면 중앙으로 고정
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;
		int x = (screenWidth - 400) / 2;
		int y = (screenHeight - 500) / 2;
		setLocation(x,0);
        
        JLabel label = new JLabel(imageIcon);
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(label, BorderLayout.CENTER);
        //Cancel 버튼
        JButton btnCancel = new JButton("Cancel");
        btnCancel.setPreferredSize(new Dimension(200, 50));
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // 프레임 닫기
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setOpaque(false); // 배경 투명 설정
        buttonPanel.add(btnCancel);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        this.add(panel);
        this.setSize(430, 800);
        this.setVisible(true);

        int delay = 3000; // 3초 대기
        Timer timer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // 프레임 닫기
            }
        });
        timer.setRepeats(false); // 한 번만 실행하도록 설정
        timer.start();
    }
}

public class Loading {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LoadingFrame();
            }
        });
    }
}

