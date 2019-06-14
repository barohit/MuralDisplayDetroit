package co.grandcircus.BaddamBoseTenbrick.MuralDisplayDetroit;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MuralController {

	@RequestMapping("/")
	public ModelAndView homeTest() {

		return new ModelAndView("index");
	}

}
