
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author TaoLe
 */
public class bai5 {
	static boolean isBlocked(String input) {
		try {
			//read webBlocked
			File myObj = new File("D:\\MonHoc\\LTM\\TAI LIEU LTM\\TAI LIEU LTM\\webBlocked.txt");
	        Scanner myReader = new Scanner(myObj);
	        while (myReader.hasNextLine()) {
	        	String data = myReader.nextLine();
	        	data = data.split("[|]")[1].toString();
	        	if (data.equalsIgnoreCase(input)) return true;
	        }
	        myReader.close();
		} catch (FileNotFoundException e) {
	        System.out.println("An error occurred.");
	        e.printStackTrace();
		}
		return false;
	}
	
	static void redirect(String input) throws URISyntaxException {
		try {
			URI uri = new URI("https://" + input +"/");
			java.awt.Desktop.getDesktop().browse(uri);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
    public static void main(String[] args) throws FileNotFoundException, MalformedURLException, IOException, URISyntaxException{
        try (Scanner sc = new Scanner(System.in)) {
			System.out.println("Search (ip or hostname): ");
			String input = sc.nextLine();
			String ValidIpAddressRegex = "^(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])$";
			String ValidHostnameRegex = "^(([a-zA-Z0-9]|[a-zA-Z0-9][a-zA-Z0-9\\-]*[a-zA-Z0-9])\\.)*([A-Za-z0-9]|[A-Za-z0-9][A-Za-z0-9\\-]*[A-Za-z0-9])$";
			Pattern ipPattern = Pattern.compile(ValidIpAddressRegex);
			Pattern hostPattern = Pattern.compile(ValidHostnameRegex);
			Matcher matcher = ipPattern.matcher(input);
			boolean matchFound = matcher.find();
			if (matchFound) {
				if (isBlocked(input)) System.out.println("trang web da bi chan");
				else redirect(input);
			}
			else {
				matcher = hostPattern.matcher(input);
				matchFound = matcher.find();
				if (matchFound) {
					InetAddress host = InetAddress.getByName(input);
                                        String hostAddress = host.getHostAddress();
					if (isBlocked(hostAddress)) System.out.println("trang web da bi chan");
					else redirect(input);
				}
				else System.out.println("not valid");
			}
		}
    }
}
