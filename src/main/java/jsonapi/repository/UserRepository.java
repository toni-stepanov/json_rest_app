package jsonapi.repository;

import jsonapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer>{

    User findById(Integer id);

//    @Modifying
//    @Query("select u from User u where id = ?1 and isOnline = ?2")
//    User findByIdAndStatus(Integer id, boolean status);

    List<User> findByIsOnline(boolean isOnline);

    List<User> findByIsOnlineAndLastChangeGreaterThan(boolean isOnline, Date date);
}
