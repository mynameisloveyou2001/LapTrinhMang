package chuong2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

//Tao kieu du lieu chung giua cac luong
class shareData4 {
	private int a, b;
	//Tao mang cac so nguyen
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
	//Luong input
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
				//Dieu kien nhap a hoac b khac 0
				if(this.data.getA() != 0 || this.data.getB() != 0) {
					//start
					System.out.println("start...");
					//in a, b
					System.out.println("a = " + this.data.getA() + " b = " + this.data.getB());
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
		//Kich thuoc moi doan
		final int sizePerPart = 10;
		int dataSize, a2, b2;
		
		//n: so luong
		int n = 0;
		
		//Khai bao du lieu chung giua cac luong
		shareData4 data = new shareData4(); 
		//Khoi tao luong input
		input t1 = new input(data);
		
		t1.start();
		//luong input phai chay truoc thi moi lay duoc datasize
		t1.join();
		
		dataSize = data.getSize();
		//Neu kich thuoc le tang n len 1
		if ((dataSize % sizePerPart) > 0) n++;
		//Chia so doan 
		n += dataSize / sizePerPart;
		
		int a1 = data.getA();	//Gia tri sau khi nhap (ban dau) cua a
		int b1 = data.getB();	//Gia tri sau khi nhap (ban dau) cua b
		for (int i = 0; i < n; i++) {
			a2 = a1 + (sizePerPart * i);	//Tang a theo cap so nhan cua 10
			b2 = a2 + sizePerPart - 1;		//b = a + 9
			
			if(a2 > b1) a2 = b1;
			if(b2 > b1) b2 = b1;
			
			primeNumber primeNumberThread = new primeNumber(data, a2, b2);
			System.out.println("+ Luong " + (i+1) + ": ");
			primeNumberThread.start();
			primeNumberThread.join();
		}

		
		try {
			//Tao file
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
