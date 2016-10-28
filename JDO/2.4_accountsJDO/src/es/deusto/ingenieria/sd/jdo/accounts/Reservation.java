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

	public Reservation(int i, User user1, Flight flight1, int j) {
		super();
		this.reservationID = reservationID;
		this.user = user;
		this.flight = flight;
		this.seats = seats;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getReservationID() {
		return reservationID;
	}

	public void setReservationID(Long reservationID) {
		this.reservationID = reservationID;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

}