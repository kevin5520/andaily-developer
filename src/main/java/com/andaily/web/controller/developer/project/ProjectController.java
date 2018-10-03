package com.andaily.web.controller.developer.project;

import com.andaily.domain.dto.developer.project.ProjectOverviewDto;
import com.andaily.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Date: 13-10-19
 *
 * @author Shengzhao Li
 */
@Controller
@RequestMapping("/project_")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @RequestMapping("overview")
    public String overview(ProjectOverviewDto projectOverviewDto, Model model) {
        projectOverviewDto = projectService.loadProjectOverviewDto(projectOverviewDto);
        model.addAttribute("projectOverviewDto", projectOverviewDto);
        return "project/project_overview";
    }

    @RequestMapping("archive/{guid}")
    public String archive(@PathVariable("guid") String guid) {
        projectService.archiveProject(guid);
        return "redirect:../overview?alert=archiveProjectInfo";
    }
}
