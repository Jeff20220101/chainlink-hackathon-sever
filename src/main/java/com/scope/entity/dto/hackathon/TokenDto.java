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
 * Date: 2023/12/8 17:32
 * Description: TokenDto
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenDto implements Serializable {

    private String symbol;

    private String contractAddress;
}