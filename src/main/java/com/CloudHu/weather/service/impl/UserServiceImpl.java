package com.CloudHu.weather.service.impl;

import com.CloudHu.weather.entity.User;
import com.CloudHu.weather.mapper.UserMapper;
import com.CloudHu.weather.service.UserService;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

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

    public int addUser(Map<String, String> map) throws IOException {
        String phone = map.get("phone").toString();
        String province = map.get("province").toString();
        String city = map.get("city").toString();
        System.out.println(phone);
        System.out.println(province);
        System.out.println(city);
        User user = new User(phone,province,city);
        int result = userMapper.addUser(user);
//        province=getPingYin(province);
//        city=getPingYin(city);
//
//        String urlStr = "https://tianqi.moji.com/weather/china/"+province+"/"+city; // 网址
//
//        //获取MoreActivity的三个tab的数据
//        Document doc;
//        doc = Jsoup.connect(urlStr).get();
//        List<String> forecast= doc.getElementsByClass("days clearfix").eachText();
//        String [] messages=forecast.get(1).split(" ");
//        String kind=messages[1];
//        String temperature=messages[2]+messages[3]+messages[4];
//
//        result.put("url",urlStr);
//        result.put("phone",phone);
//        result.put("kind",kind);
//        result.put("temperature",temperature);

        return result;
    }
}
