package by.galkina.parser.command.impl;

import by.galkina.parser.command.Command;
import by.galkina.parser.handler.XmlParser;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

public class AddCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(AddCommand.class);

    public String execute(HttpServletRequest request) {
        LOGGER.info("Add command execution.");
        List<String> content = new ArrayList<>();
        XmlParser parser = XmlParser.getInstance();
        Set<String> names = parser.getNames();
        content.addAll(names.stream().map(request::getParameter).collect(Collectors.toList()));
        parser.addElement(content);
        return "/index.jsp";
    }
}
