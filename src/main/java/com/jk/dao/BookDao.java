package com.jk.dao;

import com.jk.model.TreeBean;
import com.jk.model.TypeBean;

import java.util.List;

public interface BookDao {
    List<TreeBean> findNavTreeByPid(int pid);

    List<TypeBean> findType();

    TypeBean findType2(Integer btype2);
}
