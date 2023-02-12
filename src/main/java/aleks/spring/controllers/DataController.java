package aleks.spring.controllers;

import aleks.spring.dto.DataDTO;
import aleks.spring.dto.DataResponse;
import aleks.spring.models.Data;
import aleks.spring.services.DataService;
import aleks.spring.util.DataErrorResponse;
import aleks.spring.util.DataException;
import aleks.spring.util.DataValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

import static aleks.spring.util.ErrorsUtil.returnErrors;

@RestController
@RequestMapping("/measurements")
public class DataController {
    private final DataService dataService;
    private final DataValidator dataValidator;
    private final ModelMapper modelMapper;


    @Autowired
    public DataController(DataService dataService, DataValidator dataValidator, ModelMapper modelMapper) {
        this.dataService = dataService;
        this.dataValidator = dataValidator;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    public DataResponse getData() {
        return new DataResponse(dataService.findAll().stream().map(this::convertToDataDTO)
                .collect(Collectors.toList()));
    }

    @GetMapping("/rainyDaysCount")
    public Long getRainyDaysCount() {
        return dataService.findAll().stream().filter(Data::isRaining).count();
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid DataDTO dataDTO,
                                             BindingResult bindingResult) {

        Data data = convertToData(dataDTO);
        dataValidator.validate(data, bindingResult);

        if (bindingResult.hasErrors()) {
            returnErrors(bindingResult);
        }

        dataService.save(data);
        return ResponseEntity.ok(HttpStatus.OK); // 200 status
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

    private Data convertToData(DataDTO dataDTO) {
        return modelMapper.map(dataDTO, Data.class);
    }

    private DataDTO convertToDataDTO (Data data) {
        return modelMapper.map(data, DataDTO.class);
    }

}
