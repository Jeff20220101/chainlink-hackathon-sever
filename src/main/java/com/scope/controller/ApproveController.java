package com.scope.controller;

import com.alibaba.fastjson.JSONObject;
import com.scope.common.base.BaseResponse;
import com.scope.common.exception.BaseException;
import com.scope.entity.dto.hackathon.ApprovalDto;
import com.scope.entity.dto.hackathon.RevokeAlertDto;
import com.scope.entity.dto.hackathon.TokenDto;
import com.scope.service.HackathonService;
import com.scope.utils.RevokeContractUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 *
 * @author garen
 * Date: 2023/12/8 13:27
 * Description: ApproveController
 */
@RestController
@RequestMapping("/api/approve")
public class ApproveController {

    @Resource
    private HackathonService hackathonService;

    private final List<TokenDto> tokenList = new ArrayList<TokenDto>() {{
        add(TokenDto.builder().contractAddress("0x337610d27c682e347c9cd60bd4b3b107c9d34ddd").symbol("USDT").build());
        add(TokenDto.builder().contractAddress("0x64544969ed7ebf5f083679233325356ebe738930").symbol("USDC").build());
        add(TokenDto.builder().contractAddress("0x6ce8da28e2f864420840cf74474eff5fd80e65b8").symbol("BTC").build());
        add(TokenDto.builder().contractAddress("0xec5dcb5dbf4b114c9d0f65bccab49ec54f6a0867").symbol("DAI").build());
        add(TokenDto.builder().contractAddress("0xa83575490d7df4e2f47b7d38ef351a2722ca45b9").symbol("XRP").build());
    }};

    @RequestMapping(value = "approvals", method = RequestMethod.GET)
    public BaseResponse getApprovalList(@RequestParam("address") String address) {
        List<ApprovalDto> approvalsList = hackathonService.getApprovalsList(address);

        for (ApprovalDto approvalDto : approvalsList) {
            List<TokenDto> collect = tokenList.stream().filter(f -> f.getContractAddress().equalsIgnoreCase(approvalDto.getContractAddress())).collect(Collectors.toList());
            if (collect.size() > 0) {
                approvalDto.setSymbol(collect.get(0).getSymbol());
            }
        }

        return new BaseResponse(approvalsList);
    }

    @RequestMapping(value = "revokeAlerts", method = RequestMethod.GET)
    public BaseResponse revokeList(@RequestParam("address") String address) {
        List<RevokeAlertDto> revokeAlertList = hackathonService.getRevokeAlertList(address);
        for (RevokeAlertDto revokeAlertDto : revokeAlertList) {
            List<TokenDto> collect = tokenList.stream().filter(f -> f.getContractAddress().equalsIgnoreCase(revokeAlertDto.getContractAddress())).collect(Collectors.toList());
            if (collect.size() > 0) {
                revokeAlertDto.setSymbol(collect.get(0).getSymbol());
            }
        }
        return new BaseResponse(revokeAlertList);
    }

    @RequestMapping(value = "eventRevoke", method = RequestMethod.GET)
    public BaseResponse eventRevoke(@RequestParam("address") String address) {
        List<String> revokeAlertList = hackathonService.getWalletIdList(address);
        if (revokeAlertList.size() == 0) {
//            return new BaseResponse();
            throw new BaseException("No Revoke to execute");
        }
        String walletId = revokeAlertList.get(0);

        String transactionHash;
        try {
            transactionHash = RevokeContractUtils.revokeContract(walletId);
        } catch (Exception e) {
            throw new BaseException("emitRevokeLog failed");
        }
        return new BaseResponse(transactionHash);
    }
}