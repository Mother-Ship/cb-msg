package top.mothership.cb;

import org.springframework.cloud.openfeign.FeignClient;
import top.mothership.cb.cmd.CbCmdListeningApi;

@FeignClient(name = "cb-cmd")
public interface CbCmdService extends CbCmdListeningApi {
}
