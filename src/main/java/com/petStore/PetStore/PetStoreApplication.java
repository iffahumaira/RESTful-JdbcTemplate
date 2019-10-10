package com.petStore.PetStore;

import com.petStore.PetStore.model.Pet;
import com.petStore.PetStore.repository.PetRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Arrays;
import java.util.List;

@Slf4j
@SpringBootApplication
public class PetStoreApplication implements CommandLineRunner {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	@Qualifier("petRepositoryImpl")
	private PetRepository petRepository;

	public static void main(String[] args) {
		SpringApplication.run(PetStoreApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("SpringCrudApplication...");
		runJDBC();
	}

	void runJDBC(){

		try{
			log.info("Checking if tables exists");
			jdbcTemplate.execute("DROP TABLE pets");
		} catch (Exception e){
			log.info("Table 'pets' does not exists.");
		}

		jdbcTemplate.execute("CREATE TABLE pets (\n" +
				"    id SERIAL," +
				"    name varchar(255)," +
				"    type varchar(255)," +
				"    gender varchar(255)," +
				"    price double(11,2)," +
				"	 color varchar(255))"
		);

		List<Pet> pets = Arrays.asList(
				new Pet("Olan", "Cat", "Male", 1000.00, "Not Specified"),
				new Pet("Abu", "Cat", "Male", 980.00, "Not Specified")
		);

		// register
		log.info("[ SAVE ]"); //this is how we display a log name to log console
		pets.forEach(pet -> {
			log.info("Saving... NAME : {} , TYPE : {} , GENDER : {} , PRICE : {} , COLOR : {}",
					pet.getName(), pet.getType(), pet.getGender(), pet.getPrice(), pet.getColor());
			petRepository.register(pet);
		});

		// find by id
		log.info("[ FIND BY ID ] : ID 2");
		Pet pet = petRepository.findById(2).orElseThrow(IllegalArgumentException::new);
		log.info("Output : {}", pet);

		// update
		log.info("[ UPDATE ] : ID 2 to Name='Fitto', Color='Gold' ");
		pet.setName("Fitto");
		pet.setColor("Gold");
		petRepository.update(pet);
		log.info("Output : {}", pet);

		// delete
		log.info("[ DELETE ] : ID 1");
		pet = petRepository.findById(1).orElseThrow(IllegalArgumentException::new);
		log.info("Output : {}", pet);
		log.info("Rows affected: {}", petRepository.deleteById(1));

		// findAll
		log.info(("[ FIND ALL ]"));
		log.info("New list of product after delete {}", petRepository.findAll());

	}
}
