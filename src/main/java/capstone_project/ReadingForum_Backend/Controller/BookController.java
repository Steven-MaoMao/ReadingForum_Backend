package capstone_project.ReadingForum_Backend.Controller;

import capstone_project.ReadingForum_Backend.Model.Book;
import capstone_project.ReadingForum_Backend.Model.User;
import capstone_project.ReadingForum_Backend.Service.IBookRateService;
import capstone_project.ReadingForum_Backend.Service.IBookService;
import capstone_project.ReadingForum_Backend.Service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private IUserService userService;
    @Autowired
    private IBookService bookService;
    @Autowired
    private IBookRateService bookRateService;

    @GetMapping("/bookInfoById")
    public Result getBookInfoById(@RequestParam("id") int id) {
        try {
            Book book = bookService.selectById(id);
            Result result = new Result();
            result.setCode(1);
            result.setMessage("获取书籍信息成功！");
            Map data = new HashMap<String, Object>();
            data.put("bookInfo", book);
            result.setData(data);
            return result;
        } catch (Exception e) {
            Result result = new Result();
            result.setMessage("获取书籍信息失败！");
            return result;
        }
    }

    @GetMapping("/bookRate")
    public Result getBookRate(@RequestHeader("token") String token, @RequestParam("bookId") int bookId) {
        try {
            String username = JWT.parseToken(token);
            int userId = userService.selectByUsername(username).getId();
            int myRate = bookRateService.selectRate(bookId, userId);
            int five = bookRateService.selectFive(bookId);
            int four = bookRateService.selectFour(bookId);
            int three = bookRateService.selectThree(bookId);
            int two = bookRateService.selectTwo(bookId);
            int one = bookRateService.selectOne(bookId);
            int all = five+four+three+two+one;
            float average = (float) (five*5+four*4+three*3+two*2+one)*5/(all*5);
            int fiveRate = five*100/all;
            int fourRate = four*100/all;
            int threeRate = three*100/all;
            int twoRate = two*100/all;
            int oneRate = one*100/all;
            Map data = new HashMap<String, Object>();
            data.put("myRate", myRate);
            data.put("average", average);
            data.put("num", all);
            data.put("five", fiveRate);
            data.put("four", fourRate);
            data.put("three", threeRate);
            data.put("two", twoRate);
            data.put("one", oneRate);
            Result result = new Result();
            result.setCode(1);
            result.setMessage("获取书籍评分成功！");
            result.setData(data);
            return result;
        } catch (Exception e) {
            Result result = new Result();
            result.setMessage("获取书籍评分失败！");
            return result;
        }
    }

    @PostMapping("/updateRate")
    public Result updateRate(@RequestHeader("token") String token, @RequestBody Map<String, Integer> map) {
        try {
            String username = JWT.parseToken(token);
            int userId = userService.selectByUsername(username).getId();
            try {
                int myRate = bookRateService.selectRate(map.get("bookId"), userId);
                bookRateService.update(map.get("bookId"), userId, map.get("rate"));
                Result result = new Result();
                result.setCode(1);
                result.setMessage("更新书籍评分成功！");
                return result;
            } catch (Exception e) {
                bookRateService.insert(map.get("bookId"), userId, map.get("rate"));
                Result result = new Result();
                result.setCode(1);
                result.setMessage("新建书籍评分成功！");
                return result;
            }
        } catch (Exception e) {
            Result result = new Result();
            result.setMessage("更新书籍评分失败！");
            return result;
        }
    }
}
