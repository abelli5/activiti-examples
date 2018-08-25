package org.activiti.examples;

import org.activiti.runtime.api.ProcessRuntime;
import org.activiti.runtime.api.model.ProcessDefinition;
import org.activiti.runtime.api.query.Page;
import org.activiti.runtime.api.query.Pageable;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    private Logger lgr = LoggerFactory.getLogger(DemoApplicationTests.class);
    
    @Autowired
    private ProcessRuntime prt;
    
    @Autowired
    private SecurityUtil securityUtil;

	@Test
	public void contextLoads() {
	    securityUtil.logInAs("salaboy");
	    
        Page<ProcessDefinition> pdList = prt.processDefinitions(Pageable.of(0, 10));
        
        for (int i = 0; i < pdList.getContent().size(); i++) {
            ProcessDefinition pd = pdList.getContent().get(i);
            lgr.info("id:{}, name:{}, desc:{}", pd.getId(), pd.getName(), pd.getDescription());
        }
	}

}
