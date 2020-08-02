package sx.book.web.service.slave.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import sx.book.web.mapper.master.ChapterMasterMapper;
import sx.book.web.pojo.Chapter;
import sx.book.web.service.slave.ChapterSlaveService;


import java.util.List;

@Service
@DS("slave")
public class ChapterSlaveServiceImpl extends ServiceImpl<ChapterMasterMapper, Chapter> implements ChapterSlaveService {

    @Override
    public List<Chapter> list(Wrapper<Chapter> queryWrapper) {
        return super.list(queryWrapper);
    }
}
