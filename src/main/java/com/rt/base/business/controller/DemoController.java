package com.rt.base.business.controller;

import com.rt.base.business.dto.demo.DemoDto;
import com.rt.base.business.dto.demo.DemoMessageDto;
import com.rt.base.business.form.demo.DemoForm;
import com.rt.base.business.form.demo.DemoMessageForm;
import com.rt.base.business.form.demo.DemoRecordForm;
import com.rt.base.business.service.DemoService;
import com.rt.base.business.common.em.ResultCode;
import com.rt.base.business.common.ResultInfo;
import com.rt.base.business.common.exception.BusinessException;
import com.rt.base.business.common.utils.MessageUtils;
import com.rt.base.framework.entity.MqErrorRecord;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author wangq
 */
@Api(value = "DemoController")
@Slf4j
@RestController
@RequestMapping("/demo")
public class DemoController {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(DemoController.class);

    @Autowired
    private DemoService demoService;

    @PostMapping("/{page}")
    public String page(@PathVariable String page) {
        return page;
    }

    /**
     * 列表接口示例（不含分页）
     * @param demoForm 入参
     * @return ResultInfo
     */
    @ApiOperation(value = "Demo-selectList", response = DemoDto.class, httpMethod = "POST")
    @ApiImplicitParam()
    @PostMapping(value = "/selectList")
    public ResultInfo<List<DemoDto>> selectList(@RequestBody DemoForm demoForm) {

        try {
            // 取得列示例
            List<DemoDto> list = demoService.findList(demoForm);
            return ResultInfo.build(list);
        } catch (Exception e) {
            logger.error("系统异常", e);
            return ResultInfo.build(ResultCode.ERROR);
        }
    }


    /* ******************************* Redis 操作实例 END *********************** */

    /* ******************************* 消息国际化 操作实例 START *********************** */
    /**
     * 国际化翻译实例
     * @param demoForm 入参
     * @return ResultInfo
     */
    @ApiOperation(value = "Demo-国际化翻译", httpMethod = "POST")
    @ApiImplicitParam()
    @PostMapping(value = "/getResourceMessage")
    public ResultInfo<DemoMessageDto> getResourceMessage(@RequestBody DemoMessageForm demoForm) {
        try {
            String message = MessageUtils.getMessage(demoForm.getCode(),demoForm.getLanguage(),demoForm.getCountry(),null);
            DemoMessageDto messageDto = new DemoMessageDto();
            messageDto.setCode(demoForm.getCode());
            messageDto.setMessage(message);
            return ResultInfo.build(messageDto);
        } catch (Exception e) {
            logger.error("系统异常", e);
            return ResultInfo.build(ResultCode.ERROR);
        }
    }

    /**
     * 共通异常消息（框架自动翻译）
     * @param demoForm 入参
     * @return ResultInfo
     */
    @ApiOperation(value = "Demo-异常消息（框架自动翻译）", httpMethod = "POST")
    @ApiImplicitParam()
    @PostMapping(value = "/throwError")
    public ResultInfo<DemoMessageDto> throwError(@RequestBody DemoForm demoForm) {
        try {
            throw new Exception();
        } catch (Exception e) {
            logger.error("系统异常", e);
            return ResultInfo.build(ResultCode.ERROR);
        }
    }

    /**
     * 业务异常消息（框架自动翻译）
     * @param demoForm 入参
     * @return ResultInfo
     */
    @ApiOperation(value = "Demo-业务异常消息（框架自动翻译）", httpMethod = "POST")
    @ApiImplicitParam()
    @PostMapping(value = "/throwBusiness")
    public ResultInfo<DemoMessageDto> throwBusinessException(@RequestBody DemoForm demoForm) {
        try {
            throw new BusinessException("E_00001");
        } catch (BusinessException e) {
            logger.error("业务异常", e);
            throw e;
        } catch (Exception e) {
            logger.error("系统异常", e);
            return ResultInfo.build(ResultCode.ERROR);
        }
    }

    /**
     * 业务信息+业务数据（框架自动翻译）
     * @param demoForm 入参
     * @return ResultInfo
     */
    @ApiOperation(value = "Demo-业务信息+业务数据（框架自动翻译）", httpMethod = "POST")
    @ApiImplicitParam()
    @PostMapping(value = "/throwInfo")
    public ResultInfo<DemoDto> throwInfo(@RequestBody DemoForm demoForm) {
        try {
            return ResultInfo.build(ResultCode.SUCCESS.getCode(),"I_00001","",new DemoDto(),null);
        } catch (BusinessException e) {
            logger.error("业务异常", e);
            throw e;
        } catch (Exception e) {
            logger.error("系统异常", e);
            return ResultInfo.build(ResultCode.ERROR);
        }
    }
    /* ******************************* 消息国际化 操作实例 END *********************** */

    /* ******************************* SQLite 操作实例 START *********************** */
    /**
     * 写入SQLite数据库
     * @param recordForm 入参
     * @return ResultInfo
     */
    @ApiOperation(value = "Demo-写入SQLite数据库", httpMethod = "POST")
    @ApiImplicitParam()
    @PostMapping(value = "/insertSQLite")
    public ResultInfo<DemoDto> insertSQLite(@RequestBody DemoRecordForm recordForm) {
        try {
            demoService.createErrorRecord(new MqErrorRecord());
            return ResultInfo.build(ResultCode.SUCCESS);
        } catch (BusinessException e) {
            logger.error("业务异常", e);
            throw e;
        } catch (Exception e) {
            logger.error("系统异常", e);
            return ResultInfo.build(ResultCode.ERROR);
        }
    }

    /**
     * 查询SQLite数据库
     * @param recordForm 入参
     * @return ResultInfo
     */
    @ApiOperation(value = "Demo-查询SQLite数据库", httpMethod = "POST")
    @ApiImplicitParam()
    @PostMapping(value = "/selectSQLite")
    public ResultInfo<DemoDto> selectSQLite(@RequestBody DemoRecordForm recordForm) {
        try {
            DemoDto demoDto = new DemoDto();
            demoDto.setMqList(demoService.selectSuccessRecord());
            return ResultInfo.build(demoDto);
        } catch (BusinessException e) {
            logger.error("业务异常", e);
            throw e;
        } catch (Exception e) {
            logger.error("系统异常", e);
            return ResultInfo.build(ResultCode.ERROR);
        }
    }

    /* ******************************* SQLite 操作实例 END *********************** */

}
