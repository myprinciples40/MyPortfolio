package com.fastcampus.jpaptractice;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Board {
    @Id
    @GeneratedValue //Automatic numbering
    private Long bno; //Post number
    private String title;
    private String writer;
    private String content;
    private Long viewCnt;
    @Temporal(value = TemporalType.TIMESTAMP) //Date and time mapping
    private Date inDate;
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date upDate;

    @Override
    public String toString() {
        return "Board{" +
                "bno=" + bno +
                ", title='" + title + '\'' +
                ", Writer='" + writer + '\'' +
                ", content='" + content + '\'' +
                ", viewCnt=" + viewCnt +
                ", inDate=" + inDate +
                ", upDate=" + upDate +
                '}';
    }

    public Long getBno() {
        return bno;
    }

    public void setBno(Long bno) {
        this.bno = bno;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getViewCnt() {
        return viewCnt;
    }

    public void setViewCnt(Long viewCnt) {
        this.viewCnt = viewCnt;
    }

    public Date getInDate() {
        return inDate;
    }

    public void setInDate(Date inDate) {
        this.inDate = inDate;
    }

    public Date getUpDate() {
        return upDate;
    }

    public void setUpDate(Date upDate) {
        this.upDate = upDate;
    }
}
