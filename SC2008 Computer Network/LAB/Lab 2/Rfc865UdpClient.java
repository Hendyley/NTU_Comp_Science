package RFC865_Client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;


/*
Name: Hendy
Group: A33
IP Address: 172.21.148.5
*/
public class Rfc865UdpClient {
	static int QOTD_PORT = 17;
	static String SERVER_NAME = "hwlab1.scse.ntu.edu.sg";
	//static String SERVER_NAME = "localhost";

	public static void main(String[] args) {
		// 1. Open UDP socket
		DatagramSocket socket = null;

		try {
			InetAddress serverAddr = InetAddress.getByName(SERVER_NAME);
			socket = new DatagramSocket();
			socket.connect(serverAddr, QOTD_PORT);
			System.out.println("Client connected to " + serverAddr.getCanonicalHostName() + ":17");
		}

		catch (SocketException | UnknownHostException e) {
			e.printStackTrace();
		}

		try {
			// 1.5 Prepare message to be sent //155.69.176.9 172.21.148.5
			String message = "Hendy, A33, " + InetAddress.getLocalHost().getHostAddress();
			byte[] buffer = message.getBytes("UTF-8");

			// 2. Send UDP request to server
			DatagramPacket request = new DatagramPacket(buffer, buffer.length);
			System.out.println("Sending message...");
			socket.send(request);
			System.out.println("Message sent!");

			// 3. Receive UDP reply from server
			byte[] replyBuffer = new byte[512];
			DatagramPacket reply = new DatagramPacket(replyBuffer, replyBuffer.length);
			System.out.println("Waiting for reply...");
			socket.receive(reply);

			// 4. Process the reply from server
			String replyStr = new String(replyBuffer);
			System.out.println("Received reply: " + replyStr);
		}

		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
