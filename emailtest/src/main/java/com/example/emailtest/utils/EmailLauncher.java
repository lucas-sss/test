package com.example.emailtest.utils;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Date;
import java.util.Properties;

/**
 * @author liuwei 1215946336@qq.com
 * @version 1.0
 * @date 2018/4/21 0021
 */
public class EmailLauncher {

    public static String USERNAME = "lyy9645@163.com";
    public static String PWD = "liuwei0605";


    public static void send(String emailCOntent) throws Exception {

        Properties props = new Properties();
        // 使用的协议（JavaMail规范要求）
        props.setProperty("mail.transport.protocol", "smtp");
        // 发件人的邮箱的 SMTP 服务器地址
        props.setProperty("mail.smtp.host", "smtp.163.com");
        props.setProperty("mail.smtp.port", "25");
        // 需要请求认证
        props.setProperty("mail.smtp.auth", "true");
        // 根据配置创建会话对象, 用于和邮件服务器交互
        Session session = Session.getDefaultInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME, PWD);
            }
        });

//        session.setDebug(true);

        MimeMessage message = new MimeMessage(session);
        String nick = javax.mail.internet.MimeUtility.encodeText("卢卡斯");
        message.setFrom(new InternetAddress(nick + " <" + USERNAME + ">"));
        String[] receiveMail = new String[]{"1215946336@qq.com", "xiaokedou18@aliyun.com", "xiaokedou18@163.com", "liuwei@flksec.com"};
        Address[] addr = new Address[receiveMail.length];
        for (int i = 0; i < receiveMail.length; i++) {
            addr[i] = new InternetAddress(receiveMail[i]);
        }
        message.addRecipients(MimeMessage.RecipientType.TO, addr);
        message.setSubject("测试图片附件", "UTF-8");

        MimeBodyPart image = new MimeBodyPart();
        DataHandler dh = new DataHandler(new FileDataSource("F:\\ChromeDownload\\11.jpg"));  //读取本地文件
        image.setDataHandler(dh);   //将数据添加到节点
        image.setContentID("image_id");// 为“节点”设置一个唯一编号（在文本“节点”将引用该ID）


        //创建文本节点
        MimeBodyPart text = new MimeBodyPart();
        //将图片包含到文本内容中
        text.setContent(emailCOntent, "text/html;charset=UTF-8");


        //将文本和图片节点结合
        MimeMultipart text_image = new MimeMultipart();
        text_image.addBodyPart(text);
        text_image.addBodyPart(image);
        text_image.setSubType("related"); //关联关系 有内嵌资源要定义related

        //将混合节点封装成普通节点BodyPart,邮件最终由多个BodyPart组成
        MimeBodyPart text_image_body = new MimeBodyPart();
        text_image_body.setContent(text_image);


        //添加附件节点
        MimeBodyPart document = new MimeBodyPart();
        DataHandler dhdoc = new DataHandler(new FileDataSource("f:\\jfinal_log.txt")); //读取本地文档
        document.setDataHandler(dhdoc);   //将附件数据添加到节点
        document.setFileName(MimeUtility.decodeText(dhdoc.getName())); //设置附件文件名

        //设置文本和图片，附件的关系（混合大节点）
        MimeMultipart mm = new MimeMultipart();
        mm.addBodyPart(text_image_body);
        mm.addBodyPart(document);
        mm.setSubType("mixed");  //有附件资源要定义mixed

        //最终节点添加到邮件中
        message.setContent(mm);
        message.setSentDate(new Date());
        message.saveChanges();


        try {
            Transport.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
