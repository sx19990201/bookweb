package sx.book.web.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("chapter")
public class Chapter extends Model<Chapter> {
    //章节id
    @TableId(value = "id",type = IdType.INPUT)
    private Integer id;
    //章节名
    private String chapterName;
    //章节url
    private String chapterURL;
    //章节内容
    //private String content;
    //小说id
    private Integer bookId;
    //排序
    private Integer orderNum;

    @Override
    protected Serializable pkVal() {
        return id;
    }

}
