package com.synsyt.api.quizz.service;

import com.synsyt.api.quizz.exceptions.UserNotFoundException;
import com.synsyt.api.quizz.model.User;
import com.synsyt.api.quizz.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repository;

    @Override
    public User createUser(User user) {
        return repository.save(user);
    }

    @Override
    public Optional<User> getUserByEmail(String email) throws UserNotFoundException {
        return repository.findByEmail(email);
    }

    @Override
    public List<User> getAllUsers() {
        return repository.findAll();
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return repository.findByUsername(username);
    }

    @Override
    public void deleteUser(User user) {
        repository.delete(user);
    }
}
