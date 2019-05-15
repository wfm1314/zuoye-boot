package com.jk.service;

import com.jk.dao.BookDao;
import com.jk.model.BooksBean;
import com.jk.model.TreeBean;
import com.jk.model.TypeBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<TreeBean> findTree() {
        int pid=0;
        List<TreeBean> list = findNode(pid);
        return list ;
    }



    private List<TreeBean> findNode(int pid) {
        List<TreeBean> list  = bookDao.findNavTreeByPid(pid);
        for (TreeBean treeBean : list) {
            Integer id = treeBean.getId();
            List<TreeBean> nodelist = findNode(id);
            treeBean.setChildren(nodelist);
        }
        return list;
    }

    @Override
    public List<TypeBean> findType() {
        return bookDao.findType();
    }

    @Override
    public void saveType(BooksBean booksBean) {
        booksBean.setUpordown(0);
        mongoTemplate.save(booksBean);
    }

    @Override
    public HashMap<String, Object> findBookPage(Integer page, Integer rows, Integer btype) {
        HashMap<String,Object> hashMap=new HashMap<>();
        Query query=new Query();
        if(btype != null && btype != 0){
            query.addCriteria(Criteria.where("btype").is(btype));
        }
        long count = mongoTemplate.count(query, BooksBean.class);
        int start =(page-1)*rows;
        query.skip(start).limit(rows);
        List<BooksBean> list =mongoTemplate.find(query, BooksBean.class);
        for (BooksBean booksBean : list) {
            Integer btype2 = booksBean.getBtype();
            TypeBean type=bookDao.findType2(btype2);
            booksBean.setTypename(type.getTypename());
        }
        hashMap.put("total", count);
        hashMap.put("rows", list);
        return hashMap ;
    }

    @Override
    public void delBook(String[] ids) {
        Query query=new Query();
        query.addCriteria(Criteria.where("id").in(ids));
        mongoTemplate.remove(query, BooksBean.class);
    }

}
