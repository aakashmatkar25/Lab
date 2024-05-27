% Define the parent relationship
parent(john, mary).
parent(john, lisa).
parent(emma, mary).
parent(emma, lisa).
parent(lisa, sam).
parent(lisa, ann).

% Define the gender of individuals
male(john).
male(sam).
female(emma).
female(mary).
female(lisa).
female(ann).

% Define the father relationship
father(Father, Child) :- male(Father), parent(Father, Child).

% Define the mother relationship
mother(Mother, Child) :- female(Mother), parent(Mother, Child).

% Define the grandfather relationship
grandfather(Grandfather, Grandchild) :- father(Grandfather, Parent), parent(Parent, Grandchild).

% Define the grandmother relationship
grandmother(Grandmother, Grandchild) :- mother(Grandmother, Parent), parent(Parent, Grandchild).

% Define the sibling relationship
sibling(Sibling1, Sibling2) :-
    parent(Parent, Sibling1),
    parent(Parent, Sibling2),
    Sibling1 \= Sibling2.

% Define the aunt relationship
aunt(Aunt, NieceNephew) :-
    female(Aunt),
    sibling(Aunt, Parent),
    parent(Parent, NieceNephew).

% Define the uncle relationship
uncle(Uncle, NieceNephew) :-
    male(Uncle),
    sibling(Uncle, Parent),
    parent(Parent, NieceNephew).

% Define the cousin relationship
cousin(Cousin1, Cousin2) :-
    parent(Parent1, Cousin1),
    parent(Parent2, Cousin2),
    sibling(Parent1, Parent2).

% Define the ancestor relationship
ancestor(Ancestor, Descendant) :-
    parent(Ancestor, Descendant).
ancestor(Ancestor, Descendant) :-
    parent(Ancestor, Parent),
    ancestor(Parent, Descendant).

% Define the descendant relationship
descendant(Descendant, Ancestor) :- ancestor(Ancestor, Descendant).

% Query examples
% Who are the children of John and Emma?
% ?- parent(john, Child), parent(emma, Child).
% Who are the grandchildren of John?
% ?- grandfather(john, Grandchild).
% Who are the aunts of Sam?
% ?- aunt(Aunt, sam).
