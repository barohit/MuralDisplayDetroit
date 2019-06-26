package co.grandcircus.BaddamBoseTenbrick.MuralDisplayDetroit;


import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.math.BigDecimal;
import java.math.RoundingMode;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Random;


import javax.servlet.ServletContext;



import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;

import co.grandcircus.BaddamBoseTenbrick.MuralDisplayDetroit.Json.Location;
import co.grandcircus.BaddamBoseTenbrick.MuralDisplayDetroit.Json.Result;
import co.grandcircus.BaddamBoseTenbrick.MuralDisplayDetroit.entity.CheckIn;
import co.grandcircus.BaddamBoseTenbrick.MuralDisplayDetroit.entity.CheckInRepository;
import co.grandcircus.BaddamBoseTenbrick.MuralDisplayDetroit.entity.Favorite;
import co.grandcircus.BaddamBoseTenbrick.MuralDisplayDetroit.entity.FavoriteRepository;
import co.grandcircus.BaddamBoseTenbrick.MuralDisplayDetroit.entity.Mural;
import co.grandcircus.BaddamBoseTenbrick.MuralDisplayDetroit.entity.MuralRepository;
import co.grandcircus.BaddamBoseTenbrick.MuralDisplayDetroit.entity.User;
import co.grandcircus.BaddamBoseTenbrick.MuralDisplayDetroit.entity.UserRepository;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentials;


@Controller
public class MuralController {
	
	@Value("${map.key}")
	String mapkey;
	
	@Autowired
	ServletContext context; 
	
	@Autowired
	MuralRepository mr;
	
	@Autowired
	UserRepository ur; 
	
	@Autowired
	FavoriteRepository fr; 
	
	@Autowired
	CheckInRepository cr; 
	
