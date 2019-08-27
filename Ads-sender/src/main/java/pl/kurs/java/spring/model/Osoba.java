package pl.kurs.java.spring.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Osoba {
	@Id
	private int id;
	private String imie;
	private String nazwisko;
	private String email;
	private String branza;
	boolean visible;



}
