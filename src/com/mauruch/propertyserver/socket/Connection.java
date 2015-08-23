package com.mauruch.propertyserver.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.mauruch.propertyserver.CommandInterpreter;
import com.mauruch.propertyserver.SupportedCommands;

/**
 * 
 * @author mauro
 *
 */
public class Connection implements Runnable {

	private Socket socket;
	private ServerSocket serverSocket;
	private ClientInteraction clientInteraction;
	private final String terminate = SupportedCommands.TERMINATE.toString().toLowerCase();

	Connection(Socket socket, CommandInterpreter interpreter,
			ServerSocket serverSocket) {
		this.socket = socket;
		this.serverSocket = serverSocket;
		this.clientInteraction = new ClientInteraction(socket, interpreter);
	}

	@Override
	public void run() {

		try {
			// Get input from the client
			clientInteraction.init();
			String input = clientInteraction.toInteract();
			
			while (!input.equals(terminate) && !input.equals("exit")) {
				input = clientInteraction.toInteract();
			}
			socket.close();
			if (input.equals(terminate)) serverSocket.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

}
