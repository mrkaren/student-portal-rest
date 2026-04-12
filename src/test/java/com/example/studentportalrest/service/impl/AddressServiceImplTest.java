package com.example.studentportalrest.service.impl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddressServiceImplTest {

    private final AddressServiceImpl addressService = new AddressServiceImpl();

    @Test
    void getAddress_returnsStaticAddress() {
        assertEquals("123 Main St, Anytown, USA", addressService.getAddress());
    }
}