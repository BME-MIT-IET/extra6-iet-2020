# Statikus Kódanalízis

A statikus analízist SonarQube és SonaLint eszközök segítségével végeztem.

Az elsőként a SonarQube szervert telepítettem, más lehetőség hiányában, a saját asztali számítógépemen. Telepítés viszonylag egyszerű volt, az egyetlen nehézséget az okozta, hogy nekem több java jdk verzió is fel van telepítve, ezért meg kellett adnom egy config fáljban a megfelelő verzió elérési útját. Telepítés után, a szerverre adminként bejelentkezve létrehoztam felhasználókat magamnak, valamint a többi csapattársamnak is ilyenkor tudnék felhasználokat létrehozni.

Ezután az otthoni hálozatom routerében felvettem egy új port-forwarding szabályt az számítógépem 9000-es portjára, hogy a csapattársaim is elérjék a SonarQube szervert. 

A következő feladat a SonarQube integrálása volt az Ant build rendszerrel. Az integrációhoz a "SonarScanner for Ant"-et kell használni, amit egy jar file formájában kapunk. Ezt a jar fájlt én a lib mappában helyeztem el, majd az Ant build.xml fáljában létrehoztam egy új build targetet, ahol erre a jarra hivatkoztam. Ezután projektkonyvtárban egy parancssorból az _ant sonar_ parancsot kiadva elvégezhető a statikus analízis.

SonarQube analízis eredménye a projekt kezdeti állapotára:

![](/doc/static_analysis/sonarqube1.png)

Fejlesztőkörnyezetek közül mi az IntelliJ IDEA mellett döntöttünk. Ehhez elérhető a SonarLint plugin, amely csatlakozni tud az előzőekben létrehozott SonarQube szerverhez, és így jelentősen megkönnyíti a statikus analízis által felfedett hibák követését és javítását. 

## Kódanalízis alapján javított hibák:

Több egyszerűbb kódrész is átírásra került, az érthetőség javítása érdekében:
 - _Try with Resources_ bevezetése a manuális _stream.close()_ metódushívások helyett
 - Egymásba ágyazott ?: operátorok átírása átláthatóbb _if(...)_ szerkezetre.
 - Kulcsszavak sorrendjének megváltoztatása, hogy azok kövessék a Java standardot.

 Valamint még a következő modosításokat végeztük:
  - Egy privát függvényt áthelyeztünk egy belső osztályon belülre, mert az nem volt az osztályon kívül meghívva.
  - Bevezettünk egy _Logger_ objektumot a _System.out.println(...)_ hívások kiváltására.

  A hibajavítások után a SonarQube analízis eredménye:

  ![](/doc/static_analysis/sonarqube2.png)
  
  Látható, hogy a javítások után a SonarQube _Quality Gate_-jének állapota _Failed_ lett. Ez csak amiatt van, mert a SonarQube alapértelmezett beállításoknál megköveteli, hogy az újonnan írt kódrészek tesztlefedettsége legalább 80% legyen. Mivel nekünk jelenleg nincsenek egységtesztjeink, így ez nem is teljesül. 
