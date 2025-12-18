package com.example.demo.service;

import java.util.List;
import com.example.demo.model.AccessLog;

public interface AccessLogService {
    AccessLog createLog(AccessLog log);
    List<AccessLog> getAllLogs();
}
