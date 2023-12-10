package com.scope.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scope.common.BaseCommonConstants;
import com.scope.entity.dto.hackathon.ApprovalDto;
import com.scope.entity.dto.hackathon.RevokeAlertDto;
import com.scope.entity.dto.hackathon.SmartScWalletDto;
import com.scope.entity.po.hackathon.Hackathon;
import com.scope.mapper.hackathon.HackathonMapper;
import com.scope.service.HackathonService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author garen
 * Date: 2023/12/7 15:20
 * Description: HackathonServiceImpl
 */
@Service
@DS(BaseCommonConstants.BSC_SLAVE)
public class HackathonServiceImpl extends ServiceImpl<HackathonMapper, Hackathon> implements HackathonService {
    @Override
    public List<SmartScWalletDto> getSmartScWalletList(String address) {
        return baseMapper.getSmartScWalletList(address);
    }

    @Override
    public List<ApprovalDto> getApprovalsList(String address) {
        return baseMapper.getApprovalsList(address);
    }

    @Override
    public List<RevokeAlertDto> getRevokeAlertList(String address) {
        return baseMapper.getRevokeAlertList(address);
    }

    @Override
    public List<String> getWalletIdList(String address) {
        return baseMapper.getWalletIdList(address);
    }

}