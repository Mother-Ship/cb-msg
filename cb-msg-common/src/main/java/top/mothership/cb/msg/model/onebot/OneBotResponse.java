package top.mothership.cb.msg.model.onebot;


import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

/**
 * OneBot规范 动作的返回体
 * @param <T>
 */
@Data
public class OneBotResponse<T>{

    private String status;
    @JsonAlias("retcode")
    private int retCode;
    private T data;

}