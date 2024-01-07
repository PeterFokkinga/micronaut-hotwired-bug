package io.micronaut.turbo;

import jakarta.inject.Singleton;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Singleton
public class EpisodesRepository {
    private static final List<Episode> EPISODES = new ArrayList<>();

    static {
        EPISODES.add(Episode.builder()
                .id(1)
                .title("Jamming With Edward")
                .body("Something is carving giant pictures on Earth's surface.")
                .build());
        EPISODES.add(Episode.builder()
                        .id(2)
                        .title("Bohemian Rhapsody")
                        .body("There's a large bounty on the head of the mastermind behind several Astral Gate robberies.")
                .build());
    }
    public List<Episode> loadAllEpisodes() {
        return EPISODES;
    }

    public Optional<Episode> loadById(int episodeId) {
        if (episodeId < 1 || episodeId > EPISODES.size()) {
            return Optional.empty();
        }
        return Optional.of(EPISODES.get(episodeId - 1));
    }

    public void update(Episode episode) {
        if (episode.getId() > EPISODES.size()) {
            EPISODES.add(episode);
        } else {
            EPISODES.remove(episode.getId() - 1);
            EPISODES.add(episode.getId() - 1, episode);
        }
    }
}
