package by.galkina.parser.command.impl;

import by.galkina.parser.command.Command;
import javax.servlet.http.HttpServletRequest;

public class NoCommand implements Command {
    public String execute(HttpServletRequest request) {
        return "/index.jsp";
    }
}
