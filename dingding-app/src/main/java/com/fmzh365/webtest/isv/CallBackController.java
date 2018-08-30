package com.fmzh365.webtest.isv;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dingtalk.oapi.lib.aes.DingTalkEncryptException;
import com.dingtalk.oapi.lib.aes.DingTalkEncryptor;
import com.dingtalk.oapi.lib.aes.Utils;
import com.fmzh365.webtest.config.Constant;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

/**
 * @author liuwei liuwei@flksec.com
 * @version 1.0
 * @date 2018/8/27
 */
@Controller
@RequestMapping("/callback")
public class CallBackController {


    /**
     * 创建套件后，验证回调URL创建有效事件（第一次保存回调URL之前）
     */
    private static final String EVENT_CHECK_CREATE_SUITE_URL = "check_create_suite_url";
    /**
     * 创建套件后，验证回调URL变更有效事件（第一次保存回调URL之后）
     */
    private static final String EVENT_CHECK_UPADTE_SUITE_URL = "check_update_suite_url";

    /**
     * suite_ticket推送事件
     */
    private static final String EVENT_SUITE_TICKET = "suite_ticket";
    /**
     * 企业授权开通应用事件
     */
    private static final String EVENT_TMP_AUTH_CODE = "tmp_auth_code";
    /**
     * 相应钉钉回调时的值
     */
    private static final String CALLBACK_RESPONSE_SUCCESS = "success";

    @RequestMapping(value = "/do", method = RequestMethod.POST)
    @ResponseBody
    public Object handler(String signature, String timestamp, String nonce,
                          @RequestBody(required = false) JSONObject json) {

        DingTalkEncryptor dingTalkEncryptor;
        String params = " signature:" + signature + " timestamp:" + timestamp + " nonce:" + nonce + " json:" + json;
        try {

            dingTalkEncryptor = new DingTalkEncryptor(Constant.TOKEN, Constant.ENCODING_AES_KEY,
                    Constant.SUITE_KEY);

            //从post请求的body中获取回调信息的加密数据进行解密处理
            String encryptMsg = json.getString("encrypt");
            String plainText = dingTalkEncryptor.getDecryptMsg(signature, timestamp, nonce, encryptMsg);
            JSONObject obj = JSON.parseObject(plainText);


            //根据回调数据类型做不同的业务处理
            String eventType = obj.getString("EventType");
            if (EVENT_CHECK_CREATE_SUITE_URL.equals(eventType)) {
                System.out.println("验证新创建的回调URL有效性: " + plainText);
            } else if (EVENT_CHECK_UPADTE_SUITE_URL.equals(eventType)) {
                System.out.println("验证更新回调URL有效性: " + plainText);
            } else if (EVENT_SUITE_TICKET.equals(eventType)) {
                //suite_ticket用于用签名形式生成accessToken(访问钉钉服务端的凭证)，需要保存到应用的db。
                //钉钉会定期向本callback url推送suite_ticket新值用以提升安全性。
                //应用在获取到新的时值时，保存db成功后，返回给钉钉success加密串（如本demo的return）
                System.out.println("应用suite_ticket数据推送: " + plainText);
            } else if (EVENT_TMP_AUTH_CODE.equals(eventType)) {
                //本事件应用应该异步进行授权开通企业的初始化，目的是尽最大努力快速返回给钉钉服务端。用以提升企业管理员开通应用体验
                //即使本接口没有收到数据或者收到事件后处理初始化失败都可以后续再用户试用应用时从前端获取到corpId并拉取授权企业信息，
                // 进而初始化开通及企业。
                System.out.println("企业授权开通应用事件: " + plainText);
            } else {
                // 其他类型事件处理
            }

            // 返回success的加密信息表示回调处理成功
            return dingTalkEncryptor.getEncryptedMap(CALLBACK_RESPONSE_SUCCESS, System.currentTimeMillis(), Utils.getRandomStr(8));
        } catch (DingTalkEncryptException e) {
            //失败的情况，应用的开发者应该通过告警感知，并干预修复
            System.out.println("process callback failed！" + params);
            return null;
        }
        /*HashMap result = new HashMap<String, String>();
        result.put("success", "test success");
        return result;*/
    }
}
