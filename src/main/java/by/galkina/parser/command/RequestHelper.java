package by.galkina.parser.command;

import by.galkina.parser.command.impl.AddCommand;
import by.galkina.parser.command.impl.DeleteCommand;
import by.galkina.parser.command.impl.NoCommand;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public class RequestHelper {
    private static RequestHelper instance = null;

    private HashMap<String, Command> commands = new HashMap<>();

    private RequestHelper() {
        commands.put("add", new AddCommand());
        commands.put("delete", new DeleteCommand());
    }

    public Command getCommand(HttpServletRequest request) {
        String action = request.getParameter("command");
        Command command = commands.get(action);
        if (command == null) {
            command = new NoCommand();
        }
        return command;
    }

    public static RequestHelper getInstance() {
        if (instance == null) {
            instance = new RequestHelper();
        }
        return instance;
    }
}
