package com.raazankeet.employeesapi.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StatusResponse {
    private String status;
    private String message;
}
