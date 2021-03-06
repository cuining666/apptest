package com.cn.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataProviderFromCsv {

    /**
     * 作为数据驱动文件
     * @param fileNameRoot
     * @return
     * @throws IOException
     */
    public static Object[][] getTestData(String fileNameRoot) throws IOException {
        List<Object[]> records = new ArrayList<Object[]>();
        String record;
        //设定UTF-8字符集，使用带缓冲区的字符输入流BufferedReader读取文件内容
        BufferedReader file = new BufferedReader(new InputStreamReader(new FileInputStream(fileNameRoot),"UTF-8"));
        //忽略读取CSV文件的标题行
        file.readLine();
        //遍历读取文件中除第一行外的其他所有内容并存储在名为records的ArrayList中
        //每一行的records中存储的对象为一个String数组
        while((record = file.readLine()) != null) {
            String fields[] = record.split(",");
            records.add(fields);
        }
        //关闭文件对象
        file.close();
        //将存储测试数据的List转换为一个关于Object的二维数组
        Object[][] results = new Object[records.size()][];
        //设置二维数组每行的值，每行是一个Obejct对象
        for (int i = 0; i < records.size(); i++) {
            results[i] = records.get(i);
        }
        return results;
    }
}
