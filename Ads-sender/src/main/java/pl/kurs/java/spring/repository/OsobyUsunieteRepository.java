package pl.kurs.java.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.kurs.java.spring.model.OsobaUsunieta;

public interface OsobyUsunieteRepository extends JpaRepository<OsobaUsunieta, Integer> {
	List<OsobaUsunieta> findByVisible(boolean visible);
}
