package aleks.spring.models;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Data")

public class Data {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "temp_value")
    @NotNull(message = "temp value should not be empty")
    @Min(value = -100, message = "temp value should be between -100 and 100")
    @Max(value = 100, message = "temp value should be between -100 and 100")
    private Double value;

    @Column(name = "raining_status")
    @NotNull(message = "raining status should not be empty")
    private Boolean raining;

    @ManyToOne
    @JoinColumn(name = "sensor_name", referencedColumnName = "name")
    private Sensor sensor;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public Data() {}

    public Data(int id, Double value, Boolean raining) {
        this.id = id;
        this.value = value;
        this.raining = raining;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }



}
