# Authelia container config

U direktorijumu authelia se nalazi inicijalna konfiguracija za authelia server.
U direktorijumu compose se nalazi compose fajl koji se koristi za kreiranje 
container-a.

Pre kreiranja container-a treba:
1. Kreirati direktorijum u kojem će se nalaziti konfiguracije za container-e
2. U kreiranom direktorijumu kreirati direktorijum authelia
3. U direktorijum authelia prekopirati sadržaj iz authelia direktorijuma sa repozitorijuma
4. Kreirati direktorijum mariadb
5. Kreirati direktorijum redis
6. Putanje ka direktorijumima izmeniti u compose fajlu:
   - unutar segmenta za servise, unutar svakog servisa pojedinačno, unutar volumes segmenta
   - zameniti putanju koja se nalazi levo od znaka ":" sa putanjom do novo kreiranog direktorijuma
7. Pokrenuti terminal u direktorijumu gde se nalazi compose fajl
8. Opciono - u slučaju da nemate instaliranu ekstenziju podman-compose, instalirati datu ekstenziju
9. Pokrenuti odgovarajuću komandu (za docker ili podman):
   - docker-compose -f compose.yaml up -d
   - podman-compose -f compose.yaml up -d
