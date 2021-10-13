package cn.bngel.springcloud.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Account {

    private Long id;
    private Long userId;
    private BigDecimal total;
    private BigDecimal used;
    private BigDecimal residue;
}
