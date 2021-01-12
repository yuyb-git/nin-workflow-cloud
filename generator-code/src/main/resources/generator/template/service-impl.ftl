package ${basePackageModule}.service.impl;

import ${basePackage}.base.BaseService;
import ${basePackageModule}.dao.${modelNameUpperCamel}Mapper;
import ${basePackageModule}.domain.${modelNameUpperCamel};
import ${basePackageModule}.service.${modelNameUpperCamel}Service;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author ${author}
 * @date   ${date}
 */
@Service
public class ${modelNameUpperCamel}ServiceImpl extends BaseService<${modelNameUpperCamel}> implements ${modelNameUpperCamel}Service {
    @Resource
    private ${modelNameUpperCamel}Mapper ${modelNameLowerCamel}Mapper;

    @Override
    public int insertSelective(${modelNameUpperCamel} ${modelNameLowerCamel}, long userId) {
        return ${modelNameLowerCamel}Mapper.insertSelective(${modelNameLowerCamel});
    }
    @Override
    public int updateByPrimaryKeySelective(${modelNameUpperCamel} ${modelNameLowerCamel}, long l) {
        return 0;
    }
    @Override
    public Class getClazz(){
        return ${modelNameUpperCamel}.class;
    }
}