package top.mothership.cb.msg.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import top.mothership.cb.msg.enums.cq.PostType;
import top.mothership.cb.msg.utils.CbEnumUtil;

import java.util.Objects;

@Data
public class BaseCqMessage {
    @ApiModelProperty("主类型")
    private String postType;

    @ApiModelProperty("二级类型")
    private String messageType;

    @ApiModelProperty("二级类型")
    private String noticeType;

    @ApiModelProperty("二级类型")
    private String requestType;

    @ApiModelProperty("三级类型")
    private String subType;

}
