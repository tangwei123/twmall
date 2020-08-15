package com.xiao5.twmall.search.controller;

import com.xiao5.twmall.search.service.SearchService;
import com.xiao5.twmall.search.vo.SearchParam;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestControllerAdvice
@RequestMapping("search")
@Slf4j
public class SearchController {

    @Autowired
    public SearchService searchService;

    @GetMapping({"/", "/list.html"})
    public ModelAndView index(SearchParam searchParam){
        ModelAndView mav = new ModelAndView("list.html");
        //构建ES的搜索提交
        new SearchRequest();
        if(searchParam.getKeyword() != null){//如果读取到了关键字，那么就设置 skuTitle的搜索条件

        }
        return mav;
    }
}
