package datamodel;

import java.util.Objects;

public class Publishing {
    private String name;
    private int circulation;


    public Publishing(String name, int circulation) {
        this.name = name;
        this.circulation = circulation;
    }


    public String getName() {
        return name;
    }

    public int getCirculation() {
        return circulation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Publishing that = (Publishing) o;
        return circulation == that.circulation &&
                Objects.equals(name, that.name);
    }
}
