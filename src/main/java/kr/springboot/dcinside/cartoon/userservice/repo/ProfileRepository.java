package kr.springboot.dcinside.cartoon.userservice.repo;

import kr.springboot.dcinside.cartoon.userservice.domain.Profile;
import kr.springboot.dcinside.cartoon.userservice.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, User> {
}
