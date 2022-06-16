package com.sweetpoint.demo.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DataResponse implements Serializable {

    private static final long serialVersionUID = -8129725340647017694L;

    private String email;
    private String name;
    private Integer point;
}
