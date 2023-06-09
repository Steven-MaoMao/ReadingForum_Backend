package capstone_project.ReadingForum_Backend.Controller;

import capstone_project.ReadingForum_Backend.Model.Book;
import capstone_project.ReadingForum_Backend.Model.BookComment;
import capstone_project.ReadingForum_Backend.Model.User;
import capstone_project.ReadingForum_Backend.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private IUserService userService;
    @Autowired
    private IBookService bookService;
    @Autowired
    private IBookRateService bookRateService;
    @Autowired
    private IBookCommentService bookCommentService;
    @Autowired
    private IFavouriteService favouriteService;
    @Autowired
    private ITagService tagService;

    @Value("${web.uploadPath}")
    private String baseUploadPath;

    @GetMapping("/allBook")
    public Result getAllBook() {
        try {
            List<Book> bookList = bookService.selectAll();
            Result result = new Result();
            result.setCode(1);
            result.setMessage("获取书籍信息成功！");
            Map data = new HashMap<String, Object>();
            data.put("bookList", bookList);
            result.setData(data);
            return result;
        } catch (Exception e) {
            Result result = new Result();
            result.setMessage("程序异常，请重试！");
            return result;
        }
    }

    @GetMapping("/bookInfoById")
    public Result getBookInfoById(@RequestParam("id") int id) {
        try {
            Book book = bookService.selectById(id);
            book.setTags(tagService.selectByBook(id));
            book.setUser(userService.selectById(book.getUploadUser()));
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

    @GetMapping("/topTenBook")
    public Result getTopTenBook() {
        try {
            List<Book> bookList = bookService.selectTopTen();
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

    @GetMapping("/bookByTagPage")
    public Result selectBookByTagPage(@RequestParam("tagId") int tagId, @RequestParam("page") int page) {
        try {
            int start = (page - 1) * 16;
            List<Book> bookList;
            int totalBook;
            if(tagId == 0) {
                bookList = bookService.selectAllByPage(start);
                totalBook = bookService.selectAllNumByPage();
            } else {
                bookList = bookService.selectBookByTagPage(tagId, start);
                totalBook = bookService.selectBookNumByTagPage(tagId);
            }
            for (int i=0; i<bookList.size(); i++) {
                int id = bookList.get(i).getId();
                bookList.get(i).setTags(tagService.selectByBook(id));
            }
            Result result = new Result();
            result.setCode(1);
            result.setMessage("成功！");
            Map map = new HashMap<String, Object>();
            map.put("bookList", bookList);
            map.put("totalBook", totalBook);
            result.setData(map);
            return result;
        } catch (Exception e) {
            Result result = new Result();
            result.setMessage("程序异常，请重试！");
            return result;
        }
    }

    @GetMapping("/allTag")
    public Result selectAllTag() {
        try {
            List<Map> tagList = tagService.selectAll();
            Result result = new Result();
            result.setCode(1);
            result.setMessage("成功！");
            Map map = new HashMap<String, Object>();
            map.put("tagList", tagList);
            result.setData(map);
            return result;
        } catch (Exception e) {
            Result result = new Result();
            result.setMessage("程序异常，请重试！");
            return result;
        }
    }

    @GetMapping("/topFiveTag")
    public Result selectTopFiveTag() {
        try {
            List<Map> tagList = tagService.selectTopFive();
            Result result = new Result();
            result.setCode(1);
            result.setMessage("成功！");
            Map map = new HashMap<String, Object>();
            map.put("tagList", tagList);
            result.setData(map);
            return result;
        } catch (Exception e) {
            Result result = new Result();
            result.setMessage("程序异常，请重试！");
            return result;
        }
    }

    @GetMapping("/latestFiveBook")
    public Result selectLatestFiveBook() {
        try {
            List<Book> bookList = bookService.selectLatestFive();
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

    @GetMapping("/searchBook")
    public Result searchBook(@RequestParam("keyword") String keyword, @RequestParam("page") int page) {
        try {
            int start = (page - 1) * 16;
            keyword = "%" + keyword + "%";
            List<Book> bookList = bookService.searchBook(keyword, start);
            for (int i=0; i<bookList.size(); i++) {
                int id = bookList.get(i).getId();
                bookList.get(i).setTags(tagService.selectByBook(id));
            }
            int totalBook = bookService.searchBookNum(keyword);
            Result result = new Result();
            result.setCode(1);
            result.setMessage("搜索成功！");
            Map map = new HashMap<String, Object>();
            map.put("bookList", bookList);
            map.put("totalBook", totalBook);
            result.setData(map);
            return result;
        } catch (Exception e) {
            Result result = new Result();
            result.setMessage("程序异常，请重试！");
            return result;
        }
    }

    @GetMapping("/isFavourite")
    public Result isFavourite(@RequestHeader("token") String token, @RequestParam("bookId") int bookId) {
        try {
            String username = JWT.parseToken(token);
            int userId = userService.selectByUsername(username).getId();
            List<Integer> favouriteList = favouriteService.selectFavourite(userId);
            Result result = new Result();
            if (favouriteList.contains(bookId)) {
                result.setCode(1);
            }
            return result;
        } catch (Exception e) {
            Result result = new Result();
            result.setMessage("程序异常，请重试！");
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

    @GetMapping("/bookCommentByPage")
    public Result getBookCommentByPage(@RequestParam("bookId") int bookId, @RequestParam("page") int page) {
        try {
            int start = (page - 1) * 10;
            List<BookComment> commentList = bookCommentService.selectCommentByPage(bookId, start);
            int totalComment = bookCommentService.selectCommentNum(bookId);
            Result result = new Result();
            result.setCode(1);
            result.setMessage("获取书籍评论成功！");
            Map data = new HashMap<String, Object>();
            data.put("commentList", commentList);
            data.put("totalComment", totalComment);
            result.setData(data);
            return result;
        } catch (Exception e) {
            Result result = new Result();
            result.setMessage("获取书籍评论失败！");
            return result;
        }
    }

    @PostMapping("/uploadBookCover")
    public Result uploadBookCover(@RequestParam("file") MultipartFile newBookCover) {
        try {
            Result result = new Result();
            if (newBookCover.isEmpty()) {
                result.setMessage("文件为空，上传失败！");
                return result;
            }
            String[] splitFilename = newBookCover.getOriginalFilename().split("\\.");
            String ext = "." + splitFilename[splitFilename.length - 1];
            String uuid = UUID.randomUUID().toString();
            String newAvatarName = baseUploadPath + "BookCover/" + uuid + ext;
            newBookCover.transferTo(new File(newAvatarName));
            String bookCoverPath = "/resources/BookCover/" + uuid + ext;
            result.setCode(1);
            result.setMessage("上传成功！");
            Map map = new HashMap<String, String>();
            map.put("path", bookCoverPath);
            result.setData(map);
            return result;
        } catch (Exception e) {
            Result result = new Result();
            result.setMessage("上传失败！");
            return result;
        }
    }

    @PostMapping("/newBook")
    public Result insertNewBook(@RequestHeader("token") String token, @RequestBody Book book) {
        try {
            String username = JWT.parseToken(token);
            User user = userService.selectByUsername(username);
            book.setUploadUser(user.getId());
            Result result = new Result();
            System.out.println(book);
            if (bookService.insert(book)) {
                result.setCode(1);
                result.setMessage("上传成功！");
            } else {
                result.setMessage("上传失败！");
            }
            return result;
        } catch (Exception e) {
            Result result = new Result();
            result.setMessage("程序异常，请重试！");
            return result;
        }
    }

    @PostMapping("/favourite")
    public Result favourite(@RequestHeader("token") String token, @RequestBody Map<String, Integer> map) {
        try {
            String username = JWT.parseToken(token);
            int userId = userService.selectByUsername(username).getId();
            int bookId = map.get("bookId");
            favouriteService.insert(userId, bookId);
            Result result = new Result();
            result.setCode(1);
            result.setMessage("收藏成功！");
            return result;
        } catch (Exception e) {
            Result result = new Result();
            result.setMessage("程序异常，请重试！");
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

    @PostMapping("/comment")
    public Result comment(@RequestHeader("token") String token, @RequestBody Map<String, String> map) {
        try {
            String username = JWT.parseToken(token);
            int userId = userService.selectByUsername(username).getId();
            int bookId = Integer.parseInt(map.get("bookId"));
            String comment = map.get("comment").toString();
            bookCommentService.insert(bookId, userId, comment);
            Result result = new Result();
            result.setCode(1);
            result.setMessage("评论成功！");
            return result;
        } catch (Exception e) {
            Result result = new Result();
            result.setMessage("评论失败！");
            return result;
        }
    }

    @DeleteMapping("/deleteFavourite")
    public Result deleteFavourite(@RequestHeader("token") String token, @RequestParam("bookId") int bookId) {
        try {
            String username = JWT.parseToken(token);
            int userId = userService.selectByUsername(username).getId();
            favouriteService.delete(userId, bookId);
            Result result = new Result();
            result.setCode(1);
            result.setMessage("取消收藏成功！");
            return result;
        } catch (Exception e) {
            Result result = new Result();
            result.setMessage("程序异常，请重试！");
            return result;
        }
    }
}
