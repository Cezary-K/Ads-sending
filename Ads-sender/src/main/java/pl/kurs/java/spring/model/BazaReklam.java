package pl.kurs.java.spring.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class BazaReklam {
	@Id
	private int id;
	private String branza;
	private String url;

}
