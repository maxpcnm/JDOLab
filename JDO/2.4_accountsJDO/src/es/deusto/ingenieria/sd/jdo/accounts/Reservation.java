package es.deusto.ingenieria.sd.jdo.accounts;

import java.util.Date;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Reservation {
	@PrimaryKey
	private Long reservationID;
	private User user;
	private Flight flight;
	private Date openDate;
	private int seats;

	public Reservation(Long reservationID, User user, Flight flight, Date openDate, int seats) {
		super();
		this.reservationID = reservationID;
		this.user = user;
		this.flight = flight;
		this.openDate = openDate;
		this.seats = seats;
	}

}