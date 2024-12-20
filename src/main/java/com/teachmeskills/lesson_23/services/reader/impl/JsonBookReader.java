package com.teachmeskills.lesson_23.services.reader.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teachmeskills.lesson_23.exception.BookProcessingException;
import com.teachmeskills.lesson_23.services.reader.BookReader;
import com.teachmeskills.lesson_23.model.Book;


import java.io.File;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JsonBookReader implements BookReader<Book> {
    private static final Logger LOGGER = Logger.getLogger(JsonBookReader.class.getName());

    @Override
    public List<Book> readBooks(String filePath) throws BookProcessingException {
        try {
            LOGGER.info("Starting to read a JSON file -> " + filePath);

            File file = new File(filePath);
            if (!file.exists()) {
                throw new BookProcessingException(
                        BookProcessingException.ErrorType.FILE_NOT_FOUND,
                        "The file was not found -> " + filePath
                );
            }

            ObjectMapper objectMapper = new ObjectMapper();
            List<Book> books = objectMapper.readValue(
                    file,
                    objectMapper.getTypeFactory().constructCollectionType(List.class, Book.class)
            );

            LOGGER.info("Books read -> " + books.size());
            return books;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error when reading the JSON file..", e);
            throw new BookProcessingException(
                    BookProcessingException.ErrorType.PARSING_ERROR,
                    "Error when reading the JSON file..",
                    e
            );
        }
    }
}