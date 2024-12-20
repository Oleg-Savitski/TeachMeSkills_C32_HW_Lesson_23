package com.teachmeskills.lesson_23.services.parser;

import com.teachmeskills.lesson_23.exception.BookProcessingException;

public interface BookParser<T> {
    T findBookWithMaxPages(String filePath) throws BookProcessingException;
}