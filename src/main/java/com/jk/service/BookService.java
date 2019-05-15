package com.jk.service;

import com.jk.model.BooksBean;
import com.jk.model.TreeBean;
import com.jk.model.TypeBean;

import java.util.HashMap;
import java.util.List;

public interface BookService {
    List<TreeBean> findTree();

    List<TypeBean> findType();

    void saveType(BooksBean booksBean);

    HashMap<String, Object> findBookPage(Integer page, Integer rows, Integer btype);

    void delBook(String[] ids);
}
