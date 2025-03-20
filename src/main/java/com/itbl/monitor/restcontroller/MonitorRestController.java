package com.itbl.monitor.restcontroller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PostMapping("/deleteSelected")
    public ResponseEntity<String> deleteSelectedLogs(@RequestBody List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return ResponseEntity.badRequest().body("No IDs provided or invalid format.");
        }

        monitorLogService.deleteLogsByIds(ids);
        return ResponseEntity.ok("Selected logs deleted successfully.");
    }

}
