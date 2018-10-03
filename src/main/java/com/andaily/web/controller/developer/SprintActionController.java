package com.andaily.web.controller.developer;

import com.andaily.domain.dto.developer.*;
import com.andaily.domain.dto.log.SprintActivityOverviewDto;
import com.andaily.infrastructure.mybatis.data.SimpleProjectData;
import com.andaily.service.ScrumService;
import com.andaily.web.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Date: 13-8-21
 *
 * @author Shengzhao Li
 */
@Controller
@RequestMapping("/sprint_action")
public class SprintActionController {

    @Autowired
    private ScrumService scrumService;

    @RequestMapping("check_start/{guid}")
    public String checkStart(@PathVariable("guid") String guid, Model model) {
        CheckSprintStartDto checkSprintStartDto = scrumService.loadCheckSprintStartDto(guid);
        model.addAttribute("checkSprintStartDto", checkSprintStartDto);
        return "sprint_check_start";
    }

    @RequestMapping("manage_backlogs/{guid}")
    public String manageBacklogs(@PathVariable("guid") String guid, Model model) {
        SprintManageBacklogsDto manageBacklogsDto = scrumService.loadSprintManageBacklogsDto(guid);
        model.addAttribute("manageBacklogsDto", manageBacklogsDto);
        return "sprint_manage_backlogs";
    }

    @RequestMapping("manage_backlogs/move_sprint_backlog")
    public void moveSprintBacklog(MoveSprintBacklogDto moveSprintBacklogDto, HttpServletResponse response) throws IOException {
        boolean result = scrumService.moveSprintBacklog(moveSprintBacklogDto);
        JsonUtils.write(response, "{\"result\":" + result + "}");
    }

    @RequestMapping("load_backlogs/{guid}")
    public String loadBacklogs(@PathVariable("guid") String guid, Model model) {
        SprintBacklogsDto sprintBacklogsDto = scrumService.loadSprintBacklogsDto(guid);
        model.addAttribute("sprintBacklogsDto", sprintBacklogsDto);
        return "sprint_backlogs";
    }

    @RequestMapping("load_sprint_projects")
    public String loadSprintProjects(Model model) {
        List<SimpleProjectData> projects = scrumService.loadAvailableSimpleProjects();
        model.addAttribute("projects", projects);
        return "sprint_projects_form";
    }

    @RequestMapping("start/{guid}")
    public String start(@PathVariable("guid") String guid) {
        String sprintGuid = scrumService.startSprint(guid);
        return "redirect:../..?currentSprint.guid=" + sprintGuid + "&status=CREATED&alert=startSprintSuccess";
    }

    @RequestMapping("archive/{guid}")
    public String archive(@PathVariable("guid") String guid, String to) {
        final String projectGuid = scrumService.archiveSprint(guid);

        String url = "sprint".equalsIgnoreCase(to) ? "../../sprint_overview" : "../..";
        return "redirect:" + url + "?alert=archiveSprintSuccess&projectGuid=" + projectGuid;
    }

    //Set default sprint action
    @RequestMapping("set_default/{guid}")
    public String setDefault(@PathVariable("guid") String guid) {
        final String projectGuid = scrumService.setDefaultSprint(guid);
        return "redirect:../../sprint_overview?alert=setDefaultSprintSuccess&projectGuid=" + projectGuid;
    }

    //Cancel default sprint action
    @RequestMapping("cancel_default/{guid}")
    public String cancelDefault(@PathVariable("guid") String guid) {
        final String projectGuid = scrumService.cancelDefaultSprint(guid);
        return "redirect:../../sprint_overview?alert=cancelDefaultSprintSuccess&projectGuid=" + projectGuid;
    }

    @RequestMapping("check_finish/{guid}")
    public String checkFinish(@PathVariable("guid") String guid, Model model) {
        CheckSprintFinishDto checkSprintFinishDto = scrumService.loadCheckSprintFinishDto(guid);
        model.addAttribute("checkSprintFinishDto", checkSprintFinishDto);
        return "sprint_check_finish";
    }

    @RequestMapping("finish/{guid}")
    public String finish(@PathVariable("guid") String guid, String to) {
        scrumService.finishSprint(guid);

        String url = "sprint".equalsIgnoreCase(to) ? "../../sprint_overview?" : "../..?currentSprint.guid=" + guid + "&";
        return "redirect:" + url + "alert=finishSprintInfo";
    }

    @RequestMapping("burndown/details/{guid}")
    public String burnDownDetails(@PathVariable("guid") String guid, Model model) {
        BurnDownDetailsDto burnDownDetailsDto = scrumService.loadBurnDownDetailsDto(guid);
        model.addAttribute("burnDownDetailsDto", burnDownDetailsDto);
        return "burndown_details";
    }

    //sprint activity
    @RequestMapping("activity/{guid}")
    public String activity(@PathVariable("guid") String guid, SprintActivityOverviewDto activityOverviewDto, Model model) {
        activityOverviewDto.setSprintGuid(guid);
        activityOverviewDto = scrumService.loadSprintActivityOverviewDto(activityOverviewDto);
        model.addAttribute("activityOverviewDto", activityOverviewDto);
        return "sprint_activity";
    }


}
