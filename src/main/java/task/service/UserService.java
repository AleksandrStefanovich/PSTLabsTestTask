package task.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import task.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import task.repository.UserRepository;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository repository;

    public List<User> readAll(){return repository.findAll();}

    public User findUserByID(String id) {
        return repository.findById(id).orElse(null);
    }

    public User saveNewUser(User user) {
        return repository.save(user);
    }

    public User updateUser(String id, User user) {
        User savedUser = repository.findById(id).orElseThrow();
        if(savedUser.equals(user)){
            return savedUser;
        }
        if (!savedUser.getName().equals(user.getName())
                || savedUser.getAge() != (user.getAge())){
            savedUser.setName(user.getName());
            savedUser.setAge(user.getAge());
            repository.save(savedUser);
        }
        return  repository.findById(id).orElseThrow();
    }

    public boolean delete(String id) {
        if (repository.existsById(id)){
            repository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }

    public Page<User> findAllPage(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
