package com.DansMultiPro.Interview_AdeSalahuddin.controller;

import com.DansMultiPro.Interview_AdeSalahuddin.dto.LoginUserRequest;
import com.DansMultiPro.Interview_AdeSalahuddin.services.JobService;
import com.DansMultiPro.Interview_AdeSalahuddin.services.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@Tag(name = "01. Job")
@SecurityRequirement(name = "bearer-key")
public class JobController {

    private final JobService jobService;

    @GetMapping("/user/job/list")
    public ResponseEntity<Object> jobList() {
        return jobService.getJobList();
    }

    @GetMapping("/user/job/{id}")
    public ResponseEntity<Object> getJobById(@PathVariable("id") String id) {
        return jobService.getJobById(id);
    }
}
