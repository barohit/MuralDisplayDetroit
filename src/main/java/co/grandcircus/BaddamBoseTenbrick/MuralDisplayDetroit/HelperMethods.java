package co.grandcircus.BaddamBoseTenbrick.MuralDisplayDetroit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import co.grandcircus.BaddamBoseTenbrick.MuralDisplayDetroit.entity.Favorite;
import co.grandcircus.BaddamBoseTenbrick.MuralDisplayDetroit.entity.FavoriteRepository;
import co.grandcircus.BaddamBoseTenbrick.MuralDisplayDetroit.entity.Mural;
import co.grandcircus.BaddamBoseTenbrick.MuralDisplayDetroit.entity.MuralRepository;
import co.grandcircus.BaddamBoseTenbrick.MuralDisplayDetroit.entity.User;
import co.grandcircus.BaddamBoseTenbrick.MuralDisplayDetroit.entity.UserRepository;



@Controller
public class HelperMethods {
	
	@Autowired 
	FavoriteRepository fr; 
	
	@Autowired
	MuralRepository mr; 
	
	@Autowired 
	UserRepository ur; 
	
	

}
