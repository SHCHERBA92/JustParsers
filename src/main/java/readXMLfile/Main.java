package readXMLfile;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        MyCatalog myCatalog = new MyCatalog();

        Document document = null;
        try {
            document = getDocument("res/test.xml");
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

        ArrayList<MyBook> myBooks = (ArrayList<MyBook>) getListBooksFromDoc(document);
        myCatalog.setMyBooks(myBooks);

        System.out.println("====================");
        myCatalog.getMyBooks().stream().filter(myBook -> myBook.getPrice()<5).forEach(System.out::println);
        System.out.println("====================");
        myCatalog.getMyBooks().stream().filter(myBook -> myBook.getPrice()>30).forEach(System.out::println);
    }

    /**
     * Получение документа XML
    * */
    private static Document getDocument(String fileName) throws ParserConfigurationException, IOException, SAXException {
        File file = new File(fileName);
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        return documentBuilderFactory.newDocumentBuilder().parse(file);
    }


    /**
     * Получения списка Books для моего каталога
     * */
    private static List<MyBook> getListBooksFromDoc(Document document)
    {
        Node node = document.getFirstChild();
        System.out.println("AAA "+node.getNodeName());

        var nodeList = document.getChildNodes();

        Node nodeBook = null;
        ArrayList<MyBook> myBooks = new ArrayList<>();
        for (int i = 0; i < nodeList.getLength(); i++) {

            var nodeListLevel2 = nodeList.item(i).getChildNodes();

            for (int j = 0; j < nodeListLevel2.getLength(); j++) {
                MyBook myBook = new MyBook();

                if (nodeListLevel2.item(j).getNodeType()!=Node.ELEMENT_NODE)
                {
                    continue;
                }
                nodeBook = nodeListLevel2.item(j);

                Element element = (Element) nodeBook;
                String idBook = element.getAttribute("id");
                myBook.setId(idBook);
                System.out.println(myBook.getId() + " - id");

                NodeList nodeBookChilds = nodeBook.getChildNodes();

                for (int k = 0; k < nodeBookChilds.getLength(); k++) {
                    switch (nodeBookChilds.item(k).getNodeName()){
                        case "author":
                            myBook.setAuthor(nodeBookChilds.item(k).getTextContent());
                            System.out.println(myBook.getAuthor() + " - author");
                            break;
                        case "title":
                            myBook.setTitle(nodeBookChilds.item(k).getTextContent());
                            System.out.println(myBook.getTitle() + " - title");
                            break;
                        case "genre":
                            myBook.setGenre(nodeBookChilds.item(k).getTextContent());
                            System.out.println(myBook.getGenre() + " - genre");
                            break;
                        case "price":
                            myBook.setPrice(Double.valueOf(nodeBookChilds.item(k).getTextContent()));
                            System.out.println(myBook.getPrice() + " - price");
                            break;
                        case "publish_date":
                            myBook.setPublishDate(Date.valueOf(nodeBookChilds.item(k).getTextContent()));
                            System.out.println(myBook.getPublishDate() + " - date");
                            break;
                        case "description":
                            myBook.setDescription(nodeBookChilds.item(k).getTextContent());
                            System.out.println(myBook.getAuthor() + " - description");
                            break;
                    }
                }
                myBooks.add(myBook);
            }
        }
        return myBooks;
    }

}
