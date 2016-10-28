package es.deusto.ingenieria.sd.jdo.accounts;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.Join;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class User {
	@PrimaryKey
	private String email;
	private String password;
	private String fullName;
	private List reservation = new ArrayList();

	@Persistent(mappedBy = "user", dependentElement = "true")
	@Join
	private List<Reservation> reservations = new ArrayList<>();

	public User(String email, String password, String fullName) {
		this.email = email;
		this.password = password;
		this.fullName = fullName;
	}

	public void addReservation(Reservation reservation1) {
		this.reservation.add(reservation1);

	}

}