package chuong3;

public class ip_byteToString {
	static String byteToString(byte[] ip_byte) {
		int[] ip_int = new int[ip_byte.length];
		for (int i = 0; i < 4; i++)
			ip_int[i] = ip_byte[i] & 0xFF; //dùng AND (toán tử bit) 
		String ip_string = ip_int[0] + "." + ip_int[1] + "." + ip_int[2] + "." + ip_int[3];
		return ip_string;
	}
}
