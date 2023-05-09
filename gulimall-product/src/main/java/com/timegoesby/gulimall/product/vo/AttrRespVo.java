package com.timegoesby.gulimall.product.vo;

import lombok.Data;
import lombok.ToString;

@ToString(callSuper = true)
@Data
public class AttrRespVo extends AttrVo{

    /**
     * 所属分类名字
     */
    private String catelogName;

    /**
     * 所属分组名字
     */
    private String groupName;

    /**
     * catelogPath
     */
    private Long[] catelogPath;
}
