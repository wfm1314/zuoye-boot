package com.jk.controller;


import com.jk.model.Product;
import com.jk.service.SolrService;
import org.apache.solr.client.solrj.SolrClient;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.*;


@RestController
@RequestMapping("solr")
public class SolrController {
    @Autowired
    private SolrClient client;

    @Autowired
    private SolrService solrService;
    /**
     * 新增/修改 索引
     * 当 id 存在的时候, 此方法是修改(当然, 我这里用的 uuid, 不会存在的), 如果 id 不存在, 则是新增
     * @return
     */
    @RequestMapping("/add")
    public String add() {
        Product product = new Product();
        product.setId("8");
        product.setProduct_num(9);
        product.setProduct_price(23.1);
        product.setProduct_sell_point("很好");
        product.setProduct_title("鑫帅");
        solrService.add(product);
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        try {
            SolrInputDocument doc = new SolrInputDocument();
            doc.setField("id", uuid);
            doc.setField("product_title", "我是中国人, 我爱中国");

            /*如果spring.data.solr.host 里面配置到 core了, 那么这里就不需要传 collection1 这个参数
             * 下面都是一样的*/


            client.add("core1", doc);
            //client.commit();
            client.commit("core1");
            return uuid;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "error";
    }

    /**
     * 根据id删除索引
     * @param id
     * @return
     */
    @RequestMapping("delete")
    public String delete(String id)  {
        try {
            client.deleteById("core1",id);
            client.commit("core1");

            return id;
        } catch (Exception e) {
            e.printStackTrace();
        }


        return "error";
    }


    /**
     * 根据id查询索引
     * @return
     * @throws Exception
     */
    @RequestMapping("getById")
    public String getById() throws Exception {
        SolrDocument document = client.getById("core1", "1");
        System.out.println(document);
        return document.toString();
    }


    /**
     * 综合查询: 在综合查询中, 有按条件查询, 条件过滤, 排序, 分页, 高亮显示, 获取部分域信息
     * @return
     */
    @RequestMapping(value="search")
    public Map<String,Object> userlist(Product users, Integer page, Integer rows) throws IOException, SolrServerException, SolrServerException {
        //因为使用easyui返回数据
        Map<String,Object> mSolr=new HashMap<String,Object>();
        //把所有查询的高亮显示内容发到list中
        List<Product> userslist=new ArrayList<>();
        //查询条件的对象
        SolrQuery params = new SolrQuery();
        //判断前台传递的关键字是否为空
        if(!"".equals(users.getProduct_title()) && users.getProduct_title()!=null ){
            //不为空设置条件为关键字
            params.set("q", users.getProduct_title());
        }else{//如果为空查询所有
            params.set("q", "*:*");
        }
        //默认查询字段  一般默认指定
        params.set("df", "product_title");
        //指定查询结果返回哪些字段
        params.set("fl", "id,product_title,product_sell_point,product_price,product_num");
        // 设置高亮字段
        params.addHighlightField("product_title"); // 高亮字段
        //分页
        if(page==null){
            params.setStart(0);
        }else {
            params.setStart((page-1)*rows);
        }
        if(rows==null){
            params.setRows(5);
        }else {
            params.setRows(rows);
        }



        //高亮
        //打开开关
        params.setHighlight(true);
        //设置前缀
        params.setHighlightSimplePre("<span style='color:red'>");
        //设置后缀
        params.setHighlightSimplePost("</span>");
        //QueryResponse是查询返回的对象数据   client.query("core1",params)  查询的是哪个索引库和条件
        QueryResponse queryResponse = client.query("core1",params);
        ///查询返回的结果list对象   不包括高亮
        SolrDocumentList results = queryResponse.getResults();
        //查询出来总条数
        long numFound = results.getNumFound();
        //查询返回的高亮结果
        Map<String, Map<String, List<String>>> highlight = queryResponse.getHighlighting();
        //循环查询的所有结果
        for (SolrDocument result : results) {
            //创建对象接收循环的对象数据
            Product user=new Product();
            //设置高亮的字段
            String highname="";
            //根据id获得高亮的内容
            Map<String, List<String>> map = highlight.get(result.get("id"));
            //根据高亮字段拿到数据
            List<String> list = map.get("product_title");
            //判断数据是否为空
            if(list==null){
                //如果为空把普通字段放到对象中
                highname=(String)result.get("product_title");
            }else{
                //获得高亮字段查询的值放到变量
                highname=list.get(0);
            }
            user.setId((String)result.get("id"));
            user.setProduct_sell_point((String)result.get("product_sell_point"));
            user.setProduct_num((int)result.get("product_num"));
            user.setProduct_price((double)result.get("product_price"));
            user.setProduct_title(highname);
            userslist.add(user);

        }

        mSolr.put("total",numFound);
        mSolr.put("rows",userslist);
        return mSolr;
    }
}



