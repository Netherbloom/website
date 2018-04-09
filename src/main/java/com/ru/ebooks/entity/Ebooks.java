package com.ru.ebooks.entity;

import java.util.List;

public class Ebooks {
    private String id;

    private String copyurl;

    private String cover;

    private String createtime;

    private String name;

    private String type;

    private String updatetime;

    private String writer;

    private Integer status;

    private Integer isupdate;

    private String intro;

    public List<EbookChapter> chapters;

    public List<EbookChapter> getChapters() {
        return chapters;
    }

    public void setChapters(List<EbookChapter> chapters) {
        this.chapters = chapters;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCopyurl() {
        return copyurl;
    }

    public void setCopyurl(String copyurl) {
        this.copyurl = copyurl == null ? null : copyurl.trim();
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover == null ? null : cover.trim();
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime == null ? null : createtime.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime == null ? null : updatetime.trim();
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer == null ? null : writer.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsupdate() {
        return isupdate;
    }

    public void setIsupdate(Integer isupdate) {
        this.isupdate = isupdate;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro == null ? null : intro.trim();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Ebooks) {
            Ebooks ebooks = (Ebooks) o;
            return this.name.equals(ebooks.name)&&this.writer.equals(ebooks.writer);
        }
        return super.equals(o);
    }
}