package com.teachmeskills.lesson_23.launcher;

import com.teachmeskills.lesson_23.exception.BookProcessingException;
import com.teachmeskills.lesson_23.model.Book;

import com.teachmeskills.lesson_23.services.converter.BookConverter;
import com.teachmeskills.lesson_23.services.converter.impl.JsonToXmlBookConverter;
import com.teachmeskills.lesson_23.services.parser.BookParser;
import com.teachmeskills.lesson_23.services.parser.impl.XmlBookParser;
import com.teachmeskills.lesson_23.services.reader.BookReader;
import com.teachmeskills.lesson_23.services.reader.impl.JsonBookReader;
import com.teachmeskills.lesson_23.util.FilePathConfig;

import java.util.logging.Level;
import java.util.logging.Logger;

/** 1. Using the Jackson library, write a Java program that proofreads a
 * JSON file (books.json) and saves the data to a Java collection.

 *  2. Convert the data from this collection to XML format using the
 * JAXB library. Save the resulting XML result to a file.

 *  3. Using any parser (DOM,SAX,StAX), parse the data in the Java
 * application and output information about the book with the largest
 * number of pages to the console.
 */

public class ApplicationRunner {
    private static final Logger LOGGER = Logger.getLogger(ApplicationRunner.class.getName());

    public static void main(String[] args) {
        try {
            String jsonFilePath = FilePathConfig.JSON_FILE_PATH;
            String xmlFilePath = FilePathConfig.XML_FILE_PATH;

            LOGGER.info("The beginning of book processing ->");

            BookReader<Book> bookReader = new JsonBookReader();
            BookConverter bookConverter = new JsonToXmlBookConverter(bookReader);
            BookParser<Book> bookParser = new XmlBookParser();
            bookConverter.convert(jsonFilePath, xmlFilePath);
            Book maxPagesBook = bookParser.findBookWithMaxPages(xmlFilePath);
            LOGGER.info("A book with the maximum number of pages -> " + maxPagesBook);

        } catch (BookProcessingException e) {
            LOGGER.log(Level.SEVERE, "Book processing error -> " + e.getMessage(), e);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Unknown error..", e);
        }
    }
}