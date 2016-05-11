package com.yhl.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
  import static org.quartz.JobBuilder.*;
  import static org.quartz.TriggerBuilder.*;
  import static org.quartz.SimpleScheduleBuilder.*;

  public class QuartzTest{

      // job 类信息不会缓存 每次都是新建
      public static class NumberAdd implements Job {

          public NumberAdd(){

          }

           static int count ;

          @Override
          public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
              add();
          }

          public void add(){
              count++;
              System.out.println("count : "+count);
              try {
                  Thread.sleep(2000l);
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
          }
      }


      public static void main(String[] args) {

          try {
              // Grab the Scheduler instance from the Factory
              Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
              // and start it off
              scheduler.start();
              // define the job and tie it to our HelloJob class
              JobDetail job = newJob(NumberAdd.class)
                      .withIdentity("NumberAdd", "groupNumberAdd")
                      .build();

              // Trigger the job to run now, and then repeat every 40 seconds
              Trigger trigger = newTrigger()
                      .withIdentity("triggerNumberAdd", "groupNumberAdd")
                      .startNow()
                      .withSchedule(simpleSchedule()
                              .withIntervalInSeconds(2)
                              .repeatForever())
                      .build();

              // Tell quartz to schedule the job using our trigger
              scheduler.scheduleJob(job, trigger);

              Thread.sleep(30000l);
              scheduler.shutdown();

          } catch (Exception se) {
              se.printStackTrace();
          }
      }



  }