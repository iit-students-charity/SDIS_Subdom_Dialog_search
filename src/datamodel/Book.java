package datamodel;

public class Book {
    private String name;
    private Author author;
    private int volumeCount;
    private Publishing publishing;


    public Book(String name, String firstName, String middleName, String lastName, String publishingName,
                int volumeCount, int circulation) {
        this.name = name;
        author = new Author(firstName, middleName, lastName);
        this.volumeCount = volumeCount;
        this.publishing = new Publishing(publishingName, circulation);
    }

    public String getName() {
        return name;
    }

    public Author getAuthor() {
        return author;
    }

    public Publishing getPublishing() {
        return publishing;
    }

    public String getPublishingName() {
        return publishing.getName();
    }

    public int getPublishingCirculation() {
        return publishing.getCirculation();
    }

    public int getVolumeCount() {
        return volumeCount;
    }

    public int getVolumeCountTotal() {
        return volumeCount * publishing.getCirculation();
    }

    public boolean equals(Book book) {
        return name.equals(book.name) && author.equals(book.author) &&
                publishing.equals(book.publishing) && (volumeCount == book.volumeCount);
    }
}