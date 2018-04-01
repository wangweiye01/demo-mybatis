package com.example.demo;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.common.utils.JsonResult;
import com.example.demo.entity.Company;
import com.example.demo.entity.Department;
import com.example.demo.entity.Manager;
import com.example.demo.repository.CompanyRepository;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.ManagerRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Api(value = "测试controller", tags = {"测试JPA接口"})
public class DataController {
    @Autowired
    private ManagerRepository managerRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @ApiOperation(value = "保存部门信息", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = "Authorization", value = "token", required = true, dataType = "string"),
            @ApiImplicitParam(name = "managerName", value = "经理姓名", dataType = "int", required = true, paramType = "form"),
            @ApiImplicitParam(name = "companyName", value = "公司名称", dataType = "string", required = true, paramType = "form")
    })
    @RequestMapping(value = "/department/ready", method = RequestMethod.POST)
    public String departmentReady(@RequestParam String managerName, @RequestParam String companyName) {
        Manager manager = new Manager();
        manager.setName(managerName);

        Department department = new Department();
        department.setName("department y");

        Company company = new Company();
        company.setName(companyName);


        manager.setDepartment(department);
        department.setManager(manager);
        department.setCompany(company);
        company.getDepartmentSet().add(department);


        return "保存成功";
    }

    @ApiOperation(value = "查询所有部门", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = "Authorization", value = "token", required = true, dataType = "string"),
    })
    @RequestMapping(value = "/departments", method = RequestMethod.GET)
    public JSONObject departments() {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(0, 10, sort);
        Page departments = departmentRepository.findAll(pageable);

        return JsonResult.success(departments);
    }

    @RequestMapping(value = "/getOneCompany", method = RequestMethod.GET)
    @ApiOperation(value = "查询某个公司", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = "Authorization", value = "token", required = true, dataType = "string"),
    })
    public JSONObject getOneCompany() {
        Company company = companyRepository.findOne(1);
        Map<String, Object> result = new HashMap<>();
        result.put("id", company.getId());
        result.put("name", company.getName());
        result.put("departments", company.getDepartmentSet());

        return JsonResult.success(result);
    }

    @RequestMapping(value = "/findAllCompany", method = RequestMethod.GET)
    @ApiOperation(value = "查询所有公司", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = "Authorization", value = "token", required = true, dataType = "string"),
    })
    public JSONObject findAllCompany() {
        List<Company> companies = companyRepository.findAll();

        return JsonResult.success(companies);
    }
}
