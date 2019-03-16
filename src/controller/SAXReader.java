package controller;

import datamodel.Book;
import form.Constant;
import javafx.collections.FXCollections;
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
            return;
        }

        //return books;
    }


    private class SAXReaderHandler extends DefaultHandler {
        private static final String NAME = "name";
        private static final String LAST_NAME = "last_mame";
        private static final String FIRST_NAME = "first_name";
        private static final String MIDDLE_NAME = "middle_name";
        private static final String PUBLISHING = "publishing";
        private static final String VOLUME_COUNT = "volume_count";
        private static final String CIRCULATION = "circulation";

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
            if (name == null || name.isEmpty() || lastName == null || lastName.isEmpty() ||
                    firstName == null || firstName.isEmpty() || middleName == null || middleName.isEmpty() ||
                    volumeCount == null || volumeCount.isEmpty() || circulation == null || circulation.isEmpty()) {
                return;
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
                if (lastElementName.equals(NAME)) {
                    name = nodeText;
                }

                if (lastElementName.equals(LAST_NAME)) {
                    lastName = nodeText;
                }

                if (lastElementName.equals(FIRST_NAME)) {
                    firstName = nodeText;
                }

                if (lastElementName.equals(MIDDLE_NAME)) {
                    middleName = nodeText;
                }

                if (lastElementName.equals(PUBLISHING)) {
                    publishing = nodeText;
                }

                if (lastElementName.equals(VOLUME_COUNT)) {
                    volumeCount = nodeText;
                }

                if (lastElementName.equals(CIRCULATION)) {
                    circulation = nodeText;
                }
            }
        }
    }
}
