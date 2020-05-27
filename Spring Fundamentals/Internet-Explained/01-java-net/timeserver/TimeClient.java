package timeserver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class TimeClient {

	public static void main(String[] args) throws UnknownHostException {
		InetAddress addr = InetAddress.getByName("localhost");
		String[] cities = {"Sofia", "Rome", "Madrid", "Berlin", "Paris", "Brussels", "London"};

		for (int i = 0; i < cities.length; i++) {
			// TODO: 21.05.2020 one socket
			try (Socket socket = new Socket(addr, TimeServer.PORT)) {
				BufferedReader in = new BufferedReader(
						new InputStreamReader(socket.getInputStream()));

				PrintWriter out = new PrintWriter(new BufferedWriter(
						new OutputStreamWriter(socket.getOutputStream())), true);

				String zoneStr = "Europe/" + cities[ i % cities.length ];
				out.println(zoneStr);
				String time = in.readLine();
				System.out.println("Time in " + zoneStr + " is: " + time);
			} catch (IOException e) {
				System.err.println("Problem with server communication: " + addr);
			}
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
