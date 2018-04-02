package source;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Telefon {
	
	private int tip;
	@Id
	private String brojTelefona;
	@Column
	private String osobaId;
	
	public Telefon(){
		tip=0;
		osobaId=brojTelefona=null;
	}
	
	public Telefon(int tip,String brojTelefona,String osobaID){
		this.tip=tip;
		this.osobaId=osobaID;
		this.brojTelefona=brojTelefona;
	}
	
	public Telefon (Telefon drugi){
		tip=drugi.tip;
		brojTelefona=drugi.brojTelefona;
	}

	public int getTip() {
		return tip;
	}

	public void setTip(int tip) {
		this.tip = tip;
	}

	public String getBrojTelefona() {
		return brojTelefona;
	}

	public void setBrojTelefona(String brojTelefona) {
		this.brojTelefona = brojTelefona;
	}

	public String getOsobaId() {
		return osobaId;
	}

	public void setOsobaId(String osobaId) {
		this.osobaId = osobaId;
	}

	@Override
	public String toString() {
		return "Telefon [tip=" + tip + ", brojTelefona=" + brojTelefona + ", osobaId=" + osobaId + "]";
	}
	
	public String deduceType() {
		if(tip==0)
			return "home";
		if(tip==1)
			return "office";
		if(tip==2)
			return "fax";
		if(tip==3)
			return "cellphone";
		return "Unknown";
	}
	
	public void setTypeByString(String type) {
		if(type=="home")
			tip=0;
		if(type=="office")
			tip=1;
		if(type=="fax")
			tip=2;
		if(type=="cellphone")
			tip=3;
	}
	
	
}
