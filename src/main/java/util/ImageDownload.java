package util;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

public class ImageDownload {



    public static void main(String[] args) throws Exception {
        ExcelReader reader = ExcelUtil.getReader("D:\\Users\\Desktop\\taobaoimage.xls");
        List<List<Object>> readAll = reader.read();
//        System.out.println(readAll.size());
//        System.out.println(readAll.get(1));

        for(int i = 0; i < readAll.size(); ++i) {
            Object uri = readAll.get(i).get(0);
            Object id = readAll.get(i).get(1);
//            System.out.println(uri + "  " + id);
            try {
                download(uri.toString(), "F:/testimage/"+id.toString()+".jpg");
            }  catch (Exception e) {
                System.out.println("没有图片"+ "   "+uri+   "   " +id +"   " + i);
            }
        }

    }

    public static void download(String urlString, String filename) throws Exception {
        // 构造URL
        URL url = new URL(urlString);
        // 打开连接
        URLConnection con = url.openConnection();
        // 输入流
        InputStream is = con.getInputStream();
        // 1K的数据缓冲
        byte[] bs = new byte[1024];
        // 读取到的数据长度
        int len;
        // 输出的文件流
        OutputStream os = new FileOutputStream(filename);
        // 开始读取
        while ((len = is.read(bs)) != -1) {
            os.write(bs, 0, len);
        }
        // 完毕，关闭所有链接
        os.close();
        is.close();
    }

}
