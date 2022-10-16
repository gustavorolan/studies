package br.com.cwi.crescer.api.mapper;

import br.com.cwi.crescer.api.controller.response.MyMessagesResponse;
import br.com.cwi.crescer.api.model.Chat;
import br.com.cwi.crescer.api.security.model.UserAccount;
import org.springframework.stereotype.Component;

@Component
public class MyMessagesMapper {
    public MyMessagesResponse toResponse(UserAccount userAccount, Chat chat){
        return MyMessagesResponse.builder()
                .id(userAccount.getId())
                .fullName(userAccount.getFullName())
                .email(userAccount.getEmail())
                .assessment(userAccount.getAssessment())
                .permission(userAccount.getPermission())
                .presentation(userAccount.getPresentation())
                .relevantLinks(userAccount.getRelevantLinks())
                .imageId(userAccount.getProfileImage().getId())
                .read(chat.isRead())
                .build();
    }
}
