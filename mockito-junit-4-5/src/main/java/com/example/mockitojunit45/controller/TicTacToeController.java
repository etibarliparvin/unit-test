package com.example.mockitojunit45.controller;

import com.example.mockitojunit45.dto.MoveDto;
import com.example.mockitojunit45.dto.Result;
import com.example.mockitojunit45.tictactoe.TicTacToe;
import com.example.mockitojunit45.tictactoe.TicTacToeImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("game")
@RequiredArgsConstructor
public class TicTacToeController {

    private final TicTacToe ticTacToe;

    @PostMapping
    public Result play(@RequestBody MoveDto move) {
        return new Result(ticTacToe.play(move.getX(), move.getY()));
    }
}
