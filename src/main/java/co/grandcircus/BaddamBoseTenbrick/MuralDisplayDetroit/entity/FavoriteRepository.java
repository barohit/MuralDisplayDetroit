package co.grandcircus.BaddamBoseTenbrick.MuralDisplayDetroit.entity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {
	
	List<Favorite> findByUserid(Integer userid); 

}
