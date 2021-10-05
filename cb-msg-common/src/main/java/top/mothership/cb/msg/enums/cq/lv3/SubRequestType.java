package top.mothership.cb.msg.enums.cq.lv3;

import io.swagger.annotations.ApiModelProperty;
import org.bouncycastle.cert.ocsp.Req;
import top.mothership.cb.msg.enums.cq.lv2.Lv2Type;
import top.mothership.cb.msg.enums.cq.lv2.RequestType;

public enum SubRequestType implements Lv3Type {
    @ApiModelProperty("加群请求")
    ADD(RequestType.GROUP),

    @ApiModelProperty("邀请本体入群")
    INVITE(RequestType.GROUP),
    ;

    private RequestType parent;

    SubRequestType(RequestType parent) {
        this.parent = parent;
    }

    @Override
    public Lv2Type getParentType() {
        return parent;
    }
}
