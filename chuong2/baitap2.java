package chuong2;

import java.util.Scanner;

class baitap2 {
	static class shareData {
		private int num1, num2;
		
		public shareData() {
			
		}
		
		public shareData(int num1, int num2) {
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
	
	static class input extends Thread {
		final shareData data;
		public input(shareData data) {
			this.data = data;
		}
		
		@Override
		public void run() {
			Scanner scanner = new Scanner(System.in);
			synchronized (data) {
//				System.out.println("t1 start");
				System.out.print("Enter the first number: ");
				int temp = scanner.nextInt();
				data.setNum1(temp);
				System.out.print("Enter the second number: ");
				temp = scanner.nextInt();
				data.setNum2(temp);
				
				data.notifyAll();
			}
		}
	}
	
	static class area extends Thread {
		final shareData data;
		private int areaValue;
		
		public area(shareData data) {
			this.data = data;
		}
		
		public int getAreaValue() {
			return areaValue;
		}
		
		@Override
		public void run() {
			synchronized (data) {
				try {
					data.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
//				System.out.println("t2 start");
				this.areaValue = data.getNum1() * data.getNum2();
			}
		}
	}
	
	static class perimeter extends Thread {
		final shareData data;
		private int perimeterValue;
		
		public perimeter(shareData data) {
			this.data = data;
		}
		
		public int getPerimeterValue() {
			return perimeterValue;
		}
		
		@Override
		public void run() {
			synchronized (data) {
				try {
					data.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
//				System.out.println("t3 start");
				this.perimeterValue = 2 * (data.getNum1() + data.getNum2());
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		shareData data = new shareData();
		
		input t1 = new input(data);
		area t2 = new area(data);
		perimeter t3 = new perimeter(data);
		
		t1.start();
		t2.start();
		t3.start();
		
		t1.join();
		t2.join();
		t3.join();
		
		System.out.printf("Dien tich hinh chu nhat: %d\n", t2.getAreaValue());
		System.out.printf("Chu vi hinh chu nhat: %d\n", t3.getPerimeterValue());
	}
}