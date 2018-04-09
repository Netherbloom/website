package com.ru.ebooks.entity;

public class EbookChapter {
    private String id;

    private String chapter;

    private String ebookid;

    private Integer pri;

    private String copyurl;

    private String bookname;

    private Integer status;

    private Integer issave;

    private String content;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter == null ? null : chapter.trim();
    }

    public String getEbookid() {
        return ebookid;
    }

    public void setEbookid(String ebookid) {
        this.ebookid = ebookid == null ? null : ebookid.trim();
    }

    public Integer getPri() {
        return pri;
    }

    public void setPri(Integer pri) {
        this.pri = pri;
    }

    public String getCopyurl() {
        return copyurl;
    }

    public void setCopyurl(String copyurl) {
        this.copyurl = copyurl == null ? null : copyurl.trim();
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname == null ? null : bookname.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIssave() {
        return issave;
    }

    public void setIssave(Integer issave) {
        this.issave = issave;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}