package main.app.controller;

import main.app.domain.Employee;
import main.app.domain.EmployeeType;
import main.app.service.EmployeeService;
import main.app.service.LeaveService;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.beans.PropertyEditorSupport;
import java.sql.SQLException;


@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
        binder.registerCustomEditor(EmployeeType.class, new PropertyEditorSupport() {
            // TODO: Make it work
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                setValue(EmployeeType.valueOf(text.toUpperCase()));
            }
        });
    }


    @RequestMapping("/list")
    public String list(Model model) throws SQLException {
        //TODO: Return ANNUAL LEAVE AND SICK LEAVE
        //Idea 1 : Make a new Model class / Anonymous class for Employee and Leave combine and return it ?
        //Idea 2 : Somehow map them in repository and return it ?
        //Idea 3 : Make the Name Click able and make a new page for Leave List ?
        model.addAttribute("employees", employeeService.list());
        return "employee/list";
    }

    @RequestMapping("/info")
    public String info(@RequestParam("employeeId") Long employeeId, Model model) throws SQLException {
        Employee employee = employeeService.get(employeeId);
        model.addAttribute("employee", employee);
        model.addAttribute("employee_leave", LeaveService.listByEmployeeId(employeeId));
        return "employee/info";
    }

    @RequestMapping("/create")
    public String create(Model model) {
        model.addAttribute("employee", new Employee());
        return "employee/create";
    }

    @RequestMapping("/store")
    public String store(@Valid @ModelAttribute("employee") Employee employee, BindingResult bindingResult) throws SQLException {
        if (bindingResult.hasErrors()) {
            return "employee/create";
        }
        employeeService.createEmployeeAndLeave(employee);
        return "redirect:/employees/list";
    }

    @RequestMapping("/edit")
    public String edit(@RequestParam("employeeId") Long employeeId, Model model) throws SQLException {
        model.addAttribute("employee", employeeService.get(employeeId));
        //TODO : Make JSP page for edit
        return "employee/edit";
    }

    @RequestMapping("/update")
    public String update(@Valid @ModelAttribute("employee") Employee employee, BindingResult bindingResult) throws SQLException {
        if (bindingResult.hasErrors()) {
            return "employee/edit";
        }
        employeeService.update(employee);
        return "redirect:/employees/list";
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam("employeeId") Long employeeId) throws SQLException {
        employeeService.delete(employeeId);
        return "redirect:/employees/list";
    }
}