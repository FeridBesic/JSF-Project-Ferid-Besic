package main.webapp;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import parseri.Parser;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import service.AdresaStanovanjaService;
import service.MjestoService;
import service.OsobaService;
import service.TelefonService;
import source.AdresaStanovanja;
import source.Osoba;
import source.Telefon;
import source.Kontakt;

@ManagedBean(name="helloBean",eager=true)
@SessionScoped
public class MainBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private String name;
	private ArrayList<Kontakt> kontakti;
	private TelefonService telefonservis;
	private OsobaService osobaservis;
	private MjestoService mjestoservis;
	private AdresaStanovanjaService adresaservis;
	Parser parser;
	   
	
	public MainBean() {
	
		System.out.println("SAD SAM POKRENUT");
		name="Pocetak";
		telefonservis=new TelefonService();
		osobaservis=new OsobaService();
		mjestoservis=new MjestoService();
		adresaservis=new AdresaStanovanjaService();
		kontakti=new ArrayList<Kontakt>();
		populateList();
		parser=new Parser();
			
	}

	public String getName() {
		return name;
	}
		
	public ArrayList<Kontakt> getKontakti() {
		return kontakti;
	}

	public void setKontakti(ArrayList<Kontakt> kontakti) {
		this.kontakti = kontakti;
	}

	private int parseResponse(String str[]) {
		Integer num=-1;
		if(str.length>2) {
			num=Integer.parseInt(str[2]);
		}
		System.out.println(num);
		return num;

	}
		
	public void populateList(/*List<Kontakt> kontakti*/) {
		//createOsobe();
		List<Osoba> osobe;
		osobe=osobaservis.FetchAllOsoba();
		Kontakt k=new Kontakt();
		kontakti.clear();
		for(Osoba o : osobe) {
			k=new Kontakt(o,
			telefonservis.FindTelefonByOsobaId(o.getId()),
			adresaservis.FindAdresaStanovanjaByOsobaId(o.getId()));
			kontakti.add(k);
			
		}
	}
		
		public void createOsobe() {
			osobaservis.CreateOsoba("Ferid", "Besic", "fbesic");
			osobaservis.CreateOsoba("Jon", "Snow", "jsnow");
			osobaservis.CreateOsoba("Bruce", "Wayne", "bwayne");
			osobaservis.CreateOsoba("Clark", "Kent", "ckent");
			telefonservis.CreateTelefon("061-999-999", "bwayne", 1);
			telefonservis.CreateTelefon("061-555-121", "ckent", 1);
			telefonservis.CreateTelefon("061-111-111", "fbesic", 0);
		}
	//BINDED METHODS
		public void update(ActionEvent e) {
			
			System.out.println(e.getComponent().getClientId());	
			ArrayList<Kontakt> newkontakti=new ArrayList<Kontakt>();
			if(name.contains(".json"))
			newkontakti=parser.parseJSON(name);
			if(name.contains(".xml"))
			newkontakti=parser.parseXML(name);
			boolean test;
			updateList(newkontakti);
			populateList();
		}
		
		
		
		public void delete(ActionEvent e) {
			
			int ind=parseResponse(e.getComponent().getClientId().split(":"));
			if(ind>-1) {
				deleteList(ind);
			}
			populateList();
				
		}
		
		public void getFileName(ValueChangeEvent e) {
					name=e.getNewValue().toString();
					System.out.println(name);
					
		}
		
		//HELPER FUNCTIONS
		private void updateList(ArrayList<Kontakt> newkontakti) {
			boolean test=true;
			for(Kontakt l: newkontakti) {
				test=osobaservis.CreateOsoba(l.getOsoba().getIme(),
					l.getOsoba().getPrezime(),
					l.getOsoba().getId());
				System.out.println(test);
				if(!test)
				osobaservis.UpdateOsobaImeIPrezime(l.getOsoba().getId(),
							l.getOsoba().getIme(),
							l.getOsoba().getPrezime());
				
				for(Telefon t : l.getBrojeviTelefona())
				telefonservis.CreateTelefon(t.getBrojTelefona(),
						t.getOsobaId(),
						t.getTip());
				for(AdresaStanovanja t : l.getAdrese()) {
					adresaservis.CreateAdresaStanovanja(t.getUlica(),
							t.getPostanskiBroj(),
							t.getOsobaid());
				}
			}
		}
		
		private void deleteList(int ind) {
			//OBRISI SVE ADRESE NA KOJIMA OVA OSOBA STANUJE
			for(AdresaStanovanja l: kontakti.get(ind).getAdrese())
				adresaservis.DeleteAdresaStanovanja(l.getUlica(), l.getPostanskiBroj() ,l.getOsobaid());
			//OBRISI SVE BROJEVE TELEFONA KOJI PRIPADAJU OVOJ OSOBI
			for(Telefon l: kontakti.get(ind).getBrojeviTelefona())
				telefonservis.DeleteTelefon(l.getBrojTelefona());
			//OBRISI OSOBU
		osobaservis.DeleteOsoba(kontakti.get(ind).getOsoba().getId());
		//UKLONI IZ LISTE ZA PRIKAZ
		kontakti.remove(ind);
		}
}
