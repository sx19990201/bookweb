package sx.book.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sx.book.web.pojo.Chapter;
import sx.book.web.service.master.ChapterMasterService;
import sx.book.web.service.slave.ChapterSlaveService;


import java.util.List;

@RestController
@RequestMapping("/api/chapter")
public class ChapterController {

    @Autowired
    private ChapterMasterService chapterMasterService;
    @Autowired
    private ChapterSlaveService chapterSlaveService;

    /**
     * 根据书名查找该书对应的章节
     * @param bookId
     * @return
     */
    @CrossOrigin
    @GetMapping
    @RequestMapping("/getChapterByBookName/{bookId}")
    public List<Chapter> getChapter(@PathVariable("bookId")Integer bookId){
        QueryWrapper<Chapter> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("bookId",bookId);
        return chapterSlaveService.list(queryWrapper);
    }
}
