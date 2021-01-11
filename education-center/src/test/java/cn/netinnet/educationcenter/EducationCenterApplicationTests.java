package cn.netinnet.educationcenter;

import cn.netinnet.educationcenter.dao.SysBatchMapper;
import cn.netinnet.educationcenter.domain.SysBatch;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest(classes = EducationCenterApplication.class)
@RunWith(SpringRunner.class)
public class EducationCenterApplicationTests {
    @Resource
    SysBatchMapper sysBatchMapper;

    @Test
    public void query() {
        try{
            List<SysBatch> sysBatches = sysBatchMapper.queryList(null,null,null,null);
            System.out.println(sysBatches.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("llll");
    }
}
