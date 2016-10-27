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
		this.seats = seats;

	}

}