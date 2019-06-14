package co.grandcircus.BaddamBoseTenbrick.MuralDisplayDetroit;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import co.grandcircus.BaddamBoseTenbrick.MuralDisplayDetroit.entity.Mural;
import co.grandcircus.BaddamBoseTenbrick.MuralDisplayDetroit.entity.MuralRepository;

@Controller
public class MuralController {
	
	@Value("${map.key}")
	String mapkey;
	
	@Autowired
	MuralRepository mr;

	@RequestMapping("/")
	public ModelAndView homeTest() {

		return new ModelAndView("Index");
	}

	@RequestMapping("/art_near_me")
	public ModelAndView artNear() {
		List<Mural> murals = mr.findAll(); 
		ModelAndView mv = new ModelAndView("artnearme", "murals", murals); 
		mv.addObject("mapkey", mapkey);
		
		return mv;
		
	}
	@RequestMapping("/display_all_art")
	public ModelAndView displayArt() {
		
		ModelAndView mv = new ModelAndView("displayallart");//, "murals", murals); 
		mv.addObject("mapkey", mapkey);
		
		return mv;
		
	}
}
