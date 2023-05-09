package com.robvega.ninjagold.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class NinjaGoldController {
	Random random = new Random();
	
	// private methods
	private String dateFormat() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("(MMMM dd yyyy HH:mm a)");
		return simpleDateFormat.format(new Date());
	}
	
	private void addRecord(String place, int gold, HttpSession session) {
		ArrayList<String> records = (ArrayList<String>) session.getAttribute("records");
		
		if (place.equals("spa")) {
			records.add(String.format("You entered a %s and expended %d gold. " + dateFormat(), place, gold));
		} else {
			records.add(String.format("You entered a %s and earned %d gold. " + dateFormat(), place, gold));
		}
	
		session.setAttribute("records", records);	
	}
	
	private void addRecord(String place, int gold, boolean hasWon, HttpSession session) {
		ArrayList<String> records = (ArrayList<String>) session.getAttribute("records");
		
		if(hasWon) {
			records.add(String.format("You entered a %s and earned %d gold. " + dateFormat(), place, gold));
		} else {
			records.add(String.format("You entered a %s and lost %d gold, Ouch. " + dateFormat(), place, gold));
		}
	
		session.setAttribute("records", records);	
	}
	
	private void addGold(int gold, HttpSession session) {
		int currentGold = (int) session.getAttribute("gold");
		session.setAttribute("gold", currentGold + gold);
	}
	
	private void removeGold(int gold, HttpSession session) {
		int currentGold = (int) session.getAttribute("gold");
		session.setAttribute("gold", currentGold - gold);
	}
	
	// routes
	@GetMapping("/")
	public String index(HttpSession session) {
		if(session.isNew()) {
			session.setAttribute("gold", 0);
			session.setAttribute("records", new ArrayList<String>());
		}
		
		int gold = (int) session.getAttribute("gold");
		if (gold < -25) {
			return "redirect:/prison";
		}
		
		return "index.jsp";
	}
	
	@GetMapping("/prison")
	public String prison() {
		return "prison.jsp";
	}
	
	@PostMapping("/gold/{place}")
	public String gold(@PathVariable("place") String place) {
		System.out.println(place);
		return "redirect:/";
	}
	
	@PostMapping("/farm")
	public String farm(HttpSession session) {
		int gold = random.nextInt(10, 20);
		
		addGold(gold, session);
		addRecord("farm", gold, session);
		
		return "redirect:/";
	}
	
	@PostMapping("/cave")
	public String cave(HttpSession session) {
		int gold = random.nextInt(5, 10);
		
		addGold(gold, session);
		addRecord("cave", gold, session);
		
		return "redirect:/";
	}
	
	@PostMapping("/house")
	public String house(HttpSession session) {
		int gold = random.nextInt(2, 5);
		
		addGold(gold, session);
		addRecord("house", gold, session);
		
		return "redirect:/";
	}
	
	@PostMapping("/casino")
	public String casino(HttpSession session) {
		boolean hasWon = random.nextBoolean();
		int gold = random.nextInt(50);
		
		session.setAttribute("hasWon", hasWon);
		
		if (hasWon) {
			addGold(gold, session);
			addRecord("casino", gold, hasWon, session);
		} else {
			removeGold(gold, session);
			addRecord("casino", gold, hasWon, session);
		}
		
		return "redirect:/";
	}
	
	@PostMapping("/spa")
	public String spa(HttpSession session) {
		
		int gold = random.nextInt(5, 20);
		
		removeGold(gold, session);
		addRecord("spa", gold, session);
		
		return "redirect:/";
	}
	
	@PostMapping("/restart")
	public String restart(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
}
