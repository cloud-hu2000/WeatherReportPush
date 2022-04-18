package com.CloudHu.weather.service.impl;

import com.CloudHu.weather.dto.Result;
import com.CloudHu.weather.entity.UserWeather;
import com.CloudHu.weather.mapper.AdminMapper;
import com.CloudHu.weather.service.AdminService;
import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.teaopenapi.models.Config;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;
import java.util.Map;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper userMapper;

    public static String getPingYin(String src) {

        char[] t1 = null;
        t1 = src.toCharArray();
        String[] t2 = new String[t1.length];
        HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();

        t3.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        t3.setVCharType(HanyuPinyinVCharType.WITH_V);
        String t4 = "";
        int t0 = t1.length;
        try {
            for (int i = 0; i < t0; i++) {
                // 判断是否为汉字字符
                if (Character.toString(t1[i]).matches(
                        "[\\u4E00-\\u9FA5]+")) {
                    t2 = PinyinHelper.toHanyuPinyinStringArray(t1[i], t3);
                    t4 += t2[0];
                } else
                    t4 += Character.toString(t1[i]);
            }
            // System.out.println(t4);
            return t4;
        } catch (BadHanyuPinyinOutputFormatCombination e1) {
            e1.printStackTrace();
        }
        return t4;
    }

    public Result<Integer> addUserWeather(Map<String, String> map) throws IOException {
        String phone = map.get("phone").toString();
        String province = map.get("province").toString();
        String city = map.get("city").toString();


        UserWeather user = new UserWeather(phone,province,city);
        int res = userMapper.addUserWeather(user);

        if(res==1){
            Result<Integer>result = new Result<>(true,res);
            return result;
        }
        return new Result<>(false,"addUserWeatherFail");
    }

    public List<UserWeather> getAllUserWeather(){
        return userMapper.getAllUserWeather();
    }

    private static Client createClient(String accessKeyId, String accessKeySecret) throws Exception {
        Config config = new Config()
                // 您的AccessKey ID
                .setAccessKeyId(accessKeyId)
                // 您的AccessKey Secret
                .setAccessKeySecret(accessKeySecret);
        // 访问的域名
        config.endpoint = "dysmsapi.aliyuncs.com";
        return new com.aliyun.dysmsapi20170525.Client(config);
    }


    private String[] getAccessKey() throws IOException {
        BufferedReader br = new BufferedReader( new InputStreamReader(new FileInputStream("H:\\Win10桌面美化\\桌面图标\\File\\aliyun.csv")));

        String line;
        String AccessKeyId;
        String AccessKeySecret;
        String[] info;
        //因为数据在第二行所以要读两遍
        line=br.readLine();
        line=br.readLine();

        info = line.split(",");
        for (int i = 0;i< info.length;i++) {
            String s = info[i];
            s=s.substring(0,s.length()-1);
            s=s.substring(1,s.length());
            info[i]=s;
        }
        return info;
    }


    @Override
    //获取天气信息并发送
    public void sendWeather() throws Exception {

        List<UserWeather> allUserWeather = this.getAllUserWeather();
        for (UserWeather userWeather : allUserWeather) {
            String phone = userWeather.getPhone();
            String province = userWeather.getProvince();
            String city = userWeather.getCity();
            province = getPingYin(province);
            city = getPingYin(city);

            String urlStr = "https://tianqi.moji.com/weather/china/"+province+"/"+city; // 网址

            //获取MoreActivity的三个tab的数据
            Document doc;
            doc = Jsoup.connect(urlStr).get();
            List<String> forecast= doc.getElementsByClass("days clearfix").eachText();
            String [] messages=forecast.get(1).split(" ");
            //发送信息所要的三个属性
            city = userWeather.getCity();
            String kind=messages[1];
            String temperature=messages[2]+messages[3]+messages[4];

            //获取阿里云短信服务
            Client client = createClient(getAccessKey()[0], getAccessKey()[1]);
            SendSmsRequest sendSmsRequest = new SendSmsRequest()
                    .setSignName("倢云桑的小世界")
                    .setTemplateCode("SMS_238472382")
                    .setPhoneNumbers(phone)
                    .setTemplateParam("{\"city\":\""+city+"\",\"kind\":\""+kind+"\",\"temperature\":\""+temperature+"\"}");
            // 复制代码运行请自行打印 API 的返回值
            client.sendSms(sendSmsRequest);
        }



    }


}
