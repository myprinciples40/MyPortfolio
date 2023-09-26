package com.fastcampus.boardproject.service;

import com.fastcampus.boardproject.domain.Hashtag;
import com.fastcampus.boardproject.repository.HashtagRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@DisplayName("Business Logic Test - Hashtag")
@ExtendWith(MockitoExtension.class)
class HashtagServiceTest {
    @InjectMocks private HashtagService sut;

    @Mock private HashtagRepository hashtagRepository;

    @DisplayName("When parsing the body, it returns the hashtag names without duplicates.")
    @MethodSource
    @ParameterizedTest(name= "[{index}] \"{0}\" => {1}")
    void givenContent_whenParsing_thenReturnUniqueHashtagNames(String input, Set<String> expected) {
        // Given

        // When
        Set<String> actual = sut.parseHashtagNames(input);

        // Then
        assertThat(actual).containsExactlyInAnyOrderElementsOf(expected);
        then(hashtagRepository).shouldHaveNoInteractions();
    }

    static Stream<Arguments> givenContent_whenParsing_thenReturnUniqueHashtagNames() {
        return Stream.of(
                arguments(null, Set.of()),
                arguments("", Set.of()),
                arguments("   ", Set.of()),
                arguments("#", Set.of()),
                arguments("  #", Set.of()),
                arguments("#   ", Set.of()),
                arguments("java", Set.of()),
                arguments("java#", Set.of()),
                arguments("ja#va", Set.of("va")),
                arguments("#java", Set.of("java")),
                arguments("#java_spring", Set.of("java_spring")),
                arguments("#java-spring", Set.of("java")),
                arguments("#_java_spring", Set.of("_java_spring")),
                arguments("#-java-spring", Set.of()),
                arguments("#_java_spring__", Set.of("_java_spring__")),
                arguments("#java#spring", Set.of("java", "spring")),
                arguments("#java #spring", Set.of("java", "spring")),
                arguments("#java  #spring", Set.of("java", "spring")),
                arguments("#java   #spring", Set.of("java", "spring")),
                arguments("#java     #spring", Set.of("java", "spring")),
                arguments("  #java     #spring ", Set.of("java", "spring")),
                arguments("   #java     #spring   ", Set.of("java", "spring")),
                arguments("#java#spring#boot", Set.of("java", "spring", "boot")),
                arguments("#java #spring#boot", Set.of("java", "spring", "boot")),
                arguments("#java#spring #boot", Set.of("java", "spring", "boot")),
                arguments("#java,#spring,#boot", Set.of("java", "spring", "boot")),
                arguments("#java.#spring;#boot", Set.of("java", "spring", "boot")),
                arguments("#java|#spring:#boot", Set.of("java", "spring", "boot")),
                arguments("#java #spring  #boot", Set.of("java", "spring", "boot")),
                arguments("   #java,? #spring  ...  #boot ", Set.of("java", "spring", "boot")),
                arguments("#java#java#spring#boot", Set.of("java", "spring", "boot")),
                arguments("#java#java#java#spring#boot", Set.of("java", "spring", "boot")),
                arguments("#java#spring#java#boot#java", Set.of("java", "spring", "boot")),
                arguments("#java#spring very Long Articles~~~~~~~~~~~~~~~~~~~~~", Set.of("java", "spring")),
                arguments("Very Long Articles~~~~~~~~~~~~~~~~~~~~~#java#spring", Set.of("java", "spring")),
                arguments("Very Long Articles~~~~~~#java#spring~~~~~~~~~~~~~~~", Set.of("java", "spring")),
                arguments("Very Long Articles~~~~~~#java~~~~~~~#spring~~~~~~~~", Set.of("java", "spring"))
        );
    }

    @DisplayName("If you enter hashtag names, it will return the stored hashtags that match the names without duplicates.")
    @Test
    void givenHashtagNames_whenFindingHashtags_thenReturnsHashtagSet() {
        // Given
        Set<String> hashtagNames = Set.of("java", "spring", "boots");
        given(hashtagRepository.findByHashtagNameIn(hashtagNames)).willReturn(List.of(
                Hashtag.of("java"),
                Hashtag.of("spring")
        ));

        // When
        Set<Hashtag> hashtags = sut.findHashtagsByNames(hashtagNames);

        // Then
        assertThat(hashtags).hasSize(2);
        then(hashtagRepository).should().findByHashtagNameIn(hashtagNames);
    }
}