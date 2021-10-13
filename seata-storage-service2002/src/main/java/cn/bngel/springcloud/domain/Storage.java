package cn.bngel.springcloud.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Storage {
    private Long id;
    private Long productId;
    private Integer total;
    private Integer used;
    private Integer residue;
}
