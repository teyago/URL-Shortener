package com.goncharov.tinyurl.dto;

import lombok.Data;

@Data
public class InfoDTO {
    private String url;
    private String alias;
    private int numberOfClicks;
    private String creationDate;
    private String expirationDate;
}
