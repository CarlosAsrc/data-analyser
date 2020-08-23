package com.carlosasrc.dataanalyser.job;

import com.carlosasrc.dataanalyser.service.DataAnalysisService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DirectoryMonitorJob {
    private final DataAnalysisService dataAnalysisService;

    @Scheduled(cron = "${application.monitoring.cron-job}")
    private void verifyInputDirectory() {
        Boolean hadNewFiles = dataAnalysisService.processNewFilesMadeAvailable();
        if (hadNewFiles)
            dataAnalysisService.generateSummarizedReport();
    }
}
