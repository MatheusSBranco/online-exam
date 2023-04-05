package site.onlineexam.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import site.onlineexam.exception.UserException;
import site.onlineexam.model.User;
import site.onlineexam.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(User user) {
        userRepository.save(user);       
    }

    public Optional<User> findUserByEmail(String email) {
        try{
            return userRepository.findByEmail(email);
        } catch (HttpClientErrorException | HttpServerErrorException ex) {
            HttpStatusCode statusCode = ex.getStatusCode();
            String statusText = ex.getStatusText();
            String message = ex.getMessage();
            throw new RuntimeException("Error finding user by email: " + statusCode + " " + statusText + " " + message);
        }        
    }

    public List<User> getAllUsers(){
        try {
            return userRepository.findAll();
        } catch (HttpClientErrorException | HttpServerErrorException ex) {
            HttpStatusCode statusCode = ex.getStatusCode();
            String statusText = ex.getStatusText();
            String message = ex.getMessage();
            throw new RuntimeException("Error getting all users: " + statusCode + " " + statusText + " " + message);
        }
    }

    public User getUserById(Long id) {
        try {
            return userRepository.findById(id)
                .orElseThrow(() -> new UserException("Invalid user ID", "User ID: " + id));
        } catch (HttpClientErrorException | HttpServerErrorException ex) {
            HttpStatusCode statusCode = ex.getStatusCode();
            String statusText = ex.getStatusText();
            String message = ex.getMessage();
            throw new RuntimeException("Error getting user by Id: " + statusCode + " " + statusText + " " + message);
        }
    }    

    public void updateUser(User user) {
        userRepository.save(user);
    }

    public void deleteUser(Long id){
        try {
            Optional<User> user = userRepository.findById(id);
            if(user.isPresent()) {
                userRepository.deleteById(id);
            } else {
                throw new UserException("Invalid user ID", "User ID: " + id);
            }
            
        } catch (HttpClientErrorException | HttpServerErrorException ex) {
            HttpStatusCode statusCode = ex.getStatusCode();
            String statusText = ex.getStatusText();
            String message = ex.getMessage();
            throw new RuntimeException("Error deleting user: " + statusCode + " " + statusText + " " + message);
        }
    }    
}
