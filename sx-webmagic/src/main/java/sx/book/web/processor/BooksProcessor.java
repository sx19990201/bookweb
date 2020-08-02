package sx.book.web.processor;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import sx.book.web.ApplicationContextProvider;
import sx.book.web.mapper.master.BooksMasterMapper;
import sx.book.web.pojo.BookType;
import sx.book.web.pojo.Books;
import sx.book.web.pojo.Chapter;
import sx.book.web.service.master.BookTypeMasterService;
import sx.book.web.service.master.BooksMasterService;
import sx.book.web.service.master.ChapterMasterService;
import sx.book.web.service.slave.BookTypeSlaveService;
import sx.book.web.service.slave.BooksSlaveService;
import sx.book.web.service.slave.ChapterSlaveService;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

public class BooksProcessor implements PageProcessor {

   /* @Autowired
    private BooksMasterService booksMasterService;
    @Autowired
    private BooksSlaveService booksSlaveService;
    @Autowired
    private BookTypeMasterService bookTypeMasterService;
    @Autowired
    private BookTypeSlaveService bookTypeSlaveService;
    @Autowired
    private ChapterMasterService chapterMasterService;
    @Autowired
    private ChapterSlaveService chapterSlaveService;*/

    BooksMasterService booksMasterService = ApplicationContextProvider
            .getApplicationContext().getBean(BooksMasterService.class);

    BookTypeMasterService bookTypeMasterService = ApplicationContextProvider
            .getApplicationContext().getBean(BookTypeMasterService.class);

    ChapterMasterService chapterMasterService = ApplicationContextProvider
            .getApplicationContext().getBean(ChapterMasterService.class);

    BooksSlaveService booksSlaveService = ApplicationContextProvider
            .getApplicationContext().getBean(BooksSlaveService.class);

    BookTypeSlaveService bookTypeSlaveService = ApplicationContextProvider
            .getApplicationContext().getBean(BookTypeSlaveService.class);

    ChapterSlaveService chapterSlaveService = ApplicationContextProvider
            .getApplicationContext().getBean(ChapterSlaveService.class);


    //设置爬取网站的配置，包括：编码、抓取时间、重试次数、等等
    private Site site = Site.me()
            .setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36")  //模拟用户代理
            .setCharset("GB2312")
            .setCycleRetryTimes(1000)
            .setSleepTime(500)
            .setRetrySleepTime(10);

    //排行榜连接
    private static final String TOP_URL = "https://www.52bqg.net\\/paihangbang\\/";
    //排行榜页面内所有小说的链接
    private static final String TOP_BOOK_URL = "htt(p|ps)://www.52bqg.net\\/book\\_[0-9]{1,12}\\/";
    //章节目录url
    private static final String BOOK_MODULE_URL = "[0-9]{1,12}.html";
    //章节详细页内所有章节的url    [0-9]{1,13}.html
    private static final String BOOK_CHAPTER_URL = TOP_BOOK_URL + BOOK_MODULE_URL;

    /**
     * 定义完需要爬取的连接后，就可以制定爬取逻辑了
     *
     * @param page
     */
    @Override
    public void process(Page page) {

        //进入小说页面，在该页面发现章节url   小说详情页，可以发现章节url
        if (page.getUrl().regex(TOP_URL).match()) {
            FirstURL(page);
        } else if (page.getUrl().regex(TOP_BOOK_URL).match()) {
            BookUrl(page);
        }
    }

