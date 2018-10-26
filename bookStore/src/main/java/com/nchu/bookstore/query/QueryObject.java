package com.nchu.bookstore.query;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QueryObject {
    //当前页面
    @Getter
    @Setter
    private Integer currentPage = 1;
    //页面最多显示多少条数据
    @Getter@Setter
    private Integer pageSize = 5;

    private List<String> conditions = new ArrayList<>();//保存查询条件
    private List<Object> parameters = new ArrayList<>();//保存查询参数
    public String getQuery(){
        StringBuilder sql = new StringBuilder(100);
        this.customziedQuery();
        if (conditions.size() == 0){
            return "";
        }
        //方法 ：使用StringUtils工具类 在condition中的元素之间利用AND连接起来
        String queryString = StringUtils.join(conditions," AND ");

        return sql.append(" WHERE ").append(queryString).toString();
    }

    public void customziedQuery() {
    }

    protected void addQuery(String conditions,Object... parameters){
        this.conditions.add(conditions);

        this.parameters.addAll(Arrays.asList(parameters));
    }

    //返回查询条件的占位符参数
    public List<Object> getParameters(){
        return parameters;
    }
}

