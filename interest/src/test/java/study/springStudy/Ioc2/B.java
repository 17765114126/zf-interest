package study.springStudy.Ioc2;

import study.springStudy.Ioc.Part;

import java.util.UUID;

/**
 * @ClassName B
 * @Author zhaofu
 * @Date 2020/10/16
 * @Version V1.0
 **/
@Part
class B{
    private UUID uuid;
    public B(){
        uuid = UUID.randomUUID();
    }

    public UUID getUuid() {
        return uuid;
    }
}
