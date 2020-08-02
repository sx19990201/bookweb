package sx.book.web.mapper.master;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import sx.book.web.pojo.Chapter;

@Mapper
public interface ChapterMasterMapper extends BaseMapper<Chapter> {

    void insertChapter(@Param("chapter") Chapter chapter);

}
