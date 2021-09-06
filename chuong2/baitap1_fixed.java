package chuong2;

class shareData1 {
	private int n;
	
	public shareData1(int n) {
		this.n = n;
	}
	
	public void setN(int n) {
		this.n = n;
	}
	
	public int getN() {
		return n;
	}
}

class oddNumber extends Thread {
	final shareData1 data;
	public oddNumber(shareData1 data) {
		this.data = data;
	}
	@Override
	public void run() {
//		System.out.printf("\nt1 start\n");
		if (data.getN() > 1) {
			int i = 1;
				while(i < data.getN()) {
			System.out.println("t1 start: " + i + " ");
				i+=2;
			}
		}
//		System.out.println();
	}
}

class evenNumber extends Thread {
	final shareData1 data;
	public evenNumber(shareData1 data) {
		this.data = data;
	}
	@Override
	public void run() {
//		System.out.printf("\nt2 start\n");
		if (data.getN() > 2) {
			int i = 2;
			while(i < data.getN()) {
				System.out.println("t2 start: " + i + " ");
				i+=2;
			}
		}
//		System.out.println();
	}
}

class listNumber extends Thread {
	final shareData1 data;
	public listNumber(shareData1 data) {
		this.data = data;
	}
	@Override
	public void run() {
//		System.out.printf("\nt3 start\n");
		for(int i = 1; i <= data.getN() ; ++i) {
			System.out.println("t3 start: "+ i + " ");
		}
//		System.out.println();
	}
}

class asciiChar extends Thread {
	final shareData1 data;
	public asciiChar(shareData1 data) {
		this.data = data;
	}
	@Override
	public void run() {
//		System.out.printf("\nt4 start\n");
		int i = 65;
		for (int j = 0; j <= 25; ++j) {
			System.out.println("t4 start: " + (char) (i+j) + " ");
		}
//		System.out.println();
	}
}

public class baitap1_fixed {

	public static void main(String[] args) {
		shareData1 data = new shareData1(10);
		
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
