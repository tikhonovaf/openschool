package ru.openschool.aop.backend.aspect;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import ru.openschool.aop.backend.model.TrackTimeStore;
import ru.openschool.aop.backend.repository.TrackTimeRepository;

@Aspect
@Component
@Order(2)
@Slf4j
@RequiredArgsConstructor
public class TrackTimeAspect {

    private final TrackTimeRepository trackTimeRepository;

    @Pointcut("@annotation(ru.openschool.aop.backend.annotation.TrackTime)")
    public void methodsWithAnnotaion() {
    }

    @Pointcut("execution(* ru.openschool.aop.backend.service.*.*(..)) ")
    public void serviceMethods() {
    }

    @Around("methodsWithAnnotaion() && serviceMethods()")

    public Object trackMethodsExecutionTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        long startTime = System.currentTimeMillis();

        String methodName = proceedingJoinPoint.getSignature().getName();
        Object[] methodArgs = proceedingJoinPoint.getArgs();

        log.info("====== Логирование выполнения метода {}" , methodName);

        log.info("------ Запуск метода с параметрами {}", methodArgs);

        Object result = proceedingJoinPoint.proceed();

        long endTime = System.currentTimeMillis();

        log.info("------ Завершение выполнения метода. Время выполнения: {}.  Результат: {}", endTime - startTime, result);

        //      Записываем время выполнения метода в БД
        TrackTimeStore trackTimeStore = new TrackTimeStore();
        trackTimeStore.setMethodName(methodName);
        trackTimeStore.setExecutionTime(endTime - startTime);
        trackTimeRepository.save(trackTimeStore);

        return result;
    }
}
