package jcrm.pp.ua.crmsystem;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.Date;
import java.util.TimeZone;

@SpringBootApplication
//@EnableAutoConfiguration
@EntityScan("jcrm.pp.ua.crmsystem.entities")
public class CrmSystemApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("Etc/UTC"));
		SpringApplication.run(CrmSystemApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(ApplicationContext appContext) {
		return args -> {
			String[] beans = appContext.getBeanDefinitionNames();
			Arrays.stream(beans).sorted().forEach(System.out::println);
		};
	}

//	@Bean
//	public CommandLineRunner demo(final ContactServiceImp contactServiceImp) {
//		return new CommandLineRunner() {
//			@Override
//			public void run(String... strings) throws Exception {
//
////				Date date = new Date();
////
////				Company company = new Company("SharashMontajCo");
////				companyRepository.save(company);
////
////				Contact contact = new Contact("KrasavchEk",company);
////				contactRepository.save(contact);
////
////				Client client = new Client();
////				Client client1 = new Client();
////				client.setContact(contact);
////				clientRepository.save(client);
////				client1.setCompany(company);
////				clientRepository.save(client1);
////
////				Address address = new Address("Vasuki","Pereulok Jlobskiy 5",contact);
////				addressRepository.save(address);
////				Email email = new Email("super_puper_vasiliy5453287@nomail.com",contact);
////				emailRepository.save(email);
////				Phone phone = new Phone("454545455454",contact);
////				phoneRepository.save(phone);
////				Lead lead = new Lead("lead1","status1",100,contact);
////				Lead lead2 = new Lead("lead2","status2",101,contact);
////				leadRepository.save(lead);
////				leadRepository.save(lead2);
////				Name name = new Name(contact,"Petr","Zilberman");
////				nameRepository.save(name);
////				Task task = new Task(lead,"task1",date,contact);
////				Task task2 = new Task(lead2,"task2",date,contact);
////				taskRepository.save(task);
////				taskRepository.save(task2);
////
////
////				Address address2 = new Address("NewVasuki","Prospekt Kota-Murkota 100500",company);
////				addressRepository.save(address2);
////				Email email2 = new Email("shmoco@nomail.com",company);
////				emailRepository.save(email2);
////				Phone phone2 = new Phone("854545454555",company);
////				phoneRepository.save(phone2);
////				Lead lead3 = new Lead("lead3","status3",102,company);
////				Lead lead4 = new Lead("lead4","status4",103,company);
////				leadRepository.save(lead3);
////				leadRepository.save(lead4);
////				Task task3 = new Task(lead3,"task3",date,company);
////				Task task4 = new Task(lead4,"task4",date,company);
////				taskRepository.save(task3);
////				taskRepository.save(task4);
//
//			}
//		};
//	}
}
