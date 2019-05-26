package com.mbanq.banksystem.repository;

import com.mbanq.banksystem.model.Address;
import org.springframework.stereotype.Repository;
@Repository
public interface AddressRepository {

    Address findById(Long id);

    boolean save(Address address);
}

