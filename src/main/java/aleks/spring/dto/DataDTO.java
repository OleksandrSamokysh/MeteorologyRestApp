package aleks.spring.dto;

import aleks.spring.models.Sensor;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.*;

public class DataDTO {
    @Column(name = "temp_value")
    @NotNull(message = "temp value should not be empty")
    @Min(value = -100, message = "temp value should be between -100 and 100")
    @Max(value = 100, message = "temp value should be between -100 and 100")
    private Double value;

//    @Column(name = "raining_status")
    @NotNull(message = "raining status should not be empty")
    private Boolean raining;

//    @ManyToOne
//    @JoinColumn(name = "sensor_id", referencedColumnName = "id")
    private SensorDTO sensor;

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Boolean isRaining() {
        return raining;
    }

    public void setRaining(Boolean raining) {
        this.raining = raining;
    }

    public SensorDTO getSensor() {
        return sensor;
    }

    public void setSensor(SensorDTO sensor) {
        this.sensor = sensor;
    }
}
