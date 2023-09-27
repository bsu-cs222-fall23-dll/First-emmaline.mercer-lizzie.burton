package edu.bsu.cs222;

import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.List;

public class ArticleTest {

    private static final String SAMPLE_TITLE = "Sample Article";
    private Article article;
    private Revision firstRevision;
    private Revision secondRevision;

//    @BeforeEach
//    public void setUp() {
//        firstRevision = new Revision("JohnDoe", "2023-09-25T12:30:45+01:00");
//        secondRevision = new Revision("JaneDoe", "2023-09-24T11:20:35+01:00");
//        List<Revision> revisions = Arrays.asList(firstRevision, secondRevision);
//        article = new Article(SAMPLE_TITLE, revisions);
//    }

    @Test
    public void testGetTitle() {
        Assertions.assertEquals(SAMPLE_TITLE, article.getTitle());
    }

    @Test
    public void testGetRevisions() {
        Assertions.assertTrue(article.getRevisions().contains(firstRevision));
        Assertions.assertTrue(article.getRevisions().contains(secondRevision));
    }

    @Test
    public void testGetLatestRevision() {
        Assertions.assertEquals(firstRevision, article.getLatestRevision());
    }

    @AfterEach
    public void tearDown() {
        article = null;
    }
}
