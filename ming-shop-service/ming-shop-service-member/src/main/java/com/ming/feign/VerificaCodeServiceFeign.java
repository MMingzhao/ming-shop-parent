package com.ming.feign;

import com.ming.service.VerificaCodeServiceApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author Mingzhao
 * @date 2019/3/17 16:27
 */

@FeignClient(name = "ming-shop-service-wechat")
public interface VerificaCodeServiceFeign extends VerificaCodeServiceApi{

}
