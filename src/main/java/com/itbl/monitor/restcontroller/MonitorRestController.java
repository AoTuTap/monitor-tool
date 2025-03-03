package com.itbl.monitor.restcontroller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itbl.monitor.model.vtb.MonitorLog;
import com.itbl.monitor.service.vtb.MonitorLogService;

@RestController
@RequestMapping("/monitor")
public class MonitorRestController {

    private final MonitorLogService monitorLogService;

    public MonitorRestController(MonitorLogService monitorLogService) {
        this.monitorLogService = monitorLogService;
    }

    @GetMapping("/getLogs")
    public List<MonitorLog> getMonitorLogs() {
        // Fetch the monitor logs from your database
        return monitorLogService.getAllLogs(); // Replace with actual logic
    }
}
