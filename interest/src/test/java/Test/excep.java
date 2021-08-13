package Test;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName excep
 * @Author zhaofu
 * @Date 2021/8/13
 * @Version V1.0
 **/
@Slf4j
public class excep {
    public static void main(String[] args) {
        try {
                throw new RuntimeException("321");
        } catch (Exception e) {
            log.error("{}异常:", e);
        }
    }
}
