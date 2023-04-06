package capstone_project.ReadingForum_Backend.Controller;

import capstone_project.ReadingForum_Backend.Model.Book;
import capstone_project.ReadingForum_Backend.Model.User;
import capstone_project.ReadingForum_Backend.Service.IBookService;
import capstone_project.ReadingForum_Backend.Service.ITagService;
import capstone_project.ReadingForum_Backend.Service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/recommend")
public class RecommendController {
    @Autowired
    IUserService userService;
    @Autowired
    IBookService bookService;
    @Autowired
    ITagService tagService;

    @GetMapping("/recommendByFavourite")
    public Result getRecommendByFavourite(@RequestHeader("token") String token) {
        try {
            String username = JWT.parseToken(token);
            User user = userService.selectByUsername(username);
            List<Map> topTenFavouriteTag = tagService.orderTopTenFavouriteTagById(user.getId());
            List<Book> bookList = bookService.selectTopFiveByTag(Integer.parseInt(topTenFavouriteTag.get(0).get("id").toString()));
            for (int i=0; i<bookList.size(); i++) {
                int id = bookList.get(i).getId();
                bookList.get(i).setTags(tagService.selectByBook(id));
            }
            Result result = new Result();
            result.setCode(1);
            result.setMessage("成功！");
            Map map = new HashMap<String, Object>();
            map.put("bookList", bookList);
            result.setData(map);
            return result;
        } catch (Exception e) {
            Result result = new Result();
            result.setMessage("程序异常，请重试！");
            return result;
        }
    }
}
