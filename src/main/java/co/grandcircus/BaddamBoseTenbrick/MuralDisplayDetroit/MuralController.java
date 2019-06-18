package co.grandcircus.BaddamBoseTenbrick.MuralDisplayDetroit;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Set; 

import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import co.grandcircus.BaddamBoseTenbrick.MuralDisplayDetroit.entity.Favorite;
import co.grandcircus.BaddamBoseTenbrick.MuralDisplayDetroit.entity.FavoriteRepository;
import co.grandcircus.BaddamBoseTenbrick.MuralDisplayDetroit.entity.Mural;
import co.grandcircus.BaddamBoseTenbrick.MuralDisplayDetroit.entity.User;
import co.grandcircus.BaddamBoseTenbrick.MuralDisplayDetroit.entity.MuralRepository;
import co.grandcircus.BaddamBoseTenbrick.MuralDisplayDetroit.entity.UserRepository;

@Controller
public class MuralController {
	
	@Value("${map.key}")
	String mapkey;
	
	@Autowired
	MuralRepository mr;
	
	@Autowired
	UserRepository ur; 
	
	@Autowired
	FavoriteRepository fr; 

	@RequestMapping("/")
	public ModelAndView homeTest(HttpSession session) {
		session.setAttribute("loggedin", false);
		return new ModelAndView("Index");
	}
	

	@RequestMapping("/art_near_me")
	public ModelAndView displayArt() {
		List<Mural> murals = mr.findAll(); 
		ModelAndView mv = new ModelAndView("artnearme", "murals", murals); 
		mv.addObject("mapkey", mapkey);
		
		return mv;
		
	}
	
	@RequestMapping("/login") 
	public ModelAndView loginPage() {
		return new ModelAndView("login");
	}
	
	@RequestMapping("/loggingin")
	public ModelAndView login(HttpSession session, @RequestParam("username") String username, @RequestParam("password") String password) {
		User user = ur.findByUsername(username); 
		if (user != null) {
			if (user.getPassword().equals(password)) {
				session.setAttribute("loggedin", true);
				session.setAttribute("user", user);
				ModelAndView mv = new ModelAndView("userpage", "user", session.getAttribute("user")); 
				mv.addObject("session", session); 
				return mv; 
			} else {
				return new ModelAndView("error"); 
			}
		} else {
			return new ModelAndView("erroruser"); 
		}
	}
	
	@RequestMapping("/create")
	public ModelAndView createUser() {
		return new ModelAndView("adduser"); 
	}
	@RequestMapping("/display_all_art")
	public ModelAndView displayAllArt(HttpSession session) {
		ModelAndView mv = new ModelAndView("displayallart", "list", mr.findAll());
		if (((Boolean) session.getAttribute("loggedin")) == true) {
			System.out.println("Nick is chewing on his sweater");
			mv.addObject("userid", ((User) session.getAttribute("user")).getUserid()); 
		}
		return mv; 
	}
	
	
	@RequestMapping("/confirmation")
	public ModelAndView confirmation(@RequestParam("username") String username, @RequestParam("password") String password) {
		ur.save(new User(username, password)); 
		return new ModelAndView("confirmationpage"); 
	}
	
	@RequestMapping("faves")
	public ModelAndView favoriteMuralsPerUser(@RequestParam("user") Integer id) {
		List<Favorite> faves = fr.findByUserid(id);
		ArrayList<Mural> faves2 = new ArrayList<Mural>(); 
		for (int i = 0; i < faves.size(); i++) {
			Optional <Mural> m = mr.findById(faves.get(i).getMuralid());
			m.ifPresent(mural -> faves2.add(m.get()));
		}
		
		return new ModelAndView("faves", "faves", faves2);
	}
	
	@RequestMapping("logout") 
	public ModelAndView logout(HttpSession session) {
		session.setAttribute("loggedin", false);
		session.removeAttribute("user");
		return new ModelAndView("Index");
	}
	
