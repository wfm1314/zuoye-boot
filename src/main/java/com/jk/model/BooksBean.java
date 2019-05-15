package com.jk.model;

import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection="books")
public class BooksBean {

    private String id;
    private String bname;
    private Double price;
    private Integer btype;
    private String typename;
    private String bjianjie;
    private String bbiming;
    private Integer upordown;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getBtype() {
        return btype;
    }

    public void setBtype(Integer btype) {
        this.btype = btype;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public String getBjianjie() {
        return bjianjie;
    }

    public void setBjianjie(String bjianjie) {
        this.bjianjie = bjianjie;
    }

    public String getBbiming() {
        return bbiming;
    }

    public void setBbiming(String bbiming) {
        this.bbiming = bbiming;
    }

    public Integer getUpordown() {
        return upordown;
    }

    public void setUpordown(Integer upordown) {
        this.upordown = upordown;
    }
}
