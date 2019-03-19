package controller;

import datamodel.Book;
import form.ViewConstant;
import javafx.collections.ObservableList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

public class SAXReader {
    private String filePath;
    private ObservableList<Book> books;

    public SAXReader(ObservableList<Book> books, String filePath) {
        this.filePath = filePath;
        this.books = books;
    }

    public void read() {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser;

        try {
            parser = factory.newSAXParser();
        } catch (SAXException | ParserConfigurationException ex) {
            ex.printStackTrace();
            return;
        }

        try {
            parser.parse(new File(filePath), new SAXReaderHandler());
        } catch (SAXException | IOException ex) {
            ex.printStackTrace();
        }
    }


    private class SAXReaderHandler extends DefaultHandler {
        private String name;
        private String firstName;
        private String middleName;
        private String lastName;
        private String publishingName;
        private String volumeCount;
        private String circulation;

        private String lastElementName;

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            lastElementName = qName;
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            String[] param = { name, lastName, firstName, middleName, publishingName, volumeCount, circulation };

            for (String parameter : param) {
                if (parameter == null || parameter.isEmpty()) {
                    return;
                }
            }


            int volumeCountInt;
            int circulationInt;

            try {
                volumeCountInt = Integer.parseInt(volumeCount);
                circulationInt = Integer.parseInt(circulation);
            } catch (NumberFormatException ex) {
                volumeCountInt = -1; // gives an exception had token
                circulationInt = -1;
            }

            books.add(new Book(name, firstName, middleName, lastName, publishingName, volumeCountInt, circulationInt));

            name = null;
            lastName = null;
            firstName = null;
            middleName = null;
            publishingName = null;
            volumeCount = null;
            circulation = null;
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            String nodeText = new String(ch, start, length);

            nodeText = nodeText.replace("\n", ViewConstant.EMPTY_STRING).trim();

            if (!nodeText.isEmpty()) {
                if (lastElementName.equals(ControllerConstant.NAME_FIELD)) {
                    name = nodeText;
                }

                if (lastElementName.equals(ControllerConstant.LAST_NAME_XML_FIELD)) {
                    lastName = nodeText;
                }

                if (lastElementName.equals(ControllerConstant.FIRST_NAME_XML_FIELD)) {
                    firstName = nodeText;
                }

                if (lastElementName.equals(ControllerConstant.MIDDLE_NAME_XML_FIELD)) {
                    middleName = nodeText;
                }

                if (lastElementName.equals(ControllerConstant.PUBLISHING_NAME_XML_FIELD)) {
                    publishingName = nodeText;
                }

                if (lastElementName.equals(ControllerConstant.CIRCULATION_FIELD)) {
                    circulation = nodeText;
                }

                if (lastElementName.equals(ControllerConstant.VOLUME_COUNT_XML_FIELD)) {
                    volumeCount = nodeText;
                }
            }
        }
    }
}
