package com.mbanq.banksystem.repository;

import com.mbanq.banksystem.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsumerRepository {

    boolean save(User consumer);

    User findById(Long id);

    List<User> findAll();

    User findByPhoneNumber(String phoneNumber);

    boolean existsByPhoneNumber(String phoneNumber);
}
