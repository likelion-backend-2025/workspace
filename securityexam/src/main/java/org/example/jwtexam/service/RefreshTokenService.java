package org.example.jwtexam.service;

import lombok.RequiredArgsConstructor;
import org.example.jwtexam.domain.RefreshToken;
import org.example.jwtexam.repository.RefreshTokenRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;

    //리프레시토큰 저장   addRefreshToken(RefreshToken refreshToken)
    @Transactional
    public RefreshToken addRefreshToken(RefreshToken refreshToken) {
        return refreshTokenRepository.save(refreshToken);
    }

    //리프레시토큰 조회   findRefreshToken(String refreshToken)
    @Transactional(readOnly = true)
    public Optional<RefreshToken> findRefreshToken(String refreshToken) {
        return refreshTokenRepository.findByToken(refreshToken);
    }

    //리프레시토큰 삭제 deleteRefreshToken(String refreshToken)
    @Transactional
    public void deleteRefreshToken(String refreshToken) {
        refreshTokenRepository.findByToken(refreshToken)
                .ifPresent(refreshTokenRepository::delete);
    }

}
