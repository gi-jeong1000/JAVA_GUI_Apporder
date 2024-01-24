package Apporder;

import java.util.ArrayList;
//Login시 필요한 유저의 데이터를 데이터베이스 형식으로 계속 저장
public class UserDataSet {
	private ArrayList<User> users; //유저 클래스 연동
	
	public UserDataSet() {
		users = new ArrayList<User>(); //유저 회원을 누적으로 데이터베이스 형식으로 저장
	}
	
	// 회원 추가
	public void addUsers(User user) { //각 인스턴스 받고 넘기기
	        users.add(user);
	}
    // 회원 삭제
	public void withdraw(String id) {
       users.remove(getUser(id));
    }
	// 유저 정보 가져오기
	public User getUser(String id) {
		return users.get(users.indexOf(new User(id)));
	}
	// 회원인지 아닌지 확인
	public boolean contains(User user) {
		return users.contains(user);
	}
	// 아이디 중복 확인
    public boolean isIdOverlap(String id) {
    	return users.contains(new User(id));
    }
}
