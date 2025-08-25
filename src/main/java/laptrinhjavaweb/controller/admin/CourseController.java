package laptrinhjavaweb.controller.admin;

import laptrinhjavaweb.model.CourseModel;
import laptrinhjavaweb.service.ICategoryService;
import laptrinhjavaweb.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller(value = "courseControllerOfAdmin")
public class CourseController {

    @Autowired
    private ICourseService courseService;

    @Autowired
    private ICategoryService categoryService;

    @RequestMapping(value = "/quan-tri/khoa-hoc/danh-sach", method = RequestMethod.GET)
    public ModelAndView showList() {
        ModelAndView mav = new ModelAndView("admin/course/list");
        CourseModel model = new CourseModel();
        model.setListResult(courseService.findAll());
        mav.addObject("model", model);
        return mav;
    }

    @RequestMapping(value = "/quan-tri/khoa-hoc/them-moi", method = RequestMethod.GET)
    public ModelAndView addCourse(){
        ModelAndView mav = new ModelAndView("admin/course/add");
        CourseModel model = new CourseModel();
        mav.addObject("categories", categoryService.findAll());
        mav.addObject("model", model);
        return mav;
    }

    @RequestMapping(value = "/quan-tri/khoa-hoc/chinh-sua", method = RequestMethod.GET)
    public ModelAndView updateCourse(@RequestParam(value = "id", required = false) Long id){
        ModelAndView mav = new ModelAndView("admin/course/edit");
        CourseModel model = new CourseModel();
        if (id != null) {
            // Chỉ khi có ID, mới lấy dữ liệu từ service
            model = courseService.findOne(id);
        }
        mav.addObject("categories", categoryService.findAll());
        mav.addObject("model", model);
        return mav;
    }
}