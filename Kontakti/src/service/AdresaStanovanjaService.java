
package service;
import java.util.List;
import javax.persistence.Query;
import mapping.AdresaStanovanjaKey;
import source.AdresaStanovanja;

public class AdresaStanovanjaService extends Service{
	
	//private EntityManagerFactory emFactory;
	//private EntityManager entityManager;
	
	public AdresaStanovanjaService() {
	  super();
	   //emFactory = Persistence.createEntityManagerFactory( "Kontakti" );
	  //entityManager = emFactory.createEntityManager( );
	  entityManager.getTransaction( ).begin( );
	
	}
	
	public boolean CreateAdresaStanovanja(String street,int zipcode,String personId) {
		this.checkTransactionState();
		  AdresaStanovanja adresa = null; 
		  adresa=this.FindAdresaStanovanja(street, zipcode, personId);
		  if(adresa!=null)
			  return false;
		  adresa = new AdresaStanovanja(personId,street,zipcode);
		  //adresa.setOsobaid(personId);
	      //adresa.setPostanskiBroj(zipcode);
	      //adresa.setUlica(street);
	      //entityManager.persist( adresa );
	      entityManager.persist( adresa );
	      entityManager.getTransaction( ).commit( );
	      return true;
	      
	}
 
		
	/*public void UpdateAdresaStanovanjaUlica(String street,int zipcode,String personId,String newStreet) {
		AdresaStanovanjaKey key=new AdresaStanovanjaKey(personId,street,zipcode);
		AdresaStanovanja adresa = entityManager.find( AdresaStanovanja.class, key );
		adresa.setUlica(newStreet);
		entityManager.getTransaction( ).commit( );
	}
	
	public void UpdateAdresaStanovanjaPostanskiBroj(String street,int zipcode,String personId,int newZipcode) {
		AdresaStanovanjaKey key=new AdresaStanovanjaKey(personId,street,zipcode);
		AdresaStanovanja adresa = entityManager.find( AdresaStanovanja.class, key );
		adresa.setPostanskiBroj(newZipcode);
		entityManager.getTransaction( ).commit( );
	}
	
	public void UpdateAdresaStanovanjaOsobaId(String street,int zipcode,String personId,String newPersonId) {
		AdresaStanovanjaKey key=new AdresaStanovanjaKey(personId,street,zipcode);
		AdresaStanovanja adresa = entityManager.find( AdresaStanovanja.class, key );
		adresa.setOsobaid(newPersonId);
		entityManager.getTransaction( ).commit( );
	}
	
	public void UpdateAdresaStanovanjaOsobaMultiple(String street,int zipcode,String personId,String newStreet,int newZipcode,String newPersonId) {
		AdresaStanovanjaKey key=new AdresaStanovanjaKey(personId,street,zipcode);
		AdresaStanovanja adresa = entityManager.find( AdresaStanovanja.class, key );
		if(newPersonId!=null)
		adresa.setOsobaid(newPersonId);
		if(newZipcode!=0)
		adresa.setPostanskiBroj(newZipcode);
		if(newStreet!=null)
		adresa.setUlica(newStreet);
		entityManager.getTransaction( ).commit( );
	}*/
 
	public AdresaStanovanja FindAdresaStanovanja(String street,int zipcode,String personId) {
	   		
		  AdresaStanovanjaKey key=new AdresaStanovanjaKey(personId,street,zipcode);
	      AdresaStanovanja adresa = entityManager.find( AdresaStanovanja.class,key );

	      //System.out.println("osobaID : "+adresa.getOsobaid());
	      //System.out.println("PoštanskiBroj : "+adresa.getPostanskiBroj());
	      //System.out.println("Ulica :" + adresa.getUlica());
	      return adresa;
	}
	 
	public boolean DeleteAdresaStanovanja(String street,int zipcode,String personId) {
		this.checkTransactionState();
		  boolean ret=true;
	 	  AdresaStanovanjaKey key=new AdresaStanovanjaKey(personId,street,zipcode);
	      AdresaStanovanja adresa = entityManager.find( AdresaStanovanja.class,key );
	      
	      if(adresa.getOsobaid()!=null || adresa.getPostanskiBroj()!=0 || adresa.getUlica()!=null) {
	    	  entityManager.remove( adresa );
	    	  ret=false;
	      }
	      entityManager.getTransaction( ).commit( );
	      return ret;
	      
	}
	
	public List<AdresaStanovanja> FetchAllAdresaStanovanja(){
		/*Query query=entityManager.createQuery("Select e From AdresaStanovanja e");
		List<AdresaStanovanja> list=query.getResultList();
		return list;*/
		return this.makeQuery("Select e From AdresaStanovanja e");
	}
	
	public List<String> FetchAllAdresaStanovanjaOsobaId(){
		/*Query query = entityManager.createQuery("Select e.osobaid From AdresaStanovanja e");
		List<String> list = query.getResultList();
		return list;*/
		return this.makeQuery("Select e.osobaid From AdresaStanovanja e");
	}
	
	
	public List<Integer> FetchAllAdresaStanovanjaPostanskiBroj(){
		/*Query query = entityManager.createQuery("Select e.postanskiBroj From AdresaStanovanja e");
		List<Integer> list = query.getResultList();
		return list;*/
		return this.makeQuery("Select e.postanskiBroj From AdresaStanovanja e");
	}
	
	public List<String> FetchAllAdresaStanovanjaUlica(){
		/*Query query = entityManager.createQuery("Select e.ulica From AdresaStanovanja e");
		List<String> list = query.getResultList();
		return list;*/
		return this.makeQuery("Select e.ulica From AdresaStanovanja e");
	}
	
	public List<AdresaStanovanja> FindAdresaStanovanjaByOsobaId(String personId){
		/*Query query = entityManager.createQuery("Select e From AdresaStanovanja e Where e.osobaid="+ "'" + personId + "'");
		List<AdresaStanovanja> list = query.getResultList();
		return list;*/
		return this.makeQuery("Select e From AdresaStanovanja e Where e.osobaid="+ "'" + personId + "'");
	}
	
	public List<AdresaStanovanja> FindAdresaStanovanjaByUlica(String street){
		/*Query query = entityManager.createQuery("Select e From AdresaStanovanja e Where e.ulica="+ "'" + street + "'");
		List<AdresaStanovanja> list = query.getResultList();
		return list;*/
		return this.makeQuery("Select e From AdresaStanovanja e Where e.ulica="+ "'" + street + "'");
	}
	
	public List<AdresaStanovanja> FindAdresaStanovanjaByPostanskiBroj(Integer zipcode){
		/*Query query = entityManager.createQuery("Select e From AdresaStanovanja e Where e.postanskiBroj="+ "'" + zipcode + "'");
		List<AdresaStanovanja> list = query.getResultList();
		return list;*/
		return this.makeQuery("Select e From AdresaStanovanja e Where e.postanskiBroj="+ "'" + zipcode + "'");
	}
	 
}
