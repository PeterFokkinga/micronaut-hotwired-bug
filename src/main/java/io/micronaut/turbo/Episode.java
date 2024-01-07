package io.micronaut.turbo;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Builder
@Getter
@ToString
@RequiredArgsConstructor
public class Episode {
    private final int id;
    private final String title;
    @ToString.Exclude
    private final String body;
}
