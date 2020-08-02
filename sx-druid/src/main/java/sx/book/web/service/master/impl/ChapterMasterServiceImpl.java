package sx.book.web.service.master.impl;


import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sx.book.web.mapper.master.ChapterMasterMapper;
import sx.book.web.pojo.Chapter;
import sx.book.web.service.master.ChapterMasterService;



@Service
@DS("master")
public class ChapterMasterServiceImpl extends ServiceImpl<ChapterMasterMapper, Chapter> implements ChapterMasterService {

    @Autowired
    private ChapterMasterMapper chapterMasterMapper;

    @Override
    public void insertChapter(Chapter chapter) {
        chapterMasterMapper.insertChapter(chapter);
    }
}
