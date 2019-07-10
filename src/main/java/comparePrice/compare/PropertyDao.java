package comparePrice.compare;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class PropertyDao {
	private SessionFactory sessionFactory;
	
	/** Empty constructor */
	public PropertyDao() {
    }

		public void init() {
		
			try {
	            //Create a builder for the standard service registry
	            StandardServiceRegistryBuilder standardServiceRegistryBuilder = new StandardServiceRegistryBuilder();

	            //Load configuration from hibernate configuration file.
	            //Here we are using a configuration file that specifies Java annotations.
	            standardServiceRegistryBuilder.configure("resources/hibernate.cfg.xml"); 

	            //Create the registry that will be used to build the session factory
	            StandardServiceRegistry registry = standardServiceRegistryBuilder.build();
	            try {
	                //Create the session factory - this is the goal of the init method.
	                sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
	            }
	            catch (Exception e) {
	                    /* The registry would be destroyed by the SessionFactory, 
	                        but we had trouble building the SessionFactory, so destroy it manually */
	                    System.err.println("Session Factory build failed.");
	                    e.printStackTrace();
	                    StandardServiceRegistryBuilder.destroy(registry);
	            }

	            //Ouput result
	            System.out.println("Session factory built.");

	        }
	        catch (Throwable ex) {
	            // Make sure you log the exception, as it might be swallowed
	            System.err.println("SessionFactory creation failed. " + ex);
	        }
	    }
	    
		
		public void addProperty(Property property) {
			Session session = sessionFactory.getCurrentSession();
			

		//start transaction
		session.beginTransaction();
		session.save(property);

		//commit transaction
		session.getTransaction().commit();		
		session.close();
		System.out.println("Done!");
	}
         
        public void closeSessionFactory(){
            sessionFactory.close();
        }

		

}