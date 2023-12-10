package com.scope.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.scope.entity.dto.hackathon.ApprovalDto;
import com.scope.entity.dto.hackathon.RevokeAlertDto;
import com.scope.entity.dto.hackathon.SmartScWalletDto;
import com.scope.entity.po.hackathon.Hackathon;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author garen
 * Date: 2023/12/7 15:18
 * Description: HackathonService
 */
public interface HackathonService extends IService<Hackathon> {

    List<SmartScWalletDto> getSmartScWalletList(String address);

    List<ApprovalDto> getApprovalsList(String address);

    List<RevokeAlertDto> getRevokeAlertList(String address);

    List<String> getWalletIdList(String address);
}