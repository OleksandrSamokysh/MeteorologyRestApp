package aleks.spring.services;

import aleks.spring.models.Sensor;
import aleks.spring.repositories.SensorRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class SensorService {
    private final SensorRepository sensorRepository;

    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    public void registration (Sensor sensor) {
        sensor.setRegistrationAt(LocalDateTime.now());
        sensorRepository.save(sensor);
    }

    public Optional<Sensor> findByName(String name) {
        return sensorRepository.findByName(name);
    }


}
