package com.example.emailtest.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liuwei 1215946336@qq.com
 * @version 1.0
 * @date 2018/4/21 0021
 */
public class EmailHtmlUtil {

    private static Configuration conf;
    private static String ROOT_PATH;

    static {
        try {
            File file = ResourceUtils.getFile("classpath:ftl");
            ROOT_PATH = file.getParentFile().getPath();
            conf = new Configuration(Configuration.VERSION_2_3_23);
            conf.setDefaultEncoding("utf-8");
            conf.setDirectoryForTemplateLoading(file);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String test() {
        Writer writer = null;
        BufferedReader reader = null;
        Long time = System.currentTimeMillis();
        File temp = new File(ROOT_PATH + "/temp/" + time + ".html");
        if (!temp.getParentFile().exists()){
            temp.getParentFile().mkdirs();
        }
        try {
            Template template = conf.getTemplate("email.ftl");
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(temp), "UTF-8"));

            Map<String, Object> rootMap = new HashMap<>();
            rootMap.put("name","刘伟");
            rootMap.put("content","上一篇文章介绍了如何进行简单文本邮件的传输，相信阅读的人都能看懂并收获不少。其实编程这件事情，很多时候就是你听起来一个功能的实现好像很难，但当你着手去实现，去研究他的实现过程的时候就会发现其实也没有想象中那么难。接下来给大家介绍一下如何实现复杂邮件的传输。网上有很多教程，参考了不少，选了比较简洁且容易理解的代码共享出来，以便大家分享。ps:下面代码都是经过自己亲测可行！");
            template.process(rootMap, writer);

            reader = new BufferedReader(new FileReader(temp));
            StringBuilder emailContent = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null){
                emailContent.append(line);
            }
            return emailContent.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally {
            try {
                if (writer != null){
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (reader != null){
                    reader.close();
                }
            }catch (IOException e) {
                e.printStackTrace();
            }
            if (temp.exists()){
                temp.delete();
            }
        }
    }


}
