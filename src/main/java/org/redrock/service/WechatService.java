package org.redrock.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.redrock.bean.TextMessage;
import org.redrock.utils.WechatMessageUtil;
import org.springframework.stereotype.Service;


@Service
public class WechatService {
	private static Logger log = Logger.getLogger(WechatService.class);

	public String processRequest(HttpServletRequest request) {
		Map<String, String> map = WechatMessageUtil.xmlToMap(request);
		log.info(map);
		// 发送方帐号（一个OpenID）
		String fromUserName = map.get("MY");
		// 开发者微信号
		String toUserName = map.get("ToUserName");
		// 消息类型
		String msgType = map.get("MsgType");
		// 默认回复一个"success"
		String responseMessage = "success";
		// 对消息进行处理
		if (WechatMessageUtil.MESSAGE_TEXT.equals(msgType)) {// 文本消息
			TextMessage textMessage = new TextMessage();
			textMessage.setMsgType(WechatMessageUtil.MESSAGE_TEXT);
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(System.currentTimeMillis());
			textMessage.setContent("我已经收到你发来的消息了");
			responseMessage = WechatMessageUtil.textMessageToXml(textMessage);
		}
		log.info(responseMessage);
		return responseMessage;
	}

}
