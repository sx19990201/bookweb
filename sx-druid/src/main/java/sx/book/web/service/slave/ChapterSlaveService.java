package sx.book.web.service.slave;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import sx.book.web.pojo.Chapter;

import java.util.List;

public interface ChapterSlaveService extends IService<Chapter> {

    @Override
    List<Chapter> list(Wrapper<Chapter> wrapper);
}
