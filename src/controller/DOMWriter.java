package controller;

import datamodel.Book;
import javafx.collections.ObservableList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class DOMWriter {
    private static final String ROOT = "library";
    private static final String BOOK = "book";
    private static final String NAME = "name";
    private static final String AUTHOR = "author";
    private static final String LAST_NAME = "last_mame";
    private static final String FIRST_NAME = "first_name";
    private static final String MIDDLE_NAME = "middle_name";
    private static final String PUBLISHING = "publishing";
    private static final String VOLUME_COUNT = "volume_count";
    private static final String CIRCULATION = "circulation";
    private static final String VOLUME_COUNT_TOTAL = "volume_count_total";

    private String filePath;
    private ObservableList<Book> books;


    public DOMWriter(ObservableList<Book> books, String filePath) {
        this.books = books;
        this.filePath = filePath;
    }

    public void write()  {
        DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder;

        try {
            documentBuilder = documentFactory.newDocumentBuilder();
        } catch (ParserConfigurationException ex) {
            ex.printStackTrace();
            return;
        }
        Document document = documentBuilder.newDocument();

        Element root = document.createElement(ROOT);
        document.appendChild(root);

        for (Book book : books) {
            Element name = document.createElement(NAME);

            Element lastName = document.createElement(LAST_NAME);
            Element firstName = document.createElement(FIRST_NAME);
            Element middleName = document.createElement(MIDDLE_NAME);
            Element author = document.createElement(AUTHOR);

            Element publishing = document.createElement(PUBLISHING);
            Element volCount = document.createElement(VOLUME_COUNT);
            Element cir = document.createElement(CIRCULATION);
            Element volCountTotal = document.createElement(VOLUME_COUNT_TOTAL);


            name.appendChild(document.createTextNode(book.getName()));

            lastName.appendChild(document.createTextNode(book.getLastName()));
            firstName.appendChild(document.createTextNode(book.getFirstName()));
            middleName.appendChild(document.createTextNode(book.getMiddleName()));
            author.appendChild(lastName);
            author.appendChild(firstName);
            author.appendChild(middleName);

            publishing.appendChild(document.createTextNode(book.getPublishing()));
            volCount.appendChild(document.createTextNode(String.valueOf(book.getVolumeCount())));
            cir.appendChild(document.createTextNode(String.valueOf(book.getCirculation())));
            volCountTotal.appendChild(document.createTextNode(String.valueOf(book.getVolumeCountTotal())));


            Element bookNode = document.createElement(BOOK);
            bookNode.appendChild(name);
            bookNode.appendChild(author);
            bookNode.appendChild(publishing);
            bookNode.appendChild(volCount);
            bookNode.appendChild(cir);
            bookNode.appendChild(volCountTotal);

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
}
