/* Map of the apartment */

/* Rooms */
room(kitchen).
room(bedroom).
room(bathroom).

/* Furniture and equipment */
furniture(tv).
furniture(desk).
furniture(chair).
furniture(bed).
furniture(dresser).
furniture(fridge).
furniture(dining_table).
furniture(chair).
furniture(bath).
furniture(toilet).

/* Location objects */
location(fridge, kitchen).
location(dining_table, kitchen).
location(chair, kitchen).
location(bath, bathroom).
location(toilet, bathroom).
location(tv, bedroom).
location(desk, bedroom).
location(chair, bedroom).
location(bad, bedroom).
location(dresser, bedroom).

/* Connections between rooms */
connected(bedroom, kitchen).
connected(bedroom, bathroom).

/* Spatial relationships */
next_to(X, Y) :-
    connected(X, Y).
next_to(X, Y) :-
    connected(Y, X).

on(dining_table, plates).


:- dynamic at/2.
:- dynamic current_room/1.

/* Dynamic elements */
at(chair, bedroom).
at(plates, dining_table).
current_room(kitchen).

/* Clauses (Definitions of New Terms) */
inside(Item, Room) :-
    at(Item, Room).

/* Rules to add and remove items */
add_item(Item, Location) :-
    assert(at(Item, Location)).

remove_item(Item, Location) :-
    retract(at(Item, Location)).

/* Navigation rules */
go(Room) :-
    room(Room),
    (current_room(CurrentRoom), CurrentRoom \= Room ->
        retract(current_room(CurrentRoom)),
        asserta(current_room(Room)),
        write('You moved to the '), write(Room), write('.'),
        nl
    ;
        write('You are already in the '), write(Room), write('.'),
        nl
    ).

look :-
    current_room(CurrentRoom),
    write('You are in the '), write(CurrentRoom), write('.'),
    nl.

/* Recursive predicate to search for an item in different rooms */
search_item(Item, Room) :-
    at(Item, Room),
    write('Found the '), write(Item), write(' in the '), write(Room), write('.'),
    nl.

search_item(Item, Room) :-
    next_to(Room, NextRoom),
    search_item(Item, NextRoom).

/* Store list of items in a room */
items_in_room(Location, Items) :-
    findall(Item, at(Item, Location), Items).
  
furniture(Room, FurnitureList) :-
    findall(Furniture, location(Furniture, Room), FurnitureList).