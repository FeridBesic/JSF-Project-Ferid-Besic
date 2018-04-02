package source;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table
public class Mjesto {
	@Id
	private int postanskiBroj;
	private String grad;
	
	
	public Mjesto() {
		grad=null;
		postanskiBroj=0;
	}
	
	
	public Mjesto(int postanskiBroj, String grad) {
		super();
		this.postanskiBroj = postanskiBroj;
		this.grad = grad;
	}




	public int get_postanskiBroj() {
		return postanskiBroj;
	}


	public void set_postanskiBroj(int _postanskiBroj) {
		this.postanskiBroj = _postanskiBroj;
	}


	public String get_grad() {
		return grad;
	}


	public void set_grad(String _grad) {
		this.grad = _grad;
	}


	@Override
	public String toString() {
		return "Mjesto [postanskiBroj=" + postanskiBroj + ", grad=" + grad + "]";
	}


	
		
	
	

}
