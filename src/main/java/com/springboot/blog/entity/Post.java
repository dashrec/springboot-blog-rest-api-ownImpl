package com.springboot.blog.entity;
import lombok.*;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
        name = "posts", uniqueConstraints = {@UniqueConstraint(columnNames = {"title"})} // if u want any column unique
)
public class Post { // if we do not provide name posts jpa will take a class name Post to create table
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column(name = "title", nullable = false) // if u do not provide column name jpa will take a field name
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "content", nullable = false)
    private String content;

    @OneToMany(mappedBy = "" +
            "", cascade = CascadeType.ALL, orphanRemoval = true)  // one post might have many comments. If we delete a post it will perform cascade dilation for  all comments
    private Set<Comment> comments = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY) // whenever we get Post gpa entity we don't load category object immediately. we can get it on demand by  calling getCategory method
    @JoinColumn(name = "category_id") // foreign key in child table (child table of category table is post table)
    private Category category;
}
