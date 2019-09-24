package cn.com.sparknet.sjMessage.timer.config;

import cn.com.sparknet.sjMessage.timer.entity.SysTaskEntity;
import cn.com.sparknet.sjMessage.timer.service.SysTaskService;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.List;

/**
 * 任务初始化调度
 */
@Configuration
public class ApplicationStartQuartzJob implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private SysTaskService sysTaskService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        List<SysTaskEntity> sysTaskList = sysTaskService.queryAllList();
        Scheduler scheduler = null;
        try {
            scheduler = scheduler();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        if(!sysTaskList.isEmpty()){
            for (SysTaskEntity sysTaskEntity : sysTaskList){
                Class classs =  null;
                try {
                    classs = Class.forName(sysTaskEntity.getJobClass());
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                JobDetail jobDetail = JobBuilder.newJob(classs).withIdentity(sysTaskEntity.getJobClass(), scheduler.DEFAULT_GROUP).build();
                CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(sysTaskEntity.getCronExpression());
                CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity(sysTaskEntity.getJobClass(), scheduler.DEFAULT_GROUP)
                        .withSchedule(cronScheduleBuilder).build();
                try {
                    scheduler.scheduleJob(jobDetail, cronTrigger);
                } catch (SchedulerException e) {
                    e.printStackTrace();
                }
            }
            try {
                scheduler.start();
            } catch (SchedulerException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 初始注入scheduler
     * @return
     * @throws SchedulerException
     */
    @Bean
    public Scheduler scheduler() throws SchedulerException{
        SchedulerFactory schedulerFactoryBean = new StdSchedulerFactory();
        return schedulerFactoryBean.getScheduler();
    }
}