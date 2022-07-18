package com.example.springbootbackend.scheduler;
import com.example.springbootbackend.model.User;
import com.example.springbootbackend.model.Alert;
import com.example.springbootbackend.model.Currency;
import com.example.springbootbackend.model.Status;
import com.example.springbootbackend.service.AlertService;
import com.example.springbootbackend.service.CurrencyService;
import com.example.springbootbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;


@Component
public class Scheduler {
	private static final Logger logger = LoggerFactory.getLogger(Scheduler.class);
	@Autowired
	private AlertService alertService;
	@Autowired
	private CurrencyService currencyService;
	@Autowired
	private UserService userService;

    @Scheduled(fixedRate = 10000)
	public void someJob() throws InterruptedException {

		List<Alert> alerts = alertService.getAllAlerts();
		for( Alert alert : alerts){
			Currency currency = currencyService.getCurrencyById(alert.getCurrencyId());
			//it is safer to lock alerts
			if(currency.getCurrentPrice() < alert.getTargetPrice() && alert.getStatus() == Status.NEW){
				alertService.updateAlertStatus(Status.TRIGGERED, alert.getId());
				User user = userService.getUserById(alert.getUserId());
				logger.info("Alert for "+ user.getFirstName() +" " + user.getLastName()+ " is triggered: Currency >> "+currency.getCurrencyName()+" CurrentPrice >> "+currency.getCurrentPrice() );
			}
		}
	}

}