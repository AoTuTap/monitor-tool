package com.itbl.monitor.repository.vtb;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itbl.monitor.model.vtb.MonitorLog;

@Repository
public interface MonitorLogRepository extends JpaRepository<MonitorLog, Long> {
    List<MonitorLog> findAllByOrderByAlarmTimeDesc();

    void deleteByIdIn(List<Long> ids);

}
