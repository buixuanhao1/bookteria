package com.devteria.profile.service;

import com.devteria.profile.dto.request.ProfileCreationRequest;
import com.devteria.profile.dto.request.ProfileUpdateRequest;
import com.devteria.profile.dto.response.UserProfileResponse;
import com.devteria.profile.entity.UserProfile;
import com.devteria.profile.mapper.UserProfileMapper;
import com.devteria.profile.repository.UserProfileRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserProfileService {
    UserProfileRepository userProfileRepository;
    UserProfileMapper userProfileMapper;

    public UserProfileResponse createUserProfile(ProfileCreationRequest request){
        UserProfile userProfile = this.userProfileMapper.toUserProfile(request);
        userProfile = this.userProfileRepository.save(userProfile);
        return this.userProfileMapper.toUserProfileResponse(userProfile);
    }

    public UserProfileResponse getUserProfile(String id){
        UserProfile userProfile = this.userProfileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profile not found"));
        return this.userProfileMapper.toUserProfileResponse(userProfile);
    }

    public UserProfileResponse updateUserProfile(String id, ProfileUpdateRequest request){
        UserProfile userProfile = userProfileRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Profile not found"));
        userProfileMapper.updateUserProfile(userProfile, request);
        return userProfileMapper.toUserProfileResponse(userProfileRepository.save(userProfile));
    }

    public void deleteUserProfile(String id){
        userProfileRepository.deleteById(id);
    }
}
