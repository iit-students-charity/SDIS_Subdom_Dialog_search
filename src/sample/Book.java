package sample;

public class Book {
    private String name;
    private String author;
    private String publishing;
    private int volumeCount;
    private int circulation;
    private int volumeCountTotal;


    public Book(String _name, String _author, String _publishing, int _volumeCount, int _circulation) {
        name = _name;
        author = _author;
        publishing = _publishing;
        volumeCount = _volumeCount;
        circulation = _circulation;
        volumeCountTotal = 0; // how to generate?
    }
}
