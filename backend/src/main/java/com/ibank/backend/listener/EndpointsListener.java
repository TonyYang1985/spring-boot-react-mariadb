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
        List<Method> methodList = Arrays.asList(objClass.getDeclaredMethods());
        //删除静态函数
        methodList.removeIf(r -> Modifier.isStatic(r.getModifiers()));
        //删除私有函数
        methodList.removeIf(r -> Modifier.isPrivate(r.getModifiers()));
//        for (Method method : methodList) {
//            RequestMethod[] requestMethods = buildRequestMethod(method);
//        }
        RequestMapping annotation = objClass.getAnnotation(RequestMapping.class);
        RequestMapping declaredAnnotation = objClass.getDeclaredAnnotation(RequestMapping.class);

//        log.info("annotation  :{}",  JSON.toJSONString(annotation.value()));
//        log.info("declaredAnnotation  :{}",  JSON.toJSONString(declaredAnnotation.value()));

        for (Method method : methodList) {

            RequestMapping requestMapping = AnnotationUtils.findAnnotation(method, RequestMapping.class);
            log.info("1 RequestMapping api  method Name:{}",  JSON.toJSONString(method.getName()));
            log.info("2 RequestMapping api method :{}",  JSON.toJSONString(requestMapping.method()));
            log.info("3 RequestMapping api :{}",  JSON.toJSONString(requestMapping.value()));

            GetMapping getRequestMothed =  method.getDeclaredAnnotation(GetMapping.class);
            PutMapping putRequestMothed = method.getDeclaredAnnotation(PutMapping.class);
            PostMapping postRequestMothed =method.getDeclaredAnnotation(PostMapping.class);
            DeleteMapping deleteRequestMothed =method.getDeclaredAnnotation(DeleteMapping.class);


            log.info("getParameterAnnotations  :{}",  JSON.toJSONString(method.getParameterAnnotations()));
            Annotation[] annotations =method.getAnnotations();
            Annotation[][] parameterAnnotations = method.getParameterAnnotations();

            for( Annotation i: annotations){
                log.info("5.getAnnotations  :{}",  JSON.toJSONString(i.annotationType()));
                log.info("6. getAnnotations  :{}",  JSON.toJSONString(i.toString()));
            }

            for( Annotation[] i: parameterAnnotations){
                log.info("5.getAnnotations  :{}",  JSON.toJSONString(i));
                log.info("6. getAnnotations  :{}",  JSON.toJSONString(i.toString()));
            }
            if(getRequestMothed !=null){
               // log.info("getName  :{}",  JSON.toJSONString(method.getName()));


//                log.info("getRequestMothed  :{}",  JSON.toJSONString(getRequestMothed.value()));
//                log.info("getParameterTypes  :{}",  JSON.toJSONString(method.getParameterTypes()));
//                log.info("getParameters  :{}",  JSON.toJSONString(method.getParameters()));
//                log.info("getTypeParameters  :{}",  JSON.toJSONString(method.getTypeParameters()));
//                log.info("getParameterAnnotations  :{}",  JSON.toJSONString(method.getParameterAnnotations()));

                log.info("4 getRequestMothed api :{}",  JSON.toJSONString(getRequestMothed.value()));
                log.info("getRequestMothed  :{}",  JSON.toJSONString(getRequestMothed.value()));
                log.info("getRequestMothed  :{}",  JSON.toJSONString(method.getParameters()));
            }
            if(putRequestMothed !=null){
                log.info("putRequestMothed  :{}",  JSON.toJSONString(putRequestMothed.value()));
                log.info("putRequestMothed  :{}",  JSON.toJSONString(method.getParameters()));
            }
            if(postRequestMothed !=null){
                log.info("postRequestMothed  :{}",  JSON.toJSONString(postRequestMothed.value()));
                log.info("postRequestMothed  :{}",  JSON.toJSONString( method.getParameters()));
            }
            if(deleteRequestMothed !=null){
                log.info("deleteRequestMothed  :{}",  JSON.toJSONString(deleteRequestMothed.value()));
                log.info("deleteRequestMothed  :{}",  JSON.toJSONString( method.getParameters()));
            }
        }
        return true;
    }

    private RequestMethod[] buildRequestMethod(Method method) {
        RequestMapping requestMapping = AnnotationUtils.findAnnotation(method, RequestMapping.class);
        log.info("RequestMapping api :{}",  JSON.toJSONString(requestMapping.method()));
        log.info("RequestMapping api :{}",  JSON.toJSONString(requestMapping.value()));
        if (requestMapping != null) {
            return requestMapping.method();
        } else {
            return new RequestMethod[]{RequestMethod.GET};
        }
    }

}