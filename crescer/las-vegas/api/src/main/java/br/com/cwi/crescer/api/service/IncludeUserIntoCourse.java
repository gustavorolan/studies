package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.controller.request.IncludeUserIntoCourseRequest;
import br.com.cwi.crescer.api.controller.response.ResponseMessage;
import br.com.cwi.crescer.api.model.Course;
import br.com.cwi.crescer.api.security.model.UserAccount;
import br.com.cwi.crescer.api.security.repository.UserAccountRepository;
import br.com.cwi.crescer.api.service.finder.FindCourseWithThrow;
import br.com.cwi.crescer.api.service.finder.FindUserWithThrow;
import br.com.cwi.crescer.api.validator.InvalidateSelfOpperationValidator;
import br.com.cwi.crescer.api.validator.VerifyIfIsManagerValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IncludeUserIntoCourse {
    @Autowired
    private FindCourseWithThrow findCourseWithThrow;

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private FindUserWithThrow findUserWithThrow;

    @Autowired
    private VerifyIfIsManagerValidator verifyIfIsManagerValidator;

    @Autowired
    private InvalidateSelfOpperationValidator invalidateSelfOpperationValidator;

    public ResponseMessage include(Long courseId, IncludeUserIntoCourseRequest request) {
        verifyIfIsManagerValidator.verifyIsManager();

        UserAccount userToAdd = findUserWithThrow.findByEmailWithException(request.getEmail());

        invalidateSelfOpperationValidator.invalidateSelfOpperation(userToAdd);

        Course course = findCourseWithThrow.findByIdWithException(courseId);

        userToAdd.getMyCourses().add(course);
        course.setAuthor(userToAdd);
        userAccountRepository.save(userToAdd);

        return ResponseMessage.builder()
                .response("You have added " + userToAdd.getFullName()+ " to "+course.getName())
                .build();
    }
}
