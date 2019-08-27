package pl.kurs.java.spring.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import pl.kurs.java.spring.model.BazaReklam;
import pl.kurs.java.spring.model.Osoba;
import pl.kurs.java.spring.repository.BazaReklamRepository;

@Service
@AllArgsConstructor
public class AutoEmailSender {

	private final JavaMailSender mailSender;
	private final BazaReklamRepository brr;

	@Async
	public void sendMail(Osoba osoba) {
		SimpleMailMessage msg = new SimpleMailMessage();
		BazaReklam textReklamy = brr.findByBranza(osoba.getBranza());
		msg.setTo(osoba.getEmail());
		msg.setFrom("a@a");
		msg.setSubject("REKLAMA");
		msg.setText(textReklamy.getUrl());
		mailSender.send(msg);
	}
}
