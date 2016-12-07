package v0;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Level {
	static int nbLvl;
	static int nbFire;
	static int nbShield;
	static int nbPeon;
	static String boss;
	FileInputStream fi;
	static final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	static DocumentBuilder builder;
	static  Document document ;
	public static HashMap<Integer,Level> levelMap = new HashMap<Integer,Level>();
	
	static {
		try{
			builder  = factory.newDocumentBuilder();
			document = builder.parse(new File("./data/levels.xml"));
			Element racine = document.getDocumentElement();
			NodeList racineNodes = racine.getChildNodes();
			int nbNodes = racineNodes.getLength();
			String name;
			String nameBoss;
			int value;
			for(int i = 0 ; i < nbNodes ; i++){
				Element el =  (Element) racineNodes.item(0);
				nbFire = Integer.parseInt(el.getAttribute("FireInvaders"));
				nbShield = Integer.parseInt(el.getAttribute("ShieldInvaders"));
				nbPeon = Integer.parseInt(el.getAttribute("PeonInvaders"));
				boss = el.getAttribute("Boss");
				
				levelMap.put(i,new Level(nbFire,nbShield,nbPeon,boss));
				
			}
		}catch(ParserConfigurationException e){
			e.printStackTrace();
		}catch (SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Level(int f,int s, int p,String b){
		this.boss = b;
		this.nbFire = f;
		this.nbPeon = p;
		this.nbShield = s;
	}
}
