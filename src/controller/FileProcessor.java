package controller;

import datamodel.Book;
import form.ViewConstant;
import javafx.collections.ObservableList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.*;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

public class FileProcessor {
    private class SAXReaderHandler extends DefaultHandler {
        private ObservableList<Book> books;

        private String name;
        private String firstName;
        private String middleName;
        private String lastName;
        private String publishingName;
        private String volumeCount;
        private String circulation;

        private String lastElementName;

        public SAXReaderHandler(ObservableList<Book> books) {
            this.books = books;
        }

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


    private String filePath;


    public FileProcessor(String filePath) {
        this.filePath = filePath;
    }

    public void write(ObservableList<Book> books)  {
        DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder;

        try {
            documentBuilder = documentFactory.newDocumentBuilder();
        } catch (ParserConfigurationException ex) {
            ex.printStackTrace();
            return;
        }
        Document document = documentBuilder.newDocument();

        Element root = document.createElement(ControllerConstant.ROOT_XML_FIELD);
        document.appendChild(root);

        for (Book book : books) {
            Element name = document.createElement(ControllerConstant.NAME_FIELD);

            Element lastName = document.createElement(ControllerConstant.LAST_NAME_XML_FIELD);
            Element firstName = document.createElement(ControllerConstant.FIRST_NAME_XML_FIELD);
            Element middleName = document.createElement(ControllerConstant.MIDDLE_NAME_XML_FIELD);
            Element author = document.createElement(ControllerConstant.AUTHOR_FIELD);

            Element volCount = document.createElement(ControllerConstant.VOLUME_COUNT_XML_FIELD);
            Element publishing = document.createElement(ControllerConstant.PUBLISHING_FIELD);
            Element publishingName = document.createElement(ControllerConstant.PUBLISHING_NAME_XML_FIELD);
            Element cir = document.createElement(ControllerConstant.CIRCULATION_FIELD);


            name.appendChild(document.createTextNode(book.getName()));

            lastName.appendChild(document.createTextNode(book.getAuthor().getLastName()));
            firstName.appendChild(document.createTextNode(book.getAuthor().getFirstName()));
            middleName.appendChild(document.createTextNode(book.getAuthor().getMiddleName()));
            author.appendChild(lastName);
            author.appendChild(firstName);
            author.appendChild(middleName);

            volCount.appendChild(document.createTextNode(String.valueOf(book.getVolumeCount())));

            cir.appendChild(document.createTextNode(String.valueOf(book.getPublishing().getCirculation())));
            publishingName.appendChild(document.createTextNode(book.getPublishing().getName()));
            publishing.appendChild(publishingName);
            publishing.appendChild(cir);

            Element bookNode = document.createElement(ControllerConstant.BOOK_XML_FIELD);
            bookNode.appendChild(name);
            bookNode.appendChild(author);
            bookNode.appendChild(publishing);
            bookNode.appendChild(volCount);

            root.appendChild(bookNode);
        }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer;

        try {
            transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File(filePath));

            transformer.transform(domSource, streamResult);
        } catch (TransformerException ex) {
            ex.printStackTrace();
        }
    }

    public void read(ObservableList<Book> books) {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser;

        try {
            parser = factory.newSAXParser();
        } catch (SAXException | ParserConfigurationException ex) {
            ex.printStackTrace();
            return;
        }

        try {
            parser.parse(new File(filePath), new SAXReaderHandler(books));
        } catch (SAXException | IOException ex) {
            ex.printStackTrace();
        }
    }
}
