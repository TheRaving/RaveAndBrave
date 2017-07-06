import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

/**
 * Created by raving on 05/07/17.
 */
public class xmlParser {
    ArrayList<infobus> infobuses =  new ArrayList<infobus>();
    public ArrayList<infobus> returnInfobuses() {
        String[] ip;
        String[] subnet;
        String[] gateway;
        String[] stops;
        String stz;
        String nm ;
        int id;
        try {
            File fxmlFile = new File("/home/raving/IdeaProjects/infobus/src/infobus.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fxmlFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("infobus");
            infobuses.clear();
            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    id = Integer.valueOf(eElement.getAttribute("id"));
                    stz = eElement.getElementsByTagName("stazione").item(0).getTextContent();
                    nm = eElement.getElementsByTagName("nomeMacchina").item(0).getTextContent();
                    ip = eElement.getElementsByTagName("indirizzoIp").item(0).getTextContent().split("\\.");
                    subnet = eElement.getElementsByTagName("subnetMask").item(0).getTextContent().split("\\.");
                    gateway = eElement.getElementsByTagName("gateway").item(0).getTextContent().split("\\.");
                    stops = eElement.getElementsByTagName("fermate").item(0).getTextContent().split(",");
                    infobus infobus = new infobus(id, stz, nm, ip, subnet, gateway, stops);
                    infobuses.add(infobus);
                }
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return infobuses;
    }
    public String[] returnMachineNames(ArrayList<infobus> inf){
        String[] names = new String[inf.size()];
        for (int i =0;i < inf.size();i++) {
            names[i] = inf.get(i).getNomeMacchina();
        }
        return names;
    }
}
