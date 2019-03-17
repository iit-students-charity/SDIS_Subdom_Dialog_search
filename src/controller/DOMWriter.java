package controller;

import datamodel.Book;
import form.Constant;
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

        Element root = document.createElement(Constant.ROOT_XML_NAME);
        document.appendChild(root);

        for (Book book : books) {
            Element name = document.createElement(Constant.NAME_FIELD);

            Element lastName = document.createElement(Constant.LAST_NAME_XML_NAME);
            Element firstName = document.createElement(Constant.FIRST_NAME_XML_NAME);
            Element middleName = document.createElement(Constant.MIDDLE_NAME_XML_NAME);
            Element author = document.createElement(Constant.AUTHOR_FIELD);

            Element publishing = document.createElement(Constant.PUBLISHING_FIELD);
            Element volCount = document.createElement(Constant.VOLUME_COUNT_XML_NAME);
            Element cir = document.createElement(Constant.CIRCULATION_FIELD);
            Element volCountTotal = document.createElement(Constant.VOLUME_COUNT_TOTAL_XML_NAME);


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


            Element bookNode = document.createElement(Constant.BOOK_XML_NAME);
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
