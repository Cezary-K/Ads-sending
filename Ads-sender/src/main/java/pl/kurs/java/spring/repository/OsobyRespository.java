package pl.kurs.java.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.kurs.java.spring.model.Osoba;

public interface OsobyRespository extends JpaRepository<Osoba, Integer> {
	List<Osoba> findByImieContainingOrNazwiskoContaining(String imie, String nazwisko);

	List<Osoba> findByVisible(boolean visible);

}
