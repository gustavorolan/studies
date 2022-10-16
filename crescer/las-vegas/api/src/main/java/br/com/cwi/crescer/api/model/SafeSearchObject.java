package br.com.cwi.crescer.api.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
@Getter
@Setter
@Builder
public class SafeSearchObject {
    ArrayList<SafeSearchAnnotationObject> responses;
}