	//method to get recommendations for a user
	//returns a list of murals
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
				if (!(favoriteMuralIds.contains(entryFavorites.get(j).getMuralid())) && !(recommendedExtraMurals.contains(entryFavorites.get(j).getMuralid()))) {
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
	
	//homepage. displays a random mural as background
	@RequestMapping("/")
	public ModelAndView homeTest(HttpSession session) {
		if (((Integer) session.getAttribute("counter")) == null) {
			session.setAttribute("loggedin", false);
			session.removeAttribute("user");
			session.setAttribute("counter", -15);
		}
		
		ModelAndView mv = new ModelAndView("Index");
		
		int num = mr.findAll().size();
		Random rand = new Random();
		int rand1 = rand.nextInt(num);
		Mural mural = mr.findById(rand1).orElse(null);

		mv.addObject("mural", mural);
		return mv;
	}
	

	public void addUserSession(HttpSession session, ModelAndView mv) {
		if (((Boolean) session.getAttribute("loggedin")) == true) {
			mv.addObject("userid", ((User) session.getAttribute("user")).getUserid()); 
		}
	}
	
	//directs to a map of murals
	@RequestMapping("/art_near_me")
	public ModelAndView displayArt(HttpSession session) {
		List<Mural> murals = mr.findAll(); 
		ModelAndView mv = new ModelAndView("artnearme", "murals", murals); 
		mv.addObject("mapkey", mapkey);
		addUserSession(session, mv);
		return mv;
		
	}
	
	@RequestMapping("/login") 
	public ModelAndView loginPage(HttpSession session) {
		if(((Boolean)session.getAttribute("loggedin"))== true)
		{
			return new ModelAndView("userpage","user",session.getAttribute("user"));
			
		}
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
				return new ModelAndView("errorpass"); 
			}
		} else {
			return new ModelAndView("erroruser"); 
		}
	}
	
	@RequestMapping("userpage")
	public ModelAndView returnToUser(HttpSession session) {
		return new ModelAndView("userpage", "user", session.getAttribute("user")); 
	}
	
	@RequestMapping("/create")
	public ModelAndView createUser() {
		return new ModelAndView("adduser"); 
	}
	
	@RequestMapping("/neighborhood")
	public ModelAndView displayNeighborhood(HttpSession session) {


			
		
		HashMap<String, List<Mural>> neighborhood = new HashMap<String, List<Mural>>(); 
		List<String> nlist = mr.findDistinctNeighborhood();
		
		for(int i=0;i< nlist.size();i++) {
			neighborhood.put(nlist.get(i), mr.findAllByNeighborhood(nlist.get(i)));	
			//neighborhood.put(mural.get(i), murals.get(i));
			//System.out.println(Arrays.asList(neighborhood));
		}
		
		ModelAndView mv = new ModelAndView("neighborhood","list", neighborhood);
		addUserSession(session, mv);
		
		//mv.addObject("neighbors", mr.findDistinctMurals());
		return mv;
	}
	@RequestMapping("/display_all_art")
	public ModelAndView displayAllArt(HttpSession session) {
		ModelAndView mv = new ModelAndView("displayallart", "list", mr.findAll());
		addUserSession(session, mv);
		return mv; 
	}
	
	@RequestMapping("/upload_art")
	public ModelAndView uploadArt() {
		return new ModelAndView("uploadArt"); 
	}
	
	@RequestMapping("/upload")
	public ModelAndView fileUpload(@RequestParam("picture") MultipartFile picture, @RequestParam("url") String url, @RequestParam("name") String name, @RequestParam("artist") String artist, @RequestParam("address") String address, @RequestParam("neighborhood") String neighborhood) {
		RestTemplate rt = new RestTemplate(); 
		
		//converts address to a String
		String[] addrss = address.split(" ");
		String add = ""; 
		for (int i = 0; i < addrss.length; i++) {
			add += addrss[i];
			if (i != addrss.length - 1) {
				add += "+";
			}
		}
		
		//converts address to lattitude and longtitude in order to add to the database using google maps API
		Result res = rt.getForObject("https://maps.googleapis.com/maps/api/geocode/json?address=" + add + "&key=" + mapkey, Result.class);
		File file = null;
		try {
			file = convertMultiPartToFile(picture);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Could not convert");
		}
		
		//uploads the image to S3 on our AWS server in order to retrieve a URL; 
		BasicAWSCredentials credentials = new BasicAWSCredentials(CommonConstants.ACCESS_KEY_ID, CommonConstants.ACCESS_SEC_KEY);
		AmazonS3Client.builder();
		AmazonS3 s3client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion(Regions.US_EAST_2).build(); 
		s3client.putObject("muralbucket", name, file);
		
		//fetches the url and adds the new mural to the database
		String imgloc = "https://muralbucket.s3.us-east-2.amazonaws.com/" + name; 
		mr.save(new Mural(imgloc, res.getResults()[0].getGeometry().getLocation().getLat(), res.getResults()[0].getGeometry().getLocation().getLng(), address, neighborhood, name, artist));
		return new ModelAndView("uploadconfirmation", "location", imgloc);
		
		
	}
	
	private File convertMultiPartToFile(MultipartFile file) throws IOException {
	    File convFile = new File(file.getOriginalFilename());
	    FileOutputStream fos = new FileOutputStream(convFile);
	    fos.write(file.getBytes());
	    fos.close();
	    return convFile;
	}
	
	private static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();
	 
	    BigDecimal bd = new BigDecimal(Double.toString(value));
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
	
	
	
	@RequestMapping("selectionCheckIn")
	public ModelAndView selectionCheckIn(@RequestParam("selection") Integer muralid, HttpSession session) {
		Mural m = mr.getOne(muralid);
		if (((Boolean)session.getAttribute("loggedin")) == true) {
			cr.save(new CheckIn(m.getMuralid(), ((User)session.getAttribute("user")).getUserid()));
			System.out.println(((User)session.getAttribute("user")).getUserid());
		} else {
			cr.save(new CheckIn(m.getMuralid()));

		}
		return new ModelAndView("finalCheckIn", "mural", m);
		
	}
	@RequestMapping("/checkin")
	public ModelAndView checkin(@RequestParam("lattitude") String lat, @RequestParam("longitude") String lon) {
		List <Mural> murals = mr.findAll(); 
		ArrayList<Double> lattitude = new ArrayList<Double>(); 
		ArrayList<Double> longitude = new ArrayList<Double>(); 
		ArrayList<Mural> m = new ArrayList<Mural>(); 
		for (int i = 0; i < murals.size(); i++) {
			lattitude.add(round(murals.get(i).getLatitude(), 3));
			longitude.add(round(murals.get(i).getLongitude(), 3));
		}
		int index = 0; 
		for (int i = 0; i < lattitude.size(); i++) {
			if (round(Double.parseDouble(lat), 3) == lattitude.get(i)) {
				index = i; 
			}
			if (round(Double.parseDouble(lon), 3) == longitude.get(index)) {
				if(!(m.contains(murals.get(index)))) {
					m.add(murals.get(index)); 
				}
			}
		}
		
		return new ModelAndView("CheckIn", "mural", m);
		
	}
	
	
	@RequestMapping("/confirmation")
	public ModelAndView confirmation(@RequestParam("username") String username, @RequestParam("password") String password) {
		try {
			ur.save(new User(username, password)); 
		}  catch (DataIntegrityViolationException e){
			return new ModelAndView("errorcreate");
		}
		return new ModelAndView("confirmationpage"); 
	}
	
	@RequestMapping("faves")
	public ModelAndView favoriteMuralsPerUser(@RequestParam("user") Integer id) {
		List<Favorite> faves = fr.findByUserid(id);
		ArrayList<Mural> faves2 = new ArrayList<Mural>(); 
		for (int i = 0; i < faves.size(); i++) {
			//Optional is an advanced hibernate Query class which represents the possibility of a mural 
			//being found by the query
			Optional <Mural> m = mr.findById(faves.get(i).getMuralid());
			// this takes a function as an argument and executes only if the mural. 
			m.ifPresent(mural -> faves2.add(m.get()));
		}
		
		return new ModelAndView("faves", "faves", faves2);
	}
	
	//show checked in murals for user
	@RequestMapping("displaycheckins")
	public ModelAndView checkedinMuralsPerUser(@RequestParam("user") Integer id) {
		List<CheckIn> checkins = cr.findByUserid(id);
		ArrayList<Mural> checkins2 = new ArrayList<Mural>(); 
		for (int i = 0; i < checkins.size(); i++) {
			//Optional is an advanced hibernate Query class which represents the possibility of a mural 
			//being found by the query
			Optional <Mural> m = mr.findById(checkins.get(i).getMuralid());
			// this takes a function as an argument and executes only if the mural. 
			m.ifPresent(mural -> checkins2.add(m.get()));
		}
		
		return new ModelAndView("displaycheckin", "check", checkins2);
	}
	
	
	@RequestMapping("logout") 
	public ModelAndView logout(HttpSession session) {
		session.setAttribute("loggedin", false);
		session.removeAttribute("user");
		session.setAttribute("counter", null);
		
		int num = mr.findAll().size();
		Random rand = new Random();
		int rand1 = rand.nextInt(num);
		
		ModelAndView mv=new ModelAndView("Index");
		Mural mural = mr.findById(rand1).orElse(null);

		mv.addObject("mural", mural);
		return mv;
		 
	}
	//method to add favorites for a user
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
	
	@RequestMapping("addtofavorites")
	public ModelAndView displayUserFavorites(@RequestParam(required=false, name="favorites[]") String favorites, @RequestParam("favoritez") Integer userid, HttpSession session) {
		if (favorites != null) {
			String[] favorites2 = favorites.split(",");
			addFavorites(favorites2, userid);
		}
		ModelAndView mv = new ModelAndView("redirect:userpage");
		addUserSession(session, mv); 
		return mv; 
		
	}
	
	
	
	@RequestMapping("recommendations")
	public ModelAndView recommendations(HttpSession session) {
		
		ArrayList<Mural> recs = findRecommendations(session);
		
		// finally, the list of murals is sent to the jsp page for display. 
		ModelAndView mv = new ModelAndView("recommendations", "recommendations", recs);
		mv.addObject("user", session.getAttribute("user"));
		return mv; 
		
	}
	//add a mural to favorites
	@RequestMapping("addrecs")
	public ModelAndView addRecs(HttpSession session, @RequestParam("muralid[]") String muralid, @RequestParam("user") Integer userid) {
		String[] murals = muralid.split(",");
		Integer[] muralids = new Integer[murals.length];
		for (int i = 0; i < murals.length; i++) {
			muralids[i] = Integer.parseInt(murals[i]);
		}
		for (int i = 0; i < muralids.length; i++) {
			fr.save(new Favorite(muralids[i], userid));
		}
		return new ModelAndView("redirect:/userpage", "user", session.getAttribute("user"));
	}
	//remove a mural from favorites
		@RequestMapping("deletefav")
		public ModelAndView deletefav(HttpSession session, @RequestParam("muralid") Integer muralid, @RequestParam("user") Integer userid) {
			for (Favorite f : fr.findByUserid(userid)) {
				if (muralid == f.getMuralid()) {
					fr.deleteById(f.getId());
				}
			}
			
			return new ModelAndView("redirect:/userpage", "user", session.getAttribute("user"));
		}
	
}
