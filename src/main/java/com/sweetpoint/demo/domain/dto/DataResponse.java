package com.sweetpoint.demo.domain.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class DataResponse implements Serializable {

    private static final long serialVersionUID = -8129725340647017694L;

    private String email;
    private String name;
    private Integer point;
}
