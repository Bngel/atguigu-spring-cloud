package cn.bngel.springcloud.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CommonResult <T> {
    private Integer code;
    private T data;
    private String message;

    public CommonResult(Integer code, String message) {
        this(code, null, message);
    }

}