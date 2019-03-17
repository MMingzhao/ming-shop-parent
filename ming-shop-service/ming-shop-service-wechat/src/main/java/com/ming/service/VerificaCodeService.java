package com.ming.service;

import com.alibaba.fastjson.JSONObject;
import com.ming.api.BaseApiService;
import com.ming.api.BaseResponse;
import com.ming.constants.Constant;
import com.ming.utils.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Mingzhao
 * @date 2019/3/17 16:13
 */
@RestController
public class VerificaCodeService extends BaseApiService<JSONObject> implements VerificaCodeServiceApi {

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 功能说明:根据手机号码验证码token是否正确
     *
     * @param phone
     * @param weixinCode
     * @return
     */
    @Override
    public BaseResponse<JSONObject> verificaWeixinCode(String phone, String weixinCode) {

        if (StringUtils.isEmpty(phone)) {
            return setResultError("手机号码不能为空!");
        }

        if (StringUtils.isEmpty(weixinCode)) {
            return setResultError("注册码不能为空!");
        }

        String code = redisUtil.getString(Constant.WEIXINCODE_KEY + phone);
        if (StringUtils.isEmpty(code)) {
            return setResultError("注册码已经过期,请重新发送验证码");
        }

        if (!code.equals(weixinCode)) {
            return setResultError("注册码不正确");
        }

        return setResultSuccess("注册码验证码正确");
    }
}
