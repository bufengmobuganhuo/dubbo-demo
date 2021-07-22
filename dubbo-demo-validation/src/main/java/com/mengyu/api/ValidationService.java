package com.mengyu.api;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author yuzhang
 * @date 2021/4/22 上午10:26
 * TODO
 */
public interface ValidationService {
    void save(ValidationParameter parameter);

    void update(ValidationParameter parameter);

    void delete(@Min(1) long id,
                @NotNull
                @Size(min = 2, max = 16)
                @Pattern(regexp = "^[a-zA-Z]+$")String operator);

    // 与方法同名接口，首字母大写，用于区分验证场景，
    // 如：@NotNull(groups = ValidationService.Save.class)，表示只对save()方法进行NotNull验证
    @interface Save{}

    @interface Update{}

}
