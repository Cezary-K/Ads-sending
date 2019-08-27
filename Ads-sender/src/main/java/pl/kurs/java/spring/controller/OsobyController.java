package pl.kurs.java.spring.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.AllArgsConstructor;
import pl.kurs.java.spring.model.Osoba;
import pl.kurs.java.spring.model.OsobaUsunieta;
import pl.kurs.java.spring.repository.OsobyRespository;
import pl.kurs.java.spring.repository.OsobyUsunieteRepository;

import pl.kurs.java.spring.service.OsobaEmailServiceAdd;
import pl.kurs.java.spring.service.OsobaEmailServiceDelete;

@Controller
@RequestMapping("/osoby")
@AllArgsConstructor
public class OsobyController {

	private final OsobaEmailServiceDelete osobaEmailService;
	private final OsobaEmailServiceAdd osobaEmailAdd;
	private final OsobyUsunieteRepository usunieteRepo;
	private final OsobyRespository osobyRepo;

	@GetMapping("/")
	public String enterView(ModelMap model, HttpSession httpSession) {
		dodajIloscWejsc(model, httpSession);
		model.addAttribute("osoby", osobyRepo.findByVisible(true));

		return "osoby";
	}

	@GetMapping("/search")
	public String search(@RequestParam("search") String input, ModelMap model) {
		model.addAttribute("osoby", osobyRepo.findByImieContainingOrNazwiskoContaining(input, input));
		return "osoby";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam("ID") int[] id, ModelMap model) {
		List<Osoba> listaUsunietych = new ArrayList<Osoba>();
		for (int i = 0; i < id.length; i++) {
			osobyRepo.findById(id[i]).ifPresent(o -> setToFalse(o, listaUsunietych));

			System.out.println(listaUsunietych);
		}
		osobaEmailService.sendMail(listaUsunietych);

		model.addAttribute("osoby", osobyRepo.findByVisible(true));
		return "osoby";
	}

	@GetMapping("/przywroc")
	public String enterDeleteView(ModelMap model) {
		model.addAttribute("usuniete", usunieteRepo.findByVisible(false));
		return "usuniete";
	}

	@GetMapping("/add")
	public String add(@RequestParam("ID") int[] id, ModelMap model) {
		List<Osoba> listaDoDodania = new ArrayList<Osoba>();
		for (int i = 0; i < id.length; i++) {
			osobyRepo.findById(id[i]).ifPresent(o -> setToTrue(o, listaDoDodania));

			System.out.println(listaDoDodania);
		}
		osobaEmailAdd.sendMail(listaDoDodania);

		model.addAttribute("usuniete", usunieteRepo.findByVisible(false));
		return "usuniete";
	}

	private void setToTrue(Osoba o, List<Osoba> listaDoDodania) {
		try {
			o.setVisible(true);
			osobyRepo.saveAndFlush(o);
			OsobaUsunieta usunieta = new OsobaUsunieta(o.getId(), o.getImie(), o.getNazwisko(), o.getEmail(),
					o.getBranza(), true);
			usunieteRepo.saveAndFlush(usunieta);
			usunieteRepo.delete(usunieta);
			listaDoDodania.add(o);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	private void setToFalse(Osoba o, List<Osoba> listaUsunietych) {
		try {
			o.setVisible(false);
			osobyRepo.saveAndFlush(o);
			OsobaUsunieta usunieta = new OsobaUsunieta(o.getId(), o.getImie(), o.getNazwisko(), o.getEmail(),
					o.getBranza(), false);
			usunieteRepo.saveAndFlush(usunieta);
			listaUsunietych.add(o);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	private void dodajIloscWejsc(ModelMap model, HttpSession httpSession) {
		Integer iloscWejsc = Optional.ofNullable(httpSession.getAttribute("iloscWejsc")).map(Integer.class::cast)
				.map(i -> i + 1).orElse(1);
		httpSession.setAttribute("iloscWejsc", iloscWejsc);
		model.addAttribute("iloscWejsc", iloscWejsc);
	}

}
