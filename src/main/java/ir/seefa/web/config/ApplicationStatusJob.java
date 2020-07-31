package ir.seefa.web.config;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

import java.time.LocalDateTime;

public class ApplicationStatusJob implements Job {

    private final Logger logger = Logger.getLogger(ApplicationStatusJob.class);

    public void execute(JobExecutionContext context) {
        LocalDateTime now = LocalDateTime.now();
        logger.info("Application Memory Status => Memory state: " + ((Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (1024 * 1024)) + " MB");
    }

}
