package chuong2;

public class baitap1_fixed {

	static class shareData {
		private int n;
		
		public shareData(int n) {
			this.n = n;
		}
		
		public void setN(int n) {
			this.n = n;
		}
		
		public int getN() {
			return n;
		}
	}
	
	static class oddNumber extends Thread {
		final shareData data;
		public oddNumber(shareData data) {
			this.data = data;
		}
		@Override
		public void run() {
			System.out.printf("\nt1 start\n");
			if (data.getN() > 1) {
				int i = 1;
					while(i < data.getN()) {
				System.out.print(i + " ");
					i+=2;
				}
			}
			System.out.println();
		}
	}
	
	static class evenNumber extends Thread {
		final shareData data;
		public evenNumber(shareData data) {
			this.data = data;
		}
		@Override
		public void run() {
			System.out.printf("\nt2 start\n");
			if (data.getN() > 2) {
				int i = 2;
				while(i < data.getN()) {
					System.out.print(i + " ");
					i+=2;
				}
			}
			System.out.println();
		}
	}

	static class listNumber extends Thread {
		final shareData data;
		public listNumber(shareData data) {
			this.data = data;
		}
		@Override
		public void run() {
			System.out.printf("\nt3 start\n");
			for(int i = 1; i <= data.getN() ; ++i) {
				System.out.print(i + " ");
			}
			System.out.println();
		}
	}

	static class asciiChar extends Thread {
		final shareData data;
		public asciiChar(shareData data) {
			this.data = data;
		}
		@Override
		public void run() {
			System.out.printf("\nt4 start\n");
			int i = 65;
			for (int j = 0; j <= 25; ++j) {
				System.out.print((char) (i+j) + " ");
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		shareData data = new shareData(10);
		
		oddNumber t1 = new oddNumber(data);
		evenNumber t2 = new evenNumber(data);
		listNumber t3 = new listNumber(data);
		asciiChar t4 = new asciiChar(data);
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
	}

}
