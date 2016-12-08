package ressources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.Node;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Level {
	static int nbLvl;
	public int nbFire;
	public int nbShield;
	public int nbPeon;
	public String boss;
	FileInputStream fi;
	static final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	static DocumentBuilder builder;
	static Document document ;
	public static Map<Integer,Level> levelMap = new HashMap<Integer,Level>();
	
	static {
		try{
			builder  = factory.newDocumentBuilder();
			document = builder.parse(new File("./data/levels.xml"));
			Element racine = document.getDocumentElement();
			NodeList racineNodes = racine.getChildNodes();
			int nbNodes = racineNodes.getLength();
			for(int i = 0 ; i < nbNodes ; i++){
				if(racineNodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
					Element el = (Element) racineNodes.item(i);
					int nbFire = Integer.parseInt(el.getAttribute("FireInvaders"));
					int nbShield = Integer.parseInt(el.getAttribute("ShieldInvaders"));
					int nbPeon = Integer.parseInt(el.getAttribute("PeonInvaders"));
					String boss = el.getAttribute("Boss");
					levelMap.put(Integer.parseInt(el.getAttribute("Index")),new Level(nbFire, nbShield, nbPeon, boss));
				}
				
			}
		} catch(ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException | IOException e) {
			e.printStackTrace();
		}
	}
	public Level(int f,int s, int p,String b){
		this.boss = b;
		this.nbFire = f;
		this.nbPeon = p;
		this.nbShield = s;
	}
	
	public String toString() {
		return this.boss + " " +
		this.nbFire + " " +
		this.nbPeon + " " +
		this.nbShield;
	}
}
