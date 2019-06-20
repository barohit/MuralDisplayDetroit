package co.grandcircus.BaddamBoseTenbrick.MuralDisplayDetroit.entity;



import java.util.List;
import java.util.TreeSet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface MuralRepository extends JpaRepository<Mural, Integer> {

	@Transactional
	@Modifying
	@Query(nativeQuery=true, value="UPDATE murals SET murals.favoritecount = :favorite WHERE muralid = :muralid")
	int updateFavoriteCount(@Param("favorite") Integer favoriteCount, @Param("muralid") Integer muralid);
	
	//@Query("SELECT * FROM murals ORDER BY neighborhood ")
	List<Mural> findAllByOrderByNeighborhoodAsc();
	
	List<Mural> findAllByNeighborhood(String neighborhood);
	
	@Query(value="SELECT DISTINCT neighborhood FROM murals",nativeQuery=true)
	List<String> findDistinctNeighborhood();


}
