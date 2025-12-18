package com.example.demo.service;

import java.util.List;
import com.example.demo.model.KeyShareRequest;

public interface KeyShareRequestService {
    KeyShareRequest createRequest(KeyShareRequest request);
    List<KeyShareRequest> getAllRequests();
}