    //排行榜页面
    private void FirstURL(Page page) {

        try {
            //获取当前的url
            String link = page.getUrl().get();
            //解析HTML（由于该网站的8个分类div好像是动态的，只能解析该页面，然后挨个遍历这8个div节点）
            Document doc = Jsoup.connect(link).get();
            //获取小说排行榜的分类div
            Elements typeDiv = doc.getElementsByClass("tbo");
            //遍历这些分类div
            for (Element typediv : typeDiv) {
                //获取分类名所在的节点
                Elements typeNodo = typediv.select("span.btitle");
                //通过分类名节点获取分类名
                String typeName = typeNodo.text();
                typeName = typeName.substring(0,2);
                BookType bookType = new BookType();
                bookType.setTypeName(typeName);

                //提取该分类名，并查询数据库，如果数据库没有该条数据则添加
                QueryWrapper<BookType> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("typeName", typeName);
                BookType booktype = bookTypeSlaveService.getOne(queryWrapper);
                if (booktype == null || booktype.getTypeName()!="全本" || booktype.getTypeName()!="所有") {
                    bookTypeMasterService.insertBookType(bookType);
                }
                //根分类名查询id
                Integer typeId = bookTypeSlaveService.selectIdByName(typeName);
                //获取分类名成功后接着获取该分类下的小说节点
                Elements bookNode = typediv.select("li a");
                for (Element booknode : bookNode) {
                    String bookUrl = booknode.attr("href");
                    //将该书的分类命加入入请求中
                    //Request request = new Request(bookUrl).putExtra("typeName", typeName);
                    Request request = new Request(bookUrl).putExtra("typeId", typeId);
                    //将该请求放入爬取队列中
                    page.addTargetRequest(request);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 处理小说详细页
     *
     * @param page
     */
    private void BookUrl(Page page) {


        try {
            //解析小说详细页HTML
            Document doc = Jsoup.connect(page.getUrl().get()).get();
            //作者
            String bookAuthor = page.getHtml().xpath("//*[@id=\"info\"]/p[1]/a/text()").get();
            //简介
            String info = page.getHtml().xpath("//*[@id=\"intro\"]/text()").get();
            //书名
            String bookName = page.getHtml().xpath("//*[@id=\"info\"]/h1/text()").get();
            //该书链接
            String bookURL = page.getUrl().get();

            //图片url
            String imgURL = page.getHtml().xpath("//*[@id=\"fmimg\"]/img/@src").get();
           /* System.out.println(imgURL);*/
            //该书所属的分类id
            Integer typeId =Integer.parseInt(page.getRequest().getExtra("typeId").toString());

            Books book = new Books();
            book.setAuthor(bookAuthor);
            book.setBookURL(bookURL);
            book.setBookName(bookName);
            book.setInfo(info);
            book.setTypeId(typeId);
            book.setImgURL(imgURL);

            QueryWrapper<Books> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("bookName", bookName);
            if (booksSlaveService.getOne(queryWrapper) == null) {
                booksMasterService.insertBooks(book);
            }

            //获取保存章节的div列表
            Element listDiv = doc.getElementById("list");
            //获取章节列表内所有的章节节点
            Elements chapterList = listDiv.select("dl dd a");
            //遍历章节节点提取信息
            for (Element chapterElement : chapterList){
                //章节url
                String chapterUrl = chapterElement.attr("href");
                //章节名
                String chapterName = chapterElement.text();
                Chapter chapter = new Chapter();
                //根据小说名查找id
                /*Integer bookId = booksSlaveService.selectIdByName(bookName);*/
                chapter.setBookId(booksSlaveService.selectIdByName(bookName));
                chapter.setChapterName(chapterName);
                chapter.setChapterURL(bookURL+chapterUrl);
                //排序字段，章节url是由章节顺序123456.html这样的顺序排的所以直接截取到章节url并去掉后缀就是它的排序顺序了
                chapter.setOrderNum(Integer.parseInt(chapterUrl.substring(0,chapterUrl.indexOf("."))));
                QueryWrapper<Chapter> chapterQueryWrapper = new QueryWrapper<>();
                chapterQueryWrapper.eq("chapterName",chapterName);
                if (chapterSlaveService.getOne(chapterQueryWrapper)==null){
                    chapterMasterService.insertChapter(chapter);
                }


            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Override
    public Site getSite() {
        return site;
    }


}
