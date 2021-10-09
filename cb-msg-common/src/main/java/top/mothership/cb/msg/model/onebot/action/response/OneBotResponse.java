package top.mothership.cb.msg.model.onebot.action.response;


import com.fasterxml.jackson.annotation.JsonAlias;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * OneBot规范 动作的返回体
 * @param <T>
 */
@Data
public class OneBotResponse<T>{
    @ApiModelProperty("Action发送状态")
    private String status;

    @ApiModelProperty("状态码")
    @JsonAlias("retcode")
    private int code;

    @ApiModelProperty("返回数据")
    private T data;

    public boolean success(){
        return 0 == this.code;
    }

}