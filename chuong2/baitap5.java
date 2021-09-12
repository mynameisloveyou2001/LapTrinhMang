package chuong2;

import java.util.ArrayList;

class shareData5 {
	//Tao mang cac so nguyen
	ArrayList<Integer> primeNumbers = new ArrayList<Integer>();
	//Tong cac so nguyen to
	private int sum;
	
	public shareData5() {}
	public int getSum() {
		return sum;
	}
	public void setSum(int sum) {
		this.sum = sum;
	}
}

public class baitap5 {
	
	//Ham kiem tra mot so co phai la so nguyen to
	static boolean isPrimeNumber(int x) {
        if (x < 2) return false;
        int squareRoot = (int) Math.sqrt(x);
        for (int i = 2; i <= squareRoot; i++) {
            if (x % i == 0) {
                return false;
            }
        }
        return true;
    }
	
	//Luong tim so nguyen to tren doan xac dinh tu a toi b
	static class primeNumber extends Thread {
		final shareData5 data;
		private int a, b;
		public primeNumber(shareData5 data, int a, int b) {
			this.data = data;
			this.a = a;
			this.b = b;
		}
		
		@Override
		public void run() {
			synchronized (data) {
				//start
				System.out.println("t1 start...");
				//in a, b
				System.out.println("a = " + this.a + " b = " + this.b);
			    for (int i = this.a; i < this.b; i++) {
			    	if (isPrimeNumber(i)) {
			    		this.data.primeNumbers.add(i);
			    	}				    	
			    }
			    data.notifyAll();
				try {
					data.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	static class sum extends Thread {
		final shareData5 data;
		public sum(shareData5 data) {
			this.data = data;
		}
		
		@Override
		public void run() {
			synchronized (data) {
				try {
					data.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				int sum = 0;
				System.out.println("t2 start...");
				for(int i = 0; i < this.data.primeNumbers.size(); i++) {
					sum += this.data.primeNumbers.get(i);
				}
				this.data.setSum(sum);
				System.out.println("Tong: " + this.data.getSum());
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		shareData5 data = new shareData5();
		primeNumber t1 = new primeNumber(data, 1, 1000);
		sum t2 = new sum(data);
		t1.start();
		t2.start();
		
		t1.join();
		t2.join();
		
	}

}
