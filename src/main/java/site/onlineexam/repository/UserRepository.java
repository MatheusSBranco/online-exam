package site.onlineexam.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import site.onlineexam.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
