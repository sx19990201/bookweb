package sx.book.web.scheduing;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import sx.book.web.processor.BooksProcessor;
import sx.book.web.scheduler.SpikeFileCacheQueueScheduler;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.monitor.SpiderMonitor;
import us.codecraft.webmagic.pipeline.ConsolePipeline;

import javax.management.JMException;

@Component
public class WebMagicScheduling {
    @Scheduled(cron = "1 37/59 * * * ?")
    public void process(){
        //SpikeFileCacheQueueScheduler file = new SpikeFileCacheQueueScheduler("https://www.52bqg.net/paihangbang/");
        Spider spider = Spider.create(new BooksProcessor())
                //.addPipeline(new MysqlPipeline())
                .addPipeline(new ConsolePipeline())
                .addUrl("https://www.52bqg.net/paihangbang/")
                .thread(250);
                //.start();
        try {
            SpiderMonitor.instance().register(spider);
        } catch (JMException e) {
            e.printStackTrace();
        }
        spider.start();


        //判断爬虫是否已经结束  init初始化，running运行中，stopped结束
       /* if (spider.getStatus().equals(Spider.Status.Stopped)) {
            //获取全局的list，
            BookUtilList bookUtilList =
                    ApplicationContextProvider1.getApplicationContext().getBean(BookUtilList.class);
            //
            bookUtilList.getBooksList();
            bookUtilList.getBookTypeList();
        }*/
    }

   /* @Scheduled(cron = "1 54/59 * * * ?")
    public void process(){
        System.out.println("ahha");
    }*/
}
