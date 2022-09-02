package com.goncharov.tinyurl.dto;

import lombok.Data;

@Data
public class ResponseDTO {
    private String alias;
    private String expirationDate;
}
