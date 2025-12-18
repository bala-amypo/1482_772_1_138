package com.example.demo.service;

import java.util.List;
import com.example.demo.model.DigitalKey;

public interface DigitalKeyService {
    DigitalKey createKey(DigitalKey digitalKey);
    List<DigitalKey> getAllKeys();
}
