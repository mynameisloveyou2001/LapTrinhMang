package chuong2;

import java.util.Scanner;

class shareData2 {
	private int num1, num2;
	
	public shareData2() {
		this.num1 = 0;
		this.num2 = 0;
	}
	
	public shareData2(int num1, int num2) {
		this.num1 = num1;
		this.num2 = num2;
	}
	
	public int getNum1() {
		return num1;
	}
	public int getNum2() {
		return num2;
	}
	
	public void setNum1(int num1) {
		this.num1 = num1;
	}
	public void setNum2(int num2) {
		this.num2 = num2;
	}
}

public class baitap2_fixed {

	static class input extends Thread {
		final shareData2 data;
		public input(shareData2 data) {
			this.data = data;
		}
		
		@Override
		public void run() {
			Scanner scanner = new Scanner(System.in);
			System.out.printf("\nt1 start");
			System.out.printf("\nNhap chieu dai: ");
			int temp = scanner.nextInt();
			data.setNum1(temp);
			System.out.printf("\nNhap chieu rong: ");
			temp = scanner.nextInt();
			data.setNum2(temp);
		}
	}
	
	static class area extends Thread {
		final shareData2 data;
		private int areaValue;
		
		public area(shareData2 data) {
			this.data = data;
		}
		
		public int getAreaValue() {
			return areaValue;
		}
		
		@Override
		public void run() {
			while(true) {
				if(data.getNum1() != 0 && data.getNum2() != 0) {
					System.out.printf("\nt2 start");
					this.areaValue = data.getNum1() * data.getNum2();
					break;
				}
				else {
					System.out.printf("\nt2 is waiting...");
					try {
						sleep(5000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	static class perimeter extends Thread {
		final shareData2 data;
		private int perimeterValue;
		
		public perimeter(shareData2 data) {
			this.data = data;
		}
		
		public int getPerimeterValue() {
			return perimeterValue;
		}
		
		@Override
		public void run() {
			while(true) {
				if(data.getNum1() != 0 && data.getNum2() != 0) {
					System.out.printf("\nt3 start");
					this.perimeterValue = 2 * (data.getNum1() + data.getNum2());
					break;
				}
				else {
					System.out.printf("\nt3 is waiting...");
					try {
						sleep(5000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException {	
		shareData2 data = new shareData2();
		
		input t1 = new input(data);
		area t2 = new area(data);
		perimeter t3 = new perimeter(data);
		
		t1.start();
		t2.start();
		t3.start();
		
		t1.join();
		t2.join();
		t3.join();
		
		while(true) {
			if(t2.getAreaValue() != 0 && t3.getPerimeterValue() != 0) {
				System.out.printf("\nMain thread start");
				System.out.printf("\nDien tich hinh chu nhat: %d", t2.getAreaValue());
				System.out.printf("\nChu vi hinh chu nhat: %d", t3.getPerimeterValue());
				break;
			}
			else {
				System.out.printf("\nMain thread waiting");
				Thread.sleep(5000);
			}
		}

	}

}
