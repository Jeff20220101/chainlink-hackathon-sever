package com.scope.controller;

import com.scope.common.base.BaseResponse;
import com.scope.entity.dto.hackathon.SmartScWalletDto;
import com.scope.service.HackathonService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author garen
 * Date: 2023/12/7 11:20
 * Description: CustomerController
 */
@RestController
@RequestMapping("/api/users")
public class CustomerController {

    // nohup java -jar -Xms1024m -Xmx1024m scope-hackathon.jar >/dev/null 2>&1 &
    @Resource
    private HackathonService hackathonService;

    @RequestMapping("/getSmartScWallets")
    public BaseResponse getSmartScWallets(String address) {
        List<SmartScWalletDto> smartScWalletList = hackathonService.getSmartScWalletList(address);
        return new BaseResponse(smartScWalletList);
    }
}