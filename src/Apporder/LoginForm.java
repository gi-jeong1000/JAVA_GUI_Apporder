package Apporder;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class LoginForm extends JFrame {
    private UserDataSet users;

    private JLabel lblId;
    private JLabel lblPw;
    private JTextField tfId;
    private JPasswordField tfPw;
    private JButton btnLogin;
    private JButton btnJoin;
   
    class MyPanel extends JPanel {
        //이미지 초기 생성
    	private Image backgroundImage;
        public MyPanel(Image backgroundImage) {
            this.backgroundImage = backgroundImage;
        }
        //이미지 위치
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    public LoginForm() {
    	//화면 중앙으로 고정
    	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;
		int x = (screenWidth - 400) / 2;
		int y = (screenHeight - 500) / 2;
		setLocation(x,0);
    	users = new UserDataSet();
        init();
        setDisplay();
        addListeners();
        showFrame();
    }

    public void init() {
        Dimension lblSize = new Dimension(140, 40); // 더 큰 크기로 변경
        int tfSize = 15;
        Dimension btnSize = new Dimension(150, 45);
        //글씨 삽입 및 설정
        lblId = new JLabel("학번 ID");
        lblId.setPreferredSize(lblSize);
        lblId.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 19)); // 폰트 크기를 16으로 변경
        
        lblPw = new JLabel("Password");
        lblPw.setPreferredSize(lblSize);
        lblPw.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 19)); // 폰트 크기를 16으로 변경
     
        tfId = new JTextField(tfSize);
        tfPw = new JPasswordField(tfSize);

        btnLogin = new JButton("Login");
        btnLogin.setPreferredSize(btnSize);
        btnJoin = new JButton("Join");
        btnJoin.setPreferredSize(btnSize);
    }
    //UserDataSet 클래스 불러오기
    public UserDataSet getUsers() {
        return users;
    }
    //ID 입력 받고 메소드로 추출하여 받아오기
    public String getTfId() {
        return tfId.getText();
    }
    //화면 설정
    public void setDisplay() {
        //이미지 삽입
    	MyPanel panel = new MyPanel(new ImageIcon("Images/LoginImage.jpg").getImage());
        panel.setLayout(new BorderLayout());

        JPanel pnlNorth = new JPanel(new GridLayout(0, 1));
        pnlNorth.setOpaque(false);
        /* 위의 코드는 비밀번호를 입력받기 위한 패널을 생성하고, 
         * 해당 패널에 라벨과 텍스트 필드를 추가하는 역할을 합니다. 
         * 패널을 사용하여 컴포넌트들을 그룹화하고 배치할 수 있으며, 
         * 투명한 배경을 설정하여 디자인적인 요소를 추가*/
        JPanel pnlId = new JPanel();
        pnlId.setOpaque(false);
        pnlId.add(lblId);
        pnlId.add(tfId);

        JPanel pnlPw = new JPanel();
        pnlPw.setOpaque(false);
        pnlPw.add(lblPw);
        pnlPw.add(tfPw);

        pnlNorth.add(pnlId);
        pnlNorth.add(pnlPw);

        JPanel pnlSouth = new JPanel();
        pnlSouth.setOpaque(false);
        pnlSouth.add(btnLogin);
        pnlSouth.add(btnJoin);

        panel.add(pnlNorth, BorderLayout.NORTH);
        panel.add(pnlSouth, BorderLayout.SOUTH);

        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        add(panel);
    }

    public void addListeners() {
        //Join버튼 누르면 창 꺼지고 JoinForm 프레임 워크를 띄움
    	btnJoin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new JoinForm(LoginForm.this);
                tfId.setText("");
                tfPw.setText("");
            }
        });

        btnLogin.addActionListener(new ActionListener() {
            //Login 버튼을 눌렀을 시 다양한 경우의 수
        	@Override
            public void actionPerformed(ActionEvent e) {
                if (tfId.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(LoginForm.this,
                            "학번 아이디를 입력하세요.",
                            "동국대학교 앱 오더 로그인폼",
                            JOptionPane.WARNING_MESSAGE);
                } else if (users.contains(new User(tfId.getText()))) {
                    if (String.valueOf(tfPw.getPassword()).isEmpty()) {
                        JOptionPane.showMessageDialog(
                                LoginForm.this,
                                "비밀번호를 입력하세요.",
                                "동국대학교 앱 오더 로그인폼",
                                JOptionPane.WARNING_MESSAGE);
                    } else if (!users.getUser(tfId.getText()).getPw().equals(String.valueOf(tfPw.getPassword()))) {
                        JOptionPane.showMessageDialog(
                                LoginForm.this,
                                "비밀번호가 일치하지 않습니다.");
                    } else {
                        InformationForm infoForm = new InformationForm(LoginForm.this);
                        infoForm.setTaCheck(users.getUser(tfId.getText()).toString());
                        setVisible(false);
                        infoForm.setVisible(true);
                        tfId.setText("");
                        tfPw.setText("");
                    }
                } else {
                    JOptionPane.showMessageDialog(
                            LoginForm.this,
                            "존재하지 않는 Id입니다."
                    );
                }
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            //x버튼 눌러서 종료 시
            public void windowClosing(WindowEvent we) {
                int choice = JOptionPane.showConfirmDialog(
                        LoginForm.this,
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
    }

    public void showFrame() {
    	setTitle("Dongguk App Order Login");
        pack();
        setSize(430, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) {
        LoginForm loginForm = new LoginForm();
        SwingUtilities.invokeLater(() -> loginForm.showFrame());
        /*이벤트 디스패치 스레드에서 LoginForm 객체의 showFrame() 메서드를 실행하도록 예약하는 역할을 합니다. 
         * 이를 통해 LoginForm의 UI가 올바르게 표시되고 사용자에게 보여집니다.*/
    }

}