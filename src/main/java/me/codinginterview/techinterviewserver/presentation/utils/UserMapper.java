package me.codinginterview.techinterviewserver.presentation.utils;

import me.codinginterview.techinterviewserver.domain.DomainException;
import me.codinginterview.techinterviewserver.domain.user.User;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Map;

public class UserMapper {
    private static final String PROVIDER_NAME_GOOGLE = "google";
    private static final String PROVIDER_NAME_NAVER = "naver";
    private static final String PROVIDER_NAME_KAKAO = "kakao";

    public static User fromOauth2(String registrationId, OAuth2User oauth2User) {
        switch (registrationId.toLowerCase()) {
            case PROVIDER_NAME_GOOGLE:
                return createUserFromGoogle(oauth2User);
            case PROVIDER_NAME_NAVER:
                return createUserFromNaver(oauth2User);
            case PROVIDER_NAME_KAKAO:
                return createUserFromKakao(oauth2User);
            default:
                throw new DomainException.BadRequest(registrationId.toLowerCase() + " is illegal client name");
        }
    }

    // https://developers.google.com/identity/protocols/oauth2/openid-connect
    private static User createUserFromGoogle(OAuth2User oAuth2User) {
        return User.builder()
                   .namespace(PROVIDER_NAME_GOOGLE)
                   .localId(oAuth2User.getAttribute("sub"))
                   .name(oAuth2User.getAttribute("name"))
                   .build();
    }

    // https://developers.naver.com/docs/login/devguide/devguide.md
    private static User createUserFromNaver(OAuth2User oAuth2User) {
        return User.builder()
                   .namespace(PROVIDER_NAME_NAVER)
                   .localId(getNaverId(oAuth2User.getAttributes()))
                   .name(getNaverName(oAuth2User.getAttributes()))
                   .build();
    }

    private static String getNaverId(Map<String, Object> attributes) {
        try {
            return String.valueOf(PropertyUtils.getProperty(attributes, "response.id"));
        } catch (Exception e) {
            throw new DomainException.Conflict("naver id is empty");
        }
    }

    private static String getNaverName(Map<String, Object> attributes) {
        try {
            return String.valueOf(PropertyUtils.getProperty(attributes, "response.name"));
        } catch (Exception e) {
            throw new DomainException.Conflict("naver name is empty");
        }
    }

    // https://developers.kakao.com/docs/latest/ko/kakaologin/rest-api
    private static User createUserFromKakao(OAuth2User oAuth2User) {
        return User.builder()
                   .namespace(PROVIDER_NAME_KAKAO)
                   .localId(getKakaoId(oAuth2User.getAttributes()))
                   .name(getKakaoProfileNickname(oAuth2User.getAttributes()))
                   .build();
    }

    private static String getKakaoId(Map<String, Object> attributes) {
        return String.valueOf(attributes.get("id"));
    }

    private static String getKakaoProfileNickname(Map<String, Object> attributes) {
        try {
            return String.valueOf(PropertyUtils.getProperty(attributes, "kakao_account.profile.nickname"));
        } catch (Exception e) {
            throw new DomainException.Conflict("kakao profile name is empty");
        }
    }
}
