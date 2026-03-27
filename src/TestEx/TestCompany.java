package TestEx;

import java.time.LocalDateTime;
import java.util.Scanner;

interface Cp {
	void input();
	void process();
	void output();
}
//Field Class
class CmpVo {
	//입력
	private int cnum;
	private String name;
	private String joinDate;
	private double pay;
	private int division;
	//출력
	private double bonus;
	private double rpay;
	private String divisionName;

	//Constructor
	public CmpVo(int cnum, String name, String joinDate, double pay, int divison) {
		this.cnum = cnum;
		this.name = name;
		this.joinDate = joinDate;
		this.pay = pay;
		this.division = divison;
	}
	
	// Getter/Setter
	public int getCnum() {
		return cnum;
	}

	public void setCnum(int cnum) {
		this.cnum = cnum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJoinDate() {
		return joinDate;
	}
	public double getPay() {
		return pay;
	}
	public int getDivision() {
		return division;
	}

	public void setDivision(int division) {
		this.division = division;
	}

	public double getBonus() {
		return bonus;
	}
	
	public double getRpay() {
		return rpay;
	}

	public String getDivisionName() {
		return divisionName;
	}

	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}
	
	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}

	public void setPay(double pay) {
		this.pay = pay;
	}

	public void setBonus(double bonus) {
		this.bonus = bonus;
	}

	public void setRpay(double rpay) {
		this.rpay = rpay;
	}

	@Override
	public String toString() {
		return "Cmp [cnum=" + cnum + ", name=" + name + ", joinDate=" + joinDate + ", pay=" + pay + ", division="
				+ division + ", bonus=" + bonus + ", divisionName=" + divisionName + "]";
	}
}

//업무처리 class
class Cmp implements Cp {
	
	//이 코드를 사용하는 이유 : 데이터를 하나로 묶어서 cp 에 담아둠
	private CmpVo cp;

	@Override
	public void input() {
		Scanner sc = new Scanner(System.in);
		System.out.println("입력: 사번, 이름, 입사일, 월급, 부서번호");
		String line = sc.nextLine();
		String[] li = line.trim().split(",");
		int cnum = Integer.parseInt(li[0].trim());
		String name = li[1].trim();
		String joinDate = li[2].trim();
		double pay = Double.parseDouble(li[3].trim());
		int division = Integer.parseInt(li[4].trim());
		
		cp = new CmpVo(cnum, name, joinDate, pay, division);
	}

	@Override
	public void process() {
		//보너스
		int tY = LocalDateTime.now().getYear(); // 현재 연도 = 현재 날짜, 분/초 에서 연도만 출력한 값
		int jY = Integer.parseInt(cp.getJoinDate().substring(0, 4)); //입사 연도 = 입사일에서 0번부터 4번까지 자른 값
		int years = tY - jY; //현재 연도 - 입사 연도
		double pay = cp.getPay();
		double bonus = years * pay * 0.005; //근속연수 * 월급 * 0.5%
		cp.setBonus(bonus);
		//수령액
		double rpay = cp.getPay() + bonus; // 월급 + 보너스
		cp.setRpay(rpay);
 		//division -> divisonName
		int division = cp.getDivision();
		String divisionName = "";
		switch (division) {
		case 10: 
			divisionName = "인사";
			break;
		case 20:
			divisionName = "자재";
			break;
		case 30:
			divisionName = "총무";
			break;
		case 40:
			divisionName = "연구개발";
			break;
		case 50:
			divisionName = "생산";
			break;
		case 60:
			divisionName = "서비스";
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + division);
		}
		cp.setDivisionName(divisionName);
	}
	@Override
	public void output() {
		System.out.println(cp);
		String title = "사번 이름 입사일 월급 보너스 수령액 부서명";
		String fmt = "%d %s %s %.2f %.2f %.2f %s";
		System.out.println(title);
		System.out.printf(fmt, cp.getCnum(), cp.getName(),cp.getJoinDate(), cp.getPay(), 
				cp.getBonus(), cp.getRpay(), cp.getDivisionName());
	}
}
public class TestCompany {
	public static void main(String[] args) {
		Cmp cp = new Cmp();
		cp.input();
		cp.process();
		cp.output();
	}
}
