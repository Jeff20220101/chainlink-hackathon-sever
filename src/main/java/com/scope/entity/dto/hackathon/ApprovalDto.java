package com.scope.entity.dto.hackathon;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 *
 * @author garen
 * Date: 2023/12/8 14:26
 * Description: ApprovalDto
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApprovalDto implements Serializable {

    private Long blockNumber;

    private String transactionHash;

    private String contractAddress;

    private String symbol;

    private BigDecimal amount;
}