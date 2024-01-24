package Apporder;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class result extends JFrame {
    private JLabel confirmationLabel;
    private JLabel ticketLabel;

    public result() {
        setTitle("주문 완료"); // 창 제목 설정
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE); // 창 닫기 버튼 동작 설정
        setSize(430, 360); // 창 크기 설정
        setLocationRelativeTo(null); // 창을 화면 중앙에 위치시킴
        Font font;
        Font boldFont;
        //전체 판넬
        ImagePanel container = new ImagePanel(new ImageIcon("./images/학교.png").getImage()); // 배경 이미지 설정(현재 생략) 
        container.setLayout(new GridLayout(4, 1)); // 4x1 그리드 레이아웃 설정
        //NorthPanel
        JPanel NorthPanel = new JPanel();
        NorthPanel.setLayout(new FlowLayout()); //위 판넬은 플로우레이아웃 설정
        ImageIcon titleicon = new ImageIcon("./images/title.png"); //타이틀에 들어갈 이미지
        Image titleimg = titleicon.getImage();
        titleimg = titleimg.getScaledInstance(300, 30, Image.SCALE_SMOOTH); //이미지 리사이즈
        titleicon.setImage(titleimg);
        JLabel highbar = new JLabel(titleicon, SwingConstants.CENTER); //가운데 위치하게 설정
        highbar.setHorizontalAlignment(JLabel.CENTER); 
        NorthPanel.add(highbar, BorderLayout.CENTER);
        NorthPanel.setPreferredSize(new Dimension(430, 10)); // NorthPanel의 크기 설정
        container.add(NorthPanel); // NorthPanel 추가
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //프레임 중앙으로 띄우기
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;
		int x = (screenWidth - 400) / 2;
		int y = (screenHeight - 500) / 2;
		setLocation(x,0);
        
        //ConfirmationLabel
        confirmationLabel = new JLabel();
        confirmationLabel.setHorizontalAlignment(SwingConstants.CENTER); //텍스트 중앙에 오게 하기
        String confirmationMessage = "<html><center><h3>주문이 완료되었습니다.</h3><br>이용해주셔서 감사합니다.<br>주문번호</center></html>"; //html태그를 이용하여 크기 및 중앙 정렬
        confirmationLabel.setText(confirmationMessage);
        confirmationLabel.setSize(300, 300);
        container.add(confirmationLabel, BorderLayout.NORTH); //위치설정

        //TicketLabel
        ticketLabel = new JLabel();
        int ticketNumber = generateTicketNumber(); // 주문번호 생성
        String ticketMsg = "" + ticketNumber;
        Font resultfont = ticketLabel.getFont(); // 현재 폰트 가져오기
        Font newFont = resultfont.deriveFont(80f); // 원하는 크기로 폰트 생성
        ticketLabel.setFont(newFont); // 폰트 설정
        ticketLabel.setText(ticketMsg);
        ticketLabel.setHorizontalAlignment(JLabel.CENTER);
        container.add(ticketLabel, BorderLayout.SOUTH); //아래쪽 위치

        //buttonLabel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10)); // 버튼을 가운데 정렬하는 패널
        buttonPanel.setOpaque(false); // 버튼 패널의 배경을 투명하게 설정

        JButton backButton = new JButton("처음 화면으로 돌아가기");
        backButton.setPreferredSize(new Dimension(180, 30)); // 버튼의 크기 조정
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FirstPage FirstPage = new FirstPage(); // LoginForm 클래스의 인스턴스 생성
               FirstPage.setVisible(true); // LoginForm을 보이도록 설정
                dispose(); // 현재 페이지(result)를 닫음
            }
        });
        
        addWindowListener(new WindowAdapter() { // 상단에 x버튼 눌러도 바로 꺼지지 않고 확인 창 뜨도록 함
            @Override
            public void windowClosing(WindowEvent we) {
                int choice = JOptionPane.showConfirmDialog(
                        result.this,
                        "동국대학교 앱 오더 로그인 프로그램을 종료합니다.",
                        "종료",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.WARNING_MESSAGE
                );
                if (choice == JOptionPane.OK_OPTION) {
                    we.getWindow().dispose(); // 창을 닫음
                }
            }
        });
        
        buttonPanel.add(backButton);

        JButton exitButton = new JButton("종료");
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int choice = JOptionPane.showConfirmDialog(result.this, "동국대학교 앱 오더 로그인 프로그램을 종료합니다.",
                        "종료", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
                if (choice == JOptionPane.YES_OPTION) {
                    System.exit(0); // 프로그램 종료
                }
            }
        });
        exitButton.setPreferredSize(new Dimension(180, 30)); // 버튼의 크기 조정
        buttonPanel.add(exitButton);
        container.add(buttonPanel);

        getContentPane().add(container); // 컨텐트 팬에 container 패널 추가
        setVisible(true); // 창을 보이도록 설정
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("font/NanumSquareRoundB.ttf")); // 외부 폰트 파일 로드
            boldFont = font.deriveFont(Font.PLAIN, 14); // 볼드 폰트 설정

            confirmationLabel.setFont(boldFont);

        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
    }

    private int generateTicketNumber() {
        Random random = new Random();
        return random.nextInt(100) + 1; // 1부터 100 사이의 난수 생성
    }

    public static void main(String[] args) {

        // LoginForm의 tfId를 전달하여 result 객체 생성
        new result();
    }

    // 배경 이미지를 그리기 위한 커스텀 패널
    class ImagePanel extends JPanel {
        private Image backgroundImage;

        public ImagePanel(Image image) {
            this.backgroundImage = image;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this); // 배경 이미지 그리기
        }
    }
}