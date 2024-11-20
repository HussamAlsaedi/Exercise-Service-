package com.example.newsarticlemanagementsystem.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NewsArticle {
    @NotEmpty(message = "id: is Empty")
    @Size(max = 100, message = "id: max value is 100")
    private String id;

    @NotEmpty(message = "author is Empty")
    @Size(min = 4, max = 20, message = "author: value must be between 4 and 20 characters")
    private String author;

    @NotEmpty(message = "content is Empty")
    @Size(min = 200, message = "content: Must be more than 200 characters.")
    private String content;

    @NotEmpty(message = "category: must be not Empty")
    @Pattern(regexp = "politics|sports|technology" , message = "Category Must be either politics, sports or technology only.")
    private String category;

    @NotEmpty(message = "imageUrl: must be not Empty")
    private String image;

    private boolean isPublished = false;


}
