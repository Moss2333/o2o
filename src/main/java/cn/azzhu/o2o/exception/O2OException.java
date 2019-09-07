package cn.azzhu.o2o.exception;

import cn.azzhu.o2o.enums.ResultEnum;

/**
 * @author azzhu
 * @create 2019-09-04 09:23:55
 */
public class O2OException extends RuntimeException {
    private Integer code;

    public O2OException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public O2OException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}