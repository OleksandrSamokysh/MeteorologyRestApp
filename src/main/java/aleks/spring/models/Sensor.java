package aleks.spring.models;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "Sensor")
public class Sensor {

    @Id
    @Column(name = "name")
    @Size(min = 3, max = 30, message = "Name should be between 3 and 30 characters")
    private String name;

    @Column(name = "registration_at")
    private LocalDateTime registrationAt;

    public Sensor() {

    }
    public Sensor(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getRegistrationAt() {
        return registrationAt;
    }

    public void setRegistrationAt(LocalDateTime registrationAt) {
        this.registrationAt = registrationAt;
    }
}
