package chuong3;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.Scanner;

public class baitap4 {
	public static void main(String[] args) throws IOException {
		//https://www.google.com/
		//https://vnexpress.net/
		//https://archive.org/details/audio
		try (Scanner sc = new Scanner(System.in)) {
			System.out.print("URL: ");
			URL url = new URL(sc.nextLine());
			URLConnection urlCon = url.openConnection();
			String date_string = urlCon.getDate() > 0? new Date(urlCon.getDate()).toString() : "0";
			String expries = urlCon.getExpiration() > 0? new Date(urlCon.getExpiration()).toString() : "0";
			String lastModified = urlCon.getLastModified() > 0? new Date(urlCon.getLastModified()).toString() : "0";
			System.out.println("Ngay tao: " + date_string + "\n"
								+ "Ngay het han: " + expries + "\n"
								+ "Ngay sua doi cuoi: " + lastModified);
		}
	}
}
