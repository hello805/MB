package com.springmb.mb.config;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionAttribute;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import java.util.HashMap;
import java.util.Map;

//切面
@Aspect
// 表示该类相当于Spring的xml配置文件中的<Beans>
@Configuration
public class TransactionAdviceConfig {
     //定义切点路径
     private static final String AOP_POINTCUT_EXPRESSION = "execution (* com.zcf.commerce.service.impl.*.*(..))";

        @Autowired
        private PlatformTransactionManager transactionManager;

        @Bean
        public TransactionInterceptor txAdvice() {
            //对应增删改的事务规则
            // 事务管理规则，承载需要进行事务管理的方法名（模糊匹配）及设置的事务管理属性
            RuleBasedTransactionAttribute txAttr_REQUIRED = new RuleBasedTransactionAttribute();
            //事务的传播行为（存在事务则加入其中，不存在则新建事务）
            txAttr_REQUIRED.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
            //事务的隔离级别（读已提交的数据）
            txAttr_REQUIRED.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);



            //对应查询的事务规则
            RuleBasedTransactionAttribute txAttr_REQUIRED_READONLY = new RuleBasedTransactionAttribute();
            //事务的传播行为(存在事务则挂起该事务，执行当前逻辑，结束后再恢复上下文事务）
            txAttr_REQUIRED_READONLY.setPropagationBehavior(TransactionDefinition.PROPAGATION_NOT_SUPPORTED);
            //事务的隔离级别（读已提交的数据）
            txAttr_REQUIRED.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
            //设置为只读
            txAttr_REQUIRED_READONLY.setReadOnly(true);

            //配置事务管理规则
            NameMatchTransactionAttributeSource source = new NameMatchTransactionAttributeSource();
            // 建立一个map，用来储存要需要进行事务管理的方法名（模糊匹配）
            Map<String, TransactionAttribute> txMap = new HashMap<String, TransactionAttribute>();
            txMap.put("add*", txAttr_REQUIRED);
            txMap.put("create*", txAttr_REQUIRED);
            txMap.put("upd*", txAttr_REQUIRED);
            txMap.put("pay*", txAttr_REQUIRED);
            //txMap.put("push*", txAttr_REQUIRED);
            //txMap.put("succed*", txAttr_REQUIRED);
            txMap.put("set*", txAttr_REQUIRED);
//            txMap.put("upload*", txAttr_REQUIRED);
            txMap.put("del*", txAttr_REQUIRED);

            txMap.put("*list*", txAttr_REQUIRED_READONLY);
            txMap.put("*List", txAttr_REQUIRED_READONLY);
            txMap.put("*Num", txAttr_REQUIRED_READONLY);
            txMap.put("by*", txAttr_REQUIRED_READONLY);
            txMap.put("*By*", txAttr_REQUIRED_READONLY);
            // 注入设置好的map
            source.setNameMap(txMap);
            //单个添加匹配方法名的事务管理模式
           //  source.addTransactionalMethod("add*", txAttr_REQUIRED);

            return new TransactionInterceptor(transactionManager, source);
        }

    /**
     * @description 利用AspectJExpressionPointcut设置切面
     * @return
     */
    @Bean
        public Advisor txAdviceAdvisor() {
            // 声明切点要切入的面
            AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
            // 设置需要被拦截的路径
            pointcut.setExpression(AOP_POINTCUT_EXPRESSION);
            // 设置切面和配置好的事务管理
            return new DefaultPointcutAdvisor(pointcut, txAdvice());
        }
}
