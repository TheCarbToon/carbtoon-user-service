package kr.springboot.dcinside.cartoon.userservice.repo;

import kr.springboot.dcinside.cartoon.userservice.domain.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @EntityGraph(attributePaths = {"userProfile"}, type = EntityGraph.EntityGraphType.LOAD)
    Optional<User> findByUsername(String username);
    @EntityGraph(attributePaths = {"userProfile"}, type = EntityGraph.EntityGraphType.LOAD)
    List<User> findByUsernameIn(List<String> usernames);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);

}
