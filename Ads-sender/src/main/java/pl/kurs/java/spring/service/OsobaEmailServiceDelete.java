package pl.kurs.java.spring.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import pl.kurs.java.spring.model.Osoba;

@Service
@AllArgsConstructor
public class OsobaEmailServiceDelete {
	private final JavaMailSender mailSender;

	@Async
	public void sendMail(List<Osoba> listaUsunietych) {
		System.out.println("---- wysylam maila ----");
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo("cez.kruszewski@gmail.com");
		msg.setFrom("czarek@czarek.com");
		msg.setSubject("Usunieto osoby");
		msg.setText("Usunieto osoby: "
				+ listaUsunietych.parallelStream()
						.map(o -> o.getImie() + " " + o.getNazwisko() + ", mail {" + o.getEmail() +  "} , branza : " + o.getBranza())

						.collect(Collectors.joining("\n"))
				+ " \n Przywróć usunięte osoby : \n : http://localhost:8080/osoby/przywroc/");
		mailSender.send(msg);
		System.out.println("---- koncze wysylac maila");

	}
}
