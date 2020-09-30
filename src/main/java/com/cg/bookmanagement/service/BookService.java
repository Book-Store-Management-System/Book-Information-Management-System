package com.cg.bookmanagement.service;
import java.util.List;
import java.util.Optional;

import com.cg.bookmanagement.dto.BookDTO;

public interface BookService {

	public List<BookDTO> getAllBook();
    Optional<BookDTO> getBookById(String bookId); 
	BookDTO addBook(BookDTO books);
	BookDTO deleteBookById(String bookId);
	public boolean updateBookById(BookDTO bookItem);
	

	
	
}
