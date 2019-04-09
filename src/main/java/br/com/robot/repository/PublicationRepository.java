package br.com.robot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.robot.entity.Publication;
import br.com.robot.entity.User;

@Repository
public interface PublicationRepository extends JpaRepository<Publication, Long> {

	@Query("SELECT p FROM Publication p WHERE p.sender = ?1 ")
	public List<Publication> findAllBySender(User user);
	
	@Query("SELECT p FROM Publication p WHERE (p.title LIKE %?1% OR p.describe LIKE %?1%)")
	public List<Publication> findAllByWordKey(String wordKey);
}