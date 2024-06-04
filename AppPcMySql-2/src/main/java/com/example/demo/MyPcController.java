package com.example.demo;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.mail.MessagingException;

import org.springframework.ui.Model;

// dichiariamo una classe semplice con annotazione controller

@Controller
public class MyPcController {

	private final PcJDBCTemp computerJDBCTemp;
	private final PcOrdJDBCTemp pcOrdJDBCTemp;

	@Autowired
	public MyPcController(PcJDBCTemp computerJDBCTemp, PcOrdJDBCTemp pcOrdJDBCTemp) {
		this.computerJDBCTemp = computerJDBCTemp;
		this.pcOrdJDBCTemp = pcOrdJDBCTemp;
	}
	@Autowired
	private EmailService emailService;

// mappiamo i metodi con get mapping per indicare quelli che saranno i percorsi dell'applicazione

	Computer orol2 = new Computer();
	ArrayList<Computer> venduto = new ArrayList<>();

	@GetMapping("/")
	public String getComputer() {

		return "magazzino";

	}

	@GetMapping("/getComputer")
	public String getAll(Model model) {

		ArrayList<Computer> lista = computerJDBCTemp.ritornaComputer();
		model.addAttribute("lista", lista);

		return "listaComputer2";

	}

	@GetMapping("/change")
	public String change(Model model) {

		ArrayList<Computer> lista = computerJDBCTemp.ritornaComputer();
		model.addAttribute("magazzino", lista);

		return "changeQnt2";
	}

	@PostMapping("/changePc")
	public ResponseEntity<String> changeP(@RequestParam("pc") String[] ordini, @RequestParam("pezzi") String[] pezzi,
			Model model) {

		ArrayList<Integer> pc1 = new ArrayList<>();
		
		ArrayList<Integer> qnt1 = new ArrayList<>();

		for (String s : pezzi) {
			if (!s.isEmpty()) {
				int x = Integer.parseInt(s);
				qnt1.add(x);
			}
		}

		for (String s : ordini) {
			if (!s.isEmpty()) {
				int x = Integer.parseInt(s);
				pc1.add(x);
			}
		}

		for (int i = 0; i < qnt1.size(); i++) {

			computerJDBCTemp.updatePezzi(qnt1.get(i), pc1.get(i));

		}

		return ResponseEntity.ok("Articoli aggiunti con successo");
	}

	/*
	 * @PostMapping("/sellComputer") public String addWatch(@RequestParam("ordini")
	 * String [] ordini,@RequestParam("pezzi") String []
	 * pezzi,@RequestParam("urlImmagine") String[] urlImag,Model model){
	 * 
	 * ArrayList<Computer> orologi = computerJDBCTemp.ritornaComputer();
	 * ArrayList<Double> qnt = new ArrayList <>();
	 * 
	 * for (String s: pezzi) { if (!s.isEmpty()) { double x = Double.parseDouble(s);
	 * qnt.add(x); }} double prezzo = 0;
	 * 
	 * ArrayList <Watch> watches = new ArrayList <>();
	 * 
	 * for (int j = 0; j < ordini.length; j++) { for (int i = 0; i < orologi.size();
	 * i++) { if (orologi.get(i).modello.equals(ordini[j])) {
	 * 
	 * Watch watch = new Watch(); watch.setNome(ordini[j]);
	 * watch.setUrlImages(urlImag[j]); watch.setQnt(qnt.get(j)); watches.add(watch);
	 * 
	 * prezzo += orologi.get(i).getPrezzo() * qnt.get(j);
	 * System.out.println(watch.getUrlImages()); }
	 * 
	 * } }
	 * 
	 * model.addAttribute("ordini", watches); model.addAttribute("prezzo", prezzo);
	 * 
	 * return "getCarrello"; }
	 */

	@PostMapping("/AddPc")
	public String addComputer(@RequestParam("marca") String marca, @RequestParam("tipologia") String tipologia,
			@RequestParam("modello") String modello, @RequestParam("descrizione") String descrizione,
			@RequestParam("qnt") String qnt, @RequestParam("urlImg") String urlImg,
			@RequestParam("prezzo") String prezzo, Model model) {

		Computer comp = new Computer();

		comp.setMarca(marca);
		comp.setTipologia(tipologia);
		comp.setModello(modello);
		comp.setDescrizione(descrizione);
		comp.setQnt(Integer.parseInt(qnt));
		comp.setUrlImg(urlImg);
		comp.setPrezzo(Double.parseDouble(prezzo));

		model.addAttribute("comp", comp);
		computerJDBCTemp.insertComputer(marca, tipologia, modello, descrizione, comp.getQnt(), urlImg,
				comp.getPrezzo());
		return "insConferma";
	}

