package com.ming.service;

import com.ming.feign.VerificaCodeServiceFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Mingzhao
 * @date 2019/3/17 16:29
 */
@RestController
public class PortalService {

    // TODO 删除
    @Autowired
    private VerificaCodeServiceFeign verificaCodeServiceFeign;


}
