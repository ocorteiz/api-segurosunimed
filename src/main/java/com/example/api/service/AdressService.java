package com.example.api.service;

import com.example.api.convert.ConsumeAPI;
import com.example.api.convert.ConvertData;
import com.example.api.domain.Adress;
import com.example.api.domain.AdressDTO;
import com.example.api.domain.Customer;
import com.example.api.repository.AdressRepository;
import com.example.api.repository.CustomerRepository;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AdressService {

    private final AdressRepository adressRepository;
    private final CustomerRepository customerRepository;
    private final ConsumeAPI consumeAPI = new ConsumeAPI();
    private final ConvertData convertData = new ConvertData();

    @Autowired
    public AdressService(AdressRepository adressRepository, CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
        this.adressRepository = adressRepository;
    }

    public List<Adress> findAll() {
        return adressRepository.findAll();
    }

    @Transactional
    public Adress save(AdressDTO adressDTO) {

        String json = null;

        try {
            json = consumeAPI.obterDados("https://viacep.com.br/ws/" + adressDTO.cep() + "/json/");
            JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();

            if (jsonObject.has("erro") && jsonObject.get("erro").getAsBoolean()) {
                System.out.println("CEP não encontrado");
            } else if (jsonObject.has("cep")) {
                System.out.println("Endereço encontrado: " + jsonObject);
            } else {
                System.out.println("Resposta inesperada da API");
            }
        } catch (JsonSyntaxException e) {
            System.out.println("Erro na análise do JSON: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }

        Adress adress = convertData.obterDados(json, Adress.class);

        return adressRepository.save(adress);
    }

    @Transactional
    public void delete(Long adressId, Long costumerId) {
        if (adressId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "adressId can't be null");
        } else if (costumerId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "costumerId can't be null");
        }

        Adress adress = adressRepository.findById(adressId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Adress not found"));

        Customer customer = customerRepository.findById(costumerId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Costumer not found"));

        customer.getAdresses().remove(adress);
        customerRepository.save(customer);

        adressRepository.deleteById(adressId);
    }

}
