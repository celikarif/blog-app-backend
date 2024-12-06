package com.netas.blog.enumaration.core;

import lombok.Getter;

@Getter
public enum ResponseEnum {
    OK(200, "OK", Boolean.TRUE, Boolean.FALSE),
    BADREQUEST(400, "BADREQUEST", Boolean.FALSE, Boolean.TRUE),
    UNAUTHORIZED(401, "UNAUTHORIZED", Boolean.FALSE, Boolean.TRUE),
    FORBIDDEN(403, "FORBIDDEN", Boolean.FALSE, Boolean.TRUE),
    NOTFOUND(404, "NOTFOUND", Boolean.FALSE, Boolean.TRUE),
    INTERNAL_SERVER_ERROR(500, "INTERNAL_SERVER_ERROR", Boolean.FALSE, Boolean.TRUE),
    ERROR(1000, "ERROR", Boolean.FALSE, Boolean.TRUE),
    NOTIFICATION(1001, "NOTIFICATION", Boolean.FALSE, Boolean.FALSE),
    INFO(1002, "INFO", Boolean.TRUE, Boolean.FALSE),
    WARNING(100, "WARNING", Boolean.TRUE, Boolean.FALSE);

    private final Integer httpStatusCode;
    private final String description;
    private final Boolean isSuccess;
    private final Boolean defaultNotification;

    ResponseEnum(Integer httpStatusCode, String description, Boolean isSuccess, Boolean defaultNotification) {
        this.httpStatusCode = httpStatusCode;
        this.description = description;
        this.isSuccess = isSuccess;
        this.defaultNotification = defaultNotification;
    }
}