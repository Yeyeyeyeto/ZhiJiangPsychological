package com.eternal.zjp.core.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.eternal.common.exception.BusinessException;
import com.eternal.common.result.R;
import com.eternal.common.result.ResponseEnum;
import com.eternal.zjp.base.util.JwtUtils;
import com.eternal.zjp.core.pojo.entity.Questionnaire;
import com.eternal.zjp.core.pojo.entity.QuestionnaireRadio;
import com.eternal.zjp.core.pojo.entity.QuestionnaireWhether;
import com.eternal.zjp.core.pojo.vo.QuestionnaireRadioVO;
import com.eternal.zjp.core.pojo.vo.QuestionnaireVO;
import com.eternal.zjp.core.pojo.vo.QuestionnaireWhetherVO;
import com.eternal.zjp.core.service.QuestionnaireRadioService;
import com.eternal.zjp.core.service.QuestionnaireService;
import com.eternal.zjp.core.service.QuestionnaireWhetherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Auther Eternal
 * @Date 2022/3/5
 */
@Api(tags = "问卷管理")
@RestController
@RequestMapping("/admin/core/questionnaire")
@Slf4j
//@CrossOrigin
public class AdminQuestionnaireController {

    @Resource
    private QuestionnaireService questionnaireService;

    @Resource
    private QuestionnaireWhetherService questionnaireWhetherService;

    @Resource
    private QuestionnaireRadioService questionnaireRadioService;

    @ApiOperation("问卷列表")
    @GetMapping("/list")
    public R listAll() {
        List<Questionnaire> list = questionnaireService.list();
        return R.ok().data("list", list);
    }

    @ApiOperation(value = "根据id删除数据记录", notes = "逻辑删除数据记录")
    @DeleteMapping("/remove/{id}")
    public R removeById(
            @ApiParam(value = "数据id", required = true, example = "1")
            @PathVariable Integer id) {
        boolean result = questionnaireService.removeById(id);
        if (result) {
            return R.ok().message("删除成功");
        } else {
            return R.error().message("删除失败");
        }
    }

    @ApiOperation("新增问卷")
    @PutMapping("/save")
    public R save(
            @ApiParam(value = "问卷对象", required = true)
            @RequestBody Questionnaire questionnaire) {
        // 如果为空抛出自定义异常
        if (questionnaire.getQuestionnaireName() == null) {
            throw new BusinessException(ResponseEnum.QUESTION_AMOUNT_NULL_ERROR);
        }
        boolean result = questionnaireService.save(questionnaire);
        if (result) {
            return R.ok().message("保存成功");
        } else {
            return R.error().message("保存失败");
        }
    }

    @ApiOperation("新增咨询师问卷")
    @PutMapping("/cSave")
    public R cSave(
            @ApiParam(value = "问卷对象", required = true)
            @RequestBody Questionnaire questionnaire, HttpServletRequest request) {
        // 如果为空抛出自定义异常
        if (questionnaire.getQuestionnaireName() == null) {
            throw new BusinessException(ResponseEnum.QUESTION_AMOUNT_NULL_ERROR);
        }
        questionnaire.setAuthorId(JwtUtils.getUserId(request.getHeader("token")));
        boolean result = questionnaireService.save(questionnaire);
        if (result) {
            return R.ok().message("保存成功");
        } else {
            return R.error().message("保存失败");
        }
    }

    @ApiOperation("根据id获取问卷")
    @GetMapping("/get/{id}")
    public R getById(
            @ApiParam(value = "数据id", required = true, example = "1")
            @PathVariable Integer id) {
        Questionnaire questionnaire = questionnaireService.getById(id);
        if (questionnaire != null) {
            return R.ok().data("record", questionnaire);
        } else {
            return R.error().message("数据不存在");
        }
    }

    @ApiOperation("更新问卷")
    @PutMapping("/update")
    public R updateById(
            @ApiParam(value = "问卷对象", required = true)
            @RequestBody Questionnaire questionnaire) {
        boolean result = questionnaireService.updateById(questionnaire);
        if (result) {
            return R.ok().message("修改成功");
        } else {
            return R.error().message("修改失败");
        }
    }


    @ApiOperation("获取问卷分页列表")
    @GetMapping("/list/{page}/{limit}")
    public R listPage(
            @ApiParam(value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(value = "每页记录数", required = true)
            @PathVariable Long limit,

            @ApiParam(value = "查询关键字", required = false)
            @RequestParam String keyword) {
        //这里的@RequestParam其实是可以省略的，但是在目前的swagger版本中（2.9.2）不能省略，
        //否则默认将没有注解的参数解析为body中的传递的数据

        Page<Questionnaire> pageParam = new Page<>(page, limit);
        IPage<Questionnaire> pageModel = questionnaireService.listPage(pageParam, keyword);
        return R.ok().data("pageModel", pageModel);
    }

    @ApiOperation("获取问卷详细信息")
    @GetMapping("/show/{id}")
    public R show(
            @ApiParam(value = "问卷id", required = true)
            @PathVariable Integer id){

        QuestionnaireVO questionnaireVO = questionnaireService.getQuestionnaireVOById(id);
        return R.ok().data("questionnaireVO", questionnaireVO);
    }

    @ApiOperation("是否型问卷详情列表")
    @GetMapping("/showDetails/{id}")
    public R showDetails(
            @ApiParam(value = "问卷条目id", required = true)
            @PathVariable Integer id) {
        QueryWrapper<QuestionnaireWhether> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("questionnaire_id", id).orderByAsc("question_num");
        List<QuestionnaireWhether> itemList = questionnaireWhetherService.list(queryWrapper);
        return R.ok().data("itemList", itemList);
    }

    @ApiOperation("单项型问卷详情列表")
    @GetMapping("/showRadioDetails/{id}")
    public R showRadioDetails(
            @ApiParam(value = "问卷条目id", required = true)
            @PathVariable Integer id) {
        QueryWrapper<QuestionnaireRadio> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("questionnaire_id", id).orderByAsc("question_num");
        List<QuestionnaireRadio> itemList = questionnaireRadioService.list(queryWrapper);
        return R.ok().data("itemList", itemList);
    }


    @ApiOperation("是否题详细题目提交")
    @PostMapping("/whetherSubmit")
    public R whetherSubmit (@RequestBody QuestionnaireWhetherVO questionnaireWhetherVO) {
        questionnaireWhetherService.submit(questionnaireWhetherVO);
        return R.ok().message("提交成功");
    }

    @ApiOperation("咨询师问卷列表")
    @GetMapping("/consultantList")
    public R consultantList(HttpServletRequest request) {
        System.out.println(request.getHeader("token"));
        Integer userId = JwtUtils.getUserId(request.getHeader("token"));
        QueryWrapper<Questionnaire> questionnaireQueryWrapper = new QueryWrapper<>();
        questionnaireQueryWrapper.eq("author_id", userId);
        List<Questionnaire> list = questionnaireService.list(questionnaireQueryWrapper);
        return R.ok().data("list", list);
    }

    @ApiOperation("单选题详细题目提交")
    @PostMapping("/radioSubmit")
    public R radioSubmit (@RequestBody QuestionnaireRadioVO QuestionnaireRadioVO) {
        questionnaireWhetherService.radioSubmit(QuestionnaireRadioVO);
        return R.ok().message("提交成功");
    }

}
