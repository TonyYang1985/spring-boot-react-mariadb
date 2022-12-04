package com.ibank.backend.listener;

import com.alibaba.fastjson2.JSON;
import com.google.common.collect.Lists;
import com.ibank.backend.interfaces.annotation.AutoRegisterController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
public class EndpointsListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        ApplicationContext applicationContext = event.getApplicationContext();
//        applicationContext.getBean(RequestMappingHandlerMapping.class).getHandlerMethods()
//                .forEach((key, value) -> log.info("{} {}", key, value));

//        Map<String, Object> controllers = applicationContext.getBeansWithAnnotation(AutoRegisterController.class);
//
//        for (Map.Entry<String, Object> entry : controllers.entrySet()) {
//            Object value = entry.getValue();
//            System.out.println("拿到controller："+entry.getKey()+"，拿到value："+value);
//            Class<?> aClass = AopUtils.getTargetClass(value);
//            System.out.println("拿到Class:"+aClass);
//            RequestMapping annotation = aClass.getAnnotation(RequestMapping.class);
//            RequestMapping declaredAnnotation = aClass.getDeclaredAnnotation(RequestMapping.class);
//
//            List<Method> methods = Arrays.asList(aClass.getMethods());
//            System.out.println("Public Methods:" + methods);
//            List<Method> declaredMethods = Arrays.asList(aClass.getMethods());
//            for (int i = 0; i < declaredMethods.size() ; i++) {
//                GetMapping getMapping = declaredMethods.get(i).getAnnotation(GetMapping.class);
//                PostMapping postMapping = declaredMethods.get(i).getDeclaredAnnotation(PostMapping.class);
//
//                RequestMapping requestMapping = AnnotationUtils.findAnnotation(declaredMethods.get(i), RequestMapping.class);
//
//                if(requestMapping !=null){
//                    System.out.println("Get相关的 requestMapping："+JSON.toJSONString(requestMapping.value()));
//                    System.out.println("Get相关的 requestMapping："+JSON.toJSONString(requestMapping.params()));
//                }
//                if(getMapping !=null){
//                    System.out.println("Get相关的："+JSON.toJSONString(getMapping.value()));
//                    System.out.println("Get相关的 ："+JSON.toJSONString(getMapping.params()));
//                }
//                if(postMapping !=null){
//                    System.out.println("Post相关的："+JSON.toJSONString(postMapping.value()));
//                    System.out.println("Post相关的 ："+JSON.toJSONString(postMapping.params()));
//                }
//            }
//        }


        List<String> beanNameList = Lists.newArrayList(applicationContext.getBeanNamesForAnnotation(AutoRegisterController.class));
        log.info("scan rest controller number:{}", beanNameList.size());
        for (String beanName : beanNameList) {
            registerMapping(applicationContext,beanName);
        }

    }
    private boolean registerMapping(ApplicationContext applicationContext,String beanName) {
        Object obj = applicationContext.getBean(beanName);
        Class<?> objClass = obj.getClass();
        List<Method> methods = Arrays.asList(objClass.getDeclaredMethods());
        //remove static private
        methods.removeIf(r -> Modifier.isStatic(r.getModifiers()));
        methods.removeIf(r -> Modifier.isPrivate(r.getModifiers()));
        //
        RequestMapping annotation = objClass.getAnnotation(RequestMapping.class);
        RequestMapping declaredAnnotation = objClass.getDeclaredAnnotation(RequestMapping.class);
        log.info("0.0  RequestMapping Name:{}",  JSON.toJSONString(annotation.value()));
        for (Method method : methods) {
            RequestMapping requestMapping = AnnotationUtils.findAnnotation(method, RequestMapping.class);
            //Method Annotation
            Annotation[] annotations =method.getAnnotations();
            //Parameter
            Parameter[] parameters =  method.getParameters();
            //Parameter Annotations
            Annotation[][]  _parameters= method.getParameterAnnotations();

            log.info("1 Method  method Name:{}",  JSON.toJSONString(method.getName()));
            log.info("2 Method method :{}",  JSON.toJSONString(requestMapping.method()));
            log.info("3 Method api :{}",  JSON.toJSONString(requestMapping.value()));

            for( Parameter i: parameters){
                log.info("4. Method-Parameter  :{}",  JSON.toJSONString(i));
            }
            for( Annotation[] i: _parameters){
                log.info("5. Method-Parameter Annotations  :{}",  JSON.toJSONString(i));
            }
            for( Annotation i: annotations){
                log.info("6 . Method - Annotations  :{}",  JSON.toJSONString(i.annotationType()));
                log.info("7. Method - Annotations  :{}",  JSON.toJSONString(i.toString()));
                if(i instanceof GetMapping  ){
                    log.info("8. Method- GetMapping  :{}",  ((GetMapping) i).value());
                }
            }
        }
        return true;
    }
}