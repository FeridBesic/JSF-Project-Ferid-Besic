package source;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import mapping.AdresaStanovanjaKey;


@Entity
@IdClass(AdresaStanovanjaKey.class)
@Table
public class AdresaStanovanja {
	
	@Id
	private String ulica;
	
	@Id
	private int postanskiBroj;
	
	@Id
	private String osobaid;
	
	public AdresaStanovanja() {
		this.osobaid=null;
		this.postanskiBroj=0;
		ulica=null;
	}
	
	public AdresaStanovanja(String osobaid, String ulica, int postanskiBroj) {
		super();
		this.osobaid = osobaid;
		this.ulica = ulica;
		this.postanskiBroj = postanskiBroj;
	}

	public String getOsobaid() {
		return osobaid;
	}

	public void setOsobaid(String osobaid) {
		this.osobaid = osobaid;
	}

	public String getUlica() {
		return ulica;
	}

	public void setUlica(String ulica) {
		this.ulica = ulica;
	}

	public int getPostanskiBroj() {
		return postanskiBroj;
	}

	public void setPostanskiBroj(int postanskiBroj) {
		this.postanskiBroj = postanskiBroj;
	}
	
	
	
	

	@Override
	public String toString() {
		return ( ulica + "," + postanskiBroj);
	}

	
	
	

}
