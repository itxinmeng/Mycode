package cn.itxinmeng.thread;

import cn.itxinmeng.pojo.Page;
import cn.itxinmeng.pojo.Xpath;
import cn.itxinmeng.store.IStore;
import cn.itxinmeng.store.impl.DefaultStoreImpl;
import cn.itxinmeng.utils.HtmlUtils;
import cn.itxinmeng.utils.HttpUtils;

import java.util.Map;

/**
 * 心梦
 * 爬取职位详情
 */
public class DetailsThread extends Thread {

    private Map<String, Boolean> urlList;
    private Page page;
    Xpath xpath = new Xpath();
    IStore store = new DefaultStoreImpl();


    public void setUrlList(Map<String, Boolean> urlList) {
        this.urlList = urlList;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    @Override
    public void run() {

        xpath.setPosition("//div[@class='name']/text()");
        xpath.setPlace("//p/text()");
        xpath.setDuty("//div[@class='job-sec']/div[@class='text']/text()");//职位职责

        page = new Page();

        for (Map.Entry<String, Boolean> list : urlList.entrySet()) {

            //如果没有被遍历过
            if (!list.getValue()) {
                page.setPageContent(HttpUtils.getDataByUrl(list.getKey()));
                page = HtmlUtils.getValueByXpath(xpath, page);
                urlList.put(list.getKey(), true);
                page.setUrl(list.getKey());
                store.store(page);
            }
        }

    }


}

