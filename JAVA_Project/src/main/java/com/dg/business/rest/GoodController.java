package com.dg.business.rest;

import com.dg.business.model.Good;
import com.dg.common.model.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author TheFool
 */
@RestController
@RequestMapping(value = "/v1/private/good")
public class GoodController {

    // 新增
    @PostMapping("/save")
    public Result save(@RequestBody Good good) {
        return  Result.success();
    }
    // 修改
    @PostMapping("/edit")
    public Result edit(@RequestBody Good good) {
        return  Result.success();
    }
    // 批量删除
    @PostMapping("/delete")
    public Result delete(@RequestBody Good good) {
        return  Result.success();
    }
    // 自定义查询
    @PostMapping("/query")
    public Result query(@RequestBody Good good) {
        return  Result.success();
    }
    // 分页查询
    @PostMapping("/page")
    public Result page(@RequestBody Good good) {
        return  Result.success();
    }
    // 模板导出
    @PostMapping("/export/template")
    public Result template(@RequestBody Good good) {
        return  Result.success();
    }
    // 数据导出
    @PostMapping("/export/data")
    public Result exportData(@RequestBody Good good) {
        return  Result.success();
    }
    // 数据导入 批量新增
    @PostMapping("/import/data")
    public Result importData(@RequestBody Good good) {
        return  Result.success();
    }
    // 统计
    @PostMapping("/statistics")
    public Result statistics(@RequestBody Good good) {
        return  Result.success();
    }
    // 未关联查询 A与B关联 A有多少元素还未与B关联
    @PostMapping("/out")
    public Result out(@RequestBody Good good) {
        return  Result.success();
    }
}
