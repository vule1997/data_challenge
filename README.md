# data_challenge
Napravljene su 4 tabele: League, Team, Match i Goal
Respektivno, napravljena su 4 servisa i 4 skladista koja komuniciraju sa respektivnom bazom.

EventController je prva klasa koja se okida kada pristupimo /teams, i poseduje instance servisa sve 4 tabele koje koristi za pristupe bazi.

Kroz jsonl fajl se prolazi samo prvi put kada pristupimo /teams putanji na lokalnom serveru.
Prvo je radjeno parsiranje gde uz pomoc json jar-a izvlacimo potrebne podatke i popunjavamo bazu (parseData metoda)

*Red u tabeli Match se puni iz 2 puta, jednom za start, jednom za end (bilo kojim redom).
*Red u tabelu Goal se dodaje samo ako jedan takav vec ne postoji kako bi izbegli duple rezultate.

Kada zavrsimo sa parsiranjem, odradjeno je uklanjanje meceva koji nemaju neki od parametara, start ili finish (cleanData metoda)

Sada mozemo izracunati i poredak u tabeli.
Za svaki mec koji postoji, pretrazeni su golovi za domace ekipe i goste, a onda je odradjeno azuriranje poena i razlike golova dva tima (getResults metoda)

Poredak u odredjenoj tabeli dobijamo GET-om za putanju team/?.(teams/1,teams/2...)

Kod je poprilicno intuitivan i ima prostora za napredak pocevsi od nekog frontenda.
