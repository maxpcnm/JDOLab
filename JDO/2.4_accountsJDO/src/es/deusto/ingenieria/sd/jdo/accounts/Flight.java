package es.deusto.ingenieria.sd.jdo.accounts;

import java.util.List;
import java.util.ArrayList;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.annotations.Join;

@PersistenceCapable
public class Flight {
	@PrimaryKey
	private String flightnumber;
	private String origin;
	private String destination;
	private String airline;
	private int seats;
	private int remainingSeats;
	@Join
	private List<User> users = new ArrayList<>();

	public Flight(String flightnumber, String origin, String destination, String airline, int seats) {
		super();
		this.flightnumber = flightnumber;
		this.origin = origin;
		this.destination = destination;
		this.airline = airline;
		this.remainingSeats = seats;

	}

	public String getFlightnumber() {
		return flightnumber;
	}

	public void setFlightnumber(String flightnumber) {
		this.flightnumber = flightnumber;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getAirline() {
		return airline;
	}

	public void setAirline(String airline) {
		this.airline = airline;
	}

	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	public int getRemainingSeats() {
		return remainingSeats;
	}

	public void setRemainingSeats(int remainingSeats) {
		this.remainingSeats = remainingSeats;
	}

}