	@RequestMapping("addtofavorites")
	public ModelAndView displayUserFavorites(@RequestParam("favorites[]") String favorites, @RequestParam("favoritez") Integer userid) {
		String[] favorites2 = favorites.split(",");
		if (userid != null) {
			for (int i = 0; i < favorites2.length; i++) {
				try {
					fr.save(new Favorite(Integer.parseInt(favorites2[i]), userid));
				} catch (DataIntegrityViolationException e) {
					
				}
			}
			List<Favorite> faves = fr.findByUserid(userid);
			ArrayList<Mural> faves2 = new ArrayList<Mural>(); 
			for (int i = 0; i < faves.size(); i++) {
				Optional <Mural> m = mr.findById(faves.get(i).getMuralid());
				m.ifPresent(mural -> faves2.add(m.get()));
			}
			
			return new ModelAndView("faves", "faves", faves2);
		} else {
			for (int i = 0; i < favorites2.length; i++) {
				fr.save(new Favorite(Integer.parseInt(favorites2[i])));
			}
			return new ModelAndView("redirect:/");
		} 
		
	}
	
	@RequestMapping("recommendations")
	public ModelAndView recommendations(HttpSession session) {
		User user = ((User)session.getAttribute("user"));
		Integer userid = user.getUserid();  
		List<Favorite> userFavorites = fr.findByUserid(userid);
		
		
		HashMap<Integer, List<Favorite>> favoriteLists = new HashMap<Integer, List<Favorite>>(); 
		List<Favorite> everything = fr.findAll(); 

		
		ArrayList<Integer> userids = new ArrayList<Integer>();
		ArrayList<Integer> commonFavoriteUsers = new ArrayList<Integer>(); 
		
		for (int i = 0; i < everything.size(); i++) {
			if (!(userids.contains(everything.get(i).getUserid())) && everything.get(i).getUserid()!= null) {
				userids.add(everything.get(i).getUserid());
			}
		}
		
		for (int i = 0; i < userids.size(); i++) {
			favoriteLists.put(userids.get(i), fr.findByUserid(userids.get(i)));
		}
		
		/* for (int i = 0; i < favoriteLists.size(); i++) {
			System.out.println(favoriteLists.get(userids.get(i)));
		} */
		
		 
		for (int i = 0; i < favoriteLists.size(); i++) {
			int commonCount = 0;
			int smallerListSize = 0;
			if (userFavorites.size() < favoriteLists.get(userids.get(i)).size()) {
				smallerListSize = userFavorites.size();
			} else {
				smallerListSize = favoriteLists.get(userids.get(i)).size();
			}
			for (int j = 0; j < favoriteLists.get(userids.get(i)).size(); j++) {
				for (int k = 0; k < userFavorites.size(); k++) {
					if (favoriteLists.get(userids.get(i)).get(j).getMuralid() == userFavorites.get(k).getMuralid()) {
						commonCount++; 
					}
				}
			}
			if (commonCount >= smallerListSize / 2) {
				commonFavoriteUsers.add(userids.get(i));
			}
			 
			/*for (int l = 0; l < commonFavoriteUsers.size(); l++) {
			System.out.println(commonFavoriteUsers.get(l));
			 } */
		}
		
		
		
		ArrayList<Integer> recommendedExtraMurals = new ArrayList<Integer>(); 
		for (int i = 0; i < commonFavoriteUsers.size(); i++) {
			List<Favorite> entryFavorites = fr.findByUserid(commonFavoriteUsers.get(i));
			for (int j = 0; j < entryFavorites.size(); j++) {
				if (!(userFavorites.contains(entryFavorites.get(j)))) {
					recommendedExtraMurals.add(entryFavorites.get(j).getMuralid());
				}
			}
		}
		
		ArrayList<Mural> recs = new ArrayList<Mural>(); 
		
		for (int i = 0; i < recommendedExtraMurals.size(); i++)  {
			System.out.println(recommendedExtraMurals.get(i));
			recs.add(mr.getOne(recommendedExtraMurals.get(i)));
		}
		
		
		
		return new ModelAndView("recommendations", "recommendations", recs);
		
	}
}
