package chuong3;

import java.net.URL;
import java.net.URLConnection;
import java.io.DataInputStream;
import java.io.IOException;
//import java.net.MalformedURLException;
import java.util.Scanner;

public class baitap2 {
	public static void main(String[] args) throws IOException {
		//https://www.google.com/
		//https://vnexpress.net/
		//https://archive.org/details/audio
		try (Scanner sc = new Scanner(System.in)) {
			System.out.print("URL: ");
			URL url = new URL(sc.nextLine());
			URLConnection urlCon = url.openConnection();  
			DataInputStream content = new DataInputStream(urlCon.getInputStream());
			String thisLine;
			while((thisLine=content.readLine())!=null) {
				System.out.println(thisLine);
			}
		}
	}
}
