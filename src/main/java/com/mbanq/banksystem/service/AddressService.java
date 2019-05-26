package com.mbanq.banksystem.service;

import com.mbanq.banksystem.model.Address;
import com.mbanq.banksystem.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public Address findById(Long id) {
        return addressRepository.findById(id);
    }

    public boolean save( Address address ) {
        return  addressRepository.save(address);
    }

}
