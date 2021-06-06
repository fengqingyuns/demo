package com.example.demo.activity.config;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.impl.interceptor.SessionFactory;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.activiti.spring.boot.ProcessEngineConfigurationConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.example.demo.activity.common.CustomGroupEntityManagerFactory;
import com.example.demo.activity.common.CustomUserEntityManagerFactory;
import com.example.demo.activity.manager.CustomProcessDiagramGeneratorI;



@Configuration
public class ActivitiConfiguration implements ProcessEngineConfigurationConfigurer{
    
    @Autowired
    private CustomUserEntityManagerFactory customUserEntityManagerFactory;
    
    @Autowired
    private CustomGroupEntityManagerFactory customGroupEntityManagerFactory;
    
 ////   @Autowired
 ////   private ProcessHistoryManagerSessionFactory processHistoryManagerSessionFactory;
    
   
    @Autowired
    private CustomProcessDiagramGeneratorI customProcessDiagramGeneratorI;
    
    @Override
    public void configure(SpringProcessEngineConfiguration processEngineConfiguration) {
        // TODO Auto-generated method stub
        //processEngineConfiguration.setDataSource(dataSource);
        processEngineConfiguration.setDatabaseSchemaUpdate("none");// none true
        processEngineConfiguration.setDatabaseType("mysql");

        //processEngineConfiguration.setTransactionManager(transactionManager);

        // 流程图字体
        processEngineConfiguration.setActivityFontName("宋体");
        processEngineConfiguration.setAnnotationFontName("宋体");
        processEngineConfiguration.setLabelFontName("宋体");

        processEngineConfiguration.setJpaHandleTransaction(false);
        processEngineConfiguration.setJpaCloseEntityManager(false);
        //
//      processEngineConfiguration.setMailServerHost(mailProperty.getMailServerHost());
//      processEngineConfiguration.setMailServerUsername(mailProperty.getMailServerUsername());
//      processEngineConfiguration.setMailServerPassword(mailProperty.getMailServerPassword());
//      processEngineConfiguration.setMailServerPort(mailProperty.getMailServerPort());
        //
        processEngineConfiguration.setJobExecutorActivate(false);
        processEngineConfiguration.setAsyncExecutorEnabled(false);
        //processEngineConfiguration.setAsyncExecutorActivate(false);
        //自定义用户和组
        List<SessionFactory> customSessionFactories = new ArrayList<>();
        customSessionFactories.add(customUserEntityManagerFactory);
        customSessionFactories.add(customGroupEntityManagerFactory);
  ////      customSessionFactories.add(processHistoryManagerSessionFactory);
        processEngineConfiguration.setCustomSessionFactories(customSessionFactories);
        
    //自定义流程图样式
    processEngineConfiguration.setProcessDiagramGenerator(customProcessDiagramGeneratorI);
    }
}

