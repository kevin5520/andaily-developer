package com.andaily.infrastructure.support.st;

import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

/**
 * Date: 13-7-20
 *
 * @author Shengzhao Li
 */
public class STRenderTest {

    @Test
    public void testRender() throws Exception {
        String path = "template/test_template.html";
        String path2 = "template/test_template2";

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("title", "我的andaily");
        model.put("content", "Welcome to andaily, 我是 lsz....");
        model.put("names", Arrays.asList("lisi", "olew", "oolsee"));

        STRender stRender = new STRender(path, model);
        String text = stRender.render();

        assertNotNull(text);
        System.out.println(text);


        STRender stRender2 = new STRender(path2, model);
        String text2 = stRender2.render();
        assertNotNull(text2);
        assertTrue(text2.length() > 0);


        STRender stRender3 = new STRender(path2);
        String text3 = stRender3.render();
        assertNotNull(text3);


        Map<String, Object> model2 = new HashMap<String, Object>();
        model2.put("title", "Wli, 我love you");
        model2.put("content", "Welcome to andaily, 我是 wli....");

        String text4 = stRender.render(model2);
        assertNotNull(text4);
    }
}
