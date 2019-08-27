package pl.kurs.java.spring.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import pl.kurs.java.spring.model.Osoba;

@Service
@AllArgsConstructor
public class OsobaEmailSevicePotwierdzenie {
	private final JavaMailSender mailSender;

	@Async
	public void sendMail(Osoba osoba) {
		System.out.println("---- wysylam maila ----");
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(osoba.getEmail());
		msg.setFrom("czarek@czarek.com");
		msg.setSubject("Usunieto z listy");
		msg.setText("usunieto Cie z listy");
		mailSender.send(msg);
		System.out.println("wyslalem potwierdzenie do " + osoba.getEmail());

	}
}
