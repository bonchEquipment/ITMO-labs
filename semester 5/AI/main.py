from pyswip import Prolog

prolog = Prolog()
prolog.consult("test.pl")

def get_user_input():
    print("\nTell me about your preferences in format:\n"
          "I like *GAME TYPE* games, my favorite player is *Player_Name*")
    return input("Your preferences: ")


def parse_user_input(user_input):
    game_type = None
    favorite_player = ""

    tokens = user_input.split()
    if 'like' in tokens and tokens.index('like') < len(tokens) - 1:
        game_type = tokens[tokens.index('like') + 1].strip(',')

    if 'player' in tokens:
        for i, token in enumerate(tokens):
            if i <= tokens.index('is'):
                continue
            favorite_player += token + " "

    favorite_player = favorite_player.rstrip()
    return game_type, favorite_player


def find_by_game_type(game_type):
    query = f"recommendByStyle('{game_type}', Games)"
    return list(prolog.query(query))

def find_by_player(favorite_player):
    query = f"recommendByPlayer('{favorite_player}')"
    return list(prolog.query(query))


def find_complete_match(favorite_player, game_type):
    query = f"recommendByByPlayerAndStyle('{favorite_player}', '{game_type}')"
    return list(prolog.query(query))


def provide_recommendations(solution):
    if len(solution) == 0:
        print("No recommendations found.")
        return

    print("I recommend you to watch the game:")
    solution = solution['Games'][0]
    game_name = solution[0]
    year = solution[1]
    print(f"{game_name} played in {year}")




def main():

    while True:
        user_input = get_user_input()

        game_type, favorite_player = parse_user_input(user_input)
        result = find_complete_match(favorite_player, game_type)
        if len(result[0]) == 0:
            result = find_by_game_type(game_type)
            if len(result[0]) == 0:
                result = find_by_player(favorite_player)

        provide_recommendations(result[0])


if __name__ == "__main__":
    main()

#I like POSITIONAL games, my favorite player is Magnus Carlsen