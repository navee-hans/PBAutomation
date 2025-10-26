package Utils;

import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class XMLFileUtility {

    private static final Logger logger = LogManager.getLogger(XMLFileUtility.class);
    private static final File file = new File(System.getProperty("user.dir") + "\\src\\main\\java\\TestData\\EnvironmentConstants.xml");
    private static final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

    /**
     * Fetches user credentials from XML file based on Role name.
     *
     * @param role the role name in XML (e.g., "Admin", "Tester")
     * @return a Map containing Username and Password
     */
    public static Map<String, String> getUsers(String role) {
        Map<String, String> users = new HashMap<>();
        try {
            logger.info("📖 Reading user credentials for role: {}", role);

            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(file);
            document.getDocumentElement().normalize();

            NodeList nodeList = document.getElementsByTagName("User");
            logger.debug("Found {} <User> nodes in XML", nodeList.getLength());

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String xmlRole = element.getElementsByTagName("Role").item(0).getTextContent();

                    if (xmlRole.equalsIgnoreCase(role)) {
                        String username = element.getElementsByTagName("Username").item(0).getTextContent();
                        String password = element.getElementsByTagName("Password").item(0).getTextContent();

                        users.put("Username", username);
                        users.put("Password", password);

                        logger.info("✅ Found credentials for role '{}': Username='{}'", role, username);
                        break;
                    }
                }
            }

            if (users.isEmpty()) {
                logger.warn("⚠️ No matching user found for role: {}", role);
            }

        } catch (ParserConfigurationException | IOException | SAXException e) {
            logger.error("❌ Error while reading user credentials from XML: {}", e.getMessage(), e);
            Allure.step("❌ Error while reading user credentials: " + e.getMessage());
        }

        return users;
    }

    /**
     * Fetches the Application URL from the XML configuration.
     *
     * @return URL as a String
     */
    public static String getURL() {
        String url = "";
        try {
            logger.info("🌐 Reading application URL from EnvironmentConstants.xml");

            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(file);
            document.getDocumentElement().normalize();

            url = document.getElementsByTagName("URL").item(0).getTextContent();

            logger.info("✅ Application URL retrieved successfully: {}", url);
            Allure.step("✅ Application URL retrieved: " + url);

        } catch (ParserConfigurationException | IOException | SAXException e) {
            logger.error("❌ Error while reading URL from XML: {}", e.getMessage(), e);
            Allure.step("❌ Error while reading URL from XML: " + e.getMessage());
        }

        if (url.isEmpty()) {
            logger.warn("⚠️ URL tag not found or empty in EnvironmentConstants.xml");
            Allure.step("⚠️ URL tag not found or empty in XML");
        }

        return url;
    }
}
