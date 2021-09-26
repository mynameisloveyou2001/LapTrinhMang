package chuong3;

import java.net.URL;
import java.util.Scanner;
import java.net.MalformedURLException;

public class baitap1 {
	public static void main(String[] args) throws MalformedURLException {
		//https://www.google.com/
		//https://vnexpress.net/
		//https://archive.org/details/audio
		try (Scanner sc = new Scanner(System.in)) {
			System.out.print("URL: ");
			URL url = new URL(sc.nextLine());
			String fileName = url.getFile();
			String hostName = url.getHost();
			int port = url.getPort();
			String protocol = url.getProtocol();
			System.out.println("Ten File: " + fileName + "\n"
								+ "Ten Host: " + hostName + "\n"
								+ "So hieu cong: " + port + "\n"
								+ "Giao thuc: " + protocol);
		}
	}
}
