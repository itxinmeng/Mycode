package cn.itxinmeng;

import cn.itxinmeng.pojo.Page;
import cn.itxinmeng.thread.ListThread;

/**
 * 作者:心梦  www.itxinmeng.cn
 * 抓取BOSS上的招聘信息
 *
 * 思路:用两个线程进行爬取 一个爬取职位(RecruitmentList)  一个爬取详情 (RecruitmentDetails)
 * (以爬取所有招聘信息使用广度优先算法进行设计的  所以用两个线程 然而只实现爬取一页招聘信息)
 * RecruitmentList: 访问目标链接 使用 Xpath解析 返回的数据 获取其中所有职位详情的url 并放到map<String,Boolean></>中
 * RecruitmentDetails:遍历map中的链接 对没遍访问的链接发起GET请求 解析返回的数据  将false改为true
 */
public class StartSpider {

    public static void main(String[] args) {

        Page page = new Page();
        //目标URL
        page.setUrl("https://www.zhipin.com/job_detail/?query=java&scity=101010100&industry=&position=");
        // 北京java  url  只爬取第一页信息  爬第二页手动获取URL  或者改代码

        ListThread lt=new ListThread();

        lt.setPage(page);

        //启动爬虫
        lt.start();



    }

}
