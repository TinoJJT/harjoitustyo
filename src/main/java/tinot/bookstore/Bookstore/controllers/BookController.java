package tinot.bookstore.Bookstore.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tinot.bookstore.Bookstore.models.Book;
import tinot.bookstore.Bookstore.models.BookRepository;
import tinot.bookstore.Bookstore.models.CategoryRepository;

@Controller
public class BookController {
	
	@Autowired
	private BookRepository bRepository;
	
	@Autowired
	private CategoryRepository cRepository;

	@RequestMapping(value="/booklist", method=RequestMethod.GET)
	public String bookList(Model model) {	
        model.addAttribute("books", bRepository.findAll());
		return "booklist";
	}
	
	@RequestMapping(value = "/add")
    public String addBook(Model model){
    	model.addAttribute("book", new Book());
    	model.addAttribute("categories", cRepository.findAll());
        return "addbook";
    }
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Book book){
        bRepository.save(book);
        return "redirect:booklist";
    }   
	
	@RequestMapping(value = "/edit/{isbn}", method = RequestMethod.GET)
    public String editBook(@PathVariable("isbn") Long isbn, Model model){
		Book book = bRepository.findById(isbn).get(	);
    	model.addAttribute("book", book);
    	model.addAttribute("categories", cRepository.findAll());
        return "editbook";
    }
	
	@RequestMapping(value = "/edit/applyedits", method = RequestMethod.POST)
    public String applyEdits(Book book){
        bRepository.save(book);
        return "redirect:../booklist";
    }  
	
	@RequestMapping(value = "/delete/{isbn}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable("isbn") Long isbn, Model model){
    	bRepository.deleteById(isbn);
        return "redirect:../booklist";
	}
	
	
  /*
	@PutMapping("/edit/{id}")
    public ResponseEntity<?> update(@PathVariable("isbn") Book targetBook,  @RequestBody Book sourceBook) {
        BeanUtils.copyProperties(sourceBook, targetBook, "isbn");
        return ResponseEntity.ok(bRepository.save(targetBook));
    }
    */
}
