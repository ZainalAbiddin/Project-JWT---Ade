package com.DansMultiPro.Interview_AdeSalahuddin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import java.net.URL;
import java.time.LocalDate;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Job {
    private String id;
    private String type;
    private String url;

    @DateTimeFormat(pattern = "E MMM dd HH:mm:ss z yyyy")
    private LocalDate createdAt;

    private String company;
    private String companyUrl;
    private String location;
    private String title;
    private String description;
}
