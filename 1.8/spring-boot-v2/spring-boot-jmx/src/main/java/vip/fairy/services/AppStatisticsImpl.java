package vip.fairy.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;
import vip.fairy.entities.Singer;

import java.util.List;

@Component
@ManagedResource(description = "JMX managed resource", objectName = "jmxDemo:name=ProSpring5SingerApp")
public class AppStatisticsImpl implements AppStatistics {

	@Autowired
	private SingerService singerService;

	@ManagedAttribute(description = "Number of singers in the application")
	@Override
	public int getTotalSingerCount() {
		return singerService.findAll().size();
	}

	@ManagedOperation
	public String findJohn() {
		List<Singer> singers = singerService.findByFirstNameAndLastName("John", "Mayer");
		if (!singers.isEmpty()) {
			return singers.get(0).getFirstName() + " " + singers.get(0).getLastName();
		}
		return "not found";
	}
}
