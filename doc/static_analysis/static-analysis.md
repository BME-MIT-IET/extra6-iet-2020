# Statikus Kódanalízis

A statikus analízist SonarQube és SonaLint eszközök segítségével végeztem.

Az elsőként a SonarQube szervert telepítettem, más lehetőség hiányában, a saját asztali számítógépemen. Telepítés viszonylag egyszerű volt, pár környezeti változót kellett beállítnai, és mivel nekem több java jdk verzió is fel van telepítve, meg kellett adnom egy config fáljban a megfelelő jdk verzió elérési útját.
Telepítés után, a szerverre adminként bejelentkezve létrehoztam felhasználókat magamnak, valamint a többi csapattársamnak is ilyenkor tudnék felhasználokat létrehozni. Ezután az otthoni hálozatom routerében felvettem egy új port-forwwarding szabályt az számítógépem 9000-es portjára, hogy a csapattársaim is elérjék a SonarQube szervert. 
Ezután a SonarQube-ot kellet integrálnom az Ant build rendszerrel. Az integrációhoz a "SonarScanner for Ant"-et kell használni, amit egy jar file-ban kapunk. Ezt a jar fájlt én a lib mappában helyeztem el. Majd az Ant build.xml fáljában létrehoztam egy új build targetet, ahol ezt a jart meghivatkoztam.

SonarQube analízis eredménye a projekt kezdeti állapotára:

![](/doc/static_analysis/sonarqube1.png)

Fejlesztőkörnyezetek közül mi az IntelliJ IDEA mellett döntöttünk. Ehhez elérhető a SonarLint plugin, amely csatlakozni tud az előzőekben létrehozott SonarQube szerverhez, és így jelentősen megkönnyíti a statikus analízis által felfedett hibák követését és javítását. 
