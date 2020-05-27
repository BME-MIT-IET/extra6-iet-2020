#  Technológia fókusz: Manuális kód ellenőrzés, átszervezés

## Projekt szétbontása több modulra

A projekt egyes részeinek kiszervezése külön osztályokba. Ezt fontosnak tartottuk, mind az áttekinthetőség és a későbbi Unit tesztek elvégzésének érdekében, mivel egy nagy osztályt nehezebb is egyben megérteni az azt olvasó fejlesztőnek, és ésszerű, tesztre alkalmas méretre is kell csökkenteni, hogy a későbbi tesztesetek előkészítése ne legyen túl körülményes és ne tartson a végtelenségig.

 ![](/doc/manual_code_review/manual_code_review.PNG)
 
Ezért a CSV2RDF.java fájlban található belső osztályokat külön packege-ekbe szerveztük az osztályok öröklési hierarchiája szerint. Sok esetben az így kiszervezett osztályok és konstruktoruk láthatóságát publikussá változtattuk, hogy elérhetőek legyenek az eddig azt használó modulok számára. Külön utilities package jött létre a kisebbnek vélt,  funkcionalitást támogató kódrészek elhelyezésére, és ezek kapcsán figyeltünk rá, hogy az osztályneveink a java-s elnevezési konvenciókat kövessék.