package laptrinhjavaweb.controller.admin;

import laptrinhjavaweb.model.CategoryCourseModel;
import laptrinhjavaweb.model.CourseModel;
import laptrinhjavaweb.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller(value = "categoryCourseControllerOfAdmin")
public class CategoryCourseController {
    @Autowired
    private ICategoryService categoryService;

    @RequestMapping(value = "/quan-tri/danh-muc/danh-sach", method = RequestMethod.GET)
    public ModelAndView showList() {
        ModelAndView mav = new ModelAndView("admin/categoryCourse/list");
        CategoryCourseModel model = new CategoryCourseModel();
        model.setListResult(categoryService.findAll());
        mav.addObject("model", model);
        return mav;
    }

    @RequestMapping(value = "/quan-tri/danh-muc/them-moi", method = RequestMethod.GET)
    public ModelAndView addCategoryCourse() {
        ModelAndView mav = new ModelAndView("admin/categoryCourse/add");
        CategoryCourseModel model = new CategoryCourseModel();

        mav.addObject("model", model);
        return mav;
    }

    @RequestMapping(value = "/quan-tri/danh-muc/chinh-sua", method = RequestMethod.GET)
    public ModelAndView updateCategoryCourse(@RequestParam(value = "id", required = false) Long id) {
        ModelAndView mav = new ModelAndView("admin/categoryCourse/edit");
        CategoryCourseModel model = new CategoryCourseModel();
        if(id != null) {
            model = categoryService.findOne(id);
        }
        mav.addObject("model", model);
        return mav;
    }

}
