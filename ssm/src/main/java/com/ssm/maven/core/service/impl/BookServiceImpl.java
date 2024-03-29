package com.ssm.maven.core.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ssm.maven.core.dao.BookDao;
import com.ssm.maven.core.entity.Book;
import com.ssm.maven.core.service.BookService;

/**
 * @author 1034683568@qq.com
 * @project_name ssm-maven
 * @date 2017-3-1
 */
@Service("bookService")
public class BookServiceImpl implements BookService {
	private static final long serialVersionUID = 1L;
	@Resource
	private BookDao bookDao;

	@Override
	public List<Book> findBooks(Map<String, Object> map) {
		return bookDao.findBooks(map);
	}

	@Override
	public Long getTotalBooks(Map<String, Object> map) {
		return bookDao.getTotalBooks(map);
	}

	@Override
	public int insertBook(Book book) {
		return bookDao.insertBook(book);
	}

	@Override
	public Book getBookById(String id) {
		return bookDao.getBookById(id);
	}

}
