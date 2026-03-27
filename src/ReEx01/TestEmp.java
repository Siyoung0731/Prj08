package ReEx01;

import java.time.LocalDateTime;
import java.util.Scanner;

//interface - 구현
interface Ep {
	void input();
	void process();
	void output();
}
class EmpVo {
	//전역 변수 - Field Data
	private int num;
	private String name;
	private String hiredDate;
	private double salary;
	private int deptNum;
	
	private double bonus;
	private double pay;
	private String deptName;
	
	//Constructor - 인자가 있는 생성자
	public EmpVo(int num, String name, String hiredDate, double salary, int deptNum) {
		this.num = num;
		this.name = name;
		this.hiredDate = hiredDate;
		this.salary = salary;
		this.deptNum = deptNum;
	}
	// Getter/Setter
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHiredDate() {
		return hiredDate;
	}

	public void setHiredDate(String hiredDate) {
		this.hiredDate = hiredDate;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public int getDeptNum() {
		return deptNum;
	}

	public void setDeptNum(int deptNum) {
		this.deptNum = deptNum;
	}

	public double getBonus() {
		return bonus;
	}

	public void setBonus(double bonus) {
		this.bonus = bonus;
	}

	public double getPay() {
		return pay;
	}

	public void setPay(double pay) {
		this.pay = pay;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	// toString을 재정의
	@Override
	public String toString() {
		return "EmpVo [num=" + num + ", name=" + name + ", hiredDate=" + hiredDate + ", salary=" + salary + ", deptNum="
				+ deptNum + ", bonus=" + bonus + ", pay=" + pay + ", deptName=" + deptName + "]";
	}
	
	
}
//업무 처리 Class
class Emp implements Ep {
	//ep = new EmpVo(num, name, hiredDate, salary, deptNum); 이 코드를 ep에 저장하는 보관함
	private EmpVo ep;  
	@Override
	public void input() {
		Scanner sc = new Scanner(System.in);
		System.out.println("입력: 사번, 이름, 입사일, 월급, 부서번호");
		String line = sc.nextLine();
		String[] li = line.trim().split(",");
		int num = Integer.parseInt(li[0].trim());
		String name = li[1].trim();
		String hiredDate = li[2].trim();
		double salary = Double.parseDouble(li[3].trim());
		int deptNum = Integer.parseInt(li[4].trim());
		
		//num, name, hiredDate, salary, deptNum 이 변수들을 ep에 담는 코드
		ep = new EmpVo(num, name, hiredDate, salary, deptNum);
 	}

	@Override
	public void process() {
		//보너스 - 근속년수 * 월급 * 0.5%
		int tY = LocalDateTime.now().getYear();
		int hY = Integer.parseInt(ep.getHiredDate().substring(0, 4));
		int Ys = tY - hY;
		double salary = ep.getSalary();
		double bonus = Ys * salary * 0.005;
		ep.setBonus(bonus);
		//수령액 - 월급 + 보너스
		double pay = ep.getSalary() + ep.getBonus();
		ep.setPay(pay);
		//부서명
		int deptNum = ep.getDeptNum();
		String deptName = "";
		switch (deptNum) {
		case 10: deptName = "인사"; break;
		case 20: deptName = "자재"; break;
		case 30: deptName = "총무"; break;
		case 40: deptName = "연구개발"; break;
		case 50: deptName = "생산"; break;
		case 60: deptName = "서비스"; break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + deptNum);
		}
		ep.setDeptName(deptName);
	}
	@Override
	public void output() {
		System.out.println(ep);
		String title = "사번 이름 입사일 월급 보너스 수령액 부서명";
		String fmt = 	"%d %s %s %.2f %.2f %.2f %s";
		System.out.println(title);
		System.out.printf(fmt, ep.getNum(), ep.getName(), ep.getHiredDate(),
				ep.getSalary(), ep.getBonus(), ep.getPay(), ep.getDeptName());
	}	
}
public class TestEmp {
	public static void main(String[] args) {
		Emp ep = new Emp();
		ep.input();
		ep.process();
		ep.output();
	}
}
