package ru.openschool.aop.backend.aspect;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import ru.openschool.aop.backend.access.ActionId;
import ru.openschool.aop.backend.access.ResourceId;
import ru.openschool.aop.backend.annotation.UserAccess;
import ru.openschool.aop.backend.exception.ValidateException;
import ru.openschool.aop.backend.model.MigrUser;
import ru.openschool.aop.backend.repository.AccessRepository;
import ru.openschool.aop.backend.service.UserService;


import java.lang.annotation.Annotation;

@Aspect
@Slf4j
@Order(1)
@Component
@RequiredArgsConstructor
public class CheckAccessAspect {
    private final UserService userService;
    private final  AccessRepository accessRepository;

    /**
     * Проверка доступа к методу на основе роли текущего пользователя
     * и заданных в атрибутах аннотации ресурсе и выполняемом действии
     *
     */
    @Before("execution(* ru.openschool.aop.backend.service.*.*(..)) ")
    public void beforeInvocation(JoinPoint joinPoint) throws Exception {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.getMethod().getName();
        Class<?>[] parameterTypes = signature.getMethod().getParameterTypes();
        Annotation[] annotations = joinPoint.getTarget().getClass().getMethod(methodName, parameterTypes).getDeclaredAnnotations();
        for (Annotation annotation : annotations) {
            if (annotation.annotationType().getSimpleName().equals(UserAccess.class.getSimpleName())) {
                ActionId action = ((UserAccess) annotation).action();
                ResourceId resource = ((UserAccess) annotation).resource();
                log.info("====== Проверка доступа к методу {}", methodName);
                if (!checkAccess(action, resource)) {
                    throw ValidateException.accessDeniedException();
                }
            }
        }
    }

    private boolean checkAccess(ActionId action, ResourceId resource) {
        MigrUser user = userService.getCurrentUser();
        if (user == null) {
            return false;
        }
        // Проверяем непосредственно установленные права
        Boolean access = !accessRepository.findByRoleAndResourceIdAndActionId(user.getRole().getId(), resource.getId(), action.getId()).isEmpty();
        // Если право VIEW или RUN, то проверяем на FULL
        if (!access) {
            if (action.getId().equals(ActionId.VIEW.getId())) {
                access = !accessRepository.findByRoleAndResourceIdAndActionId(user.getRole().getId(), resource.getId(), ActionId.FULL.getId()).isEmpty();
            }
        }
        log.info("------ Результат проверки доступа для роли {}, ресурса {}, действия {} = {} ",
                user.getRole().getName(), resource.name(), action.name(), access);
        return access;
    }
}
