package com.example.robo;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.CronScheduleBuilder.*;
import static org.quartz.DateBuilder.*;

import java.text.ParseException;  
import org.quartz.SchedulerException;  



public class RoboCronTrigger {
	
	private SchedulerFactory schedulerFactory = null;
	private Scheduler scheduler = null;
	
	public RoboCronTrigger(){
		try {
			schedulerFactory = new StdSchedulerFactory();
			scheduler = schedulerFactory.getScheduler();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void start(){
		try {
			JobDetail job = newJob(RoboJob.class)
							.withIdentity("job", "group1")
							.build();
			
			
			//CronTrigger trigger = new CronTrigger("trigger", "group1", "20 * * * * *");
			CronTrigger trigger = newTrigger()
								  .withIdentity("trigger", "group1")
								  .withSchedule(cronSchedule("0 0/1 * * * ?"))
								  .build();

			
			scheduler.scheduleJob(job, trigger);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
}
