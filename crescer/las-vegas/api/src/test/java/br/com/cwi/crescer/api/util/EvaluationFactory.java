package br.com.cwi.crescer.api.util;

import br.com.cwi.crescer.api.controller.response.EvaluationResponse;
import br.com.cwi.crescer.api.model.Evaluation;

public class EvaluationFactory {
    public static Evaluation.EvaluationBuilder getBuilder(){
        return Evaluation.builder()
                .id(1L)
                .score(5)
                .comment("comment");
    }

    public static EvaluationResponse.EvaluationResponseBuilder getBuilderResponse(){
        return EvaluationResponse.builder()
                .id(1L)
                .score(5)
                .comment("comment");
    }


}
