package Apporder;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class InformationForm extends JDialog {
    private LoginForm owner; 
    private FirstPage owner2;
    private UserDataSet users;
    private JTextArea taCheck;
    private JButton btnOrder;
    private JButton btnLogout;
    private JButton btnWithdraw;
    private JoinForm joinForm;
    
    /*InformationForm 클래스의 생성자가 부모 다이얼로그로 LoginForm을 설정하고, 
     * 부모 윈도우에서 사용자 정보를 가져와서 users 변수에 저장합니다. 
     * 이를 통해 InformationForm은 LoginForm의 정보에 접근할 수 있게 됩니다.*/
    public InformationForm(LoginForm owner) { 
        super(owner, "Information", true); //super()를 이용하여 명시적으로 슈퍼 클래스 생성자 선택, owner는 InformationForm의 부모 윈도우인 LoginForm 객체를 가리킵니다.
        this.owner = owner;
        users = owner.getUsers(); 
        /*owner 객체의 getUsers() 메서드를 호출하여 사용자 정보를 가져옵니다. 
        owner는 LoginForm 객체이므로, getUsers() 메서드는 LoginForm에서 사용자 정보를 반환하는 메서드*/

        // 1. 화면 초기화
        init();

        // 2. 화면 구성 설정
        setDisplay();

        // 3. 이벤트 리스너 추가
        addListeners();

        // 4. 정보 창 보이기
        showFrame();

    }
    private void init() {
        Dimension btnsize = new Dimension(100, 25);

        taCheck = new JTextArea(10, 30);
        taCheck.setEditable(false);
        //버튼 생성
        btnOrder = new JButton("Order"); 
        btnOrder.setPreferredSize(btnsize);
        
        btnLogout = new JButton("Logout");
        btnLogout.setPreferredSize(btnsize);

        btnWithdraw = new JButton("Withdraw");
        btnWithdraw.setPreferredSize(btnsize);
    }
    private void setDisplay() {

        LineBorder lBorder = new LineBorder(Color.GRAY, 1);
        TitledBorder border = new TitledBorder(lBorder, "check your Information");
        taCheck.setBorder(border);

        JPanel pnlSouth = new JPanel();
        pnlSouth.add(btnOrder);
        pnlSouth.add(btnLogout);
        pnlSouth.add(btnWithdraw);

        JPanel pnlMain = new JPanel(new BorderLayout());
        pnlMain.add(new JScrollPane(taCheck), BorderLayout.NORTH);
        pnlMain.add(pnlSouth, BorderLayout.CENTER);

        add(pnlMain,BorderLayout.CENTER);
    }
    private void addListeners() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                dispose();
            }
        });

        btnWithdraw.addActionListener(new ActionListener() {
            @Override
            //withdraw 버튼 클릭 시
            public void actionPerformed(ActionEvent ae) {
                users.withdraw(owner.getTfId());
                JOptionPane.showMessageDialog(
                        InformationForm.this,
                        "회원 정보가 삭제되었습니다. 안녕히가세요."
                );
                dispose();
                owner.setVisible(true);
            }
        });
        btnLogout.addActionListener(new ActionListener() {
            @Override
            //Logout 버튼 클릭 시
            public void actionPerformed(ActionEvent ae) {
                JOptionPane.showMessageDialog(
                        InformationForm.this,
                        "로그아웃 되었습니다."
                );
                dispose();
                owner.setVisible(true);
            }
        });
       
        btnOrder.addActionListener(new ActionListener() {
            @Override
            //Order 버튼 클릭 시
            public void actionPerformed(ActionEvent ae) {
            	FirstPage FirstPage = new FirstPage();  // FirstPage 클래스의 인스턴스 생성
		        FirstPage.setVisible(true);  // FirstPage를 보이도록 설정
		        dispose();  // 현재 페이지(InformationForm)를 닫음
		    }
        });
    }
    //user클래스에서 Info 가져온 후 텍스트로 설정
    public void setTaCheck(String userInfo) {
        taCheck.setText(userInfo);
    }

    private void showFrame() {
        pack();
        setSize(430, 800);
        setLocationRelativeTo(owner);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE); 
        setResizable(false);
    }
}
