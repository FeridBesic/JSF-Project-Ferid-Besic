package main;
/*
import java.util.List;

import service.AdresaStanovanjaService;
import service.MjestoService;
import service.OsobaService;
import service.TelefonService;
import source.AdresaStanovanja;
import source.Mjesto;
import source.Osoba;
import source.Telefon;

public class Main {	
	     
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("We have a go!");
		
		TelefonService telefonservis=new TelefonService();
		OsobaService osobaservis=new OsobaService();
		MjestoService mjestoservis=new MjestoService();
		AdresaStanovanjaService adresaservis=new AdresaStanovanjaService();
		System.out.println(adresaservis.CreateAdresaStanovanja("Brcanska", 75000, "fbesic"));
		System.out.println(adresaservis.CreateAdresaStanovanja("Jednosmjerna", 69000, "fbesicdrugi"));
		System.out.println(adresaservis.CreateAdresaStanovanja("Ulicarska", 10000, "fbesictreci"));
		
		System.out.println(mjestoservis.CreateMjesto(74999, "Zamal Tuzla"));
		System.out.println(mjestoservis.CreateMjesto(75000, "Tuzla"));
		System.out.println(mjestoservis.CreateMjesto(70000, "Sarajevo"));
		
		System.out.println(osobaservis.CreateOsoba("Ferid", "Besic", "fbesic"));
		System.out.println(osobaservis.CreateOsoba("Lejla", "Dzinic", "ldzinic"));
		System.out.println(osobaservis.CreateOsoba("Almir", "Omerovic", "aomerovic"));
		
		System.out.println(telefonservis.CreateTelefon("062-111-111", "fbesic", 1));
		System.out.println(telefonservis.CreateTelefon("062-222-222", "ldzinic", 2));
		System.out.println(telefonservis.CreateTelefon("062-333-333", "aomerovic", 3));
		
		List<Telefon> listtelefon=telefonservis.FetchAllTelefon();
		for(Telefon l: listtelefon) {
			System.out.println(l);
			telefonservis.DeleteTelefon(l.getBrojTelefona());
		}
		
		List<Osoba> listosoba=osobaservis.FetchAllOsoba();
		for(Osoba l: listosoba) {
			System.out.println(l);
			osobaservis.DeleteOsoba(l.getId());
		}
		
		List<Mjesto> listmjesto=mjestoservis.FetchAllMjesto();
		for(Mjesto l: listmjesto) {
			System.out.println(l);
			mjestoservis.DeleteMjesto(l.get_postanskiBroj());
		}
		
		List<AdresaStanovanja> listadresa=adresaservis.FetchAllAdresaStanovanja();
		for(AdresaStanovanja l: listadresa) {
			System.out.println(l);
			adresaservis.DeleteAdresaStanovanja(l.getUlica(), l.getPostanskiBroj(), l.getOsobaid());
		}
		
		telefonservis.CloseConnection();
		osobaservis.CloseConnection();
		mjestoservis.CloseConnection();
		adresaservis.CloseConnection();
	}

}*/

/*import parseri.Parser;

public class Main {

   public static void main(String[] args) {
	  Parser par=new Parser();
	   par.parseXML("C:\\Users\\Ferid\\eclipse-workspace\\Kontakti\\src\\tester.xml");
      
	   
   }
}*/




import parseri.Parser;
public class Main {

	private static final String filePath = "C:\\Users\\Ferid\\eclipse-workspace\\Kontakti\\src\\tester.json";
	
	public static void main(String[] args) {
		Parser parser=new Parser();
		parser.parseJSON(filePath);
	}

}

