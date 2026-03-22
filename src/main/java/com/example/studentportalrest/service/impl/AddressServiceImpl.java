package com.example.studentportalrest.service.impl;

import com.example.studentportalrest.service.AddressService;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {

    public String getAddress() {
        return "123 Main St, Anytown, USA";
    }

}
