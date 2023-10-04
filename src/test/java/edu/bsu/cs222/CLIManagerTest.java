package edu.bsu.cs222;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class CLIManagerTest {

    private final ByteArrayOutputStream outputContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputContent));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(null);
    }

    @Test
    public void testProcessArticle() throws IOException {
        // Stubbing behavior
        WikipediaRevisionReader reader = mock(WikipediaRevisionReader.class);
        WikipediaRevisionParser parser = mock(WikipediaRevisionParser.class);

        String testArticleTitle = "TestArticle";
        String mockJsonData = "{...}";
        List<Revision> mockRevisions = Arrays.asList(
                new Revision("User1", LocalDateTime.now()),
                new Revision("User2", LocalDateTime.now())
        );

        when(reader.fetchRevisionData(testArticleTitle)).thenReturn(mockJsonData);
        when(parser.parseRevisions(mockJsonData)).thenReturn(mockRevisions);


        try {
            Field readerField = CLIManager.class.getDeclaredField("reader");
            Field parserField = CLIManager.class.getDeclaredField("parser");

            readerField.setAccessible(true);
            parserField.setAccessible(true);

            CLIManager manager = new CLIManager();
            readerField.set(manager, reader);
            parserField.set(manager, parser);

            manager.processArticle(testArticleTitle);

            assertTrue(outputContent.toString().contains("User1"));
            assertTrue(outputContent.toString().contains("User2"));

        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
