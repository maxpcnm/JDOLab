package es.deusto.ingenieria.sd.jdo.accounts;

import java.util.Date;
import java.util.List;

import javax.jdo.Extent;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

public class Main {
	public static void main(String args[]) {		
		System.out.println("Datanucleus + JDO example");
		System.out.println("=========================");
	
		//Create objects -  objects in memory
		User user1= new User("rebeca.cortazar@opendeusto.es", "sd1", "Rebeca Cortazar");
		User user2 = new User("roberto.carballedo@opendeusto.es", "sd2", "Roberto Carballedo");
		

		Flight flight1 = new Flight("AA6665", "Bilbao", "Hamburg", "AmericanAirlines", 193);
		
		Flight flight2 = new Flight("BA416", "Hamburg", "London", "British Airways", 246);
		Flight flight3 = new Flight("LX317", "Hamburg",	"Brussels", "Lufthansa", 296);
		

		Reservation reservation1 = new Reservation(12345, user1, flight1, 3);
		Reservation reservation2 = new Reservation(23456, user1, flight2, 4);
		Reservation reservation3 = new Reservation(34567, user2, flight1, 4);
		Reservation reservation4 = new Reservation(45678, user2, flight3, 4);

		
		user1.addReservation(reservation1);
		reservation1.setUser(user1);
		user1.addReservation(reservation2);
		reservation2.setUser(user1);
	
		
		
		user2.addReservation(reservation3);
		reservation3.setUser(user2);
		user2.addReservation(reservation4);
		reservation4.setUser(user2);
		

		
		// Load Persistence Manager Factory - referencing the Persistence Unit defined in persistence.xml
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		// Persistence Manager
		PersistenceManager pm = null;
		//Transaction to group DB operations
		Transaction tx = null;		
		
		try {
			System.out.println("- Store objects in the DB");			
			//Get the Persistence Manager
			pm = pmf.getPersistenceManager();
			//Obtain the current transaction
			tx = pm.currentTransaction();		
			//Start the transaction
			tx.begin();
			
			pm.makePersistent(user1);
			pm.makePersistent(user2);			
			
			//End the transaction
			tx.commit();			
			System.out.println("  * Objects and their relationships have been stored!");
			System.out.println("- Updating seats from 3 to 4 reservation ID ");
			System.out.println("    - " + reservation1.getReservationID() + "(of user " + reservation1.getUser().getFullName() + ")");
			
			reservation1.setSeats(4);
			Flight x = reservation1.getFlight();
			x.setRemainingSeats(x.getRemainingSeats()-1);
			System.out.println("  Seats updated!");
			
		} catch (Exception ex) {
			System.err.println(" $ Error storing objects in the DB: " + ex.getMessage());
			ex.printStackTrace();
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			
			if (pm != null && !pm.isClosed()) {
				pm.close();
				// ATTENTION -  Datanucleus detects that the objects in memory were changed and they are flushed to DB
			}
		}
		
		try {
			System.out.println("- Retrieving all the reservations using an 'Extent'...");			
			//Get the Persistence Manager
			pm = pmf.getPersistenceManager();
			//Obtain the current transaction
			tx = pm.currentTransaction();		
			//Start the transaction
			tx.begin();
		
			Extent<Reservation> extent = pm.getExtent(Reservation.class, true);
			
			for (Reservation reservation : extent) {
				System.out.println("  -> " + reservation);
			}
			//Notice the change in the accounts' balances
			//End the transaction
			tx.commit();
		} catch (Exception ex) {
			System.err.println(" $ Error retrieving accounts using an 'Extent': " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			
			if (pm != null && !pm.isClosed()) {
				pm.close();
			}
		}

		
		

		try {
			System.out.println("- Deleting Reservation 4 ...");			
			//Get the Persistence Manager
			pm = pmf.getPersistenceManager();
			//Obtain the current transaction
			tx = pm.currentTransaction();		
			//Start the transaction
			tx.begin();

			Query<Reservation> query = pm.newQuery(Reservation.class);
			@SuppressWarnings("unchecked")
			List<Reservation> resultset = (List<Reservation>) query.execute();
			
			resultset.remove(reservation4); //not sure yet whether this will work
			
			
			//End the transaction
			tx.commit();
		} catch (Exception ex) {
			System.err.println(" $ Error deleting 'User->Address' relation: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			
			if (pm != null && !pm.isClosed()) {
				pm.close();
			}
		}

//		try {
//			System.out.println("- Cleaning the DB...");			
//			//Get the Persistence Manager
//			pm = pmf.getPersistenceManager();
//			//Obtain the current transaction
//			tx = pm.currentTransaction();
//			//Start the transaction
//			tx.begin();
//			
//			//Delete users from DB
//			// As we are considering accounts as dependents on user - CASCADING BEHAVIOUR - ACCOUNTS DELETED
//			Query<User> query1 = pm.newQuery(User.class);
//			System.out.println(" * '" + query1.deletePersistentAll() + "' users and their accounts deleted from the DB.");
//			//Delete addresses from DB
//			Query<Flight> query2 = pm.newQuery(Flight.class);
//			System.out.println(" * '" + query2.deletePersistentAll() + "' addresses deleted from the DB.");
//			
//			//End the transaction
//			tx.commit();
//		} catch (Exception ex) {
//			System.err.println(" $ Error cleaning the DB: " + ex.getMessage());
//			ex.printStackTrace();
//		} finally {
//			if (tx != null && tx.isActive()) {
//				tx.rollback();
//			}
//			
//			if (pm != null && !pm.isClosed()) {
//				pm.close();
//			}
		}
		

		System.out.println("End of the Datanucleus + JDO example");
		System.out.println("====================================");	
	}}