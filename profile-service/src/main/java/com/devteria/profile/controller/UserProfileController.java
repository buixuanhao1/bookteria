package com.devteria.profile.controller;

import com.devteria.profile.dto.request.ProfileCreationRequest;
import com.devteria.profile.dto.request.ProfileUpdateRequest;
import com.devteria.profile.dto.response.UserProfileResponse;
import com.devteria.profile.service.UserProfileService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserProfileController {
    UserProfileService userProfileService;

    @PostMapping("/users")
    UserProfileResponse CreateUserProfile(@RequestBody ProfileCreationRequest request){
        return this.userProfileService.createUserProfile(request);
    }

    @GetMapping("/users/{profileId}")
    UserProfileResponse GetUserProfile(@PathVariable String profileId){
        return this.userProfileService.getUserProfile(profileId);
    }

    @PutMapping("/users/{profileId}")
    UserProfileResponse UpdateUserProfile(@PathVariable String profileId,
                                          @RequestBody ProfileUpdateRequest request){
        return userProfileService.updateUserProfile(profileId, request);
    }

    @DeleteMapping("/users/{profileId}")
    void DeleteUserProfile(@PathVariable String profileId){
        userProfileService.deleteUserProfile(profileId);
    }
}
