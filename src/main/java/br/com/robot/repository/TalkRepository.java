package br.com.robot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.robot.entity.Talk;
import br.com.robot.entity.User;

@Repository
public interface TalkRepository extends JpaRepository<Talk, Long> {

	@Query("SELECT t FROM Talk t WHERE t.sender = ?1 ")
	public List<Talk> findAllBySender(User user);
}