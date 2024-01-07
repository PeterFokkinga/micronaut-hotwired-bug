package io.micronaut.turbo;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.views.View;
import io.micronaut.views.turbo.TurboFrameView;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MainController {
    private final EpisodesRepository repository;

    @View("main")
    @Get("/")
    Map<String, Object> index() {
        return Map.of(
                "turbo", false,
                "episode", repository.loadAllEpisodes().get(0),
                "episodes", repository.loadAllEpisodes());
    }


    @TurboFrameView("view")
//    @View("main")
    @Produces(MediaType.TEXT_HTML)
    @Get("/view/{episodeId}")
    Map<String, Object> viewMessage(@PathVariable int episodeId) {
        return Map.of(
                "episode", repository.loadById(episodeId)
                        .orElseThrow());
    }


    @TurboFrameView("edit")
//    @View("main")
    @Produces(MediaType.TEXT_HTML)
    @Get("/edit/{episodeId}")
    Map<String, Object> editForm(@PathVariable int episodeId) {
        return Map.of(
                "edit", true,
                "episode", repository.loadById(episodeId)
                        .orElseThrow());
    }


@Consumes(MediaType.MULTIPART_FORM_DATA)
@TurboFrameView("view")
@Produces(value = MediaType.TEXT_HTML)
@Post("/edit")
Map<String, Object> processEdit(@Part int id, @Part String title, @Part String body) {
        var episode = Episode.builder().id(id).title(title).body(body).build();
    repository.update(episode);
    return Map.of(
            "episode", episode,
            "episodes", repository.loadAllEpisodes());
}
}
