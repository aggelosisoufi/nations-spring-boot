package com.isoufi.angelos.nationservice.filter;

import lombok.Data;

import java.util.List;

@Data
public class BaseFilter<T> {
    private T equals;
    private T from;
    private T to;
    private List<T> inValues;
}
