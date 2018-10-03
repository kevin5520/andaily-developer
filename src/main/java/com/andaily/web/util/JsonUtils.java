package com.andaily.web.util;

import com.andaily.domain.shared.Application;
import net.sf.json.JSON;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Shengzhao Li
 */
public abstract class JsonUtils {


    public static void write(HttpServletResponse response, JSON json) {

        response.setContentType("application/json;charset=" + Application.ENCODING);
        try {
            PrintWriter writer = response.getWriter();
            json.write(writer);
            writer.flush();
        } catch (IOException e) {
            throw new IllegalStateException("Write json to response error", e);
        }

    }

    public static void write(HttpServletResponse response, String json) {
        response.setContentType("application/json;charset=" + Application.ENCODING);

        try {
            PrintWriter writer = response.getWriter();
            writer.write(json);
            writer.flush();
        } catch (IOException e) {
            throw new IllegalStateException("Write json to response error", e);
        }

    }
}
