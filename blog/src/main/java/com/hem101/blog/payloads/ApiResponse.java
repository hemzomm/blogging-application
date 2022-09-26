package com.hem101.blog.payloads;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse {

    private String message;
    private boolean status;

    public ApiResponse(String message, boolean b) {
        this.message=message;
        this.status=b;
    }
}
