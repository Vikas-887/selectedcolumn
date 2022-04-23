package com.vikashbhaiya.selectedcolumn.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.TreeMap;

@Getter
@Setter
@NoArgsConstructor
public class ApiResponse {
    boolean error;
    String message;
    TreeMap<Integer, Object> data;
}
