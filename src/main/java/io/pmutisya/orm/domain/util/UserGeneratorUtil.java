package io.pmutisya.orm.domain.util;

import io.pmutisya.orm.domain.User;
import io.pmutisya.orm.domain.enumeration.Role;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

public class UserGeneratorUtil {
    private static final Random random = new Random();

    public static User generate(){
        User user = new User();

        user.setName(UUID.randomUUID().toString());
        user.setLogin(UUID.randomUUID().toString());
        user.setCreatedAt(LocalDateTime.now().toString());
        user.setOrganisationId(random.nextLong());
        user.setRole(randomEnum(Role.class));

        return user;
    }

    public static <T extends Enum<?>> T randomEnum(Class<T> clazz){
        int x = random.nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }
}
