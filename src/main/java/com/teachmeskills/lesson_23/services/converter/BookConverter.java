package com.teachmeskills.lesson_23.services.converter;

import com.teachmeskills.lesson_23.exception.BookProcessingException;

public interface BookConverter {
    void convert(String sourceFilePath, String targetFilePath) throws BookProcessingException;
}