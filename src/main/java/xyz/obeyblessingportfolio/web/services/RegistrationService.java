package xyz.obeyblessingportfolio.web.services;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.obeyblessingportfolio.data.dto.UserCredentialsDto;
import xyz.obeyblessingportfolio.data.user.UserProfile;
import xyz.obeyblessingportfolio.data.user.UserProfileRepository;


@Service
@RequiredArgsConstructor
public class RegistrationService {
    private final UserProfileRepository userRepository;

    public boolean userExists(UserCredentialsDto userCredentialsDto) {

        return userRepository.findByUsernameIgnoreCase(userCredentialsDto.getUsername())
                .isPresent();

    }

    public void save(UserProfile model) {
        userRepository.save(model);
    }
}
