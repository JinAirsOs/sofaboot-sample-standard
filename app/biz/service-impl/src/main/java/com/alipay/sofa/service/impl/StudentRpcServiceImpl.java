package com.alipay.sofa.service.impl;

import com.alipay.sofa.common.dal.dao.StudentDAO;
import com.alipay.sofa.common.dal.tables.Student;
import com.alipay.sofa.facade.StudentRpcService;
import com.alipay.sofa.runtime.api.annotation.SofaService;
import com.alipay.sofa.runtime.api.annotation.SofaServiceBinding;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.swing.text.html.Option;
import java.util.HashMap;
import java.util.Optional;
import java.util.Map;

//用@Service注册bean
//用@SofaService(bindings= {@SofaServiceBinding(bindingType = "bolt")})注册rpc服务
//也可以在xml中设置，便于理解。
@Service
@SofaService(bindings= {@SofaServiceBinding(bindingType = "bolt"),@SofaServiceBinding(bindingType = "rest")})
public class StudentRpcServiceImpl implements StudentRpcService {

    @Resource
    private StudentDAO studentDAO;

    public String sayName(){
        return "hello student rpc service";
    }

    public String getStudentNameById(int id){
        Optional<Student> studentOption = studentDAO.findById(id);
        if(studentOption.isPresent()){
            return studentOption.get().getName();
        } else {
            return "notfound";
        }
    }

    public Map<String,Student> getStudentInfoById(int id) {
        Optional<Student> studentOption = studentDAO.findById(id);
        Map<String,Student> studentInfo = new HashMap<>();
        if(studentOption.isPresent()){
            studentInfo.put("student",studentOption.get());
            return studentInfo;
        } else {
            return studentInfo;
        }
    }
}
