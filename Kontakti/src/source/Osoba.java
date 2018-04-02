package source;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Osoba {
		@Id
		private String id;
		private String ime;
		private String prezime;
		
		public Osoba() {
			ime=prezime=id=null;
		}
		
		public Osoba(String id,String ime,String prezime) {
			this.id=id;
			this.ime=ime;
			this.prezime=prezime;
		}
		
		public Osoba(Osoba other) {
			id=other.id;
			ime=other.ime;
			prezime=other.prezime;
			
		}

		public String getId() {
			return id;
		}

		public void setId(String _id) {
			this.id = _id;
		}

		public String getIme() {
			return ime;
		}

		public void setIme(String _ime) {
			this.ime = _ime;
		}

		public String getPrezime() {
			return prezime;
		}

		public void setPrezime(String _prezime) {
			this.prezime = _prezime;
		}

		@Override
		public String toString() {
			return "Osoba [id=" + id + ", ime=" + ime + ", prezime=" + prezime + "]";
		}
		
		
		
}
