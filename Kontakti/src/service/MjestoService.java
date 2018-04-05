package service;

import java.util.List;



import source.Mjesto;

public class MjestoService extends Service {
	//private EntityManagerFactory emFactory;
	//private EntityManager entityManager ;
	
	public MjestoService() {
		super();
		//emFactory = Persistence.createEntityManagerFactory( "Kontakti" );
		//entityManager = emFactory.createEntityManager();
		entityManager.getTransaction( ).begin( );
	}
	
	public boolean CreateMjesto(int zipcode,String city) {
		this.checkTransactionState();
		  Mjesto mjesto = null;
	      mjesto=FindMjesto(zipcode);
	      if(mjesto!=null)
	    	  return false;
	      mjesto = new Mjesto(zipcode,city);
	      
	      entityManager.persist( mjesto );
	      entityManager.getTransaction( ).commit( );
	      return true;

 }

	/*public void UpdateMjestoPostanskiBroj(int zipcode,int newZipcode) {
        Mjesto mjesto = entityManager.find( Mjesto.class, zipcode );
        mjesto.set_postanskiBroj(newZipcode);
	    entityManager.getTransaction( ).commit( );

	}*/
	
	public void UpdateMjestoGrad(int zipcode,String newCity) {
		this.checkTransactionState();
        Mjesto mjesto = entityManager.find( Mjesto.class, zipcode );
        mjesto.set_grad(newCity);
        entityManager.getTransaction( ).commit( );

	}
	
	/*public void UpdateMjestoMultiple(int zipcode,String newCity,int newZipcode) {
        Mjesto mjesto = entityManager.find( Mjesto.class, zipcode );
	      if(newCity!=null)
        mjesto.set_grad(newCity);
	      if(newZipcode!=0)
	      mjesto.set_postanskiBroj(newZipcode);
	      entityManager.getTransaction( ).commit( );
	      
}*/
	

	public Mjesto FindMjesto(int zipcode) {
	     Mjesto mjesto = entityManager.find( Mjesto.class, zipcode );
	      return mjesto;
	   }

	public void DeleteMjesto(int zipcode) {
		this.checkTransactionState();
	   	      
	      Mjesto mjesto = entityManager.find( Mjesto.class, zipcode);
	      entityManager.remove( mjesto );
	      entityManager.getTransaction( ).commit( );
	      
}
	public List<String> FetchAllMjestoGrad() {
		/*Query query=entityManager.createQuery("Select e.grad From Mjesto e");
		List<String> list=query.getResultList();
		return list;*/
		return this.makeQuery("Select e.grad From Mjesto e");
		
	}
	
	public List<Integer> FetchAllMjestoPostanskiBroj(){
		/*Query query=entityManager.createQuery("Select e.postanskiBroj From Mjesto e");
		List<Integer> list=query.getResultList();
		return list;*/
		return this.makeQuery("Select e.postanskiBroj From Mjesto e");
	}
	
	public List<Mjesto> FetchAllMjesto(){
		/*Query query=entityManager.createQuery("Select e From Mjesto e ");
		List<Mjesto> list=query.getResultList();
		return list;*/
		return this.makeQuery("Select e From Mjesto e ");
	}
	
	public List<Mjesto> FindAllMjestoByGrad(String city){
		/*Query query=entityManager.createQuery("Select e From Mjesto e Where e.grad="+"'"+city+"'");
		List<Mjesto> list=query.getResultList();
		return list;*/
		return this.makeQuery("Select e From Mjesto e Where e.grad="+"'"+city+"'");
	}

	/*public void CloseConnection() {
		entityManager.close( );
	    emFactory.close( );
	}*/
	
	
}
