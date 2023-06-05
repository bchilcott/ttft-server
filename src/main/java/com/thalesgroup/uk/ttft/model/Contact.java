package com.thalesgroup.uk.ttft.model;

import com.thalesgroup.uk.ttft.enums.Environment;
import com.thalesgroup.uk.ttft.enums.Operation;
import com.thalesgroup.uk.ttft.enums.SpeedUnit;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Contact {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(length = 36)
    private String trackId;

    private String name;
    private String dataSource;
    private String type;
    private String systemId;
    private Environment environment;
    private double course;
    private double speed;
    private SpeedUnit speedUnit;
    private boolean stale;
    private Operation operation;

    @OneToOne(cascade = CascadeType.ALL)
    private ContactAttitude attitude;

    @OneToOne(cascade = CascadeType.ALL)
    private ContactPosition position;

}

