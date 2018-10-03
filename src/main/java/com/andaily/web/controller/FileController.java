package com.andaily.web.controller;

import com.andaily.domain.dto.developer.commons.GeckoFileDto;
import com.andaily.service.CommonsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Shengzhao Li
 */
@Controller
@RequestMapping("/file")
public class FileController {



    @Autowired
    private CommonsService commonsService;


    @RequestMapping("image/{guid}")
    public void image(@PathVariable("guid") String guid, String w, HttpServletResponse response) throws Exception {
        GeckoFileDto fileDto = commonsService.loadFileByGuid(guid);

        final String extension = fileDto.getContextTypeExtension();
        response.setContentType("image/" + extension);
        byte[] data = fileDto.getData();

        ServletOutputStream os = response.getOutputStream();
        os.write(data);
        os.flush();
    }


    @RequestMapping("download/{guid}")
    public void fileUpload(@PathVariable("guid") String guid, HttpServletResponse response) throws Exception {
        GeckoFileDto fileDto = commonsService.loadFileByGuid(guid);

        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=" + fileDto.downloadFileName());

        ServletOutputStream out = response.getOutputStream();
        out.write(fileDto.getData());
        out.flush();
    }


}