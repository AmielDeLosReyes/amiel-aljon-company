package amiel_aljon.auth_server.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import amiel_aljon.auth_server.model.entity.AppUser;

import java.util.Optional;
@Repository
public interface AppUserRepository extends JpaRepository<AppUser,Long> {
    // todo: user related database methods should be here
    Optional<AppUser> findByEmail(String email);

    @Transactional
    @Modifying
    @Query("UPDATE AppUser a " +
            "SET a.enabled = TRUE WHERE a.email = ?1")
    int enableAppUser(String email);
}
