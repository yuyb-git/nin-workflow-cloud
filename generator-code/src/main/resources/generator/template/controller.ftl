package ${basePackageModule}.controller;

import ${basePackage}.base.BaseController;
import ${basePackageModule}.domain.${modelNameUpperCamel};
import ${commonPackage}.constant.GlobalConstant;
import ${commonPackage}.global.HttpResultEntry;
import ${basePackageModule}.service.${modelNameUpperCamel}Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpSession;

/**
 * @author ${author}
 * @date   ${date}
 */
@RestController
@RequestMapping("/${modelNameLowerCamel}")
public class ${modelNameUpperCamel}Controller{

    private final static Logger LOGGER = LoggerFactory.getLogger(${modelNameUpperCamel}Controller.class);

    @Autowired
    ${modelNameUpperCamel}Service ${modelNameLowerCamel}Service;

}


