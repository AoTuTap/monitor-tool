package com.itbl.monitor.service.vtb;

import java.util.List;

import org.springframework.stereotype.Service;

import com.itbl.monitor.model.vtb.MonitorLog;
import com.itbl.monitor.repository.vtb.MonitorLogRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class MonitorLogService {
    private final MonitorLogRepository monitorLogRepository;

    public MonitorLogService(MonitorLogRepository monitorLogRepository) {
        this.monitorLogRepository = monitorLogRepository;
    }

    public List<MonitorLog> getAllLogs() {
        return monitorLogRepository.findAllByOrderByAlarmTimeDesc();
    }

    public Long countLongs() {
        return monitorLogRepository.count();
    }

    public void deleteLog(Long id) {
        monitorLogRepository.deleteById(id);
    }

    public void deleteLogsByIds(List<Long> ids) {
        monitorLogRepository.deleteByIdIn(ids);
    }

}
