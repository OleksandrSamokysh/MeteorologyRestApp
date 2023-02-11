package aleks.spring.controllers;

import aleks.spring.dto.SensorDTO;
import aleks.spring.models.Sensor;
import aleks.spring.services.SensorService;
import aleks.spring.util.DataErrorResponse;
import aleks.spring.util.DataException;
import aleks.spring.util.SensorValidator;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static aleks.spring.util.ErrorsUtil.returnErrors;

@RestController
@RequestMapping("/sensors")
public class SensorController {

    private final SensorService sensorService;
    private final SensorValidator sensorValidator;
    private final ModelMapper modelMapper;

    public SensorController(SensorService sensorService, SensorValidator sensorValidator, ModelMapper modelMapper) {
        this.sensorService = sensorService;
        this.sensorValidator = sensorValidator;
        this.modelMapper = modelMapper;
    }


    @PostMapping("/registration")
        public ResponseEntity<HttpStatus> registration(@RequestBody @Valid SensorDTO sensorDTO,
                BindingResult bindingResult) {

        Sensor sensor = convertToSensor(sensorDTO);
        sensorValidator.validate(sensor, bindingResult);

            if (bindingResult.hasErrors()) {
                returnErrors(bindingResult);
            }

            sensorService.registration(sensor);
            return ResponseEntity.ok(HttpStatus.OK); // 200 status
        }

    private Sensor convertToSensor(SensorDTO sensorDTO) {
        return modelMapper.map(sensorDTO, Sensor.class);
    }

    @ExceptionHandler
    private ResponseEntity<DataErrorResponse> handleException(DataException e) {
            DataErrorResponse response = new DataErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );

        // In HTTP овтете тело ответа (response) и статус в загооловке
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
