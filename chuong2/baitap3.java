package chuong2;

import java.util.Random;

public class baitap3 {
	static class shareData {
		private int number;
		public shareData() {
			
		}
		
		public void setNumber(int number) {
			this.number = number;
		}
		
		public int getNumber() {
			return number;
		}
	}
	
	static class random extends Thread {
		final shareData data;
		
		public random(shareData data) {
			this.data = data;
		}
		
		@Override
		public void run() {
			System.out.println("t1 start");
			Random rand = new Random();
			for (int i = 0; i < 5; i++) {
				try {
					sleep(2000);
				} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				}
					
				data.setNumber(rand.nextInt(20) + 1);
				System.out.printf("So ngau nhien: %d\n", data.getNumber());
			}
		}
	}
	
	static class square extends Thread {
		final shareData data;

		public square(shareData data) {
			this.data = data;
		}
		
		@Override
		public void run() {
			System.out.println("t2 start");
			for (int i = 0; i < 5; i++) {
				try {
					sleep(5000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				System.out.printf("Binh phuong: %d\n", data.getNumber() * data.getNumber());
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		shareData data = new shareData();
		
		random t1 = new random(data);
		square t2 = new square(data);
		
		t1.start();
		t2.start();
	}
}
