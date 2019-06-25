package co.grandcircus.BaddamBoseTenbrick.MuralDisplayDetroit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.servlet.ModelAndView;

import co.grandcircus.BaddamBoseTenbrick.MuralDisplayDetroit.entity.Favorite;
import co.grandcircus.BaddamBoseTenbrick.MuralDisplayDetroit.entity.FavoriteRepository;
import co.grandcircus.BaddamBoseTenbrick.MuralDisplayDetroit.entity.Mural;
import co.grandcircus.BaddamBoseTenbrick.MuralDisplayDetroit.entity.MuralRepository;
import co.grandcircus.BaddamBoseTenbrick.MuralDisplayDetroit.entity.User;




public class HelperMethods {
	
	@Autowired 
	FavoriteRepository fr; 
	
	@Autowired
	MuralRepository mr; 
	
	public ArrayList<Mural> findRecommendations(HttpSession session) {
		User user = ((User)session.getAttribute("user"));
		Integer userid = user.getUserid();  
		List<Favorite> userFavorites = fr.findByUserid(userid);
		//It maps the user_id with list of favorites 
		HashMap<Integer, List<Favorite>> favoriteLists = new HashMap<Integer, List<Favorite>>(); 
		List<Favorite> everything = fr.findAll(); 

		//List of userid's for reference 
		ArrayList<Integer> userids = new ArrayList<Integer>();
		//List of users that have a lot of common favorites
		ArrayList<Integer> commonFavoriteUsers = new ArrayList<Integer>(); 
		//Add user id's to the list without adding the non user associated favorites
		for (int i = 0; i < everything.size(); i++) {
			if (!(userids.contains(everything.get(i).getUserid())) && everything.get(i).getUserid()!= null) {
				userids.add(everything.get(i).getUserid()); 
			}
		}
		//fills the favorite_list hash map
		for (int i = 0; i < userids.size(); i++) {
			favoriteLists.put(userids.get(i), fr.findByUserid(userids.get(i)));
		}
		
		/* for (int i = 0; i < favoriteLists.size(); i++) {
			System.out.println(favoriteLists.get(userids.get(i)));
		} */
		
		 
		for (int i = 0; i < favoriteLists.size(); i++) {
			int commonCount = 0;
			int smallerListSize = 0;
			//The following if else statement determines whether the current list in 
			//the hash map or the users list is smaller
			if (userFavorites.size() < favoriteLists.get(userids.get(i)).size()) {
				smallerListSize = userFavorites.size();
			} else {
				smallerListSize = favoriteLists.get(userids.get(i)).size();
			}
			//counts the number of common favorites
			for (int j = 0; j < favoriteLists.get(userids.get(i)).size(); j++) {
				for (int k = 0; k < userFavorites.size(); k++) {
					if (favoriteLists.get(userids.get(i)).get(j).getMuralid() == userFavorites.get(k).getMuralid()) {
						commonCount++; 
					}
				}
			}
			
			if (commonCount >= smallerListSize / 4) {
				commonFavoriteUsers.add(userids.get(i));
			}
			 
		}
		//Converts the  user favorites to the mural ids
		ArrayList<Integer> favoriteMuralIds = new ArrayList<Integer>(); 
		for (int i = 0; i < userFavorites.size(); i++) {
			favoriteMuralIds.add(userFavorites.get(i).getMuralid());
		}
		
		ArrayList<Integer> recommendedExtraMurals = new ArrayList<Integer>(); 
		//Every user for whom had a significant number of common favorites with the current user, is then converted into a list of favorites
		for (int i = 0; i < commonFavoriteUsers.size(); i++) {
			List<Favorite> entryFavorites = fr.findByUserid(commonFavoriteUsers.get(i));
			for (int j = 0; j < entryFavorites.size(); j++) {
				//for each of the favorites in that user's list, if the current user does NOT have that as a favorite, it is added to a recommended favorites list
				if (!(favoriteMuralIds.contains(entryFavorites.get(j).getMuralid()))) {
					recommendedExtraMurals.add(entryFavorites.get(j).getMuralid());
				}
			}
		}
		
		ArrayList<Mural> recs = new ArrayList<Mural>(); 
		//the mural ids in the recommended favorite's list is converted to a list of murals
		for (int i = 0; i < recommendedExtraMurals.size(); i++)  {
			System.out.println(recommendedExtraMurals.get(i));
			recs.add(mr.getOne(recommendedExtraMurals.get(i)));
		}
		return recs; 
		
	}
	public ArrayList<Mural> addFavorites(String[] favorites, Integer userid) {
		if (userid != null) {
			for (int i = 0; i < favorites.length; i++) {
				try {
					fr.save(new Favorite(Integer.parseInt(favorites[i]), userid));
				} catch (DataIntegrityViolationException e) {
					
				}
			}
			List<Favorite> faves = fr.findByUserid(userid);
			// converts favorites to murals. 
			ArrayList<Mural> faves2 = new ArrayList<Mural>(); 
			for (int i = 0; i < faves.size(); i++) {
				Optional <Mural> m = mr.findById(faves.get(i).getMuralid());
				m.ifPresent(mural -> faves2.add(m.get()));
			}
			
			return faves2; 
		} else {
			for (int i = 0; i < favorites.length; i++) {
				fr.save(new Favorite(Integer.parseInt(favorites[i])));
			}
			return null; 
		}
	}

}
