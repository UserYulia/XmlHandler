package by.galkina.parser.command.impl;

import by.galkina.parser.command.Command;
import by.galkina.parser.handler.XmlParser;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class DeleteCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(DeleteCommand.class);

    public String execute(HttpServletRequest request) {
        LOGGER.info("Delete command execution.");
        XmlParser parser = XmlParser.getInstance();
        Integer number = Integer.parseInt(request.getParameter("number"));
        parser.removeElement(number);
        return "/index.jsp";
    }
}
