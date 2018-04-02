package parseri;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import source.AdresaStanovanja;
import source.Kontakt;
import source.Osoba;
import source.Telefon;

public class Parser {
	private DocumentBuilderFactory dbFactory;
	private ArrayList<Kontakt> contacts;
	private JSONParser jsonParser;
	public Parser() {
		dbFactory = DocumentBuilderFactory.newInstance();
	    contacts=new ArrayList<Kontakt>();
	    jsonParser = new JSONParser();

	}
	public ArrayList<Kontakt> parseXML(String fileName){
		  Kontakt k;
	      try {
	         File inputFile = new File(fileName);
	         //DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	         Document doc = dBuilder.parse(inputFile);
	         doc.getDocumentElement().normalize();
	         System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
	        
	         NodeList nList = doc.getElementsByTagName("contact");
	         //System.out.println("----------------------------");
	         for (int temp = 0; temp < nList.getLength(); temp++) {
	            Node nNode = nList.item(temp);
	            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	               Element eElement = (Element) nNode;
	               NodeList adrs= (((Element) eElement.getElementsByTagName("addresses").item(0)).getElementsByTagName("address")); 
	               NodeList tels= ((Element)eElement.getElementsByTagName("phones").item(0)).getElementsByTagName("phone");
	               
	              Osoba osoba=new Osoba(eElement.getElementsByTagName("externalId").item(0).getTextContent(),
	            		  eElement.getElementsByTagName("lastName").item(0).getTextContent(),
	            		  eElement.getElementsByTagName("firstName").item(0).getTextContent()
	            		  ) ;
	              Telefon tel;
	             List<Telefon> telovi = new ArrayList<Telefon>();
	              for(int i=0;i<tels.getLength();i++) {
	            	  Element telefon=((Element)tels.item(i));
	            	
	            	  tel=new Telefon();
	            	  tel.setTypeByString(telefon.getElementsByTagName("type").item(0).getTextContent());
	            	  tel.setBrojTelefona(telefon.getElementsByTagName("number").item(0).getTextContent());
	            	  tel.setOsobaId(osoba.getId());
	            	  telovi.add(tel);
	            	  
	              }
	              AdresaStanovanja adr;
	              List<AdresaStanovanja> adrovi = new ArrayList<AdresaStanovanja>();
	              for(int i=0;i<adrs.getLength();i++) {
	            	  Element adresa=(Element) adrs.item(i);
	            	  adr=new AdresaStanovanja();
	            	  adr=new AdresaStanovanja(osoba.getId(),
	            			  adresa.getElementsByTagName("city").item(0).getTextContent(),
	            			  Integer.parseInt(adresa.getElementsByTagName("zip").item(0).getTextContent()));
	            	  adrovi.add(adr);
	              }
	              k=new Kontakt(osoba,telovi,adrovi);
	             
	              contacts.add(k);
	            
	         }
	         
	         }
	        
	      }
	      
	      	catch (Exception e) {
	      		e.printStackTrace();
	      		System.out.println("Došlo je do greške");
	      	}
		return contacts;
	      
	 }
	
	public ArrayList<Kontakt> parseJSON(String fileName) {
		Osoba osoba;
		Telefon tel;
		AdresaStanovanja adr;
		List<Telefon> telefoni=new ArrayList<Telefon>();
		List<AdresaStanovanja> adrese=new ArrayList<AdresaStanovanja>();
		Kontakt k;
		contacts.clear();
		
		try {
			FileReader reader = new FileReader(fileName);
			JSONObject all = (JSONObject) jsonParser.parse(reader);

			JSONObject jsonObject;
			JSONArray jsonArray=(JSONArray) all.get("contacts");
			for(int j=0;j<jsonArray.size();j++) {
				jsonObject=(JSONObject)jsonArray.get(j);
			
				osoba=new Osoba((String)jsonObject.get("externalId"),
					(String)jsonObject.get("firstName"),
					(String)jsonObject.get("lastName"));
			
				JSONArray phones= (JSONArray) jsonObject.get("phones");
				JSONObject obj;
				for(int i=0; i<phones.size(); i++){
					obj=(JSONObject)phones.get(i);
					tel=new Telefon();
					tel.setOsobaId(osoba.getId());
					tel.setBrojTelefona((String)obj.get("number"));
					tel.setTypeByString((String)obj.get("type"));
					telefoni.add(tel);
				}
			
				JSONArray addresses = (JSONArray) jsonObject.get("addresses");
				int zip=0;
				for(int z=0;z<addresses.size();z++) {
					JSONObject adresa = (JSONObject) addresses.get(z);
					zip=Integer.parseInt((String) adresa.get("zip"));
					adr=new AdresaStanovanja(osoba.getId(),
						(String)adresa.get("city"),
						zip);
				adrese.add(adr);
			}
			
			contacts.add(new Kontakt(osoba,telefoni,adrese));
			
		}

		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ParseException ex) {
			ex.printStackTrace();
		} catch (NullPointerException ex) {
			ex.printStackTrace();
		}
		
		return contacts;
		
	}
}
