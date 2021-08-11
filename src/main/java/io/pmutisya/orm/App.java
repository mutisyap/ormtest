package io.pmutisya.orm;

import io.pmutisya.orm.domain.comparison.CompareInsertPerformance;
import io.pmutisya.orm.domain.config.HibernateConfiguration;
import io.pmutisya.orm.domain.config.JDBCConfiguration;
import io.pmutisya.orm.domain.repository.UserRepository;
import io.pmutisya.orm.domain.repository.hibernate.UserORMRepository;
import io.pmutisya.orm.domain.repository.jdbc.UserJDBCRepository;

/**
 * Hello world!
 */
public class App {
    private static final CompareInsertPerformance compareInsertPerformance = new CompareInsertPerformance();

    public static void main(String[] args) throws Exception {
        configure();

        UserORMRepository userOrmRepository = new UserORMRepository();
        UserJDBCRepository userJDBCRepository = new UserJDBCRepository();

        long totalRequests = 10000;

//        testMultipleSave(totalRequests, userOrmRepository);

        testMultipleGet(totalRequests, userOrmRepository);
    }

    private static void testMultipleSave(long totalRequests, UserRepository userRepository) throws Exception {
        long hibernateNanos = compareInsertPerformance.testHibernatePerformance(totalRequests, userRepository);
        int msPerRequest = Math.toIntExact(hibernateNanos / (totalRequests * 1000000));

        System.out.println("TotalRequests: " + totalRequests + " Took : " + hibernateNanos + " ns. Per request : " + msPerRequest + " ms");
    }

    private static void testMultipleGet(long totalRequests, UserRepository userRepository) throws Exception {
        long hibernateNanos = compareInsertPerformance.testHibernatePerformance(totalRequests, userRepository);
        int msPerRequest = Math.toIntExact(hibernateNanos / (totalRequests * 1000000));

        System.out.println("TotalRequests: " + totalRequests + " Took : " + hibernateNanos + " ns. Per request : " + msPerRequest + " ms");
    }

    private static void configure() {
        HibernateConfiguration.buildSessionFactory();

        String url = "jdbc:mysql://localhost:3306/ormtest";
        String username = "username";
        String password = "password";


        JDBCConfiguration.getDataSource(url, username, password);
    }
}
