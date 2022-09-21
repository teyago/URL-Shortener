package com.goncharov.tinyurl.dto;

import lombok.Data;

import javax.validation.constraints.Pattern;

/**
 * @author Goncharov Aleksandr
 */
@Data
public class RequestDTO {

    @Pattern(regexp =
            "https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_\\+.~#?&//=]*)",
            message = "Doesn't look like an url. Try again")
    private String url;

    @Pattern(regexp = "[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1]) (2[0-3]|[01][0-9]):[0-5][0-9]",
            message = "Expiration date must be in the format: \"yyyy-MM-dd HH:mm\"")
    private String expirationDate;
}
