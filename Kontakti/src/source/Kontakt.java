package source;

import java.util.List;
import service.MjestoService;
public class Kontakt {
	private Osoba _osoba;
	private List<Telefon> _brojeviTelefona;
	private List<AdresaStanovanja> _adrese;
	//private static MjestoService mjestoservis=new MjestoService();
	
	public Kontakt() {
		_osoba=null;
		_brojeviTelefona=null;
		_adrese=null;
	}

	public Kontakt(Osoba osoba, List<Telefon> brojeviTelefona, List<AdresaStanovanja> adrese) {
		super();
		this._osoba = osoba;
		this._brojeviTelefona = brojeviTelefona;
		this._adrese = adrese;
	}

	public Osoba getOsoba() {
		return _osoba;
	}

	public void setOsoba(Osoba osoba) {
		this._osoba = osoba;
	}

	public List<Telefon> getBrojeviTelefona() {
		return _brojeviTelefona;
	}

	public void setBrojeviTelefona(List<Telefon> brojeviTelefona) {
		this._brojeviTelefona = brojeviTelefona;
	}

	public List<AdresaStanovanja> getAdrese() {
		return _adrese;
	}

	public void setAdrese(List<AdresaStanovanja> adrese) {
		this._adrese = adrese;
	}
	
	public AdresaStanovanja getFirstAddress() {
		if(!_adrese.isEmpty())
		return _adrese.get(0)/*.getUlica()+" "+mjestoservis.FindMjesto(_adrese.get(_adrese.size()).getPostanskiBroj())*/;
		else 
			return null;
	}
	
	@Override
	public String toString() {
		return "Kontakt [_osoba=" + _osoba + ", _brojeviTelefona=" + _brojeviTelefona + ", _adrese=" + _adrese + "]";
	}
	
		
	
	

}
