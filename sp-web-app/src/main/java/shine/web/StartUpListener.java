package shine.web;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import shine.app.utils.ShineProperties;
import shine.util.MapBoardLocationsTask;

public class StartUpListener implements ServletContextListener {
	private Scheduler scheduler;
	private boolean shouldStartScheduler;

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		shutdownScheduler();
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {

		ShineProperties.load(
				arg0.getServletContext().getRealPath("/") + "../shine.properties");
//		TimeZone tz = TimeZone.getTimeZone("GMT");
//		System.out.println("#################" + tz);
//		TimeZone.setDefault(tz);
		if (!ShineProperties.getBoolean("suppress.location.scheduler")) {
			startScheduler();
		}
	}

	private void startScheduler() {
		try {
			// Grab the Scheduler instance from the Factory 
			scheduler = StdSchedulerFactory.getDefaultScheduler();

			// and start it off
			if (shouldStartScheduler) {
				scheduler.start();
			}

			JobDetail job = newJob(MapBoardLocationsTask.class)
					.withIdentity("job1", "group1")
					.build();
			System.out.println(ShineProperties.getBoardLocationUpdateInterval());

			Trigger trigger = newTrigger()
					.withIdentity("trigger1", "group1")
					.startNow()
					.withSchedule(simpleSchedule()
							.withIntervalInSeconds(ShineProperties.getBoardLocationUpdateInterval())
							.repeatForever())
					.build();

			// Tell quartz to schedule the job using our trigger
			scheduler.scheduleJob(job, trigger);

		} catch (SchedulerException se) {
			se.printStackTrace();
		}

	}

	private void shutdownScheduler() {
		try {
			if (scheduler != null)
				scheduler.shutdown();

		} catch (SchedulerException se) {
			se.printStackTrace();
		}

	}

}
