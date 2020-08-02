package sx.book.web.service.master;

import com.baomidou.mybatisplus.extension.service.IService;
import sx.book.web.pojo.Chapter;

public interface ChapterMasterService extends IService<Chapter> {


    void insertChapter(Chapter chapter);
}
