package cn.itxinmeng.pojo;

import lombok.Data;

/**
 * 心梦
 * lombok插件
 * 关于lombok 访问 www.itxinmeng.cn/blog/35
 */

@Data
public class Page {

    private String pageContent;//页面HTML
    private String url;//地址
    private String positionAndSalary; //职位和薪资
    private String requirement;//要求   地点 工作经验  学历
    private String company;//公司
    private String duty;//职位要求

    @Override
    public String toString() {
        return "链接: " + url + "\n" +
                "薪资/职位: " + positionAndSalary + "\t" +
                "地点/年限/学历: " + requirement + "\t" +
                "公司: " + company + "\n" +
                "职位要求: " + duty + "\n";
    }
}
