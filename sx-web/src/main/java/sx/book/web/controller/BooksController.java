package sx.book.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sx.book.web.mapper.master.BooksMasterMapper;
import sx.book.web.pojo.Books;
import sx.book.web.service.master.BooksMasterService;
import sx.book.web.service.slave.BooksSlaveService;


import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BooksController {

    @Autowired
    private BooksMasterService booksMasterService;
    @Autowired
    private BooksSlaveService booksSlaveService;


    /**
     * 通过分类名查询该分类对应的所有小说
     * @param typeId
     * @return
     */
    @CrossOrigin    //该注解处理跨域请求
    @GetMapping("/getBookByTypeName/{typeId}")
    public IPage<Books> findgetBookByTypeName(@PathVariable("typeId")Integer typeId,
                                             @RequestParam("index")Integer index,
                                             @RequestParam("pageSize")Integer pageSize){
        Integer count = booksSlaveService.count();
        IPage<Books> page = new Page<>(index,pageSize,count);
        if (typeId==0||typeId.equals("0")||typeId.equals("")||typeId==null){
            return booksSlaveService.page(page);
        }else{
            QueryWrapper<Books> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("typeId",typeId);
            return booksSlaveService.page(page,queryWrapper);
           /* return booksSlaveService.list(queryWrapper);*/
        }
    }

    /**
     * 通过分类名查询该分类对应的所有小说
     * @return
     */
    @CrossOrigin    //该注解处理跨域请求
    @GetMapping("/getBooksAll")
    public IPage<Books> findBooks(){
        Integer count = booksSlaveService.count();
        IPage<Books> page = new Page<>(1,5,count);
        return booksSlaveService.page(page);
    }

    @CrossOrigin    //该注解处理跨域请求
    @GetMapping("/getBookById/{id}")
    public Books findBookById(@PathVariable("id")Integer id){

        QueryWrapper<Books> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",id);
        return booksSlaveService.getOne(queryWrapper);
    }

}
