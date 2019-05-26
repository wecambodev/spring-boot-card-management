package com.mbanq.banksystem.service;

import com.mbanq.banksystem.model.User;
import com.mbanq.banksystem.repository.ConsumerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsumerService {

    @Autowired
    private ConsumerRepository consumerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> findAllConsumers(){
        return consumerRepository.findAll();
    }

    public boolean addNewConsumer(User consumer){
        consumer.setPassword(passwordEncoder.encode(consumer.getPassword()));
        return consumerRepository.save(consumer);
    }

    public boolean existsByPhoneNumber(String phoneNumber) {
        return consumerRepository.existsByPhoneNumber(phoneNumber);
    }

    public boolean save(User user) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
           return  consumerRepository.save(user);
           // return jwtTokenProvider.createToken(user.getUsername(), user.getRoles());

    }
}
