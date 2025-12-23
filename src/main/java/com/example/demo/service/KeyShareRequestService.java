package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.KeyShareRequest;

public interface KeyShareRequestService {

    KeyShareRequest createRequest(KeyShareRequest request);

    List<KeyShareRequest> getAllRequests();

    KeyShareRequest approveRequest(Long id);

    KeyShareRequest rejectRequest(Long id);
}
