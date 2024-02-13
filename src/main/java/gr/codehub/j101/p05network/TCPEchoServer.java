package gr.codehub.j101.p05network;/*
 * Filename: TCPEchoServer.java
 * Description: An echo server using connection-oriented delivery system (TCP).
 *              Receives character messages from clients which are capitalized
 *              and sent back. No error handling and exceptions are implemented.
 * Operation: java TCPEchoServer [port]
 *
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

class TCPEchoServer {
	public static final int SERVER_PORT = 1235;
	public static void main(String argv[]) throws Exception {
		int port = SERVER_PORT; // Integer.parseInt(argv[0]);
		ServerSocket welcomeSocket = new ServerSocket(port);
		System.out.println("Server waiting at port: " + welcomeSocket.getLocalPort());
		while (true) {
			Socket connectionSocket = welcomeSocket.accept();
			System.out.println("Accepted connection from: " + connectionSocket.getInetAddress());
			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
			PrintStream outToClient = new PrintStream(connectionSocket.getOutputStream());
			while (true) {
				String clientSentence = null;
				try {
					clientSentence = inFromClient.readLine();
				} catch (IOException e) {
					System.out.println(connectionSocket.getInetAddress() + " broke the connection.");
					break;
				}
				System.out.println("Message Received: " + clientSentence);
				if (clientSentence.equals("exit")) {
					System.out.println("Closing connection with " + connectionSocket.getInetAddress() + ".");
					break;
				}
				String capitalizedSentence = clientSentence.toUpperCase();
				outToClient.println(capitalizedSentence);
			}
			inFromClient.close();
			outToClient.close();
			connectionSocket.close();
			System.out.println("Server waiting at port: " + welcomeSocket.getLocalPort());
		}
	}
}
