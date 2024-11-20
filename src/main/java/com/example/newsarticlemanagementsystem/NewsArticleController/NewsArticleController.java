package com.example.newsarticlemanagementsystem.NewsArticleController;

import com.example.newsarticlemanagementsystem.ApiResponse.ApiResponse;
import com.example.newsarticlemanagementsystem.Model.NewsArticle;
import com.example.newsarticlemanagementsystem.NewsArticleService.NewsArticleService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/NewsArticle")
public class NewsArticleController {
    private final NewsArticleService newsService;

    public NewsArticleController(NewsArticleService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("get")
    public ResponseEntity getblog()
    {
        return ResponseEntity.ok(newsService.getAllArticles());
    }

    @PostMapping("/add")
    public ResponseEntity addNewsArticle(@RequestBody @Valid NewsArticle newsArticle, Errors errors)
    {
        if (errors.hasErrors())
        {
            return ResponseEntity.status(400).body(errors.getAllErrors().get(0).getDefaultMessage());
        }

        boolean idAdded = newsService.addArticle(newsArticle);
        if (idAdded)
        {
            return ResponseEntity.status(200).body(new ApiResponse("News Article added successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("News Article not added successfully"));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity updateNewsArticle(@PathVariable String id, @RequestBody @Valid NewsArticle newsArticle, Errors errors) {

        if (errors.hasErrors()) {

            return ResponseEntity.status(400).body(errors.getAllErrors().get(0).getDefaultMessage());
        }

        boolean isUpdated = newsService.updateArticle(id, newsArticle);

        if (isUpdated) {
            return ResponseEntity.status(200).body(new ApiResponse("News Article is updated successfully"));
        } else {
            return ResponseEntity.status(404).body(new ApiResponse("News Article not found"));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteArticle(@PathVariable String id)
    {
        if (newsService.deleteArticle(id)) {
            return ResponseEntity.ok(new ApiResponse("Article deleted successfully"));
        }
        return ResponseEntity.status(404).body(new ApiResponse("Article not found"));
    }


    @PutMapping("/publish/{id}")
    public ResponseEntity<ApiResponse> publishArticle(@PathVariable String id) {
        if (newsService.publishArticle(id))
        {
            return ResponseEntity.ok(new ApiResponse("Article published successfully"));
        }
        return ResponseEntity.status(404).body(new ApiResponse("Article not found"));
    }

    @GetMapping("/published")
    public ResponseEntity getPublishedArticles()
    {
        return ResponseEntity.ok(newsService.getPublishedArticles());
    }


    @GetMapping("/category/{category}")
    public ResponseEntity getArticlesByCategory(@PathVariable String category)
    {
        String categoryFound = newsService.GetNewsArticlesByCategory(category);

        if (categoryFound != null) {
            return ResponseEntity.status(200).body(categoryFound);
        } else {
            return ResponseEntity.status(404).body(new ApiResponse("category not found"));
        }
    }

}
