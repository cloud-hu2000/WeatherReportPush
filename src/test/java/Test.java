import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.tea.*;
import com.aliyun.dysmsapi20170525.*;
import com.aliyun.dysmsapi20170525.models.*;
import com.aliyun.teaopenapi.*;
import com.aliyun.teaopenapi.models.*;
public class Test {
    private static String[] getAccessKey() throws IOException {
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

    public static Client createClient(String accessKeyId, String accessKeySecret) throws Exception {
        Config config = new Config()
                // 您的AccessKey ID
                .setAccessKeyId(accessKeyId)
                // 您的AccessKey Secret
                .setAccessKeySecret(accessKeySecret);
        // 访问的域名
        config.endpoint = "dysmsapi.aliyuncs.com";
        return new com.aliyun.dysmsapi20170525.Client(config);
    }
    public static void main(String[] args_) throws Exception {
        //获取阿里云短信服务
        System.out.println(getAccessKey()[0]);
        System.out.println("LTAI5t7f4zZpkerVSk1pyA8i");
        Client client = createClient(getAccessKey()[0], getAccessKey()[1]);
        SendSmsRequest sendSmsRequest = new SendSmsRequest()
                .setSignName("倢云桑的小世界")
                .setTemplateCode("SMS_238472382")
                .setPhoneNumbers("")
                .setTemplateParam("{\"city\":\""+"昆明"+"\",\"kind\":\""+"kind"+"\",\"temperature\":\""+"temperature"+"\"}");
        // 复制代码运行请自行打印 API 的返回值
        client.sendSms(sendSmsRequest);
    }



    public static void showDayTime() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.set(year, month, day, 00, 28, 00);//设置执行时间
        Date defaultdate =calendar.getTime();
        if (defaultdate.before(new Date())) {
            // 将发送时间设为明天
            calendar.add(Calendar.DATE, 1);
            defaultdate = calendar.getTime();
        }
        Timer dTimer = new Timer();
        Date finalDefaultdate = defaultdate;
        dTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("当前执行时间"+ finalDefaultdate);
            }
        }, defaultdate , 24* 60* 60 * 1000);// 24* 60* 60 * 1000  24小时
    }
    @org.junit.Test
    public void tetst() throws IOException {

    }
}
