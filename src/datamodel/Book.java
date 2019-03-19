package datamodel;

public class Book {
    private String name;
    private String firstName;
    private String middleName;
    private String lastName;
    private String publishing;
    private int volumeCount;
    private int circulation;
    private int volumeCountTotal;


    public Book(String name, String firstName, String middleName, String lastName, String publishing,
                int volumeCount, int circulation) {
        this.name = name;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.publishing = publishing;
        this.volumeCount = volumeCount;
        this.circulation = circulation;
        volumeCountTotal = this.volumeCount * this.circulation;
    }

    public String getName() {
        return name;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAuthor() {
        return lastName + ' ' + firstName.toCharArray()[0] + '.' + middleName.toCharArray()[0] + '.';
    }

    public String getPublishing() {
        return publishing;
    }

    public int getVolumeCount() {
        return volumeCount;
    }

    public int getCirculation() {
        return circulation;
    }

    public int getVolumeCountTotal() {
        return volumeCountTotal;
    }

    public boolean equals(Book book) {
        return name.equals(book.name) && firstName.equals(book.firstName) &&
                middleName.equals(book.middleName) && lastName.equals(book.lastName) &&
                publishing.equals(book.publishing) && (volumeCount == book.volumeCount) &&
                (circulation == book.circulation);
    }
}