package top.mothership.cb.msg.utils.onebot;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import top.mothership.cb.cmd.enums.MediaEntityType;
import top.mothership.cb.cmd.model.response.CbCmdResponse;
import top.mothership.cb.cmd.model.response.MediaEntity;
import top.mothership.cb.msg.utils.CbEnumUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class OneBotMessageUtil {

    public static String toCqCode(CbCmdResponse response){
        if (response == null || response.isEmpty()){
            return null;
        }

        StringBuilder text = new StringBuilder(response.getText());

        //处理纯图片逻辑
        if (!StringUtils.hasText(text)){
            text = new StringBuilder();
            for (MediaEntity media : response.getMedia()) {
                String cqCode = getCqCodeByMedia(media);
                text.append(cqCode);
            }
            return text.toString();
        }


        List<Integer> indexList = response.getMedia().stream().map(MediaEntity::getIndex).collect(Collectors.toList());
        //拆分字符串
        List<String> textShard= new ArrayList<>();
        int lastIndex = 0;
        for (Integer index : indexList) {
            textShard.add(text.substring(lastIndex,index));
            lastIndex = index;
        }

        //生成CQ码并拼接入字符串
        var i = textShard.iterator();
        text = new StringBuilder(i.next());
        for (MediaEntity media : response.getMedia()) {
            String cqCode = getCqCodeByMedia(media);
            text.append(cqCode);
            text.append(i.next());
        }
        return text.toString();
    }


    @SneakyThrows
    public static String getCqCodeByMedia(MediaEntity entity) {
        File file = File.createTempFile(String.valueOf(System.currentTimeMillis()),".data");
        FileOutputStream out = new FileOutputStream(file);
        out.write(entity.getData());
        out.close();

        val type = CbEnumUtil.getEnumOrException(MediaEntityType.class, entity.getType());
        return switch (type) {
            case IMAGE -> "[CQ:image,file=file:///" + file.getAbsolutePath() + "]";
            case VOICE -> "[CQ:record,file=file:///" + file.getAbsolutePath() + "]";
        };
    }

    public static String getAtCqCode(Long qq){
        return "[CQ:at,qq="+qq+"]";
    }
}
