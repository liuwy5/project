package com.entity;

import lombok.*;
import org.jeecgframework.poi.excel.annotation.Excel;

import java.io.Serializable;

/**
 * 要有无参构造方法
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PersonEntity implements Serializable {
    @Excel(name = "序号")
    private String id;

    @Excel(name = "姓名")
    private String name;

    @Excel(name = "员工编号")
    private String code;

    @Excel(name = "部门")
    private String department;

    @Excel(name = "联系电话")
    private String phone;

    @Excel(name = "性别", replace = "男_1, 女_2")
    private String sex;
}
