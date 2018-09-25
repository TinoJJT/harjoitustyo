package tinot.bookstore.Bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import tinot.bookstore.Bookstore.models.Book;
import tinot.bookstore.Bookstore.models.BookRepository;
import tinot.bookstore.Bookstore.models.Category;
import tinot.bookstore.Bookstore.models.CategoryRepository;
import tinot.bookstore.Bookstore.models.User;
import tinot.bookstore.Bookstore.models.UserRepository;

@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner studentDemo(BookRepository repository, CategoryRepository cRepository, UserRepository uRepository) {
		return (args) -> {
			log.info("Sample books");
			cRepository.save(new Category("Horror"));
			cRepository.save(new Category("Fantasy"));
			cRepository.save(new Category("Adventure"));
			cRepository.save(new Category("Action"));
			
			repository.save(new Book("Scary Sprites", "Tino", "1994", Long.parseLong("12345"), "20 euros", cRepository.findByName("Horror").get(0)));
			repository.save(new Book("Got to go fast", "Tero", "2018", Long.parseLong("156214"), "50 euros", cRepository.findByName("Action").get(0)));
			
			// Create users: admin/admin user/user
						User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
						User user2 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
						uRepository.save(user1);
						uRepository.save(user2);
			
			log.info("fetch all books");
			for (Book book : repository.findAll()) {
				log.info(book.toString());
			}

		};
	}
}
