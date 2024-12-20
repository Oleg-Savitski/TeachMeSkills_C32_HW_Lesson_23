package com.teachmeskills.lesson_23.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "book")
public class Book {
    private String title;
    private String author;
    private int year;
    private int pages;
    private String genre;

    public Book() {}

    @JsonProperty("title")
    @XmlElement(name = "title")
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    @JsonProperty("author")
    @XmlElement(name = "author")
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    @JsonProperty("year")
    @XmlElement(name = "year")
    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    @JsonProperty("pages")
    @XmlElement(name = "pages")
    public int getPages() { return pages; }
    public void setPages(int pages) { this.pages = pages; }

    @JsonProperty("genre")
    @XmlElement(name = "genre")
    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                ", pages=" + pages +
                ", genre='" + genre + '\'' +
                '}';
    }
}