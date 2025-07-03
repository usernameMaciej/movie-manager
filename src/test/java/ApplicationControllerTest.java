import com.usernameMaciej.controller.ApplicationController;
import com.usernameMaciej.controller.Option;
import com.usernameMaciej.exception.DuplicateException;
import com.usernameMaciej.exception.OptionNotExistsException;
import com.usernameMaciej.database.ApplicationDatabase;
import com.usernameMaciej.io.ApplicationInputOutput;
import com.usernameMaciej.model.Actor;
import com.usernameMaciej.model.Movie;
import com.usernameMaciej.model.TvSeries;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ApplicationControllerTest {

    @Mock
    private ApplicationDatabase database;

    @Mock
    private ApplicationInputOutput reader;

    @InjectMocks
    private ApplicationController controller;

    private Movie testMovie;
    private TvSeries testTvSeries;
    private Actor testActor;

    @BeforeEach
    void setUp() {
        testMovie = new Movie("Test Movie", "Test Director", 2020, "Genre", "Director", 120);
        testTvSeries = new TvSeries("Test Series", 2019, 10, "Test Producer", "Test Genre", "Test Description", 5);
        testActor = new Actor("John", "Doe", "Country");
    }

    @Test
    void chooseOption_ShouldAddMovie_WhenOptionIsAddMovie() throws DuplicateException {
        when(reader.createMovie()).thenReturn(testMovie);

        controller.chooseOption(Option.ADD_MOVIE.getNumber());

        verify(database).addMovie(testMovie);
    }

    @Test
    void chooseOption_ShouldAddTvSeries_WhenOptionIsAddTvSeries() throws DuplicateException {
        when(reader.createTvSeries()).thenReturn(testTvSeries);

        controller.chooseOption(Option.ADD_TV_SERIES.getNumber());

        verify(database).addTvSeries(testTvSeries);
    }

    @Test
    void chooseOption_ShouldAddActor_WhenOptionIsAddActor() throws DuplicateException {
        when(reader.createActor()).thenReturn(testActor);

        controller.chooseOption(Option.ADD_ACTOR.getNumber());

        verify(database).addActor(testActor);
    }

    @Test
    void chooseOption_ShouldThrowException_WhenOptionIsInvalid() {
        assertThrows(OptionNotExistsException.class,
                () -> controller.chooseOption(999));
    }

    @Test
    void addMovie_ShouldHandleDuplicateException() throws DuplicateException {
        when(reader.createMovie()).thenReturn(testMovie);
        doThrow(new DuplicateException("Duplicate")).when(database).addMovie(testMovie);

        controller.addMovie();

        verify(database).addMovie(testMovie);
    }

    @Test
    void addTvSeries_ShouldHandleDuplicateException() throws DuplicateException {
        when(reader.createTvSeries()).thenReturn(testTvSeries);
        doThrow(new DuplicateException("Duplicate")).when(database).addTvSeries(testTvSeries);

        controller.addTvSeries();

        verify(database).addTvSeries(testTvSeries);
    }

    @Test
    void addActor_ShouldHandleDuplicateException() throws DuplicateException {
        when(reader.createActor()).thenReturn(testActor);
        doThrow(new DuplicateException("Duplicate")).when(database).addActor(testActor);

        controller.addActor();

        verify(database).addActor(testActor);
    }

    @Test
    void exit_ShouldPrintGoodbyeMessage() {
        controller.exit();
        // Można dodać weryfikację wyjścia konsoli jeśli potrzebne
    }

    @Test
    void printOptions_ShouldDisplayAllOptions() {
        ApplicationController.printOptions();
        // Weryfikacja manualna - powinno wyświetlić wszystkie opcje
        // Można rozważyć przechwycenie System.out do weryfikacji
    }
}