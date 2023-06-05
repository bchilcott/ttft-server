package com.thalesgroup.uk.ttft.dto;

import com.thalesgroup.uk.ttft.enums.Environment;
import com.thalesgroup.uk.ttft.enums.Operation;
import com.thalesgroup.uk.ttft.enums.SpeedUnit;
import lombok.Data;

@Data
public class ContactDto {
    private String name;
    private String dataSource;
    private String trackId;
    private String type;
    private String systemId;
    private Environment environment;
    private double course;
    private double speed;
    private SpeedUnit speedUnit;
    private boolean stale;
    private Operation operation;
    private ContactAttitudeDto attitude;
    private ContactPositionDto position;
}
