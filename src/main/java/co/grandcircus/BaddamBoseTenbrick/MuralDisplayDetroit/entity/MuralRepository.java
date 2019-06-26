package co.grandcircus.BaddamBoseTenbrick.MuralDisplayDetroit.entity;



import java.util.List;


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
	
		
	List<Mural> findAllByNeighborhood(String neighborhood);
	List<Mural> findAllByArtistname(String artistname);
	
	@Query(value="SELECT DISTINCT neighborhood FROM murals",nativeQuery=true)
	List<String> findDistinctNeighborhood();
	
	@Query(value="SELECT DISTINCT artistname FROM murals ORDER BY artistname asc",nativeQuery=true)
	List<String> findDistinctArtist();

}
