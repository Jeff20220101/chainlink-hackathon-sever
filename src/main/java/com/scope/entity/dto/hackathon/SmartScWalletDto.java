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
 * Date: 2023/12/7 15:23
 * Description: SmartScWalletDto
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SmartScWalletDto implements Serializable {

    private String wallet;

    private Long blockNumber;
}