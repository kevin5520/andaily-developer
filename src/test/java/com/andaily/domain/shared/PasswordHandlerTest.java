package com.andaily.domain.shared;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

/**
 * @author Shengzhao Li
 */
public class PasswordHandlerTest {

    @Test
    public void testEncryptPassword() throws Exception {
        String pass = "7a81c719";
        String encryptPass = PasswordHandler.encryptPassword(pass);
        assertEquals(encryptPass, "513adf922f3f6d1d73661fa6e342088c");
    }

    @Test
    public void encryptReversiblePassword() {
        String pass = "7a81c719";
        String encryptPass = PasswordHandler.encryptReversiblePassword(pass);
        assertNotNull(encryptPass);

        String decryptPass = PasswordHandler.decryptReversiblePassword(encryptPass);
        assertEquals(pass, decryptPass);

    }
}
