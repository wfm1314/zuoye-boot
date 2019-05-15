package com.jk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("page")
public class PageController {

    @RequestMapping("toMain")
    public String toMain(){

        return "main";
    }

    @RequestMapping("toAdd")
    public String toAdd(){

        return "add";
    }

    @RequestMapping("toList")
    public String toList(){

        return "list";
    }

    @RequestMapping("toSolr")
    public String toSolr(){

        return "solr";
    }


}
