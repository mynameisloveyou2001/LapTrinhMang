package chuong2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class shareData4 {
	private int a, b;
	ArrayList<Integer> primeNumbers = new ArrayList<Integer>();
	
	public shareData4() {}
	
	public int getA() {
		return a;
	}
	public int getB() {
		return b;
	}
	public int getSize() {
		return b-a;
	}
	public void setA(int a) {
		this.a = a;
	}
	public void setB(int b) {
		this.b = b;
	}
}



public class baitap4 {

	static class input extends Thread {
		final shareData4 data;
		public input(shareData4 data) {
			this.data = data;
		}
		
		@Override
		public void run() {
			try (Scanner scanner = new Scanner(System.in)) {
				System.out.printf("\nluong input start");
				System.out.printf("\nNhap a: ");
				int temp = scanner.nextInt();
				data.setA(temp);
				System.out.printf("Nhap b: ");
				temp = scanner.nextInt();
				data.setB(temp);
			}
		}
	}
	
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
	
	static class primeNumber extends Thread{
		final shareData4 data;
		public primeNumber(shareData4 data, int a, int b) {
			this.data = data;
			this.data.setA(a);
			this.data.setB(b);
		}
		
		@Override
		public void run() {
			while(true) {
				if(this.data.getA() != 0 || this.data.getB() != 0) {
					System.out.println("start...");
			        for (int i = this.data.getA(); i < this.data.getB(); i++) {
			            if (isPrimeNumber(i)) {
			            	this.data.primeNumbers.add(i);
			            }
			        }
			        break;
				}
				else {
					System.out.println("waiting for input...");
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
		int dataSize, a2, b2;
		int n = 0;
		shareData4 data = new shareData4();

		
		input t1 = new input(data);
		
		t1.start();
		t1.join();
		
		dataSize = data.getSize();
		if ((dataSize % 10) > 0) n++;
		n += dataSize / 10;
		
		int a1 = data.getA();
		int b1 = data.getB();
		for (int i = 0; i < n; i++) {
			a2 = a1 + (10 * i);
			b2 = a2 + 9;
			
			if(b2 > b1) b2 = b1;
			
			System.out.println("luong " + (i+1) + ": " + "a = " + a2 + " b = " + b2);
			primeNumber primeNumberThread = new primeNumber(data, a2, b2);
			primeNumberThread.start();
			primeNumberThread.join();
			a1 = b2 - 9 - (10 * i);
		}
			
		Collections.sort(data.primeNumbers);
		
		try {
		    File myObj = new File("./bai4_output.txt");
		    if (myObj.createNewFile()) {
		    	System.out.println("File created: " + myObj.getName());
		    } else {
		    	System.out.println("File already exists.");
		    }
		    FileWriter myWriter = new FileWriter("./bai4_output.txt");
		    for(int i = 0; i < data.primeNumbers.size(); i++)
		    	myWriter.write(data.primeNumbers.get(i) + " ");
		    myWriter.close();
		    System.out.println("Successfully wrote to the file.");
		      
		} 
		catch (IOException e) {
		    	System.out.println("An error occurred.");
		    	e.printStackTrace();
		}
	}
}
