
import java.io.IOException;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Date;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author TaoLe
 */
public class baisix {
	
	static void redirect(String input) throws URISyntaxException {
		try {
			URI uri = new URI(input);
			java.awt.Desktop.getDesktop().browse(uri);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws IOException, URISyntaxException {
		System.out.println("URL: ");
		Scanner sc = new Scanner(System.in);
		
		//get url
		String inputUrl = sc.nextLine();
		
		URL url = new URL(inputUrl);
		String hostName = url.getHost();
		InetAddress addr = InetAddress.getByName(hostName);
//		byte[] ipByte = addr.getAddress();
		
		//get ip
//		String ipString = byteToString(ipByte);
		String ipString = addr.getHostAddress();
		//get date access
		URLConnection urlCon = url.openConnection();
		String date_string = urlCon.getDate() > 0? new Date(urlCon.getDate()).toString() : "0";
		
		//truy cap web
		System.out.println("is accessing...");
		redirect(inputUrl);
		
		//them vao history
		String text = "URL: " + inputUrl + "\n"
					+ "IP: " + ipString + "\n"
					+ "Ngay truy cap: " + date_string + "\n\n";
		Files.write(Paths.get("D:\\MonHoc\\LTM\\TAI LIEU LTM\\TAI LIEU LTM\\webHistory.txt"), text.getBytes(), StandardOpenOption.APPEND);
		System.out.println("Successfully wrote to the file.");
	}
}
