package ru.openschool.aop.backend.aspect;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import ru.openschool.aop.backend.model.TrackTimeStore;
import ru.openschool.aop.backend.repository.TrackTimeRepository;

import java.util.concurrent.CompletableFuture;

@Aspect
@Component
@Order(2)
@Slf4j
@RequiredArgsConstructor
public class TrackAsyncTimeAspect {

    private final TrackTimeRepository trackTimeRepository;

    @Pointcut("@annotation(ru.openschool.aop.backend.annotation.TrackAsyncTime)")
    public void methodsWithAnnotaion() {
    }

    @Pointcut("execution(* ru.openschool.aop.backend.service.*.*(..)) ")
    public void serviceMethods() {
    }

    @Around("methodsWithAnnotaion() && serviceMethods()")

    @Async
    public Object asyncRunner(ProceedingJoinPoint proceedingJoinPoint) {
        long startTime = System.currentTimeMillis();
        Object result = null;
        String methodName = proceedingJoinPoint.getSignature().getName();
        Object[] methodArgs = proceedingJoinPoint.getArgs();
        log.info("====== Асинхронное логирование выполнения метода {}" , methodName);
        log.info("------ Запуск метода с параметрами {}", methodArgs);
        try {
             result = proceedingJoinPoint.proceed();
        } catch (Throwable e) {
             log.error("Ошибка AsyncRunnerAspect", e);
        }
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
