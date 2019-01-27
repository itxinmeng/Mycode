package cn.itxinmeng.utils;

import cn.itxinmeng.pojo.Page;
import cn.itxinmeng.pojo.Xpath;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;

import javax.xml.ws.handler.HandlerResolver;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**心梦
 * 解析网页的工具类
 */

public class HtmlUtils {

    //创建htmlCleaner对象
    private static HtmlCleaner htmlCleaner = new HtmlCleaner();
    //存放URL的map
    private static Map<String, Boolean> urlList = new LinkedHashMap();

    /**
     * @param html  页面 html
     * @param xpath xpath表达式
     * @return 最大页码
     */
    public static int getPageNumByHtml(String html, String xpath) {
        int pageNum = 1;

        TagNode tagNode = htmlCleaner.clean(html);

        try {
            Object[] obj = tagNode.evaluateXPath(xpath);
            pageNum = Integer.parseInt(obj[4].toString());

        } catch (XPatherException e) {
            e.printStackTrace();
        }

        return pageNum;

    }


    /**
     * 通过Xpath表达式解析页面内容
     *
     * @param xpath        Xpath表达式
     * @param downloadPage 解析的网页内容
     * @return
     */
    public static Map<String, Boolean> getValueByXpath(String xpath, Page downloadPage) {
        //首先获取下载后的网页内容
        String pageContent = downloadPage.getPageContent();

        TagNode rootNode = htmlCleaner.clean(pageContent);
        try {

            Object[] obj = rootNode.evaluateXPath(xpath);
            for (Object o : obj) {
                String href = (String) o;
                if (href.endsWith("html")) {
                    urlList.put("https://www.zhipin.com" + href, false);

                }
            }
        } catch (XPatherException e) {
            e.printStackTrace();
        }

        return urlList;
    }

    public static Page getValueByXpath(Xpath xpath, Page downloadPage) {

        Page page = new Page();
        try {
            TagNode rootNode = htmlCleaner.clean(downloadPage.getPageContent());

            Object[] duty = rootNode.evaluateXPath(xpath.getDuty());//职位描述
            page.setDuty(duty[0].toString().trim());

            Object[] position = rootNode.evaluateXPath(xpath.getPosition());
            page.setPositionAndSalary(position[0].toString());//获取薪资  职位
            page.setCompany(position[2].toString()); //获取 公司名称

            Object[] place = rootNode.evaluateXPath(xpath.getPlace());//薪资职位公司
            page.setRequirement(place[1].toString());//北京3-5年硕士


        } catch (XPatherException e) {
            e.printStackTrace();
        }
        return page;
    }


}
