package com.itbl.monitor.controller.vtb;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.itbl.monitor.model.vtb.MonitorLog;
import com.itbl.monitor.service.vtb.MonitorLogService;

@Controller
public class MonitorLogController {

    private final MonitorLogService monitorLogService;

    public MonitorLogController(MonitorLogService monitorLogService) {
        this.monitorLogService = monitorLogService;
    }

    @GetMapping("/")
    public String getMethodName() {
        return "admin/dashboard/index";
    }

    @GetMapping("/monitor")
    public String getMonitorDashboard(Model model) {
        List<MonitorLog> monitorlogs = monitorLogService.getAllLogs();
        Long count = monitorLogService.countLongs();
        model.addAttribute("count", count);
        model.addAttribute("monitorlogs", monitorlogs);
        return "admin/monitor/index";
    }

    @GetMapping("/monitor/delete/{id}")
    public String postUpdateUser(Model model, @PathVariable("id") Long id) {
        monitorLogService.deleteLog(id);
        return "redirect:/monitor";
    }

}
