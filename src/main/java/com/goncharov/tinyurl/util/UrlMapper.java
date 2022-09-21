package com.goncharov.tinyurl.util;

import com.goncharov.tinyurl.dto.InfoDTO;
import com.goncharov.tinyurl.dto.RequestDTO;
import com.goncharov.tinyurl.dto.ResponseDTO;
import com.goncharov.tinyurl.entity.Url;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author Goncharov Aleksandr
 */
@Mapper
public interface UrlMapper {
    String dateFormat = "yyyy-MM-dd HH:mm";

    UrlMapper INSTANCE = Mappers.getMapper(UrlMapper.class);

    @Mapping(source = "expirationDate", target = "expirationDate", dateFormat = dateFormat)
    Url urlToAlias(RequestDTO requestDTO);

    @Mapping(target = "expirationDate", dateFormat = dateFormat)
    ResponseDTO response(Url url);

    @Mapping(target = "expirationDate", dateFormat = dateFormat)
    @Mapping(target = "creationDate", dateFormat = dateFormat)
    InfoDTO info(Url url);
}
