package edu.bsu.cs222;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class ArticleTest {
    private Article article;
    private List<Revision> revisions;

    @BeforeEach
    public void setUp() {
        revisions = Arrays.asList(
                new Revision("user1", LocalDateTime.of(2023, 9, 28, 12, 0)),
                new Revision("user2", LocalDateTime.of(2023, 9, 27, 11, 0))
        );
        article = new Article("Java", revisions);
    }

    @Test
    public void testGetLatestRevision() {
        Revision latestRevision = article.getLatestRevision();
        assertEquals(revisions.get(0), latestRevision);
    }
}
