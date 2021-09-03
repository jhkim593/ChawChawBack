package com.project.chawchaw.response;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CommonResult implements Serializable {

    private boolean success;


    private int code;


    private String msg;
}