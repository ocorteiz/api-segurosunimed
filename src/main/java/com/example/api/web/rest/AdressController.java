package com.example.api.web.rest;

import com.example.api.domain.Adress;
import com.example.api.service.AdressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/adress")
public class AdressController {

    private AdressService service;

    @Autowired
    public AdressController(AdressService adressService) {
        this.service = adressService;
    }

    @GetMapping
    public List<Adress> findAll() {
        return service.findAll();
    }

    @DeleteMapping("/{adressId}/{customerId}")
    public void delete(@PathVariable Long adressId, @PathVariable Long customerId) {
        service.delete(adressId, customerId);
    }
}
