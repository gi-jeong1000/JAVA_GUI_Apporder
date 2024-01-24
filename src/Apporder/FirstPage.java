package Apporder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class FirstPage extends JFrame{
   
   
    Font font1 = new Font(Font.MONOSPACED, Font.BOLD, 13);
    Font font2 = new Font(Font.MONOSPACED, Font.CENTER_BASELINE, 25);
    
    int count;
    int total=0;
    int col=0;
    int row=0;
    String contents = "";
    

    
   public FirstPage() {
      Container container = getContentPane();
      setTitle("동국대학교 학식 AppOrder");
      ImageIcon titleicon = new ImageIcon("./images/title.png"); //타이틀에 들어갈 이미지
      Image titleimg= titleicon.getImage();
      titleimg= titleimg.getScaledInstance(300, 30, Image.SCALE_SMOOTH); //이미지 리사이즈
      titleicon.setImage(titleimg);
      JPanel NorthPanel = new JPanel();
      NorthPanel.setBackground(Color.orange); // 배경색 설정
      NorthPanel.setLayout(new BorderLayout());

      JLabel highbar = new JLabel(titleicon,SwingConstants.CENTER);
      highbar.setHorizontalAlignment(JLabel.CENTER);
      highbar.setFont(font2);
      NorthPanel.add(highbar, BorderLayout.NORTH);
      
      addWindowListener(new WindowAdapter() {
    	    @Override
    	    public void windowClosing(WindowEvent we) {
    	        int choice = JOptionPane.showConfirmDialog(
    	                FirstPage.this,
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


      
      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      int screenWidth = screenSize.width;
      int x = (screenWidth - 400) / 2;
      setLocation(x,0);
      
      JButton button1 = new JButton("상록원");
      button1.setEnabled(false);
      JButton button2 = new JButton("기숙사식당");
      button2.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
             SecondPage secondPage = new SecondPage();  // SecondPage 클래스의 인스턴스 생성
              secondPage.setVisible(true);  // SecondPage를 보이도록 설정
              dispose();  // 현재 페이지(FirstPage)를 닫음
          }
      });
      JButton button3 = new JButton("가든쿡");
      button3.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
             ThirdPage ThirdPage = new ThirdPage();  // SecondPage 클래스의 인스턴스 생성
              ThirdPage.setVisible(true);  // SecondPage를 보이도록 설정
              dispose();  // 현재 페이지(FirstPage)를 닫음
          }
      });
      
      Panel buttonPanel = new Panel();
      buttonPanel.setLayout(new FlowLayout());
      buttonPanel.add(button1);
      buttonPanel.add(button2);
      buttonPanel.add(button3);

      NorthPanel.add(buttonPanel, BorderLayout.CENTER);

      Panel CenterPanel = new Panel();
      CenterPanel.setLayout(null);
      CenterPanel.setBackground(Color.LIGHT_GRAY);
      String menu[] = {"불닭철판", "얼큰국밥", 
            "수육국밥", "제육솥밥", "  우동", " 짜장면"};      
      int price[] = {6000, 5500, 5500, 5000, 4000, 5500};
      
      JButton bt_menu[] = new JButton[menu.length];
        TextField num[] = new TextField[menu.length];
        Button minus[] = new Button[menu.length];
        Button plus[] = new Button[menu.length];
        JButton ok[] = new JButton[menu.length];
        Label won[] = new Label[menu.length];
        Label name[] = new Label[menu.length];
        Color[] color = {new Color(190,170,50), new Color(150, 100, 0), new Color(255,255,0), new Color(173, 52, 125)};
        
        int rows = (int) Math.ceil(menu.length / 3.0); // Calculate the number of rows based on the number of menus
        
        for (int i = 0; i < menu.length; i++) {
           int row = i / 3; // Calculate the row index based on the menu index
           int col = i % 3; // Calculate the column index based on the menu index
           bt_menu[i] = new JButton(new ImageIcon("Images/" + menu[i].trim() + ".png"));
           bt_menu[i].setBackground(color[0]);

           // 이미지 리사이즈
           ImageIcon imageIcon = (ImageIcon) bt_menu[i].getIcon();
           Image image = imageIcon.getImage();
           Image resizedImage = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);

           // 리사이즈된 이미지로 ImageIcon 생성
           ImageIcon resizedIcon = new ImageIcon(resizedImage);

           // 버튼에 이미지 설정
           bt_menu[i].setIcon(resizedIcon);
           bt_menu[i].setPreferredSize(new Dimension(100, 100));
           bt_menu[i].setSize(100, 100);
           bt_menu[i].setRolloverEnabled(false); //사진에 마우스 올려도 강조 안되도록
           bt_menu[i].setBounds(25 + col * 130, 40 + row * 210, 100, 100);
           
           name[i] = new Label(menu[i]);
           name[i].setFont(font1);
           name[i].setBounds(bt_menu[i].getX()+20, bt_menu[i].getY() - 20, 115, 20);
           
           num[i] = new TextField("0");
            num[i].setBackground(Color.white);
            num[i].setEditable(false);
            num[i].setBounds(bt_menu[i].getX() + 30, bt_menu[i].getY() + 130, 40, 20);
            
            minus[i] = new Button("-");
            minus[i].setBounds(bt_menu[i].getX(), num[i].getY(), 20, 20);
            minus[i].setEnabled(true);
            
            plus[i] = new Button("+");
            plus[i].setBounds(bt_menu[i].getX() + (100 - 20), num[i].getY(), 20, 20);
            plus[i].setEnabled(true);
            
            won[i] = new Label(price[i] + "원");
            won[i].setBounds(bt_menu[i].getX() + 30, num[i].getY() - 25, 100, 20);
            
            ok[i] = new JButton("확인");
            ok[i].setBounds(bt_menu[i].getX(), num[i].getY() + 30, 100, 20);
            ok[i].setEnabled(false);
            
            CenterPanel.add(name[i]);
            CenterPanel.add(bt_menu[i]);
            CenterPanel.add(num[i]);
            CenterPanel.add(minus[i]);
            CenterPanel.add(plus[i]);
            CenterPanel.add(won[i]);
            CenterPanel.add(ok[i]);
        }
        
        // 배경 이미지
        String imagePath = "./images/apple.png";

        // Panel 생성
        JPanel SouthPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                // 배경 이미지 그리기
                Image backgroundImg = new ImageIcon(imagePath).getImage();
                g.drawImage(backgroundImg, 0, 0, getWidth(), getHeight(), null);
                super.paintComponent(g);
            }
        };
        TextArea txt = new TextArea();
     // TextArea 배경 투명 설정
        txt.setBackground(new Color(0, 0, 0, 0));
      
        String[][] data = new String[0][0];
        String[] title = {"상품명", "단가", "수량", "합계", "총 금액"};
        DefaultTableModel model = new DefaultTableModel(data, title);
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(430, 200)); // Set the preferred size of the scroll pane
        SouthPanel.add(scrollPane);
        
        Panel SelectPanel = new Panel();
        SelectPanel.setLayout(new GridLayout(1, 3, 20, 0));
        JButton order[] = new JButton[3];
      
        order[0] = new JButton("종료");
        order[1] = new JButton("리셋");
        order[2] = new JButton("주문");
        order[0].setBackground(Color.WHITE);
        order[1].setBackground(Color.WHITE);
        order[2].setBackground(Color.WHITE);
        order[0].setVerticalTextPosition(SwingConstants.CENTER);
        order[1].setVerticalTextPosition(SwingConstants.CENTER);
        order[2].setVerticalTextPosition(SwingConstants.CENTER);
        
        SelectPanel.add(order[0]);
        SelectPanel.add(order[1]);
        SelectPanel.add(order[2]);
      
        // Close button
        order[0].addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	int choice = JOptionPane.showConfirmDialog(
                        FirstPage.this,
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
       
        // Reset button
        order[1].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               model.setNumRows(0); // Clear the order history
               txt.setText("");
               total=0;
            }
        });
 
        // Order button
        order[2].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               int answer = JOptionPane.showConfirmDialog(null, "주문하시겠습니까?", "Order",JOptionPane.YES_NO_OPTION);
               if(answer == JOptionPane.YES_OPTION) {
                  if(total == 0) {	// 아무것도 선택 되지 않았을 때
                     JOptionPane.showMessageDialog(null, "선택 항목이 존재하지 않습니다.");
                  } else {
              		  LoginForm loginForm = new LoginForm();	// loginForm 객체 생성
              		  loginForm.setVisible(false);
              		  
                      // 새로운 결제창 생성
                  	  JFrame payFrame = new JFrame("Recipe");
                  	  payFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                  	  payFrame.setLayout(new BorderLayout());
                      
                  	  // Recipe 제목 생성
              		  JLabel titleLabel = new JLabel("Recipe");
            		  titleLabel.setHorizontalAlignment(SwingConstants.CENTER);		// 화면 중앙 배치
            		  titleLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30)); // 폰트 크기를 16으로 변경
                      
                      JPanel panel = new JPanel();	// 주문내역이 들어갈 panel 
                      panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // 수직으로 컴포넌트를 배치하기 위해 설정
              		  for(int i=0; i<table.getRowCount(); i++) { // 내용을 label에 추가해주기 위함
              			String result = table.getValueAt(i, 0)+" "+table.getValueAt(i, 1)+" ... "+table.getValueAt(i, 2)+"개\n";
              			JLabel label = new JLabel(result);
              			label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 19)); // 폰트 크기 변경
              		 	panel.add(label);
              	   	  }
              		  
                      JLabel tab = new JLabel("\t");	// 한줄 간격 만들기
              		  panel.add(tab);
              		  
                      JLabel price = new JLabel("총 금액 ... " + total+" 원");		// total 금액이 들어갈 label
                      price.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 19)); 	// 폰트 변경
                      panel.add(price);
                      
                      JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));	// 내용이 중앙에 정렬
                      int padding = 40; // 여백의 크기
                      EmptyBorder emptyBorder = new EmptyBorder(padding, padding, padding, padding);	// padding 설정 
                      centerPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK), emptyBorder));	// border 설정
                      centerPanel.add(panel);
                      
                      // 버튼 생성 및 크기 조절
                      JButton cardButton = new JButton("카드결제");
                      JButton cashButton = new JButton("현금결제");
                      JButton closeButton = new JButton("이전으로");
                      cardButton.setPreferredSize(new Dimension(110, 50));
                      cashButton.setPreferredSize(new Dimension(110, 50));
                      closeButton.setPreferredSize(new Dimension(110, 50));
                      
	              	  JPanel buttonPanel = new JPanel();
	            	  // 패널에 버튼 추가
	            	  buttonPanel.add(cardButton);
	            	  buttonPanel.add(cashButton);
	            	  buttonPanel.add(closeButton);
	            	  
	            	  // payFrame에 패널 추가
	            	  payFrame.add(titleLabel, BorderLayout.NORTH);
	            	  payFrame.add(centerPanel, BorderLayout.CENTER);
	            	  payFrame.add(buttonPanel, BorderLayout.SOUTH);

                      payFrame.setSize(430, 400);
	            	  payFrame.setLocation(x, getY());
                      payFrame.setVisible(true);
                      
                      // 카드결제 버튼 클릭시
                      cardButton.addActionListener(new ActionListener() {        	 
                          @Override
                          public void actionPerformed(ActionEvent e) {
                        	  payFrame.dispose();	// 주문내역을 종료
                        	  dispose();
                        	  // 결과화면 가져오기
                        	  result result = new result(); 
                		      result.setVisible(true); 
                        	  // 주문이 완료되었을 때 LoadingFrame을 띄웁니다.
                   			  LoadingFrame loadingFrame = new LoadingFrame();
                              loadingFrame.setVisible(true);
                              Timer timer =  new Timer(3000, new ActionListener() {
	                              public void actionPerformed(ActionEvent e) {
	                            	  loadingFrame.dispose();
	                            	  dispose();
	                                  
	                               }
                              });
                          }
                      });
                      // 현금결제 버튼 클릭 ( 카드결제와 동일 )
                      cashButton.addActionListener(new ActionListener() {        	 
                    	  @Override
                          public void actionPerformed(ActionEvent e) {
                        	  payFrame.dispose();
                        	  dispose();
                        	  result result = new result(); 
                		      result.setVisible(true); 
                        	  // 주문이 완료되었을 때 LoadingFrame을 띄웁니다.
                   			  LoadingFrame loadingFrame = new LoadingFrame();
                              loadingFrame.setVisible(true);
                              Timer timer =  new Timer(3000, new ActionListener() {
	                              public void actionPerformed(ActionEvent e) {
	                            	  loadingFrame.dispose();
	                            	  dispose();
	                                  
	                               }
                              });
                          }
                      });
                      // 닫기 버튼 클릭시
                      closeButton.addActionListener(new ActionListener() {        	 
                          @Override
                          public void actionPerformed(ActionEvent e) {
                          	payFrame.dispose();	// 창 닫기
                          }
                      });
	                  total=0;
	                  txt.setText("");
	                  model.setNumRows(0);   
            	}
               } else {
                  JOptionPane.showMessageDialog(null, "메뉴 선택 단계로 돌아갑니다.\n");
               }

                for (int i = 0; i < menu.length; i++) {
                    num[i].setText("0");
                }
            }
        });
       
        for (int i = 0; i < menu.length; i++) {
            int j = i;
 
            bt_menu[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    count = 0;
                }
            });
 
            minus[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (count > 0) {
                        count = count - 1;
                        num[j].setText(count + "");
                        ok[j].setEnabled(true);
                    } else {
                        minus[j].setEnabled(false);
                    }
                }
            });
            
            plus[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    count = count + 1;
                    num[j].setText(count + "");
                    ok[j].setEnabled(true);
                    if (count > 0) {
                        minus[j].setEnabled(true);
                    }
                }
            });
            
            ok[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                   num[j].setText("0");
                   total += price[j] * count;
 
                   String inputStr[] = new String[5];
                   
                   inputStr[0] = menu[j];
                   inputStr[1] = price[j] + "원";
                   inputStr[2] = "" + count;
                   inputStr[3] = price[j] * count + "원";
                   inputStr[4] = total + "원";
                   model.addRow(inputStr);
                   
                   count = 0;
                   ok[j].setEnabled(false);
                }
            });
        }

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        container.add(NorthPanel, BorderLayout.NORTH);
        container.add(CenterPanel, BorderLayout.CENTER);
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.add(SouthPanel, BorderLayout.NORTH);
        bottomPanel.add(SelectPanel, BorderLayout.CENTER);
        container.add(bottomPanel, BorderLayout.SOUTH);
        setSize(430, 800);
      setVisible(true);
   }
   
   
   public static void main(String[] args) {
        new FirstPage();
    }
}