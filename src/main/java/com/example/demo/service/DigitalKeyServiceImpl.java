package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.DigitalKey;
import com.example.demo.repository.DigitalKeyRepository;

@Service
public class DigitalKeyServiceImpl implements DigitalKeyService {

    private final DigitalKeyRepository digitalKeyRepository;

    public DigitalKeyServiceImpl(DigitalKeyRepository digitalKeyRepository) {
        this.digitalKeyRepository = digitalKeyRepository;
    }

    @Override
    public DigitalKey createKey(DigitalKey digitalKey) {

        if (digitalKey.getKeyValue() == null || digitalKey.getKeyValue().isEmpty()) {
            throw new IllegalArgumentException("key value required");
        }

        return digitalKeyRepository.save(digitalKey);
    }

    @Override
    public List<DigitalKey> getAllKeys() {
        return digitalKeyRepository.findAll();
    }
}
