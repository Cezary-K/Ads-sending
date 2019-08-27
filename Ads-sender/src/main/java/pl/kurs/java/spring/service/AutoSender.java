package pl.kurs.java.spring.service;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

import pl.kurs.java.spring.model.Osoba;
import pl.kurs.java.spring.repository.OsobyRespository;

@Component
@AllArgsConstructor
public class AutoSender {

	private final OsobyRespository osobyRepo;
	AutoEmailSender aes;

	@Scheduled(cron = "0 * * * * *")
	public void sendEmail() {

		List<Osoba> active = osobyRepo.findByVisible(true);
		for (Osoba u : active) {
			System.out.println(u.getEmail());
			aes.sendMail(u);

		}

	}
}
