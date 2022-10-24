package repository.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import entity.User;
@Repository
public interface IUserRepository extends JpaRepository<User,Integer>{

	Optional<User> findUserByEmail(String email);

	Optional<User> findUserByEmailAndPassword(String email, String password);
}
