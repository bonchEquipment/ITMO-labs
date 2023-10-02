:- dynamic(player/1).
:- dynamic(wins/2).
:- dynamic(rating/2).
:- dynamic(teacher/2).
:- dynamic(game/4).

%rules
%determin if player is a grandmaster
grandmaster(Player) :- player(Player), wins(Player, Wins), Wins >= 10,
    rating(Player, fideRating(Rating)), Rating>=2500.
%determin if given player ever played with each other
playedAgainst(Player_1, Player_2) :- game(Player_1, Player_2, _, _); game(Player_2, Player_1, _, _).
teacherOfTeacher(Teacher, Student) :-
    teacher(Teacher, Direct_Student),
    teacher(Direct_Student, Student).
strongPlayer(Player) :- rating(Player, Rating), Rating >= 2700.
%determin which player is stronger
isPlayerStronger(Player_1, Player_2):-rating(Player_1, X),rating(Player_2, Y),X>Y.

recommendByStyle(Style, Games) :-
    findall([Name, Year], game(Name, Year, Style, _, _), Games).
recommendByPlayer(Player) :- (game(Name, Year, _, Player, _);
    game(Name, Year, _, _, Player)),
    write(Name), write(':'), write(Year).
recommendByByPlayerAndStyle(Player, Style):-
    (game(Name, Year, Style, Player, _);
    game(Name, Year, Style, _, Player)),
    write(Name), write(':'), write(Year).

recommendGame(Player, Style) :-
    ( recommendByByPlayerAndStyle(Player, Style);
      recommendByStyle(Style);
      recommendByPlayer(Player)
    ).




%facts
player('Arsenii Deviatkin').
player('Magnus Carlsen').
player('Fabiano Caruana').
player('Ian Nepomniachtchi').
player('Sergey Karjakin').
player('Hikaru Nakamura').
player('Olga Rubtsova').
player('Sunil Weeramantry').
player('Anatoly Karpov').
player('Vishy Anand').
player('Judit Polgar').
player('Garry Kasparov').
player('Tyler1').
player('Mikhail Tal').

wins('Magnus Carlsen', 15).
wins('Fabiano Caruana', 10).
wins('Ian Nepomniachtchi', 12).
wins('Sergey Karjakin', 8).
wins('Hikaru Nakamura', 11).
wins('Olga Rubtsova', 3).
wins('Sunil Weeramantry', 2).
wins('Anatoly Karpov', 20).
wins('Vishy Anand', 18).
wins('Judit Polgar', 9).
wins('Garry Kasparov', 22).
wins('Tyler1', 0).
wins('Arsenii Deviatkin', 0).
wins('Mikhail Tal', 8).


rating('Magnus Carlsen', fideRating(2850)).
rating('Fabiano Caruana', fideRating(2820)).
rating('Ian Nepomniachtchi', fideRating(2790)).
rating('Sergey Karjakin', fideRating(2750)).
rating('Hikaru Nakamura', fideRating(2765)).
rating('Olga Rubtsova', fideRating(2400)).
rating('Sunil Weeramantry', fideRating(2450)).
rating('Anatoly Karpov', fideRating(2700)).
rating('Vishy Anand', fideRating(2755)).
rating('Judit Polgar', fideRating(2680)).
rating('Garry Kasparov', fideRating(2810)).
rating('Tyler1', fideRating(1020)).
rating('Arsenii Deviatkin', fideRating(510)).
rating('Mikhail Tal', fideRating(2710)).



%teacher(teacher, student)
teacher('Mikhail Tal', 'Anatoly Karpov').
teacher('Anatoly Karpov', 'Ian Nepomniachtchi').
teacher('Sunil Weeramantry', 'Hikaru Nakamura').



%game(game_name, year, game_type, player_1, player_2)
game('Positional Clash', 2011, 'POSITIONAL', 'Magnus Carlsen', 'Ian Nepomniachtchi' ).
game('Tactical Showdown', 2004, 'TACTICAL', 'Hikaru Nakamura', 'Ian Nepomniachtchi').
game('Strategic Encounter', 1986, 'STRATEGIC', 'Anatoly Karpov', 'Sunil Weeramantry').
game('Kasparov vs Polgar Battle', 1998, 'STRATEGIC', 'Garry Kasparov', 'Judit Polgar').


%player('Sergey Karjakin').
%player('Arsenii Deviatkin').
%wins('Garry Kasparov', Wins).
%chessMan(Player), not(wins(Player, 0)).
%chessMan(Player), (wins(Player, Wins), Wins >= 10); (rating(Player, fideRating(Rating)), Rating >= 2700).
%strongPlayer(Player), rating(Player, fideRating(Rating)), Rating >= 2800.
%grandmaster(Player), playedAgainst(Player, 'Hikaru Nakamura')
%teacherOfTeacher('Anatoly Karpov', Student).
