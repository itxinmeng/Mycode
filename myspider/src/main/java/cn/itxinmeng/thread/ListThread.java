package cn.itxinmeng.thread;

import cn.itxinmeng.pojo.Page;
import cn.itxinmeng.utils.HtmlUtils;
import cn.itxinmeng.utils.HttpUtils;

import java.util.Map;

/**
 * 心梦   www.itxinmeng.cn
 * 爬取职位列表
 */
public class ListThread extends Thread {

    private Page page;
    DetailsThread dt = new DetailsThread();

    public void setPage(Page page) {
        this.page = page;
    }


    @Override
    public void run() {
        //获取页面html
        String html = HttpUtils.getDataByUrl(page.getUrl());

        page.setPageContent(html);
        //解析职位详情URL 返回URL集合
        Map<String, Boolean> urllist = HtmlUtils.getValueByXpath("//@href", page);

        dt.setUrlList(urllist);
        //启动详情线程
        dt.start();
    }
}
