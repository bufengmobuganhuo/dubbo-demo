package com.mengyu.impl;

import com.mengyu.api.ValidationParameter;
import com.mengyu.api.ValidationService;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author yuzhang
 * @date 2021/4/22 上午11:13
 * TODO
 */
public class ValidationServiceImpl implements ValidationService {
    @Override
    public void save(ValidationParameter parameter) {

    }

    @Override
    public void update(ValidationParameter parameter) {

    }

    @Override
    public void delete(@Min(1) long id, @NotNull @Size(min = 2, max = 16) @Pattern(regexp = "^[a-zA-Z]+$") String operator) {

    }
}
