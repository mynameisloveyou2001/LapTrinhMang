package chuong3;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class baitap3 {
	static String byteToString(byte[] ip_byte) {
		int[] ip_int = new int[ip_byte.length];
		for (int i = 0; i < 4; i++)
			ip_int[i] = ip_byte[i] & 0xFF; //dùng AND (toán tử bit) 
		String ip_string = ip_int[0] + "." + ip_int[1] + "." + ip_int[2] + "." + ip_int[3];
		return ip_string;
	}
	
	public static void main(String[] args) throws UnknownHostException {
		//www.google.com
		//vnexpress.net
		//archive.org
		try (Scanner sc = new Scanner(System.in)) {
			InetAddress localHost = InetAddress.getLocalHost();
			System.out.print("Host: ");
			InetAddress host = InetAddress.getByName(sc.nextLine());
			byte[] localHostAddress = localHost.getAddress();
			byte[] hostAddress = host.getAddress();
			String localHostAddress_string = byteToString(localHostAddress);
			String hostAddress_string = byteToString(hostAddress);
			System.out.println("Dia chi host : " + hostAddress_string + "\n"
								+ "Dia chi localhost: " + localHostAddress_string);
		}
	}

}
