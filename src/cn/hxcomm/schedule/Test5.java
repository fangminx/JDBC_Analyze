package cn.hxcomm.schedule;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.CronScheduleBuilder.*;

public class Test5 { 
    public void go() throws Exception { 
        
        //读取properties文件，获取执行周期
        InputStream in = new FileInputStream("runtime\\runtime.properties");
        Properties prop = new Properties();
        String key = "0 0 1 * * ?";
        prop.load(in);
        if(prop.containsKey("runtime")){
            key = prop.getProperty("runtime"); // 通过key获取value
        }
        
     // Grab the Scheduler instance from the Factory
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

       
         
     // define the job and tie it to our MyJob class
        JobDetail job = newJob(MyJob.class)
            .withIdentity("job1", "group1")
            .build();

     // Trigger the job to run 
        System.out.println(key);
        Trigger trigger = newTrigger()
            .withIdentity("trigger1", "group1")
            .startNow()
            .withSchedule(cronSchedule(key))
            .build(); 

        // Tell quartz to schedule the job using our trigger
        scheduler.scheduleJob(job, trigger);
        
        // and start it off
        scheduler.start();
} 
 

    public static void main(String[] args) throws Exception { 
 
        Test5 test = new Test5(); 
        test.go(); 
    } 
 
}
