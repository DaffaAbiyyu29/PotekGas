package com.potekgas.repository;

import com.potekgas.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
//    Optional<User> findOneByUsernameAndPassword(String username, String password);

    User findByUsername(String username);
    @Procedure(procedureName = "getUserActive")
    List<User> getUserActive();
}
