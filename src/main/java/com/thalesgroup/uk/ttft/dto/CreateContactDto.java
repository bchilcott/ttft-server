package com.thalesgroup.uk.ttft.dto;

import com.thalesgroup.uk.ttft.enums.Environment;
import com.thalesgroup.uk.ttft.enums.SpeedUnit;
import com.thalesgroup.uk.ttft.enums.Operation;
import lombok.Data;

@Data
public class CreateContactDto {
    private String name;
    private String trackId;
    private String type;
    private Environment environment;
    private double course;
    private double speed;
    private SpeedUnit speedUnit;
    private Operation operation;
    private ContactAttitudeDto attitude;
    private ContactPositionDto position;
}