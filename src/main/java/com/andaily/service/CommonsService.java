package com.andaily.service;

import com.andaily.domain.dto.developer.commons.GeckoFileDto;

/**
 * @author Shengzhao Li
 */

public interface CommonsService {

    GeckoFileDto loadFileByGuid(String guid);
}