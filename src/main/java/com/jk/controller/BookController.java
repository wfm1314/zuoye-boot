package com.jk.controller;

import com.jk.model.BooksBean;
import com.jk.model.TreeBean;
import com.jk.model.TypeBean;
import com.jk.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("books")
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping("findTree")
    public List<TreeBean> findTree() {
        return bookService.findTree();
    }

    @RequestMapping("findType")
    public List<TypeBean> findType(){

        return bookService.findType();

    }

    @RequestMapping("saveType")
    public void saveType(BooksBean booksBean){
        System.out.println(booksBean);
        System.out.println(22222);
        System.out.println(222);
        bookService.saveType(booksBean);
    }


    @RequestMapping("findBookPage")
    public HashMap<String,Object> findBookPage(Integer page,Integer rows,Integer btype){

        return bookService.findBookPage(page,rows,btype);
    }

    @RequestMapping("delBook")
    public boolean delBook(String[] ids){
        try {
            bookService.delBook(ids);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
