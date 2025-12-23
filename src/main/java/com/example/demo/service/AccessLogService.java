package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.AccessLog;

public interface AccessLogService {

    AccessLog addLog(AccessLog log);

    List<AccessLog> getAllLogs();

    List<AccessLog> getLogsByGuest(Long guestId);

    List<AccessLog> getLogsByKey(Long keyId);
}
