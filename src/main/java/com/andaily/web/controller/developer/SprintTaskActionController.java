package com.andaily.web.controller.developer;

import com.andaily.domain.developer.operation.SprintTimeUtils;
import com.andaily.domain.dto.developer.*;
import com.andaily.service.ScrumService;
import com.andaily.web.util.JsonUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;

/**
 * Date: 13-8-18
 *
 * @author Shengzhao Li
 */
@Controller
@RequestMapping("/task_action")
public class SprintTaskActionController {

    @Autowired
    private ScrumService scrumService;

    @RequestMapping("start/{guid}")
    public String start(@PathVariable("guid") String guid) {
        String sprintGuid = scrumService.startTask(guid);
        return "redirect:../..?currentSprint.guid=" + sprintGuid + "&status=PENDING&alert=startTaskSuccess";
    }

    /**
     * When start do a task, will checking the task executor is current login user or not,
     * If not, will popup a confirm message.
     *
     * @param guid Sprint task guid
     */
    @RequestMapping("start_checking/{guid}")
    public String startChecking(@PathVariable("guid") String guid, Model model) {
        StartTaskCheckingResult result = scrumService.checkingStartTask(guid);
        model.addAttribute("result", result);
        return "task_start_checking";
    }

    @RequestMapping("load_backlog/{guid}")
    public String loadBacklog(@PathVariable("guid") String guid, Model model) {
        SprintFormBacklogDto backlogDto = scrumService.loadTaskBacklogDto(guid);
        model.addAttribute("backlogDto", backlogDto);
        return "task_backlog";
    }

    /**
     * Load move task action
     *
     * @param guid  Task guid
     * @param model Model
     * @return View
     */
    @RequestMapping("load_move/{guid}")
    public String loadMove(@PathVariable("guid") String guid, Model model) {
        MoveTaskDto moveTaskDto = scrumService.loadMoveTaskDto(guid);
        model.addAttribute("moveTaskDto", moveTaskDto);
        return "task_move";
    }

    @RequestMapping("move_task")
    public String moveTask(MoveTaskDto moveTaskDto) {
        String sprintGuid = scrumService.moveTask(moveTaskDto);
        return "redirect:..?currentSprint.guid=" + sprintGuid + "&alert=moveTaskInfo&status=CANCELED";
    }

    @RequestMapping("archive/{guid}")
    public String archive(@PathVariable("guid") String guid, String status) {
        String sprintGuid = scrumService.archiveTask(guid);
        return "redirect:../..?currentSprint.guid=" + sprintGuid + "&alert=archiveTaskSuccess&status=" + (StringUtils.isNotEmpty(status) ? status : "CREATED");
    }

    @RequestMapping("cancel/{guid}")
    public String cancel(@PathVariable("guid") String guid) {
        String sprintGuid = scrumService.cancelTask(guid);
        return "redirect:../..?currentSprint.guid=" + sprintGuid + "&status=CANCELED&alert=cancelTaskSuccess";
    }

    @RequestMapping("restore/{guid}")
    public String restore(@PathVariable("guid") String guid) {
        String sprintGuid = scrumService.restoreTask(guid);
        return "redirect:../..?currentSprint.guid=" + sprintGuid + "&status=CREATED&alert=restoreTaskSuccess";
    }

    @RequestMapping("revert/{guid}")
    public String revert(@PathVariable("guid") String guid) {
        String sprintGuid = scrumService.revertTask(guid);
        return "redirect:../..?currentSprint.guid=" + sprintGuid + "&status=CREATED&alert=revertTaskSuccess";
    }

    @RequestMapping("finish/{guid}")
    public String finish(@PathVariable("guid") String guid, FinishSprintTaskDto finishSprintTaskDto) {
        finishSprintTaskDto.setGuid(guid);
        String sprintGuid = scrumService.finishTask(finishSprintTaskDto);
        return "redirect:../..?currentSprint.guid=" + sprintGuid + "&status=PENDING&alert=finishTaskSuccess";
    }

    @RequestMapping("finishform/{guid}")
    public String finishForm(@PathVariable("guid") String guid, Model model) {
        SprintTaskDto sprintTaskDto = scrumService.loadSprintTaskDto(guid);
        model.addAttribute("sprintTaskDto", sprintTaskDto);
        model.addAttribute("taskTimes", SprintTimeUtils.availableTaskTimes());
        return "finish_task_form";
    }

    @RequestMapping("assign_task_form/{guid}")
    public String assignTaskForm(@PathVariable("guid") String guid, Model model) {
        AssignTaskDto assignTaskDto = scrumService.loadAssignTaskDto(guid);
        model.addAttribute("assignTaskDto", assignTaskDto);
        return "assign_task_form";
    }

    @RequestMapping("assign_task_submit/{guid}")
    public String assignTaskSubmit(@PathVariable("guid") String guid, @RequestParam(required = true) String executorGuid, Model model) {
        String sprintGuid = scrumService.assignTask(guid, executorGuid);

        model.addAttribute("currentSprint.guid", sprintGuid)
                .addAttribute("status", "CREATED")
                .addAttribute("alert", "assignTaskSuccess");
        return "redirect:../..";
    }

    @RequestMapping("comment_form/{guid}")
    public String commentForm(@PathVariable("guid") String guid, Model model) {
        SprintTaskCommentsDto commentsDto = scrumService.loadSprintTaskCommentsDto(guid);
        model.addAttribute("commentsDto", commentsDto);
        return "sprint_task_comment";
    }

    @RequestMapping("comment_submit/{guid}")
    public String commentSubmit(@PathVariable("guid") String guid, String content, Model model) {
        SprintTaskCommentDto commentDto = scrumService.persistSprintTaskComment(guid, content);
        model.addAttribute("commentDto", commentDto);
        return "sprint_task_comment_result";
    }

    @RequestMapping("comment_delete/{guid}")
    public void commentDelete(@PathVariable("guid") String guid, HttpServletResponse response) {
        boolean result = scrumService.deleteSprintTaskComment(guid);
        JsonUtils.write(response, "{\"result\":" + result + "}");
    }

}
