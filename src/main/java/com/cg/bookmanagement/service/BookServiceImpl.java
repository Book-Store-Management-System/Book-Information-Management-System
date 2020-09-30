package com.cg.bookmanagement.service;

import java.util.List;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.cg.bookmanagement.dto.BookDTO;

import com.cg.bookmanagement.exception.BookNotFoundException;
import com.cg.bookmanagement.exception.EmptyBookListException;

import com.cg.bookmanagement.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepository;

	@Override
	public List<BookDTO> getAllBook() {
		if(bookRepository.count()==0)
		{
			throw new EmptyBookListException("No book exists in the database");
		}
		return bookRepository.findAll();
	}

	@Override
	public Optional<BookDTO> getBookById(String bookId) {
		if (!bookRepository.existsById(bookId)) {
			throw new BookNotFoundException("Book with Id " + bookId + " Not Found");
		}
		return bookRepository.findById(bookId);
	}

	@Override
	public BookDTO addBook(BookDTO books)  {
		
		
		if(books.getBookId()==null) { books.setBookId("BOOK" +
		  bookRepository.count()); }
		 
		BookDTO books1 = new BookDTO(books.getBookId(),books.getTitle(), books.getAuthor(), books.getDescription(), 
				books.getIsbn(), books.getPrice(), books.getPublishDate(), books.getBookcategory());
		return bookRepository.save(books1);

	}

	@Override
	public BookDTO deleteBookById(String bookId) {
		if (!bookRepository.existsById(bookId)) {
			throw new BookNotFoundException("Book with Id " + bookId + " Not Found");
		}
		BookDTO books=bookRepository.findById(bookId).get();
		bookRepository.delete(books);
		return books;

	}

	@Override
	public boolean updateBookById(BookDTO bookItem) {
		if(bookRepository.findById(bookItem.getBookId()).isPresent()) {
			bookRepository.save(bookItem);
			return true;
		}
		return false;
	}



}
