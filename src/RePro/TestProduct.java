package RePro;

import java.util.Scanner;

interface Ipo {
	void input();
	void process();
	void output();
}
class ProdVo {
	private int num;
	private String pcode;
	private int amount;
	private double price;
	
	private String pname;
	private double kum;
	
	public ProdVo(int num, String pcode, int amount, double price) {
		this.num = num;
		this.pcode = pcode;
		this.amount = amount;
		this.price = price;
	}
	
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getPcode() {
		return pcode;
	}

	public void setPcode(String pcode) {
		this.pcode = pcode;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public double getKum() {
		return kum;
	}

	public void setKum(double kum) {
		this.kum = kum;
	}

	@Override
	public String toString() {
		return "ProdVo [num=" + num + ", pcode=" + pcode + ", amount=" + amount + ", price=" + price + ", pname="
				+ pname + ", kum=" + kum + "]";
	}
}
//업무 처리 class
class Product implements Ipo {
	private ProdVo pd;

	@Override
	public void input() {
		Scanner sc = new Scanner(System.in);
		System.out.println("입력: 번호, 제품코드, 수량, 단가");
		String line = sc.nextLine();
		String[] li = line.trim().split(",");
		int num = Integer.parseInt(li[0].trim());
		String pcode = li[1].trim();
		int amount = Integer.parseInt(li[2].trim());
		double price = Double.parseDouble(li[3].trim());
		
		pd = new ProdVo(num, pcode, amount, price);
	}

	@Override
	public void process() {
		//제품명
		String pcode = pd.getPcode().toUpperCase();
		String pname = "";
		switch(pcode) {
		case "P01": pname = "새우깡"; break;
		case "P02": pname = "빅파이"; break;
		case "P03": pname = "짱구"; break;
		case "P04": pname = "초코파이"; break;
		case "P05": pname = "수박맛 오예스"; break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + pcode);
		}
		pd.setPname(pname);
		//금액 = 수량 * 단가
		pd.setKum(pd.getAmount() * pd.getPrice());
	}

	@Override
	public void output() {
		System.out.println(pd);
		String title = "번호 제품명 수량 단가 금액(수량*단가)";
		String fmt = "%d %s %d %.2f %.2f";
		System.out.printf(fmt, pd.getNum(), pd.getPname(), pd.getAmount(), 
				pd.getPrice(), pd.getKum());
	}
}
public class TestProduct {
	public static void main(String[] args) {
		Product pd = new Product();
		pd.input();
		pd.process();
		pd.output();		
	}

}
