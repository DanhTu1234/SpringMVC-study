package laptrinhjavaweb.controller.admin;


import laptrinhjavaweb.model.Class_OfferingModel;
import laptrinhjavaweb.service.IClass_OfferingService;
import laptrinhjavaweb.service.ICourseCatalogService;
import laptrinhjavaweb.service.ISemesterService;
import laptrinhjavaweb.service.IUserService;
import laptrinhjavaweb.service.impl.Class_OfferingService;
import laptrinhjavaweb.service.impl.SemesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller(value = "classOfferingControllerOfAdmin")
public class Class_OfferingController {
    @Autowired
    private IClass_OfferingService class_OfferingService;
    @Autowired
    private ICourseCatalogService courseCatalogService;
    @Autowired
    private ISemesterService semesterService;
    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/quan-tri/lop-hoc-phan/danh-sach", method = RequestMethod.GET)
    public ModelAndView showList() {
        ModelAndView mav = new ModelAndView("admin/Offering/list");
        Class_OfferingModel model = new Class_OfferingModel();
        model.setListResult(class_OfferingService.findAll());
        mav.addObject("model", model);
        return mav;
    }

    @RequestMapping(value = "/quan-tri/lop-hoc-phan/them-moi", method = RequestMethod.GET)
    public ModelAndView addOffering() {
        ModelAndView mav = new ModelAndView("admin/Offering/add");
        Class_OfferingModel model = new Class_OfferingModel();
        mav.addObject("courseCatalog", courseCatalogService.findAll());
        mav.addObject("semester", semesterService.findAll());
        mav.addObject("user", userService.findAll());
        mav.addObject("model", model);
        return mav;
    }

    @RequestMapping(value = "/quan-tri/lop-hoc-phan/chinh-sua", method = RequestMethod.GET)
    public ModelAndView updateOffering(@RequestParam(value = "id", required = false) Long id) {
        ModelAndView mav = new ModelAndView("admin/Offering/edit");
        Class_OfferingModel model = new Class_OfferingModel();
        if(id != null) {
            model = class_OfferingService.findOne(id);
        }
        mav.addObject("courseCatalog", courseCatalogService.findAll());
        mav.addObject("semester", semesterService.findAll());
        mav.addObject("user", userService.findAll());
        mav.addObject("model", model);
        return mav;
    }
}
