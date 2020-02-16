package dev.grack.repository

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class NetworkTest {

  companion object {
    const val successResponse = "{\"events\":[{\"idEvent\":\"602385\",\"idSoccerXML\":\"402236\",\"idAPIfootball\":null,\"strEvent\":\"Everton vs Crystal Palace\",\"strEventAlternate\":\"Crystal Palace @ Everton\",\"strFilename\":\"English Premier League 2020-02-08 Everton vs Crystal Palace\",\"strSport\":\"Soccer\",\"idLeague\":\"4328\",\"strLeague\":\"English Premier League\",\"strSeason\":\"1920\",\"strDescriptionEN\":null,\"strHomeTeam\":\"Everton\",\"strAwayTeam\":\"Crystal Palace\",\"intHomeScore\":\"3\",\"intRound\":\"26\",\"intAwayScore\":\"1\",\"intSpectators\":null,\"strHomeGoalDetails\":\"18':Bernard;59':Richarlison;88':Dominic Calvert-Lewin;\",\"strHomeRedCards\":\"\",\"strHomeYellowCards\":\"\",\"strHomeLineupGoalkeeper\":\"Jordan Pickford; \",\"strHomeLineupDefense\":\"Djibril Sidibe; Yerry Mina; Mason Holgate; Lucas Digne; \",\"strHomeLineupMidfield\":\"Theo Walcott; Gylfi Sigurdsson; Tom Davies; Alex Iwobi; \",\"strHomeLineupForward\":\"Dominic Calvert-Lewin; Richarlison; \",\"strHomeLineupSubstitutes\":\"Maarten Stekelenburg; Mason Holgate; Leighton Baines; Djibril Sidibe; Alex Iwobi; Tom Davies; Moise Kean; \",\"strHomeFormation\":null,\"strAwayRedCards\":\"\",\"strAwayYellowCards\":\"10':James McCarthy;\",\"strAwayGoalDetails\":\"51':Christian Benteke;\",\"strAwayLineupGoalkeeper\":\"Vicente Guaita; \",\"strAwayLineupDefense\":\"Joel Ward; James Tomkins; Gary Cahill; Patrick van Aanholt; \",\"strAwayLineupMidfield\":\"Cheikhou Kouyate; Luka Milivojevic; James McArthur; \",\"strAwayLineupForward\":\"Jordan Ayew; Christian Benteke; Wilfried Zaha; \",\"strAwayLineupSubstitutes\":\"Wayne Hennessey; Scott Dann; Tyrick Mitchell; Max Meyer; Cheikhou Kouyate; Andros Townsend; Jairo Riedewald; \",\"strAwayFormation\":null,\"intHomeShots\":null,\"intAwayShots\":null,\"dateEvent\":\"2020-02-08\",\"dateEventLocal\":null,\"strDate\":\"08\\/02\\/20\",\"strTime\":\"12:30:00\",\"strTimeLocal\":\"13:30:00\",\"strTVStation\":null,\"idHomeTeam\":\"133615\",\"idAwayTeam\":\"133632\",\"strResult\":null,\"strCircuit\":null,\"strCountry\":null,\"strCity\":null,\"strPoster\":null,\"strFanart\":null,\"strThumb\":null,\"strBanner\":null,\"strMap\":null,\"strTweet1\":null,\"strTweet2\":null,\"strTweet3\":null,\"strVideo\":null,\"strLocked\":\"unlocked\"},{\"idEvent\":\"602382\",\"idSoccerXML\":\"402233\",\"idAPIfootball\":null,\"strEvent\":\"Brighton vs Watford\",\"strEventAlternate\":\"Watford @ Brighton\",\"strFilename\":\"English Premier League 2020-02-08 Brighton vs Watford\",\"strSport\":\"Soccer\",\"idLeague\":\"4328\",\"strLeague\":\"English Premier League\",\"strSeason\":\"1920\",\"strDescriptionEN\":null,\"strHomeTeam\":\"Brighton\",\"strAwayTeam\":\"Watford\",\"intHomeScore\":\"1\",\"intRound\":\"26\",\"intAwayScore\":\"1\",\"intSpectators\":null,\"strHomeGoalDetails\":\"79':Adrian Mariappa (Own goal);\",\"strHomeRedCards\":\"\",\"strHomeYellowCards\":\"35':Ezequiel Schelotto;90':Solly March;\",\"strHomeLineupGoalkeeper\":\"Mathew Ryan; \",\"strHomeLineupDefense\":\"Martin Montoya; Adam Webster; Lewis Dunk; Bernardo; \",\"strHomeLineupMidfield\":\"Davy Propper; Dale Stephens; Aaron Mooy; \",\"strHomeLineupForward\":\"Pascal Gross; Glenn Murray; Leandro Trossard; \",\"strHomeLineupSubstitutes\":\"David Button; Adam Webster; Bernardo; Neal Maupay; Alireza Jahanbakhsh; Aaron Connolly; Steven Alzate; \",\"strHomeFormation\":null,\"strAwayRedCards\":\"\",\"strAwayYellowCards\":\"64':Will Hughes;65':Adrian Mariappa;\",\"strAwayGoalDetails\":\"19':Abdoulaye Doucoure;\",\"strAwayLineupGoalkeeper\":\"Ben Foster; \",\"strAwayLineupDefense\":\"Adrian Mariappa; Craig Cathcart; Adam Masina; Christian Kabasele; \",\"strAwayLineupMidfield\":\"Nathaniel Chalobah; Etienne Capoue; Roberto Pereyra; Abdoulaye Doucoure; Gerard Deulofeu; \",\"strAwayLineupForward\":\"Troy Deeney; \",\"strAwayLineupSubstitutes\":\"Heurelho Gomes; Craig Dawson; Jose Holebas; Nathaniel Chalobah; Ignacio Pussetto; Danny Welbeck; Andre Gray; \",\"strAwayFormation\":null,\"intHomeShots\":null,\"intAwayShots\":null,\"dateEvent\":\"2020-02-08\",\"dateEventLocal\":null,\"strDate\":\"08\\/02\\/20\",\"strTime\":\"17:30:00\",\"strTimeLocal\":\"18:30:00\",\"strTVStation\":null,\"idHomeTeam\":\"133619\",\"idAwayTeam\":\"133624\",\"strResult\":null,\"strCircuit\":null,\"strCountry\":null,\"strCity\":null,\"strPoster\":null,\"strFanart\":null,\"strThumb\":null,\"strBanner\":null,\"strMap\":null,\"strTweet1\":null,\"strTweet2\":null,\"strTweet3\":null,\"strVideo\":null,\"strLocked\":\"unlocked\"},{\"idEvent\":\"602374\",\"idSoccerXML\":\"402225\",\"idAPIfootball\":null,\"strEvent\":\"Burnley vs Arsenal\",\"strEventAlternate\":\"Arsenal @ Burnley\",\"strFilename\":\"English Premier League 2020-02-01 Burnley vs Arsenal\",\"strSport\":\"Soccer\",\"idLeague\":\"4328\",\"strLeague\":\"English Premier League\",\"strSeason\":\"1920\",\"strDescriptionEN\":null,\"strHomeTeam\":\"Burnley\",\"strAwayTeam\":\"Arsenal\",\"intHomeScore\":\"0\",\"intRound\":\"25\",\"intAwayScore\":\"0\",\"intSpectators\":null,\"strHomeGoalDetails\":\"\",\"strHomeRedCards\":\"\",\"strHomeYellowCards\":\"82':James Tarkowski;\",\"strHomeLineupGoalkeeper\":\"Nick Pope; \",\"strHomeLineupDefense\":\"Phil Bardsley; James Tarkowski; Ben Mee; Charlie Taylor; \",\"strHomeLineupMidfield\":\"Jeff Hendrick; Jack Cork; Ashley Westwood; Dwight McNeil; \",\"strHomeLineupForward\":\"Jay Rodriguez; Chris Wood; \",\"strHomeLineupSubstitutes\":\"Joe Hart; Robert Brady; Erik Pieters; Aaron Lennon; Phil Bardsley; Matej Vydra; Kevin Long; \",\"strHomeFormation\":null,\"strAwayRedCards\":\"\",\"strAwayYellowCards\":\"44':Mesut Ozil;55':Granit Xhaka;57':Lucas Torreira;\",\"strAwayGoalDetails\":\"\",\"strAwayLineupGoalkeeper\":\"Bernd Leno; \",\"strAwayLineupDefense\":\"Hector Bellerin; Sokratis Papastathopoulos; David Luiz; Bukayo Saka; \",\"strAwayLineupMidfield\":\"Lucas Torreira; Granit Xhaka; Nicolas Pepe; Mesut Ozil; Gabriel Martinelli; \",\"strAwayLineupForward\":\"Pierre-Emerick Aubameyang; \",\"strAwayLineupSubstitutes\":\"Emiliano Martinez; Sokratis Papastathopoulos; Lucas Torreira; Joseph Willock; Daniel Ceballos; Nicolas Pepe; Edward Nketiah; \",\"strAwayFormation\":null,\"intHomeShots\":null,\"intAwayShots\":null,\"dateEvent\":\"2020-02-02\",\"dateEventLocal\":null,\"strDate\":\"02\\/02\\/20\",\"strTime\":\"14:00:00\",\"strTimeLocal\":\"15:00:00\",\"strTVStation\":null,\"idHomeTeam\":\"133623\",\"idAwayTeam\":\"133604\",\"strResult\":null,\"strCircuit\":null,\"strCountry\":null,\"strCity\":null,\"strPoster\":null,\"strFanart\":null,\"strThumb\":null,\"strBanner\":null,\"strMap\":null,\"strTweet1\":null,\"strTweet2\":null,\"strTweet3\":null,\"strVideo\":null,\"strLocked\":\"unlocked\"},{\"idEvent\":\"602373\",\"idSoccerXML\":\"402224\",\"idAPIfootball\":null,\"strEvent\":\"Tottenham vs Man City\",\"strEventAlternate\":\"Man City @ Tottenham\",\"strFilename\":\"English Premier League 2020-02-01 Tottenham vs Man City\",\"strSport\":\"Soccer\",\"idLeague\":\"4328\",\"strLeague\":\"English Premier League\",\"strSeason\":\"1920\",\"strDescriptionEN\":null,\"strHomeTeam\":\"Tottenham\",\"strAwayTeam\":\"Man City\",\"intHomeScore\":\"2\",\"intRound\":\"25\",\"intAwayScore\":\"0\",\"intSpectators\":null,\"strHomeGoalDetails\":\"63':Steven Bergwijn;71':Heung-Min Son;\",\"strHomeRedCards\":\"\",\"strHomeYellowCards\":\"44':Toby Alderweireld;66':Giovani Lo Celso;\",\"strHomeLineupGoalkeeper\":\"Hugo Lloris; \",\"strHomeLineupDefense\":\"Serge Aurier; Toby Alderweireld; Davinson Sanchez; Japhet Tanganga; \",\"strHomeLineupMidfield\":\"Giovani Lo Celso; Harry Winks; Dele Alli; \",\"strHomeLineupForward\":\"Lucas Moura; Heung-Min Son; Steven Bergwijn; \",\"strHomeLineupSubstitutes\":\"Paulo Gazzaniga; Jan Vertonghen; Erik Lamela; Eric Dier; Ryan Sessegnon; Tanguy Ndombele; Gedson Fernandes; \",\"strHomeFormation\":null,\"strAwayRedCards\":\"\",\"strAwayYellowCards\":\"12':Raheem Sterling;33':Kyle Walker;43':Oleksandr Zinchenko;90':Rodri;\",\"strAwayGoalDetails\":\"40':Ilkay Guendogan;\",\"strAwayLineupGoalkeeper\":\"Ederson Moraes; \",\"strAwayLineupDefense\":\"Kyle Walker; Fernandinho; Aymeric Laporte; Oleksandr Zinchenko; \",\"strAwayLineupMidfield\":\"Kevin De Bruyne; David Silva; Rodri; \",\"strAwayLineupForward\":\"Riyad Mahrez; Sergio Aguero; Raheem Sterling; \",\"strAwayLineupSubstitutes\":\"Claudio Bravo; Joao Cancelo; Bernardo Silva; David Silva; Gabriel Jesus; Phil Foden; Eric Garcia; \",\"strAwayFormation\":null,\"intHomeShots\":null,\"intAwayShots\":null,\"dateEvent\":\"2020-02-02\",\"dateEventLocal\":null,\"strDate\":\"02\\/02\\/20\",\"strTime\":\"16:30:00\",\"strTimeLocal\":\"17:30:00\",\"strTVStation\":null,\"idHomeTeam\":\"133616\",\"idAwayTeam\":\"133613\",\"strResult\":null,\"strCircuit\":null,\"strCountry\":null,\"strCity\":null,\"strPoster\":null,\"strFanart\":null,\"strThumb\":null,\"strBanner\":null,\"strMap\":null,\"strTweet1\":null,\"strTweet2\":null,\"strTweet3\":null,\"strVideo\":null,\"strLocked\":\"unlocked\"},{\"idEvent\":\"602378\",\"idSoccerXML\":\"402229\",\"idAPIfootball\":null,\"strEvent\":\"Leicester vs Chelsea\",\"strEventAlternate\":\"Chelsea @ Leicester\",\"strFilename\":\"English Premier League 2020-02-01 Leicester vs Chelsea\",\"strSport\":\"Soccer\",\"idLeague\":\"4328\",\"strLeague\":\"English Premier League\",\"strSeason\":\"1920\",\"strDescriptionEN\":null,\"strHomeTeam\":\"Leicester\",\"strAwayTeam\":\"Chelsea\",\"intHomeScore\":\"2\",\"intRound\":\"25\",\"intAwayScore\":\"2\",\"intSpectators\":null,\"strHomeGoalDetails\":\"54':Harvey Barnes;65':Ben Chilwell;\",\"strHomeRedCards\":\"\",\"strHomeYellowCards\":\"61':James Maddison;90':Jonny Evans;\",\"strHomeLineupGoalkeeper\":\"Kasper Schmeichel; \",\"strHomeLineupDefense\":\"Ricardo Pereira; Jonny Evans; Caglar Soyuncu; Ben Chilwell; \",\"strHomeLineupMidfield\":\"Youri Tielemans; Wilfred Ndidi; Ayoze Perez; James Maddison; Harvey Barnes; \",\"strHomeLineupForward\":\"Jamie Vardy; \",\"strHomeLineupSubstitutes\":\"Danny Ward; James Justin; Christian Fuchs; Marc Albrighton; Demarai Gray; Dennis Praet; Kelechi Iheanacho; \",\"strHomeFormation\":null,\"strAwayRedCards\":\"\",\"strAwayYellowCards\":\"56':Jorginho;76':Mateo Kovacic;\",\"strAwayGoalDetails\":\"47':Antonio Ruediger;71':Antonio Ruediger;\",\"strAwayLineupGoalkeeper\":\"Kepa Arrizabalaga; \",\"strAwayLineupDefense\":\"Reece James; Andreas Christensen; Antonio Ruediger; Cesar Azpilicueta; \",\"strAwayLineupMidfield\":\"N'Golo Kante; Jorginho; Mateo Kovacic; \",\"strAwayLineupForward\":\"Callum Hudson-Odoi; Michy Batshuayi; Willian; \",\"strAwayLineupSubstitutes\":\"Kepa Arrizabalaga; Fikayo Tomori; Marcos Alonso; Mateo Kovacic; Ross Barkley; Willian; Michy Batshuayi; \",\"strAwayFormation\":null,\"intHomeShots\":null,\"intAwayShots\":null,\"dateEvent\":\"2020-02-01\",\"dateEventLocal\":null,\"strDate\":\"01\\/02\\/20\",\"strTime\":\"12:30:00\",\"strTimeLocal\":\"13:30:00\",\"strTVStation\":null,\"idHomeTeam\":\"133626\",\"idAwayTeam\":\"133610\",\"strResult\":null,\"strCircuit\":null,\"strCountry\":null,\"strCity\":null,\"strPoster\":null,\"strFanart\":null,\"strThumb\":null,\"strBanner\":null,\"strMap\":null,\"strTweet1\":null,\"strTweet2\":null,\"strTweet3\":null,\"strVideo\":null,\"strLocked\":\"unlocked\"},{\"idEvent\":\"602377\",\"idSoccerXML\":\"402228\",\"idAPIfootball\":null,\"strEvent\":\"West Ham vs Brighton\",\"strEventAlternate\":\"Brighton @ West Ham\",\"strFilename\":\"English Premier League 2020-02-01 West Ham vs Brighton\",\"strSport\":\"Soccer\",\"idLeague\":\"4328\",\"strLeague\":\"English Premier League\",\"strSeason\":\"1920\",\"strDescriptionEN\":null,\"strHomeTeam\":\"West Ham\",\"strAwayTeam\":\"Brighton\",\"intHomeScore\":\"3\",\"intRound\":\"25\",\"intAwayScore\":\"3\",\"intSpectators\":null,\"strHomeGoalDetails\":\"30':Issa Diop;45':Robert Snodgrass;57':Robert Snodgrass;\",\"strHomeRedCards\":\"\",\"strHomeYellowCards\":\"90':Angelo Ogbonna;\",\"strHomeLineupGoalkeeper\":\"Lukasz Fabianski; \",\"strHomeLineupDefense\":\"Pablo Zabaleta; Issa Diop; Angelo Ogbonna; Aaron Cresswell; \",\"strHomeLineupMidfield\":\"Mark Noble; Declan Rice; Robert Snodgrass; Manuel Lanzini; Pablo Fornals; \",\"strHomeLineupForward\":\"Sebastien Haller; \",\"strHomeLineupSubstitutes\":\"Darren Randolph; Fabian Balbuena; Pablo Zabaleta; Arthur Masuaku; Manuel Lanzini; Pablo Fornals; Albian Ajeti; \",\"strHomeFormation\":null,\"strAwayRedCards\":\"\",\"strAwayYellowCards\":\"52':Dale Stephens;\",\"strAwayGoalDetails\":\"47':Angelo Ogbonna (Own goal);75':Pascal Gross;80':Glenn Murray;\",\"strAwayLineupGoalkeeper\":\"Mathew Ryan; \",\"strAwayLineupDefense\":\"Martin Montoya; Adam Webster; Lewis Dunk; Bernardo; \",\"strAwayLineupMidfield\":\"Davy Propper; Dale Stephens; Alireza Jahanbakhsh; Aaron Mooy; Leandro Trossard; \",\"strAwayLineupForward\":\"Neal Maupay; \",\"strAwayLineupSubstitutes\":\"David Button; Ezequiel Schelotto; Solly March; Alireza Jahanbakhsh; Steven Alzate; Aaron Connolly; Neal Maupay; \",\"strAwayFormation\":null,\"intHomeShots\":null,\"intAwayShots\":null,\"dateEvent\":\"2020-02-01\",\"dateEventLocal\":null,\"strDate\":\"01\\/02\\/20\",\"strTime\":\"15:00:00\",\"strTimeLocal\":\"16:00:00\",\"strTVStation\":null,\"idHomeTeam\":\"133636\",\"idAwayTeam\":\"133619\",\"strResult\":null,\"strCircuit\":null,\"strCountry\":null,\"strCity\":null,\"strPoster\":null,\"strFanart\":null,\"strThumb\":null,\"strBanner\":null,\"strMap\":null,\"strTweet1\":null,\"strTweet2\":null,\"strTweet3\":null,\"strVideo\":null,\"strLocked\":\"unlocked\"},{\"idEvent\":\"602376\",\"idSoccerXML\":\"402227\",\"idAPIfootball\":null,\"strEvent\":\"Crystal Palace vs Sheffield United\",\"strEventAlternate\":\"Sheffield United @ Crystal Palace\",\"strFilename\":\"English Premier League 2020-02-01 Crystal Palace vs Sheffield United\",\"strSport\":\"Soccer\",\"idLeague\":\"4328\",\"strLeague\":\"English Premier League\",\"strSeason\":\"1920\",\"strDescriptionEN\":null,\"strHomeTeam\":\"Crystal Palace\",\"strAwayTeam\":\"Sheffield United\",\"intHomeScore\":\"0\",\"intRound\":\"25\",\"intAwayScore\":\"1\",\"intSpectators\":null,\"strHomeGoalDetails\":\"\",\"strHomeRedCards\":\"72':Joel Ward;\",\"strHomeYellowCards\":\"24':James Tomkins;67':Luka Milivojevic;88':Joel Ward;\",\"strHomeLineupGoalkeeper\":\"Vicente Guaita; \",\"strHomeLineupDefense\":\"Joel Ward; James Tomkins; Gary Cahill; Patrick van Aanholt; \",\"strHomeLineupMidfield\":\"Cheikhou Kouyate; Luka Milivojevic; James McArthur; \",\"strHomeLineupForward\":\"Andros Townsend; Jordan Ayew; Wilfried Zaha; \",\"strHomeLineupSubstitutes\":\"Wayne Hennessey; Scott Dann; Martin Kelly; Max Meyer; Cheikhou Kouyate; Andros Townsend; Jairo Riedewald; \",\"strHomeFormation\":null,\"strAwayRedCards\":\"\",\"strAwayYellowCards\":\"18':George Baldock;68':John Fleck;\",\"strAwayGoalDetails\":\"58':Oliver Norwood;\",\"strAwayLineupGoalkeeper\":\"Dean Henderson; \",\"strAwayLineupDefense\":\"Chris Basham; John Egan; Jack O'Connell; \",\"strAwayLineupMidfield\":\"George Baldock; John Lundstram; Oliver Norwood; John Fleck; Enda Stevens; \",\"strAwayLineupForward\":\"Oliver McBurnie; Lys Mousset; \",\"strAwayLineupSubstitutes\":\"Michael Verrips; Phil Jagielka; Jack Robinson; John Lundstram; Luke Freeman; Ben Osborn; Lys Mousset; \",\"strAwayFormation\":null,\"intHomeShots\":null,\"intAwayShots\":null,\"dateEvent\":\"2020-02-01\",\"dateEventLocal\":null,\"strDate\":\"01\\/02\\/20\",\"strTime\":\"15:00:00\",\"strTimeLocal\":\"16:00:00\",\"strTVStation\":null,\"idHomeTeam\":\"133632\",\"idAwayTeam\":\"133811\",\"strResult\":null,\"strCircuit\":null,\"strCountry\":null,\"strCity\":null,\"strPoster\":null,\"strFanart\":null,\"strThumb\":null,\"strBanner\":null,\"strMap\":null,\"strTweet1\":null,\"strTweet2\":null,\"strTweet3\":null,\"strVideo\":null,\"strLocked\":\"unlocked\"},{\"idEvent\":\"602375\",\"idSoccerXML\":\"402226\",\"idAPIfootball\":null,\"strEvent\":\"Watford vs Everton\",\"strEventAlternate\":\"Everton @ Watford\",\"strFilename\":\"English Premier League 2020-02-01 Watford vs Everton\",\"strSport\":\"Soccer\",\"idLeague\":\"4328\",\"strLeague\":\"English Premier League\",\"strSeason\":\"1920\",\"strDescriptionEN\":null,\"strHomeTeam\":\"Watford\",\"strAwayTeam\":\"Everton\",\"intHomeScore\":\"2\",\"intRound\":\"25\",\"intAwayScore\":\"3\",\"intSpectators\":null,\"strHomeGoalDetails\":\"10':Adam Masina;40':Roberto Pereyra;\",\"strHomeRedCards\":\"\",\"strHomeYellowCards\":\"69':Adam Masina;78':Isaac Success;\",\"strHomeLineupGoalkeeper\":\"Ben Foster; \",\"strHomeLineupDefense\":\"Adrian Mariappa; Craig Dawson; Craig Cathcart; Adam Masina; \",\"strHomeLineupMidfield\":\"Nathaniel Chalobah; Etienne Capoue; Ignacio Pussetto; Abdoulaye Doucoure; Gerard Deulofeu; \",\"strHomeLineupForward\":\"Troy Deeney; \",\"strHomeLineupSubstitutes\":\"Heurelho Gomes; Jose Holebas; Will Hughes; Ignacio Pussetto; Danny Welbeck; Andre Gray; Isaac Success; \",\"strHomeFormation\":null,\"strAwayRedCards\":\"\",\"strAwayYellowCards\":\"59':Fabian Delph;85':Yerry Mina;86':Mason Holgate;\",\"strAwayGoalDetails\":\"45':Yerry Mina;45':Yerry Mina;90':Theo Walcott;\",\"strAwayLineupGoalkeeper\":\"Jordan Pickford; \",\"strAwayLineupDefense\":\"Djibril Sidibe; Yerry Mina; Mason Holgate; Lucas Digne; \",\"strAwayLineupMidfield\":\"Theo Walcott; Gylfi Sigurdsson; Fabian Delph; Bernard; \",\"strAwayLineupForward\":\"Dominic Calvert-Lewin; Richarlison; \",\"strAwayLineupSubstitutes\":\"Maarten Stekelenburg; Leighton Baines; Michael Keane; Seamus Coleman; Morgan Schneiderlin; Bernard; Moise Kean; \",\"strAwayFormation\":null,\"intHomeShots\":null,\"intAwayShots\":null,\"dateEvent\":\"2020-02-01\",\"dateEventLocal\":null,\"strDate\":\"01\\/02\\/20\",\"strTime\":\"15:00:00\",\"strTimeLocal\":\"16:00:00\",\"strTVStation\":null,\"idHomeTeam\":\"133624\",\"idAwayTeam\":\"133615\",\"strResult\":null,\"strCircuit\":null,\"strCountry\":null,\"strCity\":null,\"strPoster\":null,\"strFanart\":null,\"strThumb\":null,\"strBanner\":null,\"strMap\":null,\"strTweet1\":null,\"strTweet2\":null,\"strTweet3\":null,\"strVideo\":null,\"strLocked\":\"unlocked\"},{\"idEvent\":\"602372\",\"idSoccerXML\":\"402223\",\"idAPIfootball\":null,\"strEvent\":\"Bournemouth vs Aston Villa\",\"strEventAlternate\":\"Aston Villa @ Bournemouth\",\"strFilename\":\"English Premier League 2020-02-01 Bournemouth vs Aston Villa\",\"strSport\":\"Soccer\",\"idLeague\":\"4328\",\"strLeague\":\"English Premier League\",\"strSeason\":\"1920\",\"strDescriptionEN\":null,\"strHomeTeam\":\"Bournemouth\",\"strAwayTeam\":\"Aston Villa\",\"intHomeScore\":\"2\",\"intRound\":\"25\",\"intAwayScore\":\"1\",\"intSpectators\":null,\"strHomeGoalDetails\":\"37':Philip Billing;44':Nathan Ake;\",\"strHomeRedCards\":\"\",\"strHomeYellowCards\":\"19':Jefferson Lerma;65':Callum Wilson;\",\"strHomeLineupGoalkeeper\":\"Aaron Ramsdale; \",\"strHomeLineupDefense\":\"Adam Smith; Steve Cook; Nathan Ake; Diego Rico; \",\"strHomeLineupMidfield\":\"Harry Wilson; Jefferson Lerma; Philip Billing; Ryan Fraser; \",\"strHomeLineupForward\":\"Callum Wilson; Dominic Solanke; \",\"strHomeLineupSubstitutes\":\"Artur Boruc; Steve Cook; Andrew Surman; Lewis Cook; Junior Stanislas; Dominic Solanke; Sam Surridge; \",\"strHomeFormation\":null,\"strAwayRedCards\":\"\",\"strAwayYellowCards\":\"36':Tyrone Mings;43':Douglas Luiz;90':Kortney Hause;\",\"strAwayGoalDetails\":\"70':Mbwana Samatta;\",\"strAwayLineupGoalkeeper\":\"Pepe Reina; \",\"strAwayLineupDefense\":\"Ezri Konsa; Tyrone Mings; Kortney Hause; \",\"strAwayLineupMidfield\":\"Frederic Guilbert; Marvelous Nakamba; Douglas Luiz; Matt Targett; \",\"strAwayLineupForward\":\"Anwar El-Ghazi; Mbwana Samatta; Jack Grealish; \",\"strAwayLineupSubstitutes\":\"Oerjan Haaskjold Nyland; Ahmed El Mohamady; Bjorn Engels; Conor Hourihane; Henri Lansbury; Trezeguet; Keinan Davis; \",\"strAwayFormation\":null,\"intHomeShots\":null,\"intAwayShots\":null,\"dateEvent\":\"2020-02-01\",\"dateEventLocal\":null,\"strDate\":\"01\\/02\\/20\",\"strTime\":\"15:00:00\",\"strTimeLocal\":\"16:00:00\",\"strTVStation\":null,\"idHomeTeam\":\"134301\",\"idAwayTeam\":\"133601\",\"strResult\":null,\"strCircuit\":null,\"strCountry\":null,\"strCity\":null,\"strPoster\":null,\"strFanart\":null,\"strThumb\":null,\"strBanner\":null,\"strMap\":null,\"strTweet1\":null,\"strTweet2\":null,\"strTweet3\":null,\"strVideo\":null,\"strLocked\":\"unlocked\"},{\"idEvent\":\"602371\",\"idSoccerXML\":\"402222\",\"idAPIfootball\":null,\"strEvent\":\"Newcastle vs Norwich\",\"strEventAlternate\":\"Norwich @ Newcastle\",\"strFilename\":\"English Premier League 2020-02-01 Newcastle vs Norwich\",\"strSport\":\"Soccer\",\"idLeague\":\"4328\",\"strLeague\":\"English Premier League\",\"strSeason\":\"1920\",\"strDescriptionEN\":null,\"strHomeTeam\":\"Newcastle\",\"strAwayTeam\":\"Norwich\",\"intHomeScore\":\"0\",\"intRound\":\"25\",\"intAwayScore\":\"0\",\"intSpectators\":null,\"strHomeGoalDetails\":\"\",\"strHomeRedCards\":\"\",\"strHomeYellowCards\":\"53':DeAndre Yedlin;80':Nabil Bentaleb;\",\"strHomeLineupGoalkeeper\":\"Martin Dubravka; \",\"strHomeLineupDefense\":\"DeAndre Yedlin; Federico Fernandez; Jamaal Lascelles; Ciaran Clark; Matt Ritchie; \",\"strHomeLineupMidfield\":\"Miguel Almiron; Isaac Hayden; Nabil Bentaleb; Allan Saint-Maximin; \",\"strHomeLineupForward\":\"Joelinton; \",\"strHomeLineupSubstitutes\":\"Karl Darlow; Danny Rose; Fabian Schaer; Florian Lejeune; Valentino Lazaro; Christian Atsu; Sean Longstaff; \",\"strHomeFormation\":null,\"strAwayRedCards\":\"\",\"strAwayYellowCards\":\"44':Todd Cantwell;\",\"strAwayGoalDetails\":\"\",\"strAwayLineupGoalkeeper\":\"Tim Krul; \",\"strAwayLineupDefense\":\"Max Aarons; Christoph Zimmermann; Grant Hanley; Sam Byram; \",\"strAwayLineupMidfield\":\"Alexander Tettey; Kenny McLean; Lukas Rupp; Ondrej Duda; Todd Cantwell; \",\"strAwayLineupForward\":\"Teemu Pukki; \",\"strAwayLineupSubstitutes\":\"Ralf Faehrmann; Jamal Lewis; Tom Trybull; Mario Vrancic; Onel Hernandez; Emiliano Buendia; Josip Drmic; \",\"strAwayFormation\":null,\"intHomeShots\":null,\"intAwayShots\":null,\"dateEvent\":\"2020-02-01\",\"dateEventLocal\":null,\"strDate\":\"01\\/02\\/20\",\"strTime\":\"15:00:00\",\"strTimeLocal\":\"16:00:00\",\"strTVStation\":null,\"idHomeTeam\":\"134777\",\"idAwayTeam\":\"133608\",\"strResult\":null,\"strCircuit\":null,\"strCountry\":null,\"strCity\":null,\"strPoster\":null,\"strFanart\":null,\"strThumb\":null,\"strBanner\":null,\"strMap\":null,\"strTweet1\":null,\"strTweet2\":null,\"strTweet3\":null,\"strVideo\":null,\"strLocked\":\"unlocked\"},{\"idEvent\":\"602370\",\"idSoccerXML\":\"402221\",\"idAPIfootball\":null,\"strEvent\":\"Man United vs Wolves\",\"strEventAlternate\":\"Wolves @ Man United\",\"strFilename\":\"English Premier League 2020-02-01 Man United vs Wolves\",\"strSport\":\"Soccer\",\"idLeague\":\"4328\",\"strLeague\":\"English Premier League\",\"strSeason\":\"1920\",\"strDescriptionEN\":null,\"strHomeTeam\":\"Man United\",\"strAwayTeam\":\"Wolves\",\"intHomeScore\":\"0\",\"intRound\":\"25\",\"intAwayScore\":\"0\",\"intSpectators\":null,\"strHomeGoalDetails\":\"\",\"strHomeRedCards\":\"\",\"strHomeYellowCards\":\"56':Bruno Fernandes;79':Victor Nilsson Lindeloef;84':Luke Shaw;\",\"strHomeLineupGoalkeeper\":\"David De Gea; \",\"strHomeLineupDefense\":\"Aaron Wan-Bissaka; Victor Nilsson Lindeloef; Harry Maguire; Brandon Williams; \",\"strHomeLineupMidfield\":\"Fred; Bruno Fernandes; Andreas Pereira; Juan Mata; Daniel James; \",\"strHomeLineupForward\":\"Anthony Martial; \",\"strHomeLineupSubstitutes\":\"Sergio Romero; Diogo Dalot; Phil Jones; Brandon Williams; Jesse Lingard; Tahith Chong; Mason Greenwood; \",\"strHomeFormation\":null,\"strAwayRedCards\":\"\",\"strAwayYellowCards\":\"57':Ruben Neves;71':Joao Moutinho;\",\"strAwayGoalDetails\":\"\",\"strAwayLineupGoalkeeper\":\"Rui Patricio; \",\"strAwayLineupDefense\":\"Leander Dendoncker; Conor Coady; Romain Saiss; \",\"strAwayLineupMidfield\":\"Matt Doherty; Ruben Neves; Joao Moutinho; Jonny; \",\"strAwayLineupForward\":\"Adama Traore; Raul Jimenez; Pedro Neto; \",\"strAwayLineupSubstitutes\":\"John Ruddy; Bruno Jordao; Pedro Neto; Daniel Podence; Morgan Gibbs-White; Leander Dendoncker; Max Kilman; \",\"strAwayFormation\":null,\"intHomeShots\":null,\"intAwayShots\":null,\"dateEvent\":\"2020-02-01\",\"dateEventLocal\":null,\"strDate\":\"01\\/02\\/20\",\"strTime\":\"17:30:00\",\"strTimeLocal\":\"18:30:00\",\"strTVStation\":null,\"idHomeTeam\":\"133612\",\"idAwayTeam\":\"133599\",\"strResult\":null,\"strCircuit\":null,\"strCountry\":null,\"strCity\":null,\"strPoster\":null,\"strFanart\":null,\"strThumb\":null,\"strBanner\":null,\"strMap\":null,\"strTweet1\":null,\"strTweet2\":null,\"strTweet3\":null,\"strVideo\":null,\"strLocked\":\"unlocked\"},{\"idEvent\":\"602369\",\"idSoccerXML\":\"402220\",\"idAPIfootball\":null,\"strEvent\":\"Liverpool vs Southampton\",\"strEventAlternate\":\"Southampton @ Liverpool\",\"strFilename\":\"English Premier League 2020-02-01 Liverpool vs Southampton\",\"strSport\":\"Soccer\",\"idLeague\":\"4328\",\"strLeague\":\"English Premier League\",\"strSeason\":\"1920\",\"strDescriptionEN\":\"\",\"strHomeTeam\":\"Liverpool\",\"strAwayTeam\":\"Southampton\",\"intHomeScore\":\"4\",\"intRound\":\"25\",\"intAwayScore\":\"0\",\"intSpectators\":null,\"strHomeGoalDetails\":\"47':Alex Oxlade-Chamberlain;60':Jordan Henderson;72':Mohamed Salah;90':Mohamed Salah;\",\"strHomeRedCards\":\"\",\"strHomeYellowCards\":\"\",\"strHomeLineupGoalkeeper\":\"Alisson Becker; \",\"strHomeLineupDefense\":\"Trent Alexander-Arnold; Joseph Gomez; Virgil van Dijk; Andrew Robertson; \",\"strHomeLineupMidfield\":\"Alex Oxlade-Chamberlain; Jordan Henderson; Georginio Wijnaldum; \",\"strHomeLineupForward\":\"Mohamed Salah; Roberto Firmino; Divock Origi; \",\"strHomeLineupSubstitutes\":\"Adrian; Dejan Lovren; Naby Keita; Takumi Minamino; Adam Lallana; Divock Origi; Joel Matip; \",\"strHomeFormation\":null,\"strAwayRedCards\":\"\",\"strAwayYellowCards\":\"65':James Ward-Prowse;89':Jack Stephens;\",\"strAwayGoalDetails\":\"\",\"strAwayLineupGoalkeeper\":\"Alex McCarthy; \",\"strAwayLineupDefense\":\"Kevin Danso; Jack Stephens; Jannik Vestergaard; Ryan Bertrand; \",\"strAwayLineupMidfield\":\"Moussa Djenepo; James Ward-Prowse; Pierre-Emile Hoejbjerg; Nathan Redmond; \",\"strAwayLineupForward\":\"Danny Ings; Shane Long; \",\"strAwayLineupSubstitutes\":\"Angus Gunn; Kevin Danso; Jannik Vestergaard; William Smallbone; Sofiane Boufal; Michael Obafemi; Che Adams; \",\"strAwayFormation\":null,\"intHomeShots\":null,\"intAwayShots\":null,\"dateEvent\":\"2020-02-01\",\"dateEventLocal\":\"2020-02-01\",\"strDate\":\"01\\/02\\/20\",\"strTime\":\"15:00:00\",\"strTimeLocal\":\"16:00:00\",\"strTVStation\":null,\"idHomeTeam\":\"133602\",\"idAwayTeam\":\"134778\",\"strResult\":\"\",\"strCircuit\":null,\"strCountry\":null,\"strCity\":null,\"strPoster\":null,\"strFanart\":null,\"strThumb\":null,\"strBanner\":null,\"strMap\":null,\"strTweet1\":\"\",\"strTweet2\":\"\",\"strTweet3\":\"\",\"strVideo\":\"https:\\/\\/www.youtube.com\\/watch?v=36E1iFRgfSg\",\"strLocked\":\"unlocked\"},{\"idEvent\":\"602307\",\"idSoccerXML\":\"402158\",\"idAPIfootball\":null,\"strEvent\":\"West Ham vs Liverpool\",\"strEventAlternate\":\"Liverpool @ West Ham\",\"strFilename\":\"English Premier League 2019-12-21 West Ham vs Liverpool\",\"strSport\":\"Soccer\",\"idLeague\":\"4328\",\"strLeague\":\"English Premier League\",\"strSeason\":\"1920\",\"strDescriptionEN\":\"\",\"strHomeTeam\":\"West Ham\",\"strAwayTeam\":\"Liverpool\",\"intHomeScore\":\"0\",\"intRound\":\"18\",\"intAwayScore\":\"2\",\"intSpectators\":null,\"strHomeGoalDetails\":\"\",\"strHomeRedCards\":\"\",\"strHomeYellowCards\":\"34':Issa Diop;43':Mark Noble;\",\"strHomeLineupGoalkeeper\":\"Darren Randolph; \",\"strHomeLineupDefense\":\"Issa Diop; Angelo Ogbonna; Aaron Cresswell; Jeremy Ngakia; \",\"strHomeLineupMidfield\":\"Mark Noble; Declan Rice; Robert Snodgrass; Manuel Lanzini; Pablo Fornals; \",\"strHomeLineupForward\":\"Michail Antonio; \",\"strHomeLineupSubstitutes\":\"Darren Randolph; Fabian Balbuena; Pablo Zabaleta; Goncalo Cardoso; Carlos Sanchez; Pablo Fornals; Albian Ajeti; \",\"strHomeFormation\":null,\"strAwayRedCards\":\"\",\"strAwayYellowCards\":\"\",\"strAwayGoalDetails\":\"35':Mohamed Salah;52':Alex Oxlade-Chamberlain;\",\"strAwayLineupGoalkeeper\":\"Alisson Becker; \",\"strAwayLineupDefense\":\"Trent Alexander-Arnold; Joseph Gomez; Virgil van Dijk; Andrew Robertson; \",\"strAwayLineupMidfield\":\"Alex Oxlade-Chamberlain; Jordan Henderson; Georginio Wijnaldum; \",\"strAwayLineupForward\":\"Mohamed Salah; Roberto Firmino; Divock Origi; \",\"strAwayLineupSubstitutes\":\"Adrian; Dejan Lovren; Joel Matip; Fabinho; Naby Keita; Curtis Jones; Takumi Minamino; \",\"strAwayFormation\":null,\"intHomeShots\":null,\"intAwayShots\":null,\"dateEvent\":\"2020-01-29\",\"dateEventLocal\":\"2020-10-29\",\"strDate\":\"29\\/01\\/20\",\"strTime\":\"19:45:00\",\"strTimeLocal\":\"20:45:00\",\"strTVStation\":null,\"idHomeTeam\":\"133636\",\"idAwayTeam\":\"133602\",\"strResult\":\"\",\"strCircuit\":null,\"strCountry\":null,\"strCity\":null,\"strPoster\":null,\"strFanart\":null,\"strThumb\":null,\"strBanner\":null,\"strMap\":null,\"strTweet1\":\"\",\"strTweet2\":\"\",\"strTweet3\":\"\",\"strVideo\":\"https:\\/\\/www.youtube.com\\/watch?v=pDK-YYpDG8Q\",\"strLocked\":\"unlocked\"},{\"idEvent\":\"602363\",\"idSoccerXML\":\"402214\",\"idAPIfootball\":null,\"strEvent\":\"Wolves vs Liverpool\",\"strEventAlternate\":\"Liverpool @ Wolves\",\"strFilename\":\"English Premier League 2020-01-21 Wolves vs Liverpool\",\"strSport\":\"Soccer\",\"idLeague\":\"4328\",\"strLeague\":\"English Premier League\",\"strSeason\":\"1920\",\"strDescriptionEN\":\"\",\"strHomeTeam\":\"Wolves\",\"strAwayTeam\":\"Liverpool\",\"intHomeScore\":\"1\",\"intRound\":\"24\",\"intAwayScore\":\"2\",\"intSpectators\":null,\"strHomeGoalDetails\":\"51':Raul Jimenez;\",\"strHomeRedCards\":\"\",\"strHomeYellowCards\":\"\",\"strHomeLineupGoalkeeper\":\"Rui Patricio; \",\"strHomeLineupDefense\":\"Leander Dendoncker; Conor Coady; Romain Saiss; \",\"strHomeLineupMidfield\":\"Matt Doherty; Ruben Neves; Joao Moutinho; Jonny; \",\"strHomeLineupForward\":\"Adama Traore; Raul Jimenez; Pedro Neto; \",\"strHomeLineupSubstitutes\":\"John Ruddy; Willy Boly; Max Kilman; Morgan Gibbs-White; Diogo Jota; Benny Ashley-Seal; Ryan Giles; \",\"strHomeFormation\":null,\"strAwayRedCards\":\"\",\"strAwayYellowCards\":\"56':Andrew Robertson;\",\"strAwayGoalDetails\":\"8':Jordan Henderson;85':Roberto Firmino;\",\"strAwayLineupGoalkeeper\":\"Alisson Becker; \",\"strAwayLineupDefense\":\"Trent Alexander-Arnold; Joseph Gomez; Virgil van Dijk; Andrew Robertson; \",\"strAwayLineupMidfield\":\"Alex Oxlade-Chamberlain; Jordan Henderson; Georginio Wijnaldum; \",\"strAwayLineupForward\":\"Mohamed Salah; Roberto Firmino; Sadio Mane; \",\"strAwayLineupSubstitutes\":\"Adrian; Fabinho; Takumi Minamino; Divock Origi; Joel Matip; Curtis Jones; Neco Williams; \",\"strAwayFormation\":null,\"intHomeShots\":null,\"intAwayShots\":null,\"dateEvent\":\"2020-01-23\",\"dateEventLocal\":\"2020-01-23\",\"strDate\":\"23\\/01\\/20\",\"strTime\":\"20:00:00\",\"strTimeLocal\":\"21:00:00\",\"strTVStation\":null,\"idHomeTeam\":\"133599\",\"idAwayTeam\":\"133602\",\"strResult\":\"\",\"strCircuit\":null,\"strCountry\":null,\"strCity\":null,\"strPoster\":null,\"strFanart\":null,\"strThumb\":null,\"strBanner\":null,\"strMap\":null,\"strTweet1\":\"\",\"strTweet2\":\"\",\"strTweet3\":\"\",\"strVideo\":\"https:\\/\\/www.youtube.com\\/watch?v=bGceOnPoW6s\",\"strLocked\":\"unlocked\"},{\"idEvent\":\"602366\",\"idSoccerXML\":\"402217\",\"idAPIfootball\":null,\"strEvent\":\"Tottenham vs Norwich\",\"strEventAlternate\":\"Norwich @ Tottenham\",\"strFilename\":\"English Premier League 2020-01-22 Tottenham vs Norwich\",\"strSport\":\"Soccer\",\"idLeague\":\"4328\",\"strLeague\":\"English Premier League\",\"strSeason\":\"1920\",\"strDescriptionEN\":null,\"strHomeTeam\":\"Tottenham\",\"strAwayTeam\":\"Norwich\",\"intHomeScore\":\"2\",\"intRound\":\"24\",\"intAwayScore\":\"1\",\"intSpectators\":null,\"strHomeGoalDetails\":\"38':Dele Alli;79':Heung-Min Son;\",\"strHomeRedCards\":\"\",\"strHomeYellowCards\":\"\",\"strHomeLineupGoalkeeper\":\"Paulo Gazzaniga; \",\"strHomeLineupDefense\":\"Jan Vertonghen; Ryan Sessegnon; Serge Aurier; Toby Alderweireld; \",\"strHomeLineupMidfield\":\"Christian Eriksen; Tanguy Ndombele; Giovani Lo Celso; Dele Alli; Heung-Min Son; \",\"strHomeLineupForward\":\"Lucas Moura; \",\"strHomeLineupSubstitutes\":\"Paulo Gazzaniga; Davinson Sanchez; Japhet Tanganga; Eric Dier; Christian Eriksen; Tanguy Ndombele; Gedson Fernandes; \",\"strHomeFormation\":null,\"strAwayRedCards\":\"\",\"strAwayYellowCards\":\"47':Todd Cantwell;\",\"strAwayGoalDetails\":\"70':Teemu Pukki;\",\"strAwayLineupGoalkeeper\":\"Tim Krul; \",\"strAwayLineupDefense\":\"Max Aarons; Christoph Zimmermann; Grant Hanley; Sam Byram; \",\"strAwayLineupMidfield\":\"Alexander Tettey; Kenny McLean; Todd Cantwell; Ondrej Duda; Onel Hernandez; \",\"strAwayLineupForward\":\"Teemu Pukki; \",\"strAwayLineupSubstitutes\":\"Michael McGovern; Jamal Lewis; Tom Trybull; Onel Hernandez; Marco Stiepermann; Ibrahim Amadou; Josip Drmic; \",\"strAwayFormation\":null,\"intHomeShots\":null,\"intAwayShots\":null,\"dateEvent\":\"2020-01-22\",\"dateEventLocal\":null,\"strDate\":\"22\\/01\\/20\",\"strTime\":\"19:30:00\",\"strTimeLocal\":\"20:30:00\",\"strTVStation\":null,\"idHomeTeam\":\"133616\",\"idAwayTeam\":\"133608\",\"strResult\":null,\"strCircuit\":null,\"strCountry\":null,\"strCity\":null,\"strPoster\":null,\"strFanart\":null,\"strThumb\":null,\"strBanner\":null,\"strMap\":null,\"strTweet1\":null,\"strTweet2\":null,\"strTweet3\":null,\"strVideo\":null,\"strLocked\":\"unlocked\"}]}"
    const val errorResponse = "{\nevents: \"Invalid League ID passed\"}"
  }

  private lateinit var mockWebServer: MockWebServer

  private lateinit var success: MockResponse
  private lateinit var failed: MockResponse

  @Before
  fun setUp() {
    mockWebServer = MockWebServer()
    mockWebServer.url("/")

    success = MockResponse()
          .setBody(successResponse)
          .setResponseCode(200)

    failed = MockResponse()
          .setBody(errorResponse)
          .setResponseCode(505)
  }

  @Test
  fun `success mock web server`() {
    mockWebServer.enqueue(success)
    Assert.assertEquals("HTTP/1.1 200 OK", success.status)
  }

  @Test
  fun `failed mock web server`() {
    mockWebServer.enqueue(failed)
    Assert.assertEquals("HTTP/1.1 505 Server Error", failed.status)
  }

}