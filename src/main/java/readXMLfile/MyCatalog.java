package readXMLfile;

import java.util.List;

public class MyCatalog {

    List<MyBook> myBooks;

    public MyCatalog(List<MyBook> myBooks) {
        this.myBooks = myBooks;
    }

    public MyCatalog() {
    }

    public List<MyBook> getMyBooks() {
        return myBooks;
    }

    public void setMyBooks(List<MyBook> myBooks) {
        this.myBooks = myBooks;
    }
}
