package com.dg.common.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author ty
 * @date 2022/9/9
 * @apiNote
 */

public class BaseEntity implements Serializable {
    @TableId
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty("ID")
    @JsonSerialize(using = ToStringSerializer.class)

    private Long id;
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty("创建人")
    private String createBy;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty("创建时间")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp createDate;

    @TableField(fill = FieldFill.UPDATE)
    @ApiModelProperty("更新人")
    private String updateBy;

    @TableField(fill = FieldFill.UPDATE)

    @ApiModelProperty("更新时间")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp updateDate;

    public BaseEntity cleanEntity() {
        this.setCreateBy((String)null);
        this.setCreateDate((Timestamp)null);
        this.setUpdateBy((String)null);
        this.setUpdateDate((Timestamp)null);
        return this;
    }

    public BaseEntity() {
    }

    public Long getId() {
        return this.id;
    }

    public String getCreateBy() {
        return this.createBy;
    }

    public Timestamp getCreateDate() {
        return this.createDate;
    }

    public String getUpdateBy() {
        return this.updateBy;
    }

    public Timestamp getUpdateDate() {
        return this.updateDate;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setCreateBy(final String createBy) {
        this.createBy = createBy;
    }

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    public void setCreateDate(final Timestamp createDate) {
        this.createDate = createDate;
    }

    public void setUpdateBy(final String updateBy) {
        this.updateBy = updateBy;
    }

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    public void setUpdateDate(final Timestamp updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        return "BaseEntity(id=" + this.getId() + ", createBy=" + this.getCreateBy() + ", createDate=" + this.getCreateDate() + ", updateBy=" + this.getUpdateBy() + ", updateDate=" + this.getUpdateDate() + ")";
    }
}
