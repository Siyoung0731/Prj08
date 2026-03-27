package ExArray;

class Member {
	// Field
	private int num;
	private String name;
	private String tel;

	// Constructor
	public Member() {
	}

	public Member(int num, String name, String tel) {
		this.num = num;
		this.name = name;
		this.tel = tel;
	}
	// Object는 최상위 클래스, equals 는 메모리 참조 주소를 비교하는 메소드
	// Override - 재정의 
	public boolean equals(Object obj) {
		Member other = (Member) obj;
		return num == other.num;
//		return Objects.equals(name, other.name);
	}
	@Override
	public String toString() {
		return "Member [num=" + num + ", name=" + name + ", tel=" + tel + "]";
	}

}

public class ObjectArray {
	public static void main(String[] args) {
		// 여러명의 정보를 입력 받아
		// 검색 객체를 만들어서 해당 멤버를 찾는다.

		/*
		 * Member m1 = new Member(1, "민지", "010-1234-5678"); 
		 * Member m2 = new Member(2, "원영", "010-1111-5555"); 
           Member m3 = new Member(3, "윈터", "010-2222-6666");
		 * Member m4 = new Member(4, "유진", ""); // 빈 문자열 - empty String 
		 * Member m5 = new Member(5, "유나", "010-4444-8888"); 
		 * Member m6 = new Member(6, "닝닝", null); //Null
		 * 
		 * System.out.println(m1); System.out.println(m2); System.out.println(m3);
		 * System.out.println(m4); System.out.println(m5); System.out.println(m6);
		 */

		Member[] mArr = new Member[6];

		mArr[0] = new Member(1, "민지", "010-1234-5678");
		mArr[1] = new Member(2, "원영", "010-1111-5555");
		mArr[2] = new Member(3, "윈터", "010-2222-6666");
		mArr[3] = new Member(4, "유진", "");
		mArr[4] = new Member(5, "유나", "010-4444-8888");
		mArr[5] = new Member(6, "닝닝", null);
		
		for (Member member : mArr) {
			System.out.println(member);
		}
		System.out.println("============");
		
		// 찾을 사람의 정보
		Member mem = new Member(7, null, null);
		boolean isFound = false;
		for (int i = 0; i < mArr.length; i++) {
			Member member = mArr[i];
//			if(member == mem) {
//				System.out.println(member);
//			} 
			if(member.equals(mem)) {
				System.out.println(member);
				isFound = true;
				break; // 반복문을 탈출
			} 
		}
		// 못찾음
		if(!isFound)
			System.out.println("못찾음");
	}

}