package com.mauruch.propertyserver.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.mauruch.propertyserver.CommandInterpreter;

/**
 * 
 * @author mauro
 * 
 */
public class Server {

	private static int maxConnections = 0;
	// Default socket port
	public static final int DEFAULT_PORT = 10500;

	public static void main(String[] args) {
		int i = 0;
		int port = ((args.length != 0) ? Integer.parseInt(args[0])
				: DEFAULT_PORT);
		try {
			ServerSocket listener = new ServerSocket(port);
			Socket server;
			CommandInterpreter interpreter = new CommandInterpreter();

			while ((i++ < maxConnections) || (maxConnections == 0)) {
				server = listener.accept();
				Connection conn_c = new Connection(server, interpreter,
						listener);
				Thread t = new Thread(conn_c);
				t.start();
			}
		} catch (IOException ioe) {
			System.out.println("IOException on socket listen: " + ioe);
		}
	}

}
