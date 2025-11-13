package org.example.oauthexam.service;

import lombok.RequiredArgsConstructor;
import org.example.oauthexam.entity.SocialUser;
import org.example.oauthexam.repository.SocialUserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SocialUserService {
    private final SocialUserRepository socialUserRepository;

    //소셜에서 얻어온 정보를 저장하기 위한 메서드 -- 이미 소셜 정보를 가진 사용자와, 처음 방문한 사용자
    @Transactional
    public SocialUser saveOrUpdateUser(String socialId, String provider, String username, String email, String avatarUrl){
        Optional<SocialUser> existingUser = socialUserRepository.findBySocialIdAndProvider(socialId, provider);
        SocialUser socialUser;

        if(existingUser.isPresent()){
            //이미 소셜로 가입을 했던 사용자인데,  소셜 정보가 업데이트 되었을 수 있으니 기존 정보를 수정!!
            socialUser = existingUser.get();
            socialUser.setUsername(username);
            socialUser.setEmail(email);
            socialUser.setAvatarUrl(avatarUrl);
        }else{
            //처음 방문 한 사용자
            socialUser = new SocialUser();
            socialUser.setSocialId(socialId);
            socialUser.setProvider(provider);
            socialUser.setUsername(username);
            socialUser.setEmail(email);
            socialUser.setAvatarUrl(avatarUrl);
        }
        return socialUserRepository.save(socialUser);
    }
}
