package com.prolific.swag.app;

/**
 * Created by Danish556 on 3/30/14.
 */
public class BookObject {

    public BookObject(){}
    public BookObject(String author,String title,String publisher,String lastCheckedOut,String lastCheckedOutBy,String categories,String url,String id)
    {
        this.author             = author;
        this.categories         = categories;
        this.lastCheckedOutBy   = lastCheckedOutBy;
        this.lastCheckedOut     = lastCheckedOut;
        this.publisher          = publisher;
        this.title              = title;
        this.url                = url;
        this.id                 = id;
    }

    //BookObject To String Method
    public String toString()
    {
     String toReturn = "Title                 :"  + title +
                       "Author                :"  + author+
                       "LastCheckedDetails    :"  + lastCheckedOut +lastCheckedOut +
                       "Publisher             :"  + publisher +
                       "Categories            :"  + categories +
                       "url                   :"  + url +
                       "id                    :"  +id;
        return toReturn;
    }
    //Setters and Getters
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getPublisher() {
        return publisher;
    }
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
    public String getLastCheckedOutBy() {
        return lastCheckedOutBy;
    }
    public void setLastCheckedOutBy(String lastCheckedOutBy) {
        this.lastCheckedOutBy = lastCheckedOutBy;
    }
    public String getCategories() {
        return categories;
    }
    public void setCategories(String categories) {
        this.categories = categories;
    }
    public void setLastCheckedOut(String lastCheckedOut){this.lastCheckedOut = lastCheckedOut;}
    public String getLastCheckedOut(){return lastCheckedOut;}
    public String getUrl() {return url;}
    public void setUrl(String url) {
        this.url = url;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    private String title            =  "";
    private String author           =  "";
    private String publisher        =  "";
    private String lastCheckedOutBy =  "";
    private String lastCheckedOut   =  "";
    private String categories       =  "";
    private String url              =  "";
    private String id               =  "";

}
