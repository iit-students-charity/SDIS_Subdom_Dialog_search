package controller;

import datamodel.Book;
import form.Constant;
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
        private String publishing;
        private String volumeCount;
        private String circulation;

        private String lastElementName;

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            lastElementName = qName;
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            String[] param = { name, lastName, firstName, middleName, publishing, volumeCount, circulation };

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

            books.add(new Book(name, firstName, middleName, lastName, publishing, volumeCountInt, circulationInt));

            name = null;
            lastName = null;
            firstName = null;
            middleName = null;
            publishing = null;
            volumeCount = null;
            circulation = null;
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            String nodeText = new String(ch, start, length);

            nodeText = nodeText.replace("\n", Constant.EMPTY_STRING).trim();

            if (!nodeText.isEmpty()) {
                if (lastElementName.equals(Constant.NAME_FIELD)) {
                    name = nodeText;
                }

                if (lastElementName.equals(Constant.LAST_NAME_XML_NAME)) {
                    lastName = nodeText;
                }

                if (lastElementName.equals(Constant.FIRST_NAME_XML_NAME)) {
                    firstName = nodeText;
                }

                if (lastElementName.equals(Constant.MIDDLE_NAME_XML_NAME)) {
                    middleName = nodeText;
                }

                if (lastElementName.equals(Constant.PUBLISHING_FIELD)) {
                    publishing = nodeText;
                }

                if (lastElementName.equals(Constant.VOLUME_COUNT_XML_NAME)) {
                    volumeCount = nodeText;
                }

                if (lastElementName.equals(Constant.CIRCULATION_FIELD)) {
                    circulation = nodeText;
                }
            }
        }
    }
}
