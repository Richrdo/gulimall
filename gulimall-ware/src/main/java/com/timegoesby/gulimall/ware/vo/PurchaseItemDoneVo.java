package com.timegoesby.gulimall.ware.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PurchaseItemDoneVo {
    @NotNull
    private Long itemId;
    private Integer status;
    private String reason;
}
