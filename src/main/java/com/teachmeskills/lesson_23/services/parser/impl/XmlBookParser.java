package com.teachmeskills.lesson_23.services.parser.impl;

import com.teachmeskills.lesson_23.exception.BookProcessingException;
import com.teachmeskills.lesson_23.model.BookWrapper;
import com.teachmeskills.lesson_23.services.parser.BookParser;
import com.teachmeskills.lesson_23.model.Book;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;


import java.io.File;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class XmlBookParser implements BookParser<Book> {
    private static final Logger LOGGER = Logger.getLogger(XmlBookParser.class.getName());

    @Override
    public Book findBookWithMaxPages(String filePath) throws BookProcessingException {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(BookWrapper.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            BookWrapper bookWrapper = (BookWrapper) unmarshaller.unmarshal(new File(filePath));
            List<Book> books = bookWrapper.getBooks();

            Book maxPagesBook = books.stream()
                    .max(Comparator.comparingInt(Book::getPages))
                    .orElseThrow(() -> new BookProcessingException(
                            BookProcessingException.ErrorType.PARSING_ERROR,
                            "No books found.."
                    ));

            LOGGER.info("A book with the maximum number of pages was found -> " + maxPagesBook);
            return maxPagesBook;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "XML parsing error..", e);
            throw new BookProcessingException(
                    BookProcessingException.ErrorType.PARSING_ERROR,
                    "XML file parsing error..",
                    e
            );
        }
    }
}