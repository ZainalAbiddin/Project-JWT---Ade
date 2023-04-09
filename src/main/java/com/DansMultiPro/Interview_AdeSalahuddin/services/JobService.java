package com.DansMultiPro.Interview_AdeSalahuddin.services;

import com.DansMultiPro.Interview_AdeSalahuddin.dto.Job;
import com.DansMultiPro.Interview_AdeSalahuddin.middleware.DansMultiPro;
import com.DansMultiPro.Interview_AdeSalahuddin.utils.LoggerUtils;
import com.DansMultiPro.Interview_AdeSalahuddin.utils.ResponseHandler;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class JobService {

    private DansMultiPro dansMultiPro;
    @Autowired
    private LoggerUtils loggerUtils;

    public ResponseEntity<Object> getJobList() {
        List<Job> jobList;
        try {
            jobList = dansMultiPro.getListJob();
            if (jobList.isEmpty()) {
                loggerUtils.errorLogger("joblist null", "job-service");
                return ResponseHandler.generateResponse("Failed Get Job list", HttpStatus.NOT_FOUND, "");
            }
        } catch (Exception e) {
            loggerUtils.errorLogger(e.getMessage(), "job-service");
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND, "");
        }
        loggerUtils.infoLogger("success", "job-service", String.valueOf(jobList));
        return ResponseHandler.generateResponse("success get job list", HttpStatus.OK, jobList);
    }

    public ResponseEntity<Object> getJobById(String id) {
        Job job;
        try {
            job = dansMultiPro.getPostById(id);
            if (null == job.getId()) {
                loggerUtils.errorLogger("job null", "job-service");
                return ResponseHandler.generateResponse("Failed Get Job", HttpStatus.NOT_FOUND, "");
            }
        } catch (Exception e) {
            loggerUtils.errorLogger(e.getMessage(), "job-service");
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND, "");
        }
        loggerUtils.infoLogger("success", "job-service", String.valueOf(job));
        return ResponseHandler.generateResponse("success get job", HttpStatus.OK, job);
    }
}