	@PostMapping("/sellPc")
	public String addWatch(@RequestParam("ordini") String[] ordini, @RequestParam("pezzi") String[] pezzi,
			Model model) {
		ArrayList<PcOrd> pc = new ArrayList<>();
		ArrayList<Computer> lista = computerJDBCTemp.ritornaComputer();
		ArrayList<Integer> qnt = new ArrayList<>();

		for (String s : pezzi) {
			if (!s.isEmpty()) {
				int x = Integer.parseInt(s);
				qnt.add(x);

			}
		}
		double prezzo = 0;

		for (int j = 0; j < ordini.length; j++) {
			for (int i = 0; i < lista.size(); i++) {
				if (lista.get(i).id == (Integer.parseInt(ordini[j]))) {

					computerJDBCTemp.scalaPezzi(qnt.get(j), lista.get(i).id);
					prezzo += lista.get(i).getPrezzo() * qnt.get(j);
					PcOrd computer = new PcOrd();
					computer.setModello(lista.get(i).getModello());
					computer.setQnt(qnt.get(j));
					computer.setUrlImages(lista.get(i).getUrlImg());
					pc.add(computer);

				}

			}
		}

		System.out.println(prezzo);

		model.addAttribute("lista", pc);

		model.addAttribute("prezzo", prezzo);
		return "acqConferma";
	}

	@PostMapping("/confEmail")
	public ResponseEntity<String> confEmail(Model model, @RequestParam("prezzo") String prezzo,
			@RequestParam("modello") String[] ordini, @RequestParam("qnt") String[] pezzi, @RequestParam("email") String to, @RequestParam("urlImg") String urlImg) {
		ArrayList<PcOrd> pc = new ArrayList<>();
		ArrayList<Integer> qnt = new ArrayList<>();
		ArrayList<Computer> lista = computerJDBCTemp.ritornaComputer();
		String text = "";
		for (String s : pezzi) {
			if (!s.isEmpty()) {
				int x = Integer.parseInt(s);
				qnt.add(x);

			}
		}

		for (int j = 0; j < ordini.length; j++) {
			for (int i = 0; i < lista.size(); i++) {
				if (lista.get(i).getModello().equals(ordini[j])) {

					PcOrd computer = new PcOrd();
					computer.setModello(lista.get(i).getModello());
					computer.setQnt(qnt.get(j));
					computer.setUrlImages(lista.get(i).getUrlImg());
					pc.add(computer);
					
				}

			}
		}
		// Andiamo ad aggiungere i pezzi dei prodotti acquistati nella tabella pcord di mysql
		for (PcOrd comp : pc) {
			 pcOrdJDBCTemp.updatePezzi(comp.getQnt(), comp.getModello());
			 }
		
		
		
		// riguarda invio email
		String subject = "Informazioni acquisto";
		// Invia l'url ma non l'immagine
		/*for (PcOrd acq : pc) {
			
		text = "Hai acquistato il modello: " + acq.getModello() + "\nQuantità di pezzi: " + acq.getQnt() + "\n" + acq.getUrlImages();
		}
		emailService.sendSimpleEmail(to, subject, text);
		return ResponseEntity.ok("Email sent successfully");*/
		
		// inviare anche l'immagine
		StringBuilder htmlBody = new StringBuilder();

        htmlBody.append("<h1>Informazioni sull'acquisto</h1>");

        for (PcOrd acq : pc) {
            htmlBody.append("<h3 style='color: blue;'>Hai acquistato il modello: ").append(acq.getModello()).append("</h3>");
            htmlBody.append("<p>Quantità di pezzi: ").append(acq.getQnt()).append("</p>");
            htmlBody.append("<img src='").append(acq.getUrlImages()).append("' alt='Immagine del componente' style='width:300px;height:auto;'>");
            htmlBody.append("<hr>");
        }
        	htmlBody.append("<h3 style='color: red;'>Prezzo totale: " +prezzo +" euro</h3");
        try {
            emailService.sendHtmlEmail(to, subject, htmlBody.toString());
            return ResponseEntity.ok("Email inviata con successo");
        } catch (MessagingException e) {
            return ResponseEntity.status(500).body("Error sending email: " + e.getMessage());
        }
		
		
	}

	@GetMapping("/formEmail")
	public String formEmail() {

		return "formEmail";
	}

	@PostMapping("sendEmail")
	public ResponseEntity<String> sendEmail(@RequestParam("to") String to, @RequestParam("subject") String subject,
			@RequestParam("text") String text) {
		emailService.sendSimpleEmail(to, subject, text);
		return ResponseEntity.ok("Email sent successfully");
	}
	@GetMapping("/showPcOrders")
    public String showPcOrders(Model model) {
        ArrayList<PcOrd> pcOrders = new ArrayList<>();
        pcOrders = pcOrdJDBCTemp.ritornaOrdPc();
        
        // Convertiamo la lista in una stringa JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String pcOrdersJson = "";
    	try {
			pcOrdersJson = objectMapper.writeValueAsString(pcOrders);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       

        // Passiamo la stringa JSON al modello
        model.addAttribute("pcOrdersJson", pcOrdersJson);
        
        model.addAttribute("pcOrders", pcOrders);
        return "pcOrders";
    }
}
