package co.grandcircus.BaddamBoseTenbrick.MuralDisplayDetroit.entity;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	User findByUsername(String username); 
	
	@Transactional
	@Modifying
	@Query(nativeQuery=true, value="UPDATE users SET users.muralids = :muralid WHERE userid = :userid")
	Integer updateFavorites(@Param("muralid") String muralids, @Param("userid") Integer userid);

}
