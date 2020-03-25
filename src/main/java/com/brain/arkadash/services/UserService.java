package com.brain.arkadash.services;


import com.brain.arkadash.domain.User;
import com.brain.arkadash.exeptions.UsernameAlreadyExistsException;
import com.brain.arkadash.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User saveUser (User newUser){

        try {

            newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));

            newUser.setUsername(newUser.getUsername());
            newUser.setConfirmPassword("");

            //make sure that password and confirm paassword match

            // we do not persist or shw confirm paassword


            return userRepository.save(newUser);

        } catch (Exception e) {
            throw new UsernameAlreadyExistsException("Username '" + newUser.getUsername()+ "' already exists");

        }



    }

}
