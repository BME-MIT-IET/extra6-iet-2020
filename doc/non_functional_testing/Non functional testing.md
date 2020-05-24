# Felhasználó Fókusz: Nem funkciónális jellemzők vizsgálata

## Teljesítmény

A programhoz készült példa csv fálj mindössze 4 rekordot tartalmaz, ezért ahhoz, hogy érdemi teljesítmény tesztelést tudjunk végezni, az első feladat az volt, hogy létrehozzunk egy olyan programot, ami tetszőleges méretű bemeneti csv fáljt tud generálni. Ezt egy egyszerű java programmal oldottuk meg, ami az nft_inputgenerator mappában található.

Az erőforráshasználat megfigyelésére az ej-technologies JProfiler programot használtam.
Két általunk generált csv fájlal teszteltünk: egy 500,000 sorossal és egy 1,000,000 sorossal.
A tesztekhez továbbra is az autós példa ttl fájlját használtuk.
Az erőforráshasználat az 500,000 soros fáljra:

 ![](/doc/non_functional_testing/performance_analysis1.PNG)
 
 Futási idő: 21.2s  
 Legmagasabb memória használat: 310.2 MB
 
 
 Az erőforráshasználat az 1,000,000 soros fáljra:

 ![](/doc/non_functional_testing/performance_analysis2.PNG)
 
 Futási idő: 39.2s  
 Legmagasabb memória használat: 311.8 MB
 
Megfigyelések:
  - A program jól skálázható, a futási idő a bemenet méretének körülbelül lineáris függvénye. (O(n))
  - A memóriahasználat nem függ a bemenet méretétől.
  - A CPU használat a JVM indulásakor felugrik, majd beáll 6.25%-ra. Ez azért van, mert a tesztkörnyezetben 16 szál volt elérhető, és a program 1 szálon fut, az a szál viszont 100%-ra van terhelve. Amennyiben cél a futásidő csökkentése, érdemes lenne párhuzamosítással próbálkozni.
  
  ## Biztonság
  
  
