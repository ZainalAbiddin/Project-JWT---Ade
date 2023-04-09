package com.DansMultiPro.Interview_AdeSalahuddin.middleware;

import com.DansMultiPro.Interview_AdeSalahuddin.dto.Job;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.cloud.openfeign.FeignClient;

import java.util.List;

@FeignClient(value = "jobMiddleware", url = "http://dev3.dansmultipro.co.id/api/recruitment")
public interface DansMultiPro {

    @GetMapping(value = "/positions.jso")
    List<Job> getListJob();

    @GetMapping(value = "/positions/{postId}", produces = "application/json")
    Job getPostById(@PathVariable("postId") String postId);
}

