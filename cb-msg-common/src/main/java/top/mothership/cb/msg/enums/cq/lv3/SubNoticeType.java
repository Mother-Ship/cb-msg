package top.mothership.cb.msg.enums.cq.lv3;

import lombok.AllArgsConstructor;
import top.mothership.cb.msg.enums.cq.PostType;
import top.mothership.cb.msg.enums.cq.lv2.Lv2Type;
import top.mothership.cb.msg.enums.cq.lv2.NoticeType;

@AllArgsConstructor
public enum SubNoticeType implements Lv3Type {

    ADD(NoticeType.ESSENCE),
    DELETE(NoticeType.ESSENCE),

    SET(NoticeType.GROUP_ADMIN),
    UNSET(NoticeType.GROUP_ADMIN),

    LEAVE(NoticeType.GROUP_DECREASE),
    KICK(NoticeType.GROUP_DECREASE),
    KICK_ME(NoticeType.GROUP_DECREASE),

    APPROVE(NoticeType.GROUP_INCREASE),
    INVITE(NoticeType.GROUP_INCREASE),

    BAN(NoticeType.GROUP_BAN),
    LIFT_BAN(NoticeType.GROUP_BAN),

    POKE(NoticeType.NOTIFY),
    LUCKY_KING(NoticeType.NOTIFY),
    HONOR(NoticeType.NOTIFY),


    ;

    private NoticeType parent;

    @Override
    public Lv2Type getParentType() {
        return parent;
    }

}
