package cn.itxinmeng.store.impl;

import cn.itxinmeng.pojo.Page;
import cn.itxinmeng.store.IStore;

import java.io.FileWriter;
import java.io.IOException;

/**
 * 心梦
 * 默认存储实现类
 * 数据默认存储路径  result/xinmeng.txt
 */
public class DefaultStoreImpl implements IStore<Page> {
    @Override
    public void store(Page page) {
        System.out.println(page);
        try {
            FileWriter file = new FileWriter(System.getProperty("user.dir") + "/src/main/java/result/xinmeng.txt", true);
            file.write(page.toString() + "\n");

            file.flush();
            file.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
