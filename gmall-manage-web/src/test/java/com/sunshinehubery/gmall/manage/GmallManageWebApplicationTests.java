package com.sunshinehubery.gmall.manage;

import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GmallManageWebApplicationTests {

    @Test
    public void contextLoads() throws IOException, MyException {
        //配置fdfs全局链接地址
        String tracker = GmallManageWebApplicationTests.class.getResource("/tracker.conf").getPath();//获取配置文件的路径
        ClientGlobal.init(tracker);
        TrackerClient trackerClient = new TrackerClient();
        //获取一个TrackerServer实例
        TrackerServer trackerServer = trackerClient.getConnection();
        //通过trackerServer获取一个Storage链接的客户端
        StorageClient storageClient = new StorageClient(trackerServer,null);
        String[] upload_file = storageClient.upload_file("C:/Users/Administrator//Pictures/Saved Pictures/a.jpg", "jpg", null);//最后一个参数list表示文件元数据（像素，大小）
        String url = "http://192.168.241.130";
        for (String s : upload_file) {
            url+="/"+s;
        }
        System.out.println(url);
    }

}
