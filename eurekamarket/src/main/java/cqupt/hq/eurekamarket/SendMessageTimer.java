package cqupt.hq.eurekamarket;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SendMessageTimer {
	
	@Scheduled(cron="0 16 14 * * *")
	public void scheduledTask(){
		System.out.println("sssssssssssss");
	}
}
//	public static void main(String[] args) {  
//		String time="11:39:00";
//		String[] s=time.split(":");
//		int[]  i=new int[3];
//		for(int ii=0;ii<s.length;ii++){
//			i[ii]=Integer.parseInt(s[ii]);
//		}
//		Calendar calendar = Calendar.getInstance();
//        
//        /**
//         * 指定触发的时间      现在指定时间为  time  秒时触发
//         */
////      calendar.set(Calendar.DAY_OF_MONTH,27);//设置日期为27号
////      calendar.set(Calendar.MONTH, 10);//设置日期为11月份   这里10表示11月份    11就表示12月份
//        calendar.set(Calendar.HOUR_OF_DAY, i[0]); //设置15点的时候触发
//        calendar.set(Calendar.MINUTE,i[1] ); //设置43分钟的时候触发
//        calendar.set(Calendar.SECOND, i[2]); //设置第一秒的时候触发
//        
//        Date times = calendar.getTime();
//        
//        
//		Timer timer = new Timer();  
//        timer.schedule(new MyTask(),times); 
//	}
//		
//}
//class MyTask extends TimerTask{
//	@Override
//	public void run() {
//		System.out.println("sssssssssssssssss");
//	}
	

