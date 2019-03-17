package com.ming.mp.handler;

import com.ming.constants.Constant;
import com.ming.mp.builder.TextBuilder;
import com.ming.mp.utils.JsonUtils;
import com.ming.utils.RedisUtil;
import com.ming.utils.RegexUtil;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

import static me.chanjar.weixin.common.api.WxConsts.XmlMsgType;

/**
 * @author Binary Wang(https://github.com/binarywang)
 */
@Component
public class MsgHandler extends AbstractHandler {

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 发送验证码消息
     */
    @Value("${ming.weixin.registration.code.message}")
    private String registrationCodeMessage;

    /**
     * 默认回复消息
     */
    @Value("${ming.weixin.default.registration.code.message}")
    private String defaultRegistrationCodeMessage;

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                    Map<String, Object> context, WxMpService weixinService,
                                    WxSessionManager sessionManager) {

        if (!wxMessage.getMsgType().equals(XmlMsgType.EVENT)) {
            // 可以选择将消息保存到本地
        }

        //当用户输入关键词如“你好”，“客服”等，并且有客服在线时，把消息转发给在线客服
        try {
            if (StringUtils.startsWithAny(wxMessage.getContent(), "你好", "客服")
                && weixinService.getKefuService().kfOnlineList()
                .getKfOnlineList().size() > 0) {
                return WxMpXmlOutMessage.TRANSFER_CUSTOMER_SERVICE()
                    .fromUser(wxMessage.getToUser())
                    .toUser(wxMessage.getFromUser()).build();
            }
        } catch (WxErrorException e) {
            e.printStackTrace();
        }

        // 获取微信消息信息
        String messageContent = wxMessage.getContent();
        // 检验手机号的正确性
        if (RegexUtil.checkMobile(messageContent)) {
            // 生成验证码
            int registCode = registCode();
            String returnWxMsgContent = String.format(registrationCodeMessage, registCode);

            // 将验证码暂存到redis中
            redisUtil.setString(Constant.WEIXINCODE_KEY + messageContent, registCode + "", Constant.WEIXINCODE_TIMEOUT);

            //返回验证码
            return new TextBuilder().build(returnWxMsgContent, wxMessage, weixinService);
        }
        return new TextBuilder().build(defaultRegistrationCodeMessage, wxMessage, weixinService);
    }


    /**
     *
     * 生成注册码
     */
    private int registCode() {
        return (int) ((Math.random() * 9000) + 1000);
    }

}
