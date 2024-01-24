package Apporder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class RecipePage extends JFrame {
	public RecipePage() {
		setTitle("총 구매 영수증 다시 확인");
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int x = (screenWidth - 400) / 2;
        setLocation(x,0);
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                int choice = JOptionPane.showConfirmDialog(
                        RecipePage.this,
                        "동국대학교 앱 오더 로그인 프로그램을 종료합니다.",
                        "종료",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.WARNING_MESSAGE
                );
                if (choice == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });	
		Container contentPane = getContentPane();
		
		contentPane.setLayout(new FlowLayout());
		
		//버튼 센터 상단고정 (프레임 커져도 상단에 고정)
		JButton button1 = new JButton("카드결제");
		button1.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	result result = new result();  // SecondPage 클래스의 인스턴스 생성
		        result.setVisible(true);  // SecondPage를 보이도록 설정
		        dispose();  // 현재 페이지(FirstPage)를 닫음 // 현재 페이지(FirstPage)를 닫음
		    }
		});
		
		JButton button2 = new JButton("현장결제(현금)");
		button2.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	result result = new result();  // SecondPage 클래스의 인스턴스 생성
		        result.setVisible(true);  // SecondPage를 보이도록 설정
		        dispose();  // 현재 페이지(FirstPage)를 닫음
		    }
		});
		
		Panel buttonPanel = new Panel();
	    buttonPanel.setLayout(new FlowLayout());
	   
	    contentPane.add(button1);
        contentPane.add(button2);
		setSize(430, 800);
		setVisible(true);
	}

	public static void main(String[] args) {
		new RecipePage();
	}
}
