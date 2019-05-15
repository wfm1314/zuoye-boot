package com.jk.service;

import com.jk.dao.SolrDao;
import com.jk.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SolrServiceImpl implements SolrService{
    @Autowired
    private SolrDao solrDao;

    @Override
    public void add(Product product) {
        solrDao.add(product);
    }
}
