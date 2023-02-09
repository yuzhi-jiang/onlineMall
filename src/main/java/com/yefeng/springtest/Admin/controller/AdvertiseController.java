package com.yefeng.springtest.Admin.controller;

import com.yefeng.springtest.Admin.entity.Advertise;
import com.yefeng.springtest.Admin.service.AdvertiseService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

import static com.yefeng.springtest.util.Constant.*;

@RestController
@RequestMapping("/admin/advertise")
public class AdvertiseController {
    @Resource
    private AdvertiseService advertiseService;

    @RequestMapping("/add")
    public Map<String ,Object> addAdvertise(Advertise advertise){
        HashMap<String, Object> map = new HashMap<>();

        if(advertise!=null){
            Integer Code = advertiseService.addAdvertise(advertise);
            if(Code==CODE_OK){
                map.put(ERROR_CODE,CODE_OK);
                map.put(ERROR_MSSAGE,"添加广告成功");
            }else {
                map.put(ERROR_CODE,CODE_OK);
                map.put(ERROR_MSSAGE,"添加广告失败");
            }
        }
        return map;
    }


}
