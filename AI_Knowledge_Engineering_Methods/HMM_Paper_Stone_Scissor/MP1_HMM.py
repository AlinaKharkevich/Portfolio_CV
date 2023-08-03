import random
import matplotlib.pyplot as plt

class State:
    PAPER = 0
    ROCK = 1
    SCISSORS = 2

# list 'transition_matrix'
# transition probabilities from the 'paper' state to 'paper', 'rock', and 'scissors', respectively
transition_matrix = [
    [0.33, 0.33, 0.34],
    [0.34, 0.33, 0.33],
    [0.33, 0.34, 0.33]
]

def update_transition_matrix(prev, curr, payout):
    alpha = 0.1 #learning rate
    prev_index = prev
    curr_index = curr
    transition_matrix[prev_index][curr_index] += alpha * payout #based on the game outcome 
    row_sum = sum(transition_matrix[prev_index]) # row sum of the updated transition probabilities
    transition_matrix[prev_index] = [p / row_sum for p in transition_matrix[prev_index]] #normalizing

def play_game(player_choice, computer_choice):
    if player_choice == computer_choice: #same choice - tie = 0
        return 0
    elif (player_choice == State.PAPER and computer_choice == State.ROCK 
            or player_choice == State.ROCK and computer_choice == State.SCISSORS
            or player_choice == State.SCISSORS and computer_choice == State.PAPER):
        return 1 #winning options +1
    else:
        return -1 #lose -1

def choose_computer_state(curr_state):
    r = random.random()
    curr_index = curr_state
    if r < transition_matrix[curr_index][0]:# If random number is smaller than the probability of transitioning to paper, we will have paper
        return State.PAPER
    elif r < transition_matrix[curr_index][0] + transition_matrix[curr_index][1]: # If random number is between the probabilities of transitioning to paper and rock
        return State.ROCK
    else:
        return State.SCISSORS # Otherwise scissors

def main():

    num_games = int(input("How many games do you want to play? "))
    manual = input("Do you want to play manually or automatically? Enter 'm' or 'a': ")
    cash_register = 0
    cash_register_history = []
    wins = 0
    losses = 0
    ties = 0
    prev_computer_state = State.PAPER

    for i in range(num_games): # loop once for each game
        if manual == 'm':
            player_input = input(f"Game {i+1}: Enter your choice (rock, paper, or scissors): ")
            if player_input == "rock":
                player_state = State.ROCK
            elif player_input == "paper":
                player_state = State.PAPER
            elif player_input == "scissors":
                player_state = State.SCISSORS
            else:
                print("Invalid input. Please try again.")
                continue

        elif manual == 'a':
            player_state = random.randint(0, 2) #  in automatic game the state is choosem randomly
            if player_state == State.ROCK:
                player_input = "Rock"
            elif player_state == State.PAPER:
                player_input = "Paper"
            else:
                player_input = "Scissors"
            print(f"Game {i+1}:")
            print(f"Player - {player_input}") # Print player choice
        else:
            print("Invalid input. Please try again.")
            continue

        computer_state = choose_computer_state(prev_computer_state) # Paper is null computer state
        payout = play_game(player_state, computer_state) # +1,-1 or 0 outcome
        cash_register += payout
        cash_register_history.append(cash_register)
        update_transition_matrix(prev_computer_state, computer_state, payout)
        prev_computer_state = computer_state

        state_dict = {0: 'Rock', 1: 'Paper', 2: 'Scissors'}
        computer_state_str = state_dict[computer_state]
        print(f"Computer - {computer_state_str}") # Print computer choice
        
        if payout == 1:
            print("You win! = + 1")
            wins += 1
        elif payout == -1:
            print("You lose! = - 1")
            losses += 1
        else:
            print("Tie! = + 0")
            ties += 1
        print(f"Your score now: {cash_register}\n") # total cash rfegister result

    # Game summery
    differences = [cash_register_history[i] - cash_register_history[i-1] if i > 0 else cash_register_history[i] for i in range(num_games)]
    print("Game Summary: ")
    print(f"Points in each match: {differences}")
    print(f"Cash register score: {cash_register_history}")
   
    # Bar char for wins/loses/ties during a game
    labels = ['Wins', 'Losses', 'Ties']
    values = [wins, losses, ties]
    plt.bar(labels, values)
    plt.xlabel("Game Result")
    plt.ylabel("Number of Games")
    plt.title("Points in each match")
    for i, v in enumerate(values):
        plt.text(i, v, str(v), ha='center')
    plt.show()

    # Plot cash register history
    plt.plot(cash_register_history)
    plt.xlabel("Game Number")
    plt.ylabel("Cash Register")
    plt.title("Change in Cash Register Over Time")
    plt.plot(cash_register_history, marker='o', linestyle='solid')
    plt.show()

if __name__ == "__main__":
    main()