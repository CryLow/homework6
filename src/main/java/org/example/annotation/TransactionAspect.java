package org.example.annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Aspect
@Component
public class TransactionAspect {
    private final DataSource dataSource;

    public TransactionAspect(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    @Around("@annotation(Transaction)")
    public Object managerTransaction(ProceedingJoinPoint joinPoint){
        try {
            Connection connection = dataSource.getConnection();
            connection.setAutoCommit(false);

            try {
                Object result = joinPoint.proceed();
                connection.commit();
                return result;
            }   catch (RuntimeException e){
                connection.rollback();
                throw e;
            } finally {
                connection.close();
            }

        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
