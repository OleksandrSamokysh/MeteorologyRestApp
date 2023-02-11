package aleks.spring.util;

import aleks.spring.models.Data;
import aleks.spring.services.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class DataValidator implements Validator {

    private final SensorService sensorService;

    @Autowired
    public DataValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Data.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Data data = (Data) o;

        if(data.getSensor() == null)
            return;

        if(sensorService.findByName(data.getSensor().getName()).isEmpty()) {
            errors.rejectValue("sensor", "", "Указанный сенсор не зарегистрирован");
        }
    }
}