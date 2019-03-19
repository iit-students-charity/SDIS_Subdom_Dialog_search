package controller;

import datamodel.Book;
import form.ViewConstant;
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
}
