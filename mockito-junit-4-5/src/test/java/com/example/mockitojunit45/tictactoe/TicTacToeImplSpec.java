package com.example.mockitojunit45.tictactoe;

import com.example.mockitojunit45.domain.Step;
import com.example.mockitojunit45.repository.StepRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TicTacToeImplSpec {

    @Mock
    private StepRepository stepRepository;

    private TicTacToeImpl ticTacToe;

    @BeforeEach
    public final void before() {
        ticTacToe = new TicTacToeImpl(stepRepository);
    }

    @Test
    public void whenXOutsideBoardThenRuntimeException() {
        assertThatThrownBy(() -> ticTacToe.play(5, 2))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("X is outside board");
    }

    @Test
    public void whenYOutsideBoardThenRuntimeException() {
        assertThatThrownBy(() -> ticTacToe.play(2, 5))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Y is outside board");
    }

    @Test
    public void whenOccupiedThenRuntimeException() {
        // Arrange
        Step step = new Step();
        step.setXCord(1);
        step.setYCord(1);
        step.setPlayer('X');
        List steps = List.of(step);
        when(stepRepository.findAll()).thenReturn(steps);

        // Act & Assert
        assertThatThrownBy(() -> ticTacToe.play(1, 1)) // player y
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Box is occupied");
    }

    @Test
    public void givenFirstTurnWhenNextPlayerThenX() {
        assertThat(ticTacToe.nextPlayer()).isEqualTo('X');
    }

    @Test
    public void givenFirstTurnWhenNextPlayerThenO() {
        // Arrange
        Step step = new Step();
        step.setXCord(1);
        step.setYCord(1);
        step.setPlayer('X');
        List steps = List.of(step);
        when(stepRepository.findAll()).thenReturn(steps);

        // Act & Assert
        assertThat(ticTacToe.nextPlayer()).isEqualTo('O');
    }

    @Test
    public void whenPlayThenNoWinner() {
        String actual = ticTacToe.play(1, 1);
        assertThat(actual).isEqualTo("No winner");
    }

    @Test
    public void whenPlayAndWholeVerticalLineThenWinner() {
        // Arrange
        Step x1 = new Step(1L, 1, 1, 'X');
        Step y1 = new Step(2L, 1, 2, 'O');
        Step x2 = new Step(3L, 2, 1, 'X');
        Step y2 = new Step(4L, 2, 2, 'O');
        when(stepRepository.findAll()).thenReturn(List.of(x1, y1, x2, y2));

        // Act & Assert
        assertThat(ticTacToe.play(3, 1)).isEqualTo("X is the winner"); // X
    }

    @Test
    public void whenPlayAndWholeHorizontalLineThenWinner() {
        // Arrange
        Step x1 = new Step(1L, 2, 1, 'X');
        Step y1 = new Step(2L, 1, 1, 'O');
        Step x2 = new Step(3L, 3, 1, 'X');
        Step y2 = new Step(4L, 1, 2, 'O');
        Step x3 = new Step(5L, 2, 2, 'X');
        when(stepRepository.findAll()).thenReturn(List.of(x1, y1, x2, y2, x3));

        // Act & Assert
        assertThat(ticTacToe.play(1, 3)).isEqualTo("O is the winner");
    }

    @Test
    public void whenPlayAndTopBottomDiagonalLineThenWinner() {
        // Arrange
        Step x1 = new Step(1L, 1, 1, 'X');
        Step y1 = new Step(2L, 1, 2, 'O');
        Step x2 = new Step(3L, 2, 2, 'X');
        Step y2 = new Step(4L, 1, 3, 'O');
        when(stepRepository.findAll()).thenReturn(List.of(x1, y1, x2, y2));

        // Act & Assert
        assertThat(ticTacToe.play(3, 3)).isEqualTo("X is the winner");
    }

    @Test
    public void whenPlayAndBottomTopDiagonalLineThenWinner() {
        // Arrange
        Step x1 = new Step(1L, 1, 3, 'X');
        Step y1 = new Step(2L, 1, 1, 'O');
        Step x2 = new Step(3L, 2, 2, 'X');
        Step y2 = new Step(4L, 1, 2, 'O');
        when(stepRepository.findAll()).thenReturn(List.of(x1, y1, x2, y2));

        // Act & Assert
        assertThat(ticTacToe.play(3, 1)).isEqualTo("X is the winner");
    }

    @Test
    public void whenAllBoxesAreFilledThenDraw() {
        // Arrange
        Step x1 = new Step(1L, 1, 1, 'X');
        Step y1 = new Step(2L, 1, 3, 'O');
        Step x2 = new Step(3L, 1, 2, 'X');
        Step y2 = new Step(4L, 2, 1, 'O');
        Step x3 = new Step(5L, 2, 3, 'X');
        Step y3 = new Step(6L, 2, 2, 'O');
        Step x4 = new Step(7L, 3, 2, 'X');
        Step y4 = new Step(8L, 3, 1, 'O');
        when(stepRepository.findAll()).thenReturn(List.of(x1, y1, x2, y2, x3, y3, x4, y4));

        // Act & Assert
        assertThat(ticTacToe.play(3, 3)).isEqualTo("The result is draw");
    }
}
