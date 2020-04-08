package de.rieckpil.tutorials;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.concurrent.ThreadLocalRandom;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
public class PersonLoader implements CommandLineRunner {

	@Autowired
	private PersonRepository personRepository;
	@Autowired
	private AddressRepository addressRepository;

	@Override
	public void run(String... args) throws Exception {

		System.out.println("Loading entries...");

		String[] firstnames = { "Tom", "Max", "Anna", "Hanna", "Mike", "Duke", "Fred", "Tim", "Paul", "Luke", "Tobias",
				"Timi", "Michelle", "Thomas", "Andrew" };
		String[] lastnames = { "Smith", "Taylor", "Williams", "Hammer", "Lewis", "Jones", "Evans", "Harris", "Mayer",
				"Schmid" };

		LocalDateTime initDate = LocalDateTime.of(1990, 12, 12, 12, 12);

		for (int i = 0; i < 10_000; i++) {
			Person p = new Person();
			p.setBudget(ThreadLocalRandom.current().nextInt(10000));
			p.setDob(Instant.ofEpochSecond(initDate.plusDays(i).toEpochSecond(ZoneOffset.UTC)));
			p.setFirstname(firstnames[ThreadLocalRandom.current().nextInt(0, firstnames.length)]);
			p.setLastname(lastnames[ThreadLocalRandom.current().nextInt(0, lastnames.length)]);
      Faker faker = new Faker();
      String streetName = faker.address().streetName();
      String country = faker.address().country();
      Address address = new Address(streetName, country, p);
			p.setAddress(address);
      personRepository.save(p);
//      Address address = new Address(streetName, country, p);
//			addressRepository.save(address);
//      p.setAddress(address);
		}

		System.out.println("...Finished loading 10.000 entities");
	}

}
