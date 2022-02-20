package writeXMLfile;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Element rootElement = null;
        TeamOf teamOf = new TeamOf(new ArrayList<Jober>(Arrays.asList(
                new Jober(11,"Pavel","C++",32),
                new Jober(12,"Kolaya","Python",22),
                new Jober(13,"Sasha","JS",35)
        )));
        try {
            Document document = createDocument();
            rootElement = document.createElementNS(null,"Team");
            document.appendChild(rootElement);

            ArrayList<Node> nodeArrayList = new ArrayList<>();

            for (int i = 0; i < teamOf.getJobers().size(); i++) {
                rootElement.appendChild(createRoot(document,"Jober", String.valueOf(teamOf.jobers.get(i).getId()),teamOf.getJobers().get(i)));
            }


            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            transformer.setOutputProperty(OutputKeys.INDENT,"yes");
            DOMSource source = new DOMSource(document);

            StreamResult console = new StreamResult(System.out);
//            StreamResult file = new StreamResult(new File("res/test2.xml"));

            transformer.transform(source,console);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }


    }

    /**
     *  создаём ДОМ документ
     * */
    private static Document createDocument() throws ParserConfigurationException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        Document document = documentBuilderFactory.newDocumentBuilder().newDocument();
        return document;
    }

    private static Element createRoot(Document document, String tag, String atribut, Jober jober)
    {
        Element element = document.createElement(tag);
        element.setAttribute("id",atribut);

        element.appendChild(createChild(document,"name",jober.getName()));
        element.appendChild(createChild(document,"age", String.valueOf(jober.getAge())));
        element.appendChild(createChild(document,"lang",jober.getLang()));
        return element;
    }

    private static Node createChild(Document document, String tag, String val)
    {
        Element element = document.createElement(tag);
        element.setTextContent(val);
        return element;
    }
}
