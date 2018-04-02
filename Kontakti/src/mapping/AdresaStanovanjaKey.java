package mapping;

import java.io.Serializable;

public class AdresaStanovanjaKey implements Serializable {
	private String osobaid;
	private String ulica;
	private int postanskiBroj;
	
	public AdresaStanovanjaKey() {
		osobaid=null;
		ulica=null;
		postanskiBroj=0;
	}

	public AdresaStanovanjaKey(String osobaid, String ulica, int postanskiBroj) {
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((osobaid == null) ? 0 : osobaid.hashCode());
		result = prime * result + postanskiBroj;
		result = prime * result + ((ulica == null) ? 0 : ulica.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AdresaStanovanjaKey other = (AdresaStanovanjaKey) obj;
		if (osobaid == null) {
			if (other.osobaid != null)
				return false;
		} else if (!osobaid.equals(other.osobaid))
			return false;
		if (postanskiBroj != other.postanskiBroj)
			return false;
		if (ulica == null) {
			if (other.ulica != null)
				return false;
		} else if (!ulica.equals(other.ulica))
			return false;
		return true;
	}

	

	
	
	
	
}
