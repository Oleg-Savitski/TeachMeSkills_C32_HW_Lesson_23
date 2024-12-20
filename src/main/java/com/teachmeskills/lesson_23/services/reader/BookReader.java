package com.teachmeskills.lesson_23.services.reader;

import com.teachmeskills.lesson_23.exception.BookProcessingException;

import java.util.List;

public interface BookReader<T> {
    List<T> readBooks(String filePath) throws BookProcessingException;
}