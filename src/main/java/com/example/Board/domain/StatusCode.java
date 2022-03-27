package com.example.Board.domain;

import lombok.Getter;

@Getter
public enum StatusCode {

    //    server
    INTERNAL_SERVER_ERROR(500, "C_001", "Server Fail"),
    METHOD_NOT_ALLOWED(405, "C_002", "Method type Not collect check get,post,put or else"),
    INVALID_INPUT_VALUE(400, "C_003", "Invalid value"),
    INVALID_TYPE_VALUE(400, "C_004", "Invalid Type Error"),
    ENTITY_NOT_FOUND(400, "C_005", "Can not Found Entity"),

    //    auth
    SUCCESS(200, "AU_000", "Request Success"),
    AUTH_ERROR(400, "AU_001", "Auth Fail"),
    DUPLICATED_USER(400, "AU_002", "Duplicated User"),
    UNAUTHORIZED_REDIRECT_URI(400, "AU_004", "Unauthorized redirect URI please login"),
    BAD_LOGIN(400, "AU_005", "Bad Login ID or Password"),
    UNAUTHORIZED_MEMBER(403, "AU_006", "User Not Found"),

    //    register
    BAD_REGISTER(400, "RG_001", "Sign Up Fail, please check data"),


    ;

    private final String code;
    private final String message;
    private final int status;

    StatusCode(int status, String code, String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }
}
