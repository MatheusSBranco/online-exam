package site.onlineexam.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import site.onlineexam.model.User;
import site.onlineexam.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(User user) {
        try{
            userRepository.save(user);
        } catch (Exception e){
            throw new RuntimeException("Error creating user: "+ e.getMessage());
        }        
    }

    public Optional<User> findUserByEmail(String email) {
        try{
            return userRepository.findByEmail(email);
        } catch(Exception e){
            throw new RuntimeException("Error finding user by email: "+ e.getMessage());
        }        
    }

    public List<User> getAllUsers(){
        try {
            return userRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error getting all users: " + e.getMessage());
        }
    }

    public User getUserById(Long id) {
        try {
            return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id: " + id));
        } catch (Exception e) {
            throw new RuntimeException("Error getting user by id: " + e.getMessage());
        }
    }    

    public void updateUser(User user) {
        try {
            userRepository.save(user);
        } catch (Exception e) {
            throw new RuntimeException("Error updating user: " + e.getMessage());
        }
    }

    public void deleteUser(Long id){
        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error deleting user: " + e.getMessage());
        }
    }    
}
