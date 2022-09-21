package com.goncharov.tinyurl.dto;

import lombok.Data;

/**
 * @author Goncharov Aleksandr
 */
@Data
public class ResponseDTO {
    private String alias;
    private String expirationDate;
}
