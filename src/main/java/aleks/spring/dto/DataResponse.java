package aleks.spring.dto;

import java.util.List;

public class DataResponse {
    private List<DataDTO> response;

    public DataResponse(List<DataDTO> response) {
        this.response = response;
    }

    public List<DataDTO> getResponse() {
        return response;
    }

    public void setResponse(List<DataDTO> response) {
        this.response = response;
    }
}
