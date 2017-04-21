package com.my.dubrovnyi.shop.listener;

import com.my.dubrovnyi.shop.controller.STAXController;
import com.my.dubrovnyi.shop.db.entities.xml.RuleRoles;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.xml.stream.XMLStreamException;

@WebListener
public class ApplicationListener implements ServletContextListener {
    private static final Logger LOG = Logger.getLogger(ApplicationListener.class);
    private static final String FILE_NAME = "input.xml";
    private static final String ACCESS_RULES_VALUE = "accessRules";

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        LOG.debug("Listener was called");
        STAXController staxController = new STAXController(FILE_NAME);
        try {
            staxController.parse();
            RuleRoles ruleRoles = staxController.getRuleRoles();
            ServletContext sc = servletContextEvent.getServletContext();

            sc.setAttribute(ACCESS_RULES_VALUE, ruleRoles);
        } catch (XMLStreamException e) {
            LOG.error("Error when parsing STAX Controller " + e.getMessage());
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        LOG.debug("Listener was destroyed");
    }
}