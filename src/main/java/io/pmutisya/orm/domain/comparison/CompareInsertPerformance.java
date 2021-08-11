package io.pmutisya.orm.domain.comparison;

import io.pmutisya.orm.domain.User;
import io.pmutisya.orm.domain.repository.UserRepository;
import io.pmutisya.orm.domain.util.UserGeneratorUtil;

public class CompareInsertPerformance {
    public long testHibernatePerformance(long requests, UserRepository userRepository) throws Exception {
        long totalTime = 0;
        for (int i = 0; i < requests; i++) {
            User user = UserGeneratorUtil.generate();

            long start = System.nanoTime();
            userRepository.save(user);
            long timeTaken = System.nanoTime() - start;

            totalTime += timeTaken;
        }
        return totalTime;
    }

    public long testGetMultipleTimesPerformance(long requests, long id, UserRepository userRepository) throws Exception {
        long totalTime = 0;
        for (int i = 0; i < requests; i++) {

            long start = System.nanoTime();
            User user = userRepository.findById(id);

            assert(user != null);

            long timeTaken = System.nanoTime() - start;

            totalTime += timeTaken;
        }
        return totalTime;
    }
}
