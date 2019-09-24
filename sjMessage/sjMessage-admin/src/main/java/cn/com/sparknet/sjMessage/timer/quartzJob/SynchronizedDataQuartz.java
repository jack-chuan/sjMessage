package cn.com.sparknet.sjMessage.timer.quartzJob;

import cn.com.sparknet.sjMessage.datalist.service.impl.SynchronizedDataServiceImpl;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SynchronizedDataQuartz implements Job {

    // 需要注入的依赖bean
    private static SynchronizedDataServiceImpl synchronizedDataServiceImpl;

    // 定义一个set方法
    @Autowired
    public void setTaskStoreService(SynchronizedDataServiceImpl synchronizedDataServiceImpl) {
        SynchronizedDataQuartz.synchronizedDataServiceImpl = synchronizedDataServiceImpl;
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        synchronizedDataServiceImpl.dataKTL();
    }
}
