package co.grandcircus.BaddamBoseTenbrick.MuralDisplayDetroit;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import co.grandcircus.BaddamBoseTenbrick.MuralDisplayDetroit.entity.Mural;
import co.grandcircus.BaddamBoseTenbrick.MuralDisplayDetroit.entity.MuralRepository;

@Controller
public class MuralController {
	
	@Autowired
	MuralRepository mr;

	@RequestMapping("/")
	public ModelAndView homeTest() {

		return new ModelAndView("Index");
	}

	@RequestMapping("/art_near_me")
	public ModelAndView displayArt() {
		List<Mural> murals = mr.findAll(); 
		return new ModelAndView("artnearme", "murals", murals); 
		
	}
}
