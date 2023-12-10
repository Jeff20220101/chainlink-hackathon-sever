package com.scope.mapper.hackathon;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.scope.entity.dto.hackathon.ApprovalDto;
import com.scope.entity.dto.hackathon.RevokeAlertDto;
import com.scope.entity.dto.hackathon.SmartScWalletDto;
import com.scope.entity.po.hackathon.Hackathon;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author garen
 * Date: 2023/12/7 15:21
 * Description: HackathonMapper
 */
public interface HackathonMapper extends BaseMapper<Hackathon> {

    List<SmartScWalletDto> getSmartScWalletList(@Param("address") String address);

    List<ApprovalDto> getApprovalsList(@Param("address") String address);

    List<RevokeAlertDto> getRevokeAlertList(@Param("address") String address);

    List<String> getWalletIdList(@Param("address") String address);
}