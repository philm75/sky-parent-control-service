package com.mcs.parentalcontrol;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mcs.parentalcontrol.service.ParentalControlService;
import com.mcs.parentalcontrol.service.TechnicalFailureException;
import com.mcs.parentalcontrol.service.TitleNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;

import static java.lang.System.exit;

/**
 * Application Entry point.
 * 
 * @author Phil Merrilees.
 *
 */
@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private ParentalControlService parentalControlService;
    
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(Application.class);
        app.run(args);
    }
	
	@Override
    public void run(String... args) throws Exception {
		if (args.length == 2) {			
			try {
				System.out.println("Customer Control Level -> " + args[0]);
				System.out.println("Movie ID -> " + args[1]);
				
				boolean result = parentalControlService.checkCustomerControlLevel(args[0], args[1]);
				if (result) {
					System.out.println(String.format("Customer is allowed to watch movie %s", args[1]));
				} else {
					System.out.println(String.format("Customer is not allowed to watch movie %s", args[1]));					
				}
			} catch(TitleNotFoundException e) {
				System.out.println(e.getMessage());
			} catch(TechnicalFailureException e) {
				System.out.println(e.getMessage());			
			}
        } else {
            System.out.println("Please enter a customer control level and movie ID");
        }
		exit(0);	
    }
}