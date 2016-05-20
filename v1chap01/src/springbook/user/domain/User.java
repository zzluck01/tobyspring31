package springbook.user.domain;
/*
 * 작성자 : zzluck01
 * 작성일 : 2016년 5월 20일
 * 파일명 : User.java
 * 파일 내용 :
 * 		- 사용자 정보 저장 시 자바빈 규약 따르는 오브젝트.
 * 		- 사용자 정보를 저장 할 User클래스.
 * 		- id, name, password 세 개의 프로퍼티를 가진 User 클래스.
 * 		- 자바 빈이란?
 * 			!디폴트 생성자 : 자바 빈은 파라미터가 없는 디폴트 생성자를 갖고 있어야 함. 툴이나 프레임웤에서 리플렉션을 이용해 오브젝트 생성하기 때문.
 * 			!프로퍼티 : 자바빈이 노출하는 이름을 가진 속성. set으로 시작하는 수정자 메소드와 get으로 시작하는 접근자 메소드를 이용해 수정 조회 가능.
 */
public class User {
	String id;
	String name;
	String password;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
