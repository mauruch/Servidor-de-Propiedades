package com.mauruch.propertyserver;

import com.mauruch.propertyserver.exceptions.UnsupportedCommandException;
import static com.mauruch.propertyserver.utils.Constants.*;

/**
 * 
 * @author mauro
 * 
 */
public class CommandInterpreter {

	private PropertyServer<String, String> server = new PropertyServerImpl();

	//TODO Replace by a pattern design
	public String execute(String command) {
		try {
			String[] splitted = command.split(SEPARATOR_EMPTY_SPACE);
			SupportedCommands supportedCommand = validateCommand(command);
			switch (supportedCommand) {
			case GET:
				return server.get(splitted[1]);
			case SET:
				return server.set(splitted[1]);
			case INC:
				return server.inc(splitted[1]);
			case LIST:
				return server.list();
			case CLEAR:
				return server.clear(splitted[1]);
			case EXIT:
				return server.exit();
			case TERMINATE:
				return server.terminate();
			default:
				return "";
			}

		} catch (UnsupportedCommandException e) {
			return e.getMessage();
		} catch (ArrayIndexOutOfBoundsException e) {
			return INVALID_COMMAND_SYNTAX;
		}
	}

	public SupportedCommands validateCommand(String command) {
		String[] splitted = command.split(SEPARATOR_EMPTY_SPACE);
		String action = splitted[0];
		SupportedCommands supportedCommand;
		try {
			supportedCommand = SupportedCommands.valueOf(action.toUpperCase());
		} catch (IllegalArgumentException e) {
			throw new UnsupportedCommandException(INVALID_COMMAND);
		}

		return supportedCommand;

	}

}
