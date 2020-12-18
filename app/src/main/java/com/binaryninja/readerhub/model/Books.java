package com.binaryninja.readerhub.model;

public class Books {
   private String ownerId;
   private String ownerName;
   private String bookID;
   private String bookName;
   private String bookCategories;
   private String bookLanguage;
   private int bookPage;
   private String bookPublishedDate;

    public Books(String ownerId, String ownerName, String bookID, String bookName, String bookCategories, String bookLanguage, int bookPage, String bookPublishedDate) {
        this.ownerId = ownerId;
        this.ownerName = ownerName;
        this.bookID = bookID;
        this.bookName = bookName;
        this.bookCategories = bookCategories;
        this.bookLanguage = bookLanguage;
        this.bookPage = bookPage;
        this.bookPublishedDate = bookPublishedDate;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookCategories() {
        return bookCategories;
    }

    public void setBookCategories(String bookCategories) {
        this.bookCategories = bookCategories;
    }

    public String getBookLanguage() {
        return bookLanguage;
    }

    public void setBookLanguage(String bookLanguage) {
        this.bookLanguage = bookLanguage;
    }

    public int getBookPage() {
        return bookPage;
    }

    public void setBookPage(int bookPage) {
        this.bookPage = bookPage;
    }

    public String getBookPublishedDate() {
        return bookPublishedDate;
    }

    public void setBookPublishedDate(String bookPublishedDate) {
        this.bookPublishedDate = bookPublishedDate;
    }
}
