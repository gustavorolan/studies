package br.com.cwi.crescer.api.util;

import br.com.cwi.crescer.api.controller.request.RegisterUserRequest;
import br.com.cwi.crescer.api.controller.response.RegisteredCoursesResponse;
import br.com.cwi.crescer.api.controller.response.UserCompleteResponse;
import br.com.cwi.crescer.api.controller.response.UserResponse;
import br.com.cwi.crescer.api.model.Attachment;
import br.com.cwi.crescer.api.security.model.UserAccount;

import java.util.ArrayList;

public class UserAccountFactory {

    public static  UserAccount get(){
        return getBuilder().build();
    }

    public static UserAccount.UserAccountBuilder getBuilder(){
        return UserAccount.builder()
                .id(1L)
                .fullName("name")
                .registration("1")
                .email("name@email.com")
                .identifier("identifier")
                .password("password")
                .createdComments(new ArrayList<>())
                .createdCourses(new ArrayList<>())
                .createdCourses(new ArrayList<>())
                .profileImage(new Attachment())
                .myCourses(new ArrayList<>())
                .createdVideos(new ArrayList<>())
                .doubts(new ArrayList<>())
                .assessment(5)
                .evaluations(new ArrayList<>());
    }

    public static UserResponse getUserResponse(){
        return getUserResponseBuilder().build();
    }
    public static UserResponse.UserResponseBuilder getUserResponseBuilder(){
        return UserResponse.builder()
                .id(1L)
                .fullName("name")
                .email("name@email.com")
                .assessment(5);
    }

    public static RegisteredCoursesResponse.RegisteredCoursesResponseBuilder getBuilderUserWithCourses(){
        return RegisteredCoursesResponse.builder()
                .id(1L)
                .email("name@email.com");
    }

    public static RegisterUserRequest getUserRequest(){
        return RegisterUserRequest.builder()
                .fullName("name")
                .email("name@email.com")
                .password("password")
                .build();
    }

    public static UserCompleteResponse.UserCompleteResponseBuilder getBuilderUserCompleteResponse (){
        return UserCompleteResponse.builder()
                .id(1L)
                .fullName("name")
                .email("name@email.com")
                .assessment(5);

    }
}
