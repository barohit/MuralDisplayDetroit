package co.grandcircus.BaddamBoseTenbrick.MuralDisplayDetroit.entity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	User findByUsername(String username); 


}
