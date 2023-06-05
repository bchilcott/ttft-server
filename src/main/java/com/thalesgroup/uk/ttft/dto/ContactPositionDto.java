package com.thalesgroup.uk.ttft.dto;

import com.thalesgroup.uk.ttft.enums.AltitudeUnit;
import lombok.Data;

@Data
public class ContactPositionDto {
    private double latitude;
    private double longitude;
    private double altitude;
    private AltitudeUnit altitudeUnit;
}
