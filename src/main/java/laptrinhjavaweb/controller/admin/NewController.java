package laptrinhjavaweb.controller.admin;

import laptrinhjavaweb.model.CourseModel;
import laptrinhjavaweb.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller(value = "newControllerOfAdmin")
public class NewController {

    @Autowired
    private ICourseService newService;

    @RequestMapping(value = "/quan-tri/bai-viet/danh-sach", method = RequestMethod.GET)
    public ModelAndView showList(@ModelAttribute("model") CourseModel model) {
        ModelAndView mav = new ModelAndView("admin/new/list");
        model.setListResult(newService.findAll());
        mav.addObject("model", model);
        return mav;
    }

    @RequestMapping(value = "/quan-tri/bai-viet/them-moi", method = RequestMethod.GET)
    public ModelAndView addNew(){
        ModelAndView mav = new ModelAndView("admin/new/add");
        return mav;
    }

    @RequestMapping(value = "/quan-tri/bai-viet/chinh-sua", method = RequestMethod.GET)
    public ModelAndView editNew(){
        ModelAndView mav = new ModelAndView("admin/new/edit");
        return mav;
    }
}