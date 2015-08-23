package com.mauruch.propertyserver.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import static com.mauruch.propertyserver.utils.Constants.*;

import com.mauruch.propertyserver.CommandInterpreter;

/**
 * 
 * @author mauro
 *
 */
public class ClientInteraction {

	private Socket socket;
	private OutputStream out;
	private PrintWriter pw;
	private BufferedReader br;
	private String input;
	private CommandInterpreter interpreter;

	public ClientInteraction(Socket server, CommandInterpreter interpreter) {
		this.socket = server;
		this.interpreter = interpreter;
	}

	public void init() {
		input = "";
		try {
			out = socket.getOutputStream();
			pw = new PrintWriter(out, true);
			pw.println(WELCOME);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String toInteract() {
		try {
			pw.print(PROMPT);
			pw.flush();
			br = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			input = br.readLine();
			pw.println(interpreter.execute(input));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return input;

	}

}
