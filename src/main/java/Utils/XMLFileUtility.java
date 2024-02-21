package Utils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XMLFileUtility {
    static File file = new File(System.getProperty("user.dir") + "\\src\\main\\java\\TestData\\EnvironmentConstants.xml");
    static DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

    public static Map<String,String> getUsers(String Role){
        Map<String, String> users = new HashMap<>();
        try{
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(file);
            document.getDocumentElement().normalize();
            NodeList nodelist = document.getElementsByTagName("User");
            for(int i = 0; i<nodelist.getLength(); ++i)
            {
                Node node = nodelist.item(i);
                System.out.println(node.getNodeName());
                if(node.getNodeType()==node.ELEMENT_NODE){
                    Element tType = (Element)node;
                    if(tType.getElementsByTagName("Role").item(0).getTextContent().equals(Role)){
                        users.put("Username",tType.getElementsByTagName("Username").item(0).getTextContent());
                        users.put("Password",tType.getElementsByTagName("Password").item(0).getTextContent());
                    }
                }

            }
            return users;
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return users;
    }

    public static String getURL() {
        String URL = "";
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(file);
            document.getDocumentElement().normalize();
            URL = document.getElementsByTagName("URL").item(0).getTextContent();
            return URL;
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return URL;
    }
}
