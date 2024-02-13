package gr.codehub.j101.p05network;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Util {
	public static String getCorrectIp() {
		// Connect on a UDP socket has the following effect: it sets the destination for Send/Recv, discards all
		// packets from other addresses, and - which is what we use - transfers the socket into "connected" state,
		// settings its appropriate fields. This includes checking the existence of the route to the destination
		// according to the system's routing table and setting the local endpoint accordingly. The last part seems to
		// be undocumented officially but it looks like an integral trait of Berkeley sockets API (a side effect of
		// UDP "connected" state) that works reliably in both Windows and Linux across versions and distributions.
		//
		// So, this method will give the local address that would be used to connect to the specified remote host.
		// There is no real connection established, hence the specified remote ip can be unreachable.
		//
		// Only works on Windows, not on OSX
		String ipCorrect = null;
		try (final DatagramSocket socket = new DatagramSocket()) {
			socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
			ipCorrect = socket.getLocalAddress().getHostAddress();
		} catch (SocketException | UnknownHostException e) {
			e.printStackTrace();
		}
		return ipCorrect;
	}
}
