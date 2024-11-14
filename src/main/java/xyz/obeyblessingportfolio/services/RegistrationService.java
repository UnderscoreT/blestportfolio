package xyz.obeyblessingportfolio.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.obeyblessingportfolio.data.dto.UserCredentialsDto;
import xyz.obeyblessingportfolio.data.user.UserProfile;
import xyz.obeyblessingportfolio.data.user.UserProfileRepository;

@Service
@RequiredArgsConstructor
public class RegistrationService {
    private final UserProfileRepository userProfileRepository;

    public boolean userExists(UserCredentialsDto userCredentialsDto) {

        return userProfileRepository.findByUsernameIgnoreCase(userCredentialsDto.getUsername())
                .isPresent();

    }

    public void save(UserProfile model) {
        userProfileRepository.save(model);
    }
}
