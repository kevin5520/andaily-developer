package com.andaily.web.controller.developer.backlog;

import com.andaily.domain.dto.developer.BacklogDownloadDocumentsDto;
import com.andaily.service.BacklogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

/**
 * Date: 13-9-10
 *
 * @author Shengzhao Li
 */
@Controller
@RequestMapping("/backlog_action")
public class BacklogActionController {

    @Autowired
    private BacklogService backlogService;


    @RequestMapping("archive/{guid}")
    public String archive(@PathVariable("guid") String guid) {
        final String projectGuid = backlogService.archiveBacklog(guid);
        return "redirect:../../backlog_overview?alert=archiveBacklogInfo&projectGuid=" + projectGuid;
    }

    @RequestMapping("remove_document/{guid}")
    public String removeDocument(@PathVariable("guid") String guid, @RequestParam(required = true) String backlogGuid) {
        backlogService.removeDocument(guid);
        return "redirect:../../backlog_form/" + backlogGuid;
    }

    @RequestMapping("download_documents/{guid}")
    public void downloadDocuments(@PathVariable("guid") String guid, HttpServletResponse response) throws Exception {
        BacklogDownloadDocumentsDto downloadDocumentsDto = backlogService.loadBacklogDownloadDocumentsDto(guid);

        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=" + downloadDocumentsDto.getDownloadFilename());

        ServletOutputStream out = response.getOutputStream();
        out.write(downloadDocumentsDto.getData());
        out.flush();
    }


}
