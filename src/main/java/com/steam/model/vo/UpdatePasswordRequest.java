package com.steam.model.vo;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

/**
 * @author : JOSE 2019/3/24 6:54 PM
 */
@ApiModel("修改密码REQ")
@Getter
@Setter
public class UpdatePasswordRequest extends BaseRequest {

    @Length(min = 3, max = 10, message = "密码必须在3-10位之间")
    private String password;
}
