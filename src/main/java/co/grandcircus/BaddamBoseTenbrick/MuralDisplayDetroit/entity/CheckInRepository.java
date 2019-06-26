package co.grandcircus.BaddamBoseTenbrick.MuralDisplayDetroit.entity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckInRepository extends JpaRepository<CheckIn, Integer> {

	List<CheckIn> findByUserid(Integer userid);
}
