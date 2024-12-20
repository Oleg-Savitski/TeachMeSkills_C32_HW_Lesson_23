package com.teachmeskills.lesson_23.services.converter.impl;

import com.teachmeskills.lesson_23.exception.BookProcessingException;
import com.teachmeskills.lesson_23.model.BookWrapper;
import com.teachmeskills.lesson_23.services.converter.BookConverter;
import com.teachmeskills.lesson_23.services.reader.BookReader;
import com.teachmeskills.lesson_23.model.Book;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;

import java.io.File;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JsonToXmlBookConverter implements BookConverter {
    private static final Logger LOGGER = Logger.getLogger(JsonToXmlBookConverter.class.getName());
    private final BookReader<Book> bookReader;

    public JsonToXmlBookConverter(BookReader<Book> bookReader) {
        this.bookReader = bookReader;
    }

    @Override
    public void convert(String sourceFilePath, String targetFilePath) throws BookProcessingException {
        try {
            List<Book> books = bookReader.readBooks(sourceFilePath);

            BookWrapper bookWrapper = new BookWrapper();
            bookWrapper.setBooks(books);

            JAXBContext jaxbContext = JAXBContext.newInstance(BookWrapper.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            marshaller.marshal(bookWrapper, new File(targetFilePath));

            LOGGER.info("The JSON to XML conversion is complete. File -> " + targetFilePath);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Conversion error..", e);
            throw new BookProcessingException(
                    BookProcessingException.ErrorType.CONVERSION_ERROR,
                    "Error converting JSON to XML..",
                    e
            );
        }
    }
}