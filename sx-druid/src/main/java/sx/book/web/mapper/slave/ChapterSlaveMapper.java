package sx.book.web.mapper.slave;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import sx.book.web.pojo.Chapter;


import java.util.List;

@Mapper
public interface ChapterSlaveMapper extends BaseMapper<Chapter> {

   List<Chapter> selectByChapterWrapper(@Param(Constants.WRAPPER) Wrapper<Chapter> chapterWrapper);

}
