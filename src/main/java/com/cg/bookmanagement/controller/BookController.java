package com.cg.bookmanagement.controller;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.bookmanagement.dto.BookDTO;
import com.cg.bookmanagement.exception.NullParameterException;

import com.cg.bookmanagement.service.BookService;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin("*")
@RequestMapping("/bookmanagement")
public class BookController {

	private static final Logger logger =LoggerFactory.getLogger(BookController.class);
	
	@Autowired
	BookService bookservice;
	
	//Getting all the avaliable books

	@ApiOperation(value="Get all the available books",notes="This api end points is to view all the books",
			response= BookDTO.class)
	@GetMapping("/getallbooks")
	public List<BookDTO> getAllBook() {
		return this.bookservice.getAllBook();
	}

	//Getting book by Id
	
	@ApiOperation(value="Get the  books by the id given as parameter",notes=" This api end points is to view a single book by id",
			response= BookDTO.class)
	@GetMapping("/getBookbyId")
	public Optional<BookDTO> getBookById(@RequestParam String bookId)  {
		if(bookId.equals("")) {
			logger.error("Null request,  Id not provided for  /getBookById");
			throw new NullParameterException("Null request, please provide Id to see the books!");  //
		}

		return this.bookservice.getBookById(bookId);

	}

	//Adding Books
	
	@ApiOperation(
			value = "Add books to Website",	notes = "Admin can add book to Website with this API",
			response = BookDTO.class
			)
	@PostMapping("/addBook")
	public BookDTO addBook(@RequestBody BookDTO books) {
		if( books.getAuthor().trim().length() == 0 ||
				books.getTitle().trim().length() == 0) { 
			logger.error("Null request,book details not provided at /addBook");
			throw new NullParameterException("Null request, please provide book details to add it in database");
		}
		
		
		return bookservice.addBook(books) ;
		
	}
	
	//Updating the Book

	@ApiOperation(value="To update a book in the website",notes="This api end points should update  a existing book in the website"
					+ "This api can only be accesed by the Admin of the "
					+ "BookStore Management System ",
					response= String.class)
	@PostMapping("/updateBookById")
	public String updateBookById(@RequestBody BookDTO bookItem){ 
			
			
			  if(bookservice.updateBookById(bookItem)) 
			  { 
				  return "successfully changed"; 
			  }
			  else { 
				  logger.error("Null request,  Id not provided for  /updateBookById");
			  throw new
			  NullParameterException("Null request, please provide inputs to update the books details!"  //
			  ); }
			 
		
		
	}
	
	//Delete the book by Id
	
	@ApiOperation(value="To delete a book from the website",notes="This api end points should delete a existing book in the website"
					+ "This api can only be accesed by the Admin of the "
					+ "BookStore Management System ",
					response= String.class)
	@DeleteMapping("/deleteBookById")
	public BookDTO deleteBookById(@RequestParam String bookId)  {

		if(bookId.equals("")) {
			logger.error("Null request,  Id not provided for  /deleteBookById");
			throw new NullParameterException("Null request, please provide Id to delete the book!");  //
		}
		
		return bookservice.deleteBookById(bookId);
		
	}
	

}
	
