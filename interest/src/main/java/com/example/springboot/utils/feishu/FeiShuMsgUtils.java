package com.example.springboot.utils.feishu;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Order(4)
public class FeiShuMsgUtils {

//	@Autowired
//	private Tracer tracer;

	@Autowired
	private FeiShuRobotUtils feiShuRobotUtils;

//	@Value("${spring.application.name:default}")
	private String serviceName = "acsjk";

//	@Value("${spring.profiles.active:dev}")
	private String active = "dev";

	public void sendDingMsg(String msg) {
//		if (!active.equals(CoreConstants.ENV_DEV) && !active.equals(CoreConstants.ENV_LOCAL)) {
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("environment: ");
			stringBuilder.append(active);
			stringBuilder.append("\n");
			stringBuilder.append("TraceId: ");
//			if(Objects.nonNull(tracer) && Objects.nonNull(tracer.currentSpan())){
//				stringBuilder.append(tracer.currentSpan().context().traceIdString());
//			}
			stringBuilder.append("\n");
			stringBuilder.append("ServiceName: ");
			stringBuilder.append(serviceName);
			stringBuilder.append("\n");
			stringBuilder.append("Content: ");
			stringBuilder.append(msg);
			feiShuRobotUtils.sendMsg(stringBuilder.toString());
//		}
	}
}
