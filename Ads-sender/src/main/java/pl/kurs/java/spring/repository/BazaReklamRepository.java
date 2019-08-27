package pl.kurs.java.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.kurs.java.spring.model.BazaReklam;

public interface BazaReklamRepository extends JpaRepository<BazaReklam, Integer> {
	BazaReklam findByBranza(String branza);
}
