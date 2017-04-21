package com.my.dubrovnyi.shop.controller;

import com.my.dubrovnyi.shop.constants.XML;
import com.my.dubrovnyi.shop.db.entities.xml.Rule;
import com.my.dubrovnyi.shop.db.entities.xml.RuleRoles;
import org.apache.log4j.Logger;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.stream.StreamSource;

public class STAXController {
    private static final Logger LOG = Logger.getLogger(STAXController.class);
    private String xmlFileName;
    private RuleRoles ruleRoles;

    public STAXController(String xmlFileName) {
        this.xmlFileName = xmlFileName;
    }

    public void parse() throws XMLStreamException {
        LOG.info("Was started XML-parsing");
        Rule rule = null;

        String currentElement = null;

        XMLInputFactory factory = XMLInputFactory.newInstance();
        factory.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, true);

        XMLEventReader reader = factory.createXMLEventReader(new StreamSource(xmlFileName));

        while (reader.hasNext()) {
            XMLEvent event = reader.nextEvent();
            if (event.isCharacters() && event.asCharacters().isWhiteSpace()) {
                continue;
            }
            if (event.isStartElement()) {
                StartElement startElement = event.asStartElement();
                currentElement = startElement.getName().getLocalPart();
                if (XML.SECURITY.equalsTo(currentElement)) {
                    ruleRoles = new RuleRoles();
                    continue;
                }
                if (XML.CONSTRAINT.equalsTo(currentElement)) {
                    rule = new Rule();
                }
            }

            if (event.isCharacters()) {
                Characters characters = event.asCharacters();

                if (rule == null) {
                    LOG.info("Empty rules files");
                    continue;
                }
                if (XML.URL_PATH.equalsTo(currentElement)) {
                    rule.setUrlPatter(characters.getData());
                    continue;
                }
                if (XML.ROLE.equalsTo(currentElement)) {
                    rule.setRole(characters.getData());
                }
            }
            if (event.isEndElement()) {
                EndElement endElement = event.asEndElement();
                String localName = endElement.getName().getLocalPart();

                if (XML.CONSTRAINT.equalsTo(localName)) {
                    addRule(rule);
                }
            }
        }
        reader.close();
    }

    public RuleRoles getRuleRoles() {
        return ruleRoles;
    }

    private void addRule(Rule rule) {
        ruleRoles.addRule(rule, rule.getRole());
    }
}