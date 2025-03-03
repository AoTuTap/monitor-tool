package com.itbl.monitor.model.vtb;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "monitor_logs")
public class MonitorLog {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "monitor_logs_seq")
    @SequenceGenerator(name = "monitor_logs_seq", sequenceName = "monitor_logs_seq", allocationSize = 1)
    private Long id;
    private String alarmName;
    private String sqlString;
    private String alarmType;
    private String alarmLevel;
    private String alarmValue;
    private Date alarmTime;

    public MonitorLog() {
    }

    public MonitorLog(Long id, String alarmName, String sqlString, String alarmType, String alarmLevel,
            String alarmValue,
            Date alarmTime) {
        this.id = id;
        this.alarmName = alarmName;
        this.sqlString = sqlString;
        this.alarmType = alarmType;
        this.alarmLevel = alarmLevel;
        this.alarmValue = alarmValue;
        this.alarmTime = alarmTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAlarmName() {
        return alarmName;
    }

    public void setAlarmName(String alarmName) {
        this.alarmName = alarmName;
    }

    public String getSqlString() {
        return sqlString;
    }

    public void setSqlString(String sqlString) {
        this.sqlString = sqlString;
    }

    public String getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(String alarmType) {
        this.alarmType = alarmType;
    }

    public String getAlarmLevel() {
        return alarmLevel;
    }

    public void setAlarmLevel(String alarmLevel) {
        this.alarmLevel = alarmLevel;
    }

    public String getAlarmValue() {
        return alarmValue;
    }

    public void setAlarmValue(String alarmValue) {
        this.alarmValue = alarmValue;
    }

    public Date getAlarmTime() {
        return alarmTime;
    }

    public void setAlarmTime(Date alarmTime) {
        this.alarmTime = alarmTime;
    }

}
