package Apporder;
//User는 사용자 정보를 저장하는 데 사용되는 클래스
class User {
    private String id;
    private String pw;
    private String name;
    private String eMail;
    private String asertain;
    //각 입력받고 저장 할 인스턴스 설정
    public User(String id, String pw, String name, String eMail, String asertain) {
        setId(id);
        setPw(pw);
        setName(name);
        seteMail(eMail);
        setasertain(asertain);
    }
    public User(String id) { //id 매개변수를 받아 사용자 객체를 생성합니다. setId(id) 메서드를 호출하여 id 값을 설정합니다.
        setId(id);
    }

    public String getId() { //저장된 id 값을 반환합니다.
        return id;
    }
    public void setId(String id) { //id 매개변수를 받아 객체의 id 필드를 설정합니다.
        this.id = id;
    }
    public String getPw() {
        return pw;
    }
    public void setPw(String pw) { //pw 매개변수를 받아 객체의 pw 필드를 설정합니다.
        this.pw = pw;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String geteMail() {
        return eMail;
    }
    public void seteMail(String eMail) {
        this.eMail = eMail;
    }
    public String getasertain() {
        return asertain;
    }
    public void setasertain(String asertain) {
        this.asertain = asertain;
    }
    @Override
    /*객체 간의 동등성을 정확하게 비교하기 위해 오버라이딩된 equals() 메서드를 제공하는 것입니다. 
     * 이를 통해 객체의 내용을 기준으로 동등성을 판단할 수 있으며, 
     * 필요한 경우 객체를 검색하거나 컬렉션에 저장하는 등의 작업을 수행할 수 있습니다.
     */
    public boolean equals(Object o) {
        if(o == null || !(o instanceof User)) {
            return false;
        }
        User temp = (User)o;

        return id.equals(temp.getId());
    }
    
    @Override
    //추후에 InformationForm에서 화면을 구성하는 텍스트 및 각 입력 값들을 저장 및 통합
    public String toString() {
        String info = "School ID: " + id + "\n";
        info += "PassWord: " + pw + "\n";
        info += "Name: " + name + "\n";
        info += "e-Mail: " + eMail + "\n";
        info += "Asertain: " + asertain + "\n";
        return info;
    }
}
