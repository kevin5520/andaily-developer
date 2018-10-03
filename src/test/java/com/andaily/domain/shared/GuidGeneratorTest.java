package com.andaily.domain.shared;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author Shengzhao Li
 */
public class GuidGeneratorTest {
    @Test
    public void testGenerate() throws Exception {
        for (int i = 0; i < 5; i++) {
            String generate = GuidGenerator.generate();
            Assert.assertNotNull(generate);
            System.out.println(generate);
        }
    }
}
