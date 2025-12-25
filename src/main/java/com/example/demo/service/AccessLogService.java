package com.example.demo.service;

import com.example.demo.model.AccessLog;
import java.util.List;

public interface AccessLogService {
    List<AccessLog> getLogsForGuest(Long guestId);
}
