package org.example.bookshop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Author extends IdClass{
    private String authorName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;
    private String citizenship;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String genre;

    @OneToMany(mappedBy = "author")
    public List<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        book.setAuthor(this);
        books.add(book);
    }

    public Author(String authorName, LocalDate dateOfBirth, String citizenship, Gender gender, String genre) {
        this.authorName = authorName;
        this.dateOfBirth = dateOfBirth;
        this.citizenship = citizenship;
        this.gender = gender;
        this.genre = genre;
    }
}
