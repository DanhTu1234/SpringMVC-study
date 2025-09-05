package laptrinhjavaweb.controller.admin;

import laptrinhjavaweb.model.DepartmentModel;
import laptrinhjavaweb.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller(value = "departmentControllerOfAdmin")
public class DepartmentController {

    @Autowired
    private IDepartmentService departmentService;

    @RequestMapping(value = "/quan-tri/khoa/danh-sach", method = RequestMethod.GET)
    public ModelAndView showList() {
        ModelAndView mav = new ModelAndView("admin/department/list");
        DepartmentModel model = new DepartmentModel();
        model.setListResult(departmentService.findAll());
        mav.addObject("model", model);
        return mav;
    }

    @RequestMapping(value = "/quan-tri/khoa/them-moi", method = RequestMethod.GET)
    public ModelAndView addDepartment(){
        ModelAndView mav = new ModelAndView("admin/department/add");
        DepartmentModel model = new DepartmentModel();
        mav.addObject("model", model);
        return mav;
    }

    @RequestMapping(value = "/quan-tri/khoa/chinh-sua", method = RequestMethod.GET)
    public ModelAndView updateDepartment(@RequestParam(value = "id", required = false) Long id){
        ModelAndView mav = new ModelAndView("admin/department/edit");
        DepartmentModel model = new DepartmentModel();
        if (id != null) {
            // Chỉ khi có ID, mới lấy dữ liệu từ service
            model = departmentService.findOne(id);
        }
        mav.addObject("model", model);
        return mav;
    }
}