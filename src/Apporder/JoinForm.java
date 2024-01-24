package Apporder;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class JoinForm extends JDialog {
    private LoginForm owner;
    private UserDataSet users;

    
    private JLabel lblTitle;
    private JLabel lblId;
    private JLabel lblPw;
    private JLabel lblRe;
    private JLabel lblName;
    private JLabel lblEmail;
    private JRadioButton rbtnMale;
    private JRadioButton rbtnFemale;
    private JTextField tfId;
    private JPasswordField tfPw;
    private JPasswordField tfRe;
    private JTextField tfName;
    private JTextField tfEmail;
    private JButton btnJoin;
    private JButton btnCancel;

    
    
    public JoinForm(LoginForm owner) { //LoginForm을 부모 클래스로 설정.
        super(owner, "Join", true); //super()를 사용
        this.owner = owner;
        users = owner.getUsers(); //UserDataSet 클래스의 메소드 가져옴
        //화면 중앙으로 고정
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;
		int x = (screenWidth - 400) / 2;
		int y = (screenHeight - 500) / 2;
		setLocation(x,0);
        
        init();
        setDisplay();
        addListeners();
        showFrame();  
    }
    private void init() {
        // 크기 고정
        int tfSize = 10;
        Dimension lblSize = new Dimension(100, 45);
        Dimension btnSize = new Dimension(150, 45);

        //각 항목에 맞는 변수 설정 및 초기화
        lblTitle = new JLabel("- Input your information -");
        lblTitle.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        lblId = new JLabel("학번 ID", JLabel.LEFT);
        lblId.setPreferredSize(lblSize);
        lblPw = new JLabel("Password", JLabel.LEFT);
        lblPw.setPreferredSize(lblSize);
        lblRe = new JLabel("Retry", JLabel.LEFT);
        lblRe.setPreferredSize(lblSize);
        lblName = new JLabel("Name", JLabel.LEFT);
        lblName.setPreferredSize(lblSize);
        lblEmail = new JLabel("School E-mail", JLabel.LEFT);
        lblEmail.setPreferredSize(lblSize);
        //입력 받을 변수 설정 및 저장
        tfId = new JTextField(tfSize);
        tfPw = new JPasswordField(tfSize);
        tfRe = new JPasswordField(tfSize);
        tfName = new JTextField(tfSize);
        tfEmail = new JTextField(tfSize);

        rbtnMale = new JRadioButton("재학생", true);
        rbtnFemale = new JRadioButton("휴학생");
        ButtonGroup group = new ButtonGroup();
        group.add(rbtnMale);
        group.add(rbtnFemale);
        //버튼 생성
        btnJoin = new JButton("Join");
        btnJoin.setPreferredSize(btnSize);
        btnCancel = new JButton("Cancel");
        btnCancel.setPreferredSize(btnSize);

    }
    private void setDisplay() {
        // FlowLayout 가운데 정렬
        FlowLayout flowCenter = new FlowLayout(FlowLayout.CENTER);

        // pnlMain(pnlMNorth / pnlMCenter / pnlMSouth)
        JPanel pnlMain = new JPanel(new BorderLayout());

        // pnlMNorth(lblTitle)
        JPanel pnlMNorth = new JPanel(flowCenter);
        pnlMNorth.add(lblTitle);

        // pnlMCenter(pnlId / pnlPw / pnlRe / pnlName / pnlEmail)
        JPanel pnlMCenter = new JPanel(new GridLayout(0, 1));
        JPanel pnlId = new JPanel(flowCenter);
        pnlId.add(lblId);
        pnlId.add(tfId);

        JPanel pnlPw = new JPanel(flowCenter);
        pnlPw.add(lblPw);
        pnlPw.add(tfPw);

        JPanel pnlRe = new JPanel(flowCenter);
        pnlRe.add(lblRe);
        pnlRe.add(tfRe);

        JPanel pnlName = new JPanel(flowCenter);
        pnlName.add(lblName);
        pnlName.add(tfName);

        JPanel pnlEmail = new JPanel(flowCenter);
        pnlEmail.add(lblEmail);
        pnlEmail.add(tfEmail);

        pnlMCenter.add(pnlId);
        pnlMCenter.add(pnlPw);
        pnlMCenter.add(pnlRe);
        pnlMCenter.add(pnlName);
        pnlMCenter.add(pnlEmail);

        // pnlMSouth(rbtnMale / rbtnFemale)
        JPanel pnlMSouth = new JPanel(flowCenter);
        pnlMSouth.add(rbtnMale);
        pnlMSouth.add(rbtnFemale);
        pnlMSouth.setBorder(new TitledBorder("Asertain"));

        // pnlMain
        pnlMain.add(pnlMNorth, BorderLayout.NORTH);
        pnlMain.add(pnlMCenter, BorderLayout.CENTER);
        pnlMain.add(pnlMSouth, BorderLayout.SOUTH);

        // pnlSouth(btnJoin / btnCancel)
        JPanel pnlSouth = new JPanel(flowCenter);
        pnlSouth.add(btnJoin);
        pnlSouth.add(btnCancel);

        // 화면 테두리의 간격을 주기 위해 설정 (insets 사용 가능)
        pnlMain.setBorder(new EmptyBorder(0, 20, 0, 20));
        pnlSouth.setBorder(new EmptyBorder(0, 0, 10, 0));

        add(pnlMain, BorderLayout.NORTH);
        add(pnlSouth, BorderLayout.SOUTH);
    }


    
    private void addListeners() {
        //x창 눌렀을 때 창 꺼짐 메시지
    	addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                dispose();
                owner.setVisible(true);
            }
        });
        //Cancel 버튼 눌렀을 시
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                dispose();
                owner.setVisible(true);
            }
        });
        //Join 버튼 눌렀을 시
        btnJoin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                // 정보 하나라도 비어있으면
                if (isBlank()) {
                    JOptionPane.showMessageDialog(
                            JoinForm.this,
                            "모든 정보를 입력해주세요."
                    );
                    // 모두 입력했을 때
                } else {
                    // Id 중복일 때
                    String email = tfEmail.getText();
                    String id = tfId.getText();

                    if (users.isIdOverlap(tfId.getText())) {
                        JOptionPane.showMessageDialog(
                                JoinForm.this,
                                "이미 존재하는 Id입니다."
                        );
                        tfId.requestFocus();

                        // Pw와 Re가 일치하지 않았을 때
                    } else if (id.length() != 10) {
                        JOptionPane.showMessageDialog(
                                JoinForm.this,
                                "학번은 10자리여야 합니다. 다시 입력해주세요."
                        );
                        tfId.requestFocus();

                        // Pw와 Re가 일치하지 않았을 때
                    } else if (!String.valueOf(tfPw.getPassword()).equals(String.valueOf(tfRe.getPassword()))) {
                        JOptionPane.showMessageDialog(
                                JoinForm.this,
                                "Password와 Retry가 일치하지 않습니다."
                        );
                        tfPw.requestFocus();
                    } else if (!email.contains("dgu.ac.kr")) {
                        JOptionPane.showMessageDialog(
                                JoinForm.this,
                                "이메일 주소는 DGU 메일(dgu.ac.kr) 이어야 합니다. 다시 입력해주세요."
                        );
                        tfEmail.requestFocus();
                    } else {
                        users.addUsers(new User(
                                tfId.getText(),
                                String.valueOf(tfPw.getPassword()),
                                tfName.getText(),
                                tfEmail.getText(),
                                getAsertain())
                        );
                        JOptionPane.showMessageDialog(
                                JoinForm.this,
                                "회원가입을 완료했습니다!"
                        );
                        // 회원가입이 완료되었을 때 LoadingFrame을 띄웁니다.                      
                        LoadingFrame loadingFrame = new LoadingFrame();
                        loadingFrame.setVisible(true);
                       
                        loadingFrame.setAlwaysOnTop(true);  // 프레임을 최상위로 설정
                        
                        Timer timer =  new Timer(3000, new ActionListener() {
                        	@Override
                            public void actionPerformed(ActionEvent e) {
                                loadingFrame.dispose();
                                dispose();
                                owner.setVisible(true);
                            }
                        });
                        timer.setRepeats(false);
                        timer.start();
                    }
                }
            }
        });
    }
    //입력란이 비었을 때 메소드 생성
    public boolean isBlank() {
        boolean result = false;
        if(tfId.getText().isEmpty()) {
            tfId.requestFocus();
            return true;
        }
        if(String.valueOf(tfPw.getPassword()).isEmpty()) {
            tfPw.requestFocus();
            return true;
        }
        if(String.valueOf(tfRe.getPassword()).isEmpty()) {
            tfRe.requestFocus();
            return true;
        }
        if(tfName.getText().isEmpty()) {
            tfName.requestFocus();
            return true;
        }
        if(tfEmail.getText().isEmpty()) {
            tfEmail.requestFocus();
            return true;
        }
        return result;
    }
    
    public String getAsertain() {
        if(rbtnMale.isSelected()) {
            return rbtnMale.getText();
        }
        return rbtnFemale.getText();
    }

    private void showFrame() {
        pack();
        setSize(430, 800);
        setLocationRelativeTo(owner);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }
}