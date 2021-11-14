project structure

follow clean arch on project [ data - domain - presentation ] layer

modules

- data module [depends on domain ] contains  remote for api  and local for cashing using db and datastore
- domain Module
- presentation Layer [depends on domain] (feature-games-list , feature-favorite-gener , feature-game-details)

