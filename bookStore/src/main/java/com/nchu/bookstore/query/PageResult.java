package com.nchu.bookstore.query;

import lombok.Getter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Getter
public class PageResult{
    private List<?> listData;//显示出的结果
    private Integer totalCount;//总记录条数

    //用户确定
    private Integer currentPage;//当前页数
    private Integer pageSize;//每页最多显示多少条

    //计算而来
    private Integer startPage = 1;//首页
    private Integer pervPage;//上一页
    private Integer nextPage;//下一页
    private Integer totalPage;//末页

    private List pageItems;
    public static PageResult empty(Integer pageSize){
        return new PageResult(Collections.EMPTY_LIST,0,1,pageSize);
    }

    public PageResult(List<?> listData, Integer totalCount, Integer currentPage, Integer pageSize) {
        this.listData = listData;
        this.totalCount = totalCount;
        this.currentPage = currentPage;
        this.pageSize = pageSize;

        this.totalPage = totalCount%pageSize == 0?totalCount/pageSize:totalCount/pageSize+1;
        this.pervPage = currentPage>1?currentPage-1:1;
        this.nextPage = currentPage<totalPage?currentPage+1:totalPage;
        this.pageItems = Arrays.asList(3,5,7);
    }
}
