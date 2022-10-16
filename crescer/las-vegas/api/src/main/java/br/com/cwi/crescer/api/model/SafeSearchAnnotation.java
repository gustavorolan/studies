package br.com.cwi.crescer.api.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SafeSearchAnnotation {
    private String adult;
    private String spoof;
    private String medical;
    private String violence;
    private String racy;
}
