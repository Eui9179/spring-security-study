package site.leui.ssiach6.config.dummy;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import site.leui.ssiach6.user.EncryptionAlgorithm;
import site.leui.ssiach6.user.User;
import site.leui.ssiach6.user.UserRepository;

@Configuration
public class Dummy {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Dummy(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Bean
    CommandLineRunner initData() {
        return args -> userRepository.save(getUser());
    }

    public User getUser() {
        User user = new User();
        user.setUsername("test");
        user.setPassword(bCryptPasswordEncoder.encode("test"));
        user.setPassword("test");
        user.setAlgorithm(EncryptionAlgorithm.BCRYPT);
        return user;
    }
}
