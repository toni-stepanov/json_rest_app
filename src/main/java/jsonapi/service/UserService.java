package jsonapi.service;

import jsonapi.entity.User;
import jsonapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User save(User user){
        return userRepository.save(user);
    }

    public User findById(Integer id) {
        return userRepository.findById(id);
    }

    public List<User> findByStatus(boolean isOnline) {
        return userRepository.findByIsOnline(isOnline);
    }

    public List<User> findUserByStatusAndDate(boolean isOnline, Date date) {
        return userRepository.findByIsOnlineAndLastChangeGreaterThan(isOnline, date);
    }
}
