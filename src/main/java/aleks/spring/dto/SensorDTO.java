package aleks.spring.dto;

import javax.persistence.Column;
import javax.validation.constraints.Size;

public class SensorDTO {

    @Column(name = "name")
    @Size(min = 3, max = 30, message = "Name should be between 3 and 30 characters")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
