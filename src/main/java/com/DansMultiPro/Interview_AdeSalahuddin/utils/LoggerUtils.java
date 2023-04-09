package com.DansMultiPro.Interview_AdeSalahuddin.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LoggerUtils {

    private static final String Line = "====================";

    public void infoLogger(String message, String serviceName, String data){
        log.info(Line+serviceName+Line);
        log.info(Line+message+Line);
        log.info(data);
        log.info(Line+serviceName+Line);
    }

    public void errorLogger(String message, String serviceName){
        log.info(Line+serviceName+Line);
        log.info(Line+message+Line);
        log.info(Line+serviceName+Line);
    }
}
