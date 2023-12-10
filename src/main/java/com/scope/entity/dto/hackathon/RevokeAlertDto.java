package com.scope.entity.dto.hackathon;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @author garen
 * Date: 2023/12/8 13:29
 * Description: RevokeAlertDto
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RevokeAlertDto implements Serializable {

    private Long blockNumber;

    private String contractAddress;

    private String symbol;

    private String amount = "0";

    private String reason;

    private String transactionHash;

    private String walletId;
}