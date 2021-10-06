package top.mothership.cb.msg.model;

import lombok.Data;
import top.mothership.cb.msg.enums.cq.PostType;
import top.mothership.cb.msg.utils.CbEnumUtil;

import java.util.Objects;

@Data
public class BaseCqMessage {
    private String postType;
    private String messageType;
    private String noticeType;
    private String requestType;
    private String subType;

    public boolean postTypeMatch(PostType postType){
        PostType thisType = CbEnumUtil.getCqType(PostType.class, this.postType);
        return Objects.equals(postType,thisType);
    }
    public boolean secondaryTypeMatch(PostType postType){
        PostType thisType = CbEnumUtil.getCqType(PostType.class, this.postType);
        return Objects.equals(postType,thisType);
    }
}
