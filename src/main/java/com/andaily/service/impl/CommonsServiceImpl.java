package com.andaily.service.impl;

import com.andaily.domain.developer.commons.CommonsRepository;
import com.andaily.domain.developer.commons.GeckoFile;
import com.andaily.domain.dto.developer.commons.GeckoFileDto;
import com.andaily.service.CommonsService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Shengzhao Li
 */
public class CommonsServiceImpl implements CommonsService {


    @Autowired
    private CommonsRepository commonsRepository;


    @Override
    public GeckoFileDto loadFileByGuid(String guid) {
        final GeckoFile geckoFile = commonsRepository.findGeckoFileByGuid(guid);
        return new GeckoFileDto(geckoFile, true);
    }
}