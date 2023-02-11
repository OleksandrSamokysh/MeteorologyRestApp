package aleks.spring.services;

import aleks.spring.models.Data;
import aleks.spring.repositories.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class DataService {

    private final DataRepository dataRepository;
    private final SensorService sensorService;

    @Autowired
    public DataService(DataRepository dataRepository, SensorService sensorService) {
        this.dataRepository = dataRepository;
        this.sensorService = sensorService;
    }

    public List<Data> findAll() {
        return dataRepository.findAll();
    }


    @Transactional
    public void save (Data data) {
        enrichPerson(data);
        dataRepository.save(data);
    }

    @Transient
    private void enrichPerson(Data data) {
        data.setSensor(sensorService.findByName(data.getSensor().getName()).get());
        data.setCreatedAt(LocalDateTime.now());
    }

    public List<Data> findByRaining (Boolean raining) {
        return dataRepository.findByRaining(raining);
    }
}
