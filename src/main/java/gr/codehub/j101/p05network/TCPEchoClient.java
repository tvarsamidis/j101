package gr.codehub.j101.p05network;
/*
 * Filename: TCPEchoClient.java
 * Description: An echo client using connection-oriented delivery system (TCP).
 *              Sends character messages to a server which are echoed capitalized.
 *              No error handling and exceptions are implemented
 * Operation: java TCPEchoClient [hostname] [port]
 *
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

class TCPEchoClient {
	public static void main(String argv[]) throws Exception {
		String serverMachine = "localhost"; // argv[0];
		int port = TCPEchoServer.SERVER_PORT; // Integer.parseInt(argv[1]);
		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
		Socket clientSocket = new Socket(serverMachine, port);
		System.out.println("Connected to: " + clientSocket.getInetAddress() + " on port " + port);
		PrintStream outToServer = new PrintStream(clientSocket.getOutputStream());
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

		while (true) {
			System.out.println("Type message to send to server: ");
			String sentence = inFromUser.readLine();
			outToServer.println(sentence);
			if (sentence.equals("exit")) {
				break;
			}
			String modifiedSentence = inFromServer.readLine();
			System.out.println("Server returned: " + modifiedSentence);
		}
		System.out.println("Closing socket.");
		clientSocket.close();
	}
} 
