package com.example.newsarticlemanagementsystem.NewsArticleService;

import com.example.newsarticlemanagementsystem.Model.NewsArticle;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Date;


@Service
public class NewsArticleService {

    private final ArrayList<NewsArticle> articles = new ArrayList<>();


    public ArrayList<NewsArticle> getAllArticles() {
        return articles;
    }

    public boolean addArticle(NewsArticle newsArticle)
    {
        if(articles.add(newsArticle))
        {
            return true;
        }
        return false;
    }

   public boolean updateArticle(String id, NewsArticle updatedArticle) {
        for (int i = 0; i < articles.size(); i++) {
            if (articles.get(i).getId().equals(id)) {
                articles.set(i, updatedArticle);
                return true;
            }
        }
        return false;
    }

    public boolean deleteArticle(String id)
    {
        for (int i = 0; i < articles.size(); i++) {
            if (articles.get(i).getId().equals(id)) {
                articles.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean publishArticle(String id) {
        for (NewsArticle newsArticle : articles) {
            if (newsArticle.getId().equals(id)) {
                newsArticle.setPublished(true);
                return true;
            }
        }
        return false;
    }


    public ArrayList<NewsArticle> getPublishedArticles()
    {
        ArrayList<NewsArticle> publishedArticles = new ArrayList<>();

        for (NewsArticle newsArticle : articles) {
            if (newsArticle.isPublished()) {
                publishedArticles.add(newsArticle);
            }
        }
        return publishedArticles;
    }


        public NewsArticle GetNewsArticlesByCategory(String  Category) {
        for (NewsArticle newsArticle: articles) {
            if (newsArticle.getCategory().equalsIgnoreCase(Category)) {
                return newsArticle;
            }
        }
        return null;
    }

}
