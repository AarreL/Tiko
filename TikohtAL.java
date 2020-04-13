/*
Tietokantaohjelmointi 2020 harjoitustyö
Ryhmä 1:
Ville Lehtimäki, 425203, ville.lehtimaki@tuni.fi
Aarre Leinonen, 428786, aarre.leinonen@tuni.fi
Mikko Luukko, 431534, mikko.luukko@tuni.fi
*/



/*
Ohje:
1. kopioi kotihakemistoon shell.sis.uta.fi:ssa
2. kääntäminen shell.sis.fi:ssa komennolla: javac Testi.java
3. ajo shell.sis.uta.fi:ssa komennolla: java -classpath /usr/share/java/postgresql.jar:. Testi
*/


import java.sql.*;
import java.util.Scanner;


public class Tikoht {

	// tietokannan ja käyttäjän tiedot

	// private static final String AJURI = "org.postgresql.Driver";
	private static final String PROTOKOLLA = "jdbc:postgresql:";
	private static final String PALVELIN = "dbstud2.sis.uta.fi";
	private static final int PORTTI = 5432;
	private static final String TIETOKANTA = "al428786";  // tähän oma käyttäjätunnus
	private static final String KAYTTAJA = "al428786";  // tähän oma käyttäjätunnus
	private static final String SALASANA = "a1a9r9r8e";  // tähän tietokannan salasana

	private static Scanner input = new Scanner(System.in);

/********************************************************************************************************************** */

	public static void tapahtumat(Statement stmt) {
		System.out.println("Valitse tapahtuma:");
		System.out.println("1. T1");
		System.out.println("2. T2");
		String toiminto = input.nextLine();

		if(toiminto.equals("T1)")) {
			System.out.println("Syötä kohteen nimi:");
			String nimi = input.nextLine();
			System.out.println("Syötä kohteen osoite:");
			String osoite = input.nextLine();
			System.out.println("Syötä kohteen omistajan (asiakkaan) id:");		
			String asiakas_id = input.nextLine();
			try {		
				stmt.executeUpdate("INSERT INTO tyokohde (kohde_nimi, kohde_osoite, asiakas_id) VALUES ('" + nimi + "', '" + osoite + "', '" + asiakas_id + "')");							
			} catch (SQLException poikkeus) {				
				System.out.println("Tapahtui seuraava virhe: " + poikkeus.getMessage());     
			}
		}
		else if(toiminto.equals("T2")) {
			//ja tähän tyyliin jatkuu halutut tapahtumat, raportit omaan operaatioon alle
		}

	}

	public static void raportit (Statement stmt) {
		System.out.println("Valitse raportti:");
		System.out.println("1. R1");
		System.out.println("2. R2");
		String toiminto = input.nextLine();

		if(toiminto.equals("R1")) {


		}
		else if(toiminto.equals("R2")) {

	
		}
	}
/************************************************************************************************************************************ */

	public static void asiakas(Statement stmt) {
		System.out.println("Valitse toiminto:");
		System.out.println("1. Näytä asiakkaat");
		System.out.println("2. Lisää asiakas");
		System.out.println("3. Muokkaa asiakkaan tietoja");
		System.out.println("4. Poista asiakas");
		System.out.println("Syötä toiminnon numero:");
		String toiminto = input.nextLine();
		if (toiminto.equals("1")) {
			try {
				ResultSet rset = stmt.executeQuery("SELECT * FROM asiakas");
				while (rset.next()) {
					System.out.println("Asiakkaan id: " + rset.getInt("asiakas_id") + ", nimi: " + rset.getString("asiakas_nimi") + ", osoite: " + rset.getString("asiakas_osoite"));        
				}
			} catch (SQLException poikkeus) {				
				System.out.println("Tapahtui seuraava virhe: " + poikkeus.getMessage());     
			}
		} else if (toiminto.equals("2")) {
			System.out.println("Syötä asiakkaan nimi:");
			String nimi = input.nextLine();
			System.out.println("Syötä asiakkaan osoite:");
			String osoite = input.nextLine();
			try {		
				stmt.executeUpdate("INSERT INTO asiakas (asiakas_nimi, asiakas_osoite) VALUES ('" + nimi + "', '" + osoite + "')");							
			} catch (SQLException poikkeus) {				
				System.out.println("Tapahtui seuraava virhe: " + poikkeus.getMessage());     
			}  
		} else if (toiminto.equals("3")) {
			System.out.println("Syötä asiakkaan id:");
			String id = input.nextLine();
			System.out.println("Valitse muutettava tieto:");
			System.out.println("1. Nimi");
			System.out.println("2. Osoite");
			System.out.println("Syötä muutettavan tiedon numero:");
			String muutettava = input.nextLine();
			if (muutettava.equals("1") || muutettava.equals("2")) {
				if (muutettava.equals("1")) {
					muutettava = "asiakas_nimi";
				} else if (muutettava.equals("2")) {
					muutettava = "asiakas_osoite";
				}
				System.out.println("Syötä muutettavan tiedon uusi arvo:");
				String uusiArvo = input.nextLine();
				try {		
					stmt.executeUpdate("UPDATE asiakas SET " + muutettava + " = '" + uusiArvo + "' WHERE asiakas_id = " + id );							
				} catch (SQLException poikkeus) {				
					System.out.println("Tapahtui seuraava virhe: " + poikkeus.getMessage());     
				}   
			} else {
				System.out.println("Virheellinen valinta");
			}
		} else if (toiminto.equals("4")) {
			System.out.println("HUOM! Asiakasta ei voi poistaa mikäli häneen viitataan muissa tauluissa");
			System.out.println("Syötä poistettavan asiakkaan id:");
			String id = input.nextLine();
			try {
				stmt.executeUpdate("DELETE FROM asiakas WHERE asiakas_id = " + id);
			} catch (SQLException poikkeus) {				
				System.out.println("Tapahtui seuraava virhe: " + poikkeus.getMessage());     
			}  
		} else {
			System.out.println("Virheellinen valinta.");
		}
	}

	public static void kohde(Statement stmt) {
		System.out.println("Valitse toiminto:");
		System.out.println("1. Näytä kohteet");
		System.out.println("2. Lisää kohde");
		System.out.println("3. Muokkaa kohteen tietoja");
		System.out.println("4. Poista kohde");
		System.out.println("Syötä toiminnon numero:");
		String toiminto = input.nextLine();		
		if (toiminto.equals("1")) {			
			try {
				ResultSet rset = stmt.executeQuery("SELECT * FROM tyokohde");
				while (rset.next()) {
					System.out.println("Kohteen id: " + rset.getInt("kohde_id") + ", nimi: " + rset.getString("kohde_nimi") + ", osoite: " 
						+ rset.getString("kohde_osoite") + ", omistajan id: " + rset.getString("asiakas_id"));        
				}
			} catch (SQLException poikkeus) {				
				System.out.println("Tapahtui seuraava virhe: " + poikkeus.getMessage());     
			}
		} else if (toiminto.equals("2")) {
			System.out.println("Syötä kohteen nimi:");
			String nimi = input.nextLine();
			System.out.println("Syötä kohteen osoite:");
			String osoite = input.nextLine();
			System.out.println("Syötä kohteen omistajan (asiakkaan) id:");		
			String asiakas_id = input.nextLine();
			try {		
				stmt.executeUpdate("INSERT INTO tyokohde (kohde_nimi, kohde_osoite, asiakas_id) VALUES ('" + nimi + "', '" + osoite + "', '" + asiakas_id + "')");							
			} catch (SQLException poikkeus) {				
				System.out.println("Tapahtui seuraava virhe: " + poikkeus.getMessage());     
			}	
		} else if (toiminto.equals("3")) {
			System.out.println("Syötä kohteen id:");
			String id = input.nextLine();
			System.out.println("Valitse muutettava tieto:");
			System.out.println("1. Nimi");
			System.out.println("2. Osoite");
			System.out.println("3. Asiakkaan (omistajan) id");
			System.out.println("Syötä muutettavan tiedon numero:");
			String muutettava = input.nextLine();
			if (muutettava.equals("1") || muutettava.equals("2")|| muutettava.equals("3")) {
				if (muutettava.equals("1")) {
					muutettava = "kohde_nimi";
				} else if (muutettava.equals("2")) {
					muutettava = "kohde_osoite";
				} else if (muutettava.equals("3")) {
					muutettava = "asiakas_id";
				}
				System.out.println("Syötä muutettavan tiedon uusi arvo:");
				String uusiArvo = input.nextLine();
				try {		
					stmt.executeUpdate("UPDATE tyokohde SET " + muutettava + " = '" + uusiArvo + "' WHERE kohde_id = " + id );							
				} catch (SQLException poikkeus) {				
					System.out.println("Tapahtui seuraava virhe: " + poikkeus.getMessage());     
				}   
			} else {
				System.out.println("Virheellinen valinta");
			}
		} else if (toiminto.equals("4")) {
			System.out.println("HUOM! kohdetta ei voi poistaa mikäli siihen viitataan muissa tauluissa");
			System.out.println("Syötä poistettavan kohteen id:");
			String id = input.nextLine();
			try {
				stmt.executeUpdate("DELETE FROM tyokohde WHERE kohde_id = " + id);
			} catch (SQLException poikkeus) {				
				System.out.println("Tapahtui seuraava virhe: " + poikkeus.getMessage());     
			}  						
		} else {
			System.out.println("Virheellinen valinta.");
		}
	}

	public static void projekti(Statement stmt) {
		System.out.println("Valitse toiminto:");
		System.out.println("1. Näytä projektit (perustiedot)");
		System.out.println("2. Näytä projektin lisätiedot");
		System.out.println("3. Lisää uusi projekti");
		System.out.println("4. Lisää projektille tunteja");
		System.out.println("5. Lisää projektille tarvikkeita");
		System.out.println("6. Poista projekti");
		System.out.println("Syötä toiminnon numero:");
		String toiminto = input.nextLine();			
		if (toiminto.equals("1")) {			
			try {
				ResultSet rset = stmt.executeQuery("SELECT * FROM projekti");
				while (rset.next()) {
					System.out.println("Projektin id: " + rset.getInt("projekti_id") + ", kohteen id: " + rset.getString("kohde_id") + ", projektin nimi: " + rset.getString("projektinimi"));        
				}
			} catch (SQLException poikkeus) {				
				System.out.println("Tapahtui seuraava virhe: " + poikkeus.getMessage());     
			}
		} else if (toiminto.equals("2")) {	
			System.out.println("Syötä projektin id:");
			String projekti_id = input.nextLine();	
			try {
				ResultSet rset = stmt.executeQuery("SELECT projektinimi, tyyppi_nimi, maara, ROUND(maara*tyyppi_hinta*(CASE WHEN ale_p IS NOT NULL THEN 1 - ale_p / 100 ELSE 1 END), 2) AS hinta FROM projekti "
					+ "JOIN tunnit ON tunnit.projekti_id = projekti.projekti_id JOIN tuntityyppi ON tuntityyppi.tyyppi_id = tunnit.tyyppi_id WHERE projekti.projekti_id = " + projekti_id);
				while (rset.next()) {
					System.out.println("Projektin nimi: " + rset.getString("projektinimi") + ", tunnin tyyppi: " + rset.getString("tyyppi_nimi") + ", määrä: " 
						+ rset.getString("maara") + ", yhteishinta: " + rset.getString("hinta"));        
				}
				rset = stmt.executeQuery("SELECT projektinimi, tarvikeluettelo.tarvike_nimi, maara, ROUND(maara*myyntihinta*(CASE WHEN ale_p IS NOT NULL THEN 1 - ale_p / 100 ELSE 1 END), 2) AS hinta FROM projekti "
					+ "JOIN tarvikeluettelo ON tarvikeluettelo.projekti_id = projekti.projekti_id JOIN tarvike ON tarvike.tarvike_nimi = tarvikeluettelo.tarvike_nimi WHERE projekti.projekti_id = " + projekti_id);
				while (rset.next()) {
					System.out.println("Projektin nimi: " + rset.getString("projektinimi") + ", tarvike: " + rset.getString("tarvike_nimi") + ", määrä: " 
						+ rset.getString("maara") + ", yhteishinta: " + rset.getString("hinta"));        
				}
			} catch (SQLException poikkeus) {				
				System.out.println("Tapahtui seuraava virhe: " + poikkeus.getMessage());     
			}
		} else if (toiminto.equals("3")) {
			System.out.println("Syötä kohteen id:");
			String kohde_id = input.nextLine();
			System.out.println("Syötä projektin nimi:");
			String nimi = input.nextLine();
			try {		
				stmt.executeUpdate("INSERT INTO projekti (kohde_id, projektinimi) VALUES ('" + kohde_id + "', '" + nimi + "')");							
			} catch (SQLException poikkeus) {				
				System.out.println("Tapahtui seuraava virhe: " + poikkeus.getMessage());     
			}
		} else if (toiminto.equals("4")) {	
			System.out.println("Syötä projektin id:");
			String projekti_id = input.nextLine();
			System.out.println("Syötä tunnin tyyppi (1 = Suunnittelu, 2 = Työ, 3 = Aputyö):");
			String tyyppi_id = input.nextLine();
			System.out.println("Syötä tuntien määrä:");
			String maara = input.nextLine();
			System.out.println("Syötä alennusprosentti väliltä 0...100 (ei pakollinen):");
			String alennus = input.nextLine();
			String kysely;
			if (alennus.equals("")) {
				kysely = "INSERT INTO tunnit (projekti_id, tyyppi_id, maara) VALUES ('" + projekti_id + "', '" + tyyppi_id + "', '" + maara + "')";
			} else {
				kysely = "INSERT INTO tunnit (projekti_id, tyyppi_id, maara, ale_p) VALUES ('" + projekti_id + "', '" + tyyppi_id + "', '" + maara 
					+ "', '" + alennus + "')";
			}
			try {
				stmt.executeUpdate(kysely);	
			} catch (SQLException poikkeus) {				
				System.out.println("Tapahtui seuraava virhe: " + poikkeus.getMessage());     
			}
		} else if (toiminto.equals("5")) {	
			System.out.println("Syötä projektin id:");
			String projekti_id = input.nextLine();
			System.out.println("Syötä tarvikkeen nimi:");
			String tarvike_nimi = input.nextLine();
			System.out.println("Syötä tarvikkeen määrä:");
			String maara = input.nextLine();
			System.out.println("Syötä alennusprosentti väliltä 0...100 (ei pakollinen):");
			String alennus = input.nextLine();
			String kysely;
			if (alennus.equals("")) {
				kysely = "INSERT INTO tarvikeluettelo (projekti_id, tarvike_nimi, maara) VALUES ('" + projekti_id + "', '" + tarvike_nimi + "', '" + maara + "')";
			} else {
				kysely = "INSERT INTO tarvikeluettelo (projekti_id, tarvike_nimi, maara, ale_p) VALUES ('" + projekti_id + "', '" + tarvike_nimi + "', '" + maara 
					+ "', '" + alennus + "')";
			}
			try {
				stmt.executeUpdate(kysely);	
			} catch (SQLException poikkeus) {				
				System.out.println("Tapahtui seuraava virhe: " + poikkeus.getMessage());     
			}
		} else if (toiminto.equals("6")) {
			System.out.println("HUOM! projektia ei voi poistaa mikäli siihen viitataan muissa tauluissa");
			System.out.println("Syötä poistettavan projektin id:");
			String id = input.nextLine();
			try {
				stmt.executeUpdate("DELETE FROM projekti WHERE projekti_id = " + id);
			} catch (SQLException poikkeus) {				
				System.out.println("Tapahtui seuraava virhe: " + poikkeus.getMessage());     
			}  			
		} else {
			System.out.println("Virheellinen valinta.");
		}
	}

	public static void lasku(Statement stmt) {
		System.out.println("Valitse toiminto:");
		System.out.println("1. Näytä laskut (perustiedot)");
		System.out.println("2. Näytä yksityiskohtainen lasku");
		System.out.println("3. Lisää lasku");
		System.out.println("4. Poista lasku");
		System.out.println("Syötä toiminnon numero:");
		String toiminto = input.nextLine();		
		if (toiminto.equals("1")) {			
			try {
				ResultSet rset = stmt.executeQuery("SELECT * FROM lasku WHERE tyyppi = 'lasku'");
				while (rset.next()) {
					System.out.println("Laskun id: " + rset.getInt("lasku_id") + ", projektin id: " + rset.getString("projekti_id") + ", päivämäärä: " 
						+ rset.getString("paivamaara") + ", eräpäivä: " + rset.getString("erapaiva") + ", tyyppi: " + rset.getString("tyyppi") + ", loppusumma: "
						+ rset.getString("loppusumma") + ", kotitalousvähennys: " + rset.getString("kotitalousvah"));        
				}
			} catch (SQLException poikkeus) {				
				System.out.println("Tapahtui seuraava virhe: " + poikkeus.getMessage());     
			}
		} else if (toiminto.equals("2")) {	
			System.out.println("Syötä laskun id:");
			String lasku_id = input.nextLine();	
			try {
				ResultSet rset = stmt.executeQuery("Select projekti_id FROM lasku WHERE lasku_id = " + lasku_id);
				rset.next();
				String projekti_id = rset.getString("projekti_id");		
				
				rset = stmt.executeQuery("SELECT asiakas_nimi, asiakas_osoite, kohde_nimi, kohde_osoite, projektinimi FROM lasku "
					+ "JOIN projekti ON projekti.projekti_id = lasku.projekti_id JOIN tyokohde ON tyokohde.kohde_id = projekti.kohde_id "
					+ "JOIN asiakas ON asiakas.asiakas_id = tyokohde.asiakas_id WHERE lasku_id = " + lasku_id);
				while (rset.next()) {
					System.out.println("\nAsiakas: " + rset.getString("asiakas_nimi") + ", osoite: " + rset.getString("asiakas_osoite") + "\nKohde: " 
						+ rset.getString("kohde_nimi") + ", osoite: " + rset.getString("kohde_osoite") + "\nProjekti: " + rset.getString("projektinimi"));        
				}

				rset = stmt.executeQuery("SELECT tyyppi_nimi, maara, ROUND(maara*tyyppi_hinta*(CASE WHEN ale_p IS NOT NULL THEN 1 - ale_p / 100 ELSE 1 END), 2) AS hinta FROM projekti "
					+ "JOIN tunnit ON tunnit.projekti_id = projekti.projekti_id JOIN tuntityyppi ON tuntityyppi.tyyppi_id = tunnit.tyyppi_id WHERE projekti.projekti_id = " + projekti_id);
				System.out.println("\nTunnit:");
				while (rset.next()) {
					System.out.println("Tunnin tyyppi: " + rset.getString("tyyppi_nimi") + ", määrä: " + rset.getString("maara") + ", yhteishinta: " + rset.getString("hinta"));        
				}

				rset = stmt.executeQuery("SELECT tarvikeluettelo.tarvike_nimi, maara, ROUND(maara*myyntihinta*(CASE WHEN ale_p IS NOT NULL THEN 1 - ale_p / 100 ELSE 1 END), 2) AS hinta FROM projekti "
					+ "JOIN tarvikeluettelo ON tarvikeluettelo.projekti_id = projekti.projekti_id JOIN tarvike ON tarvike.tarvike_nimi = tarvikeluettelo.tarvike_nimi WHERE projekti.projekti_id = " + projekti_id);
				System.out.println("\nTarvikkeet:");
				while (rset.next()) {
					System.out.println("Tarvike: " + rset.getString("tarvike_nimi") + ", määrä: " 
						+ rset.getString("maara") + ", yhteishinta: " + rset.getString("hinta"));     
				}
				
				rset = stmt.executeQuery("SELECT * FROM lasku WHERE lasku_id = " + lasku_id);
				while (rset.next()) {
					System.out.println("Laskun id: " + rset.getInt("lasku_id") + ", projektin id: " + rset.getString("projekti_id") + ", päivämäärä: " 
						+ rset.getString("paivamaara") + ", eräpäivä: " + rset.getString("erapaiva") + ", tyyppi: " + rset.getString("tyyppi") + ", loppusumma: "
						+ rset.getString("loppusumma") + ", kotitalousvähennys: " + rset.getString("kotitalousvah"));        
				}
			} catch (SQLException poikkeus) {				
				System.out.println("Tapahtui seuraava virhe: " + poikkeus.getMessage());     
			}
		} else if (toiminto.equals("3")) {
			System.out.println("Syötä projektin id:");
			String projekti_id = input.nextLine();
			System.out.println("Syötä laskun päivämäärä (muodossa YYYY-MM-DD):");
			String pvm = input.nextLine();
			System.out.println("Syötä laskun eräpäivä (muodossa YYYY-MM-DD):");
			String erapaiva = input.nextLine();
			double tyonOsuus = 0;
			double loppusumma = 0;
			double kotitalousvah = 0;
			try {
				// Haetaan projektin tunnit ja lisätään niiden yhteishinnat loppusummaan sekä tyonOsuus-muuttujaan kotitalousvähennyksen laskemista varten
				ResultSet rset = stmt.executeQuery("SELECT ROUND(maara*tyyppi_hinta*(CASE WHEN ale_p IS NOT NULL THEN 1 - ale_p / 100 ELSE 1 END), 2) AS hinta FROM projekti "
					+ "JOIN tunnit ON tunnit.projekti_id = projekti.projekti_id JOIN tuntityyppi ON tuntityyppi.tyyppi_id = tunnit.tyyppi_id WHERE projekti.projekti_id = " + projekti_id);
				while (rset.next()) {
					tyonOsuus += Double.parseDouble(rset.getString("hinta"));
					loppusumma += Double.parseDouble(rset.getString("hinta"));
				}
				rset = stmt.executeQuery("SELECT ROUND(maara*myyntihinta*(CASE WHEN ale_p IS NOT NULL THEN 1 - ale_p / 100 ELSE 1 END), 2) AS hinta FROM projekti "
					+ "JOIN tarvikeluettelo ON tarvikeluettelo.projekti_id = projekti.projekti_id JOIN tarvike ON tarvike.tarvike_nimi = tarvikeluettelo.tarvike_nimi WHERE projekti.projekti_id = " + projekti_id);
				while (rset.next()) {
					loppusumma += Double.parseDouble(rset.getString("hinta"));
				}
				if (tyonOsuus >= 2250) {
					kotitalousvah = 2250;
				} else {
					kotitalousvah = tyonOsuus;
				}
				stmt.executeUpdate("INSERT INTO lasku (projekti_id, paivamaara, erapaiva, tyyppi, loppusumma, tila, kotitalousvah) VALUES ('" + projekti_id + "', '" 
					+ pvm + "', '" + erapaiva + "', 'lasku', '" + Double.toString(loppusumma) + "', 'kesken', '" + Double.toString(kotitalousvah) + "')");							
			} catch (SQLException poikkeus) {				
				System.out.println("Tapahtui seuraava virhe: " + poikkeus.getMessage());     
			}			
		} else if (toiminto.equals("4")) {
			System.out.println("HUOM! laskua ei voi poistaa mikäli siihen viitataan muissa tauluissa");
			System.out.println("Syötä poistettavan laskun id:");
			String id = input.nextLine();
			try {
				stmt.executeUpdate("DELETE FROM lasku WHERE lasku_id = " + id);
			} catch (SQLException poikkeus) {				
				System.out.println("Tapahtui seuraava virhe: " + poikkeus.getMessage());     
			}  				
		} else {
			System.out.println("Virheellinen valinta.");
		}
	}

	public static void tarjous(Statement stmt) {
		System.out.println("Valitse toiminto:");
		System.out.println("1. Näytä tarjoukset");
		System.out.println("2. Lisää tarjous");
		System.out.println("3. Poista tarjous");
		System.out.println("Syötä toiminnon numero:");
		String toiminto = input.nextLine();		
		if (toiminto.equals("1")) {			
			try {
				ResultSet rset = stmt.executeQuery("SELECT * FROM lasku WHERE tyyppi = 'tarjous'");
				while (rset.next()) {
					System.out.println("Laskun id: " + rset.getInt("lasku_id") + ", projektin id: " + rset.getString("projekti_id") + ", päivämäärä: " 
						+ rset.getString("paivamaara") + ", eräpäivä: " + rset.getString("erapaiva") + ", tyyppi: " + rset.getString("tyyppi") + ", loppusumma: "
						+ rset.getString("loppusumma") + ", kotitalousvähennys: " + rset.getString("kotitalousvah"));        
				}
			} catch (SQLException poikkeus) {				
				System.out.println("Tapahtui seuraava virhe: " + poikkeus.getMessage());     
			}
		} else if (toiminto.equals("2")) {
			System.out.println("Syötä projektin id:");
			String projekti_id = input.nextLine();
			System.out.println("Syötä laskun päivämäärä (muodossa YYYY-MM-DD):");
			String pvm = input.nextLine();
			System.out.println("Syötä laskun eräpäivä (muodossa YYYY-MM-DD):");
			String erapaiva = input.nextLine();
			System.out.println("Syötä laskun loppusumma:");
			String summa = input.nextLine();
			System.out.println("Syötä laskun kotitalousvähennys:");
			String kotitalousvah = input.nextLine();
			try {
				stmt.executeUpdate("INSERT INTO lasku (projekti_id, paivamaara, erapaiva, tyyppi, loppusumma, tila, kotitalousvah) VALUES ('" + projekti_id + "', '" 
					+ pvm + "', '" + erapaiva + "', 'tarjous', '" + summa + "', 'kesken', '" + kotitalousvah + "')");							
			} catch (SQLException poikkeus) {				
				System.out.println("Tapahtui seuraava virhe: " + poikkeus.getMessage());     
			}	
		} else if (toiminto.equals("3")) {
			System.out.println("HUOM! tarjousta ei voi poistaa mikäli siihen viitataan muissa tauluissa");
			System.out.println("Syötä poistettavan tarjouksen id:");
			String id = input.nextLine();
			try {
				stmt.executeUpdate("DELETE FROM lasku WHERE lasku_id = " + id);
			} catch (SQLException poikkeus) {				
				System.out.println("Tapahtui seuraava virhe: " + poikkeus.getMessage());     
			}  			
		} else {
			System.out.println("Virheellinen valinta.");
		}
	}

	public static void tarvike(Statement stmt) {
		System.out.println("Valitse toiminto:");
		System.out.println("1. Näytä tarvikkeet");
		System.out.println("2. Lisää tarvike varastoon");
		System.out.println("3. Poista tarvike varastoon");
		System.out.println("Syötä toiminnon numero:");
		String toiminto = input.nextLine();		
		if (toiminto.equals("1")) {			
			try {
				ResultSet rset = stmt.executeQuery("SELECT * FROM tarvike");
				while (rset.next()) {
					System.out.println("Tarvikkeen nimi: " + rset.getString("tarvike_nimi") + ", sisäänostohinta: " + rset.getString("sisaanostohinta") + ", myyntihinta: " 
						+ rset.getString("myyntihinta") + ", ALV-prosentti: " + rset.getString("alv_p") + ", yksikkö: " + rset.getString("yksikko") + ", varastosaldo: "
						+ rset.getString("varastosaldo"));        
				}
			} catch (SQLException poikkeus) {				
				System.out.println("Tapahtui seuraava virhe: " + poikkeus.getMessage());     
			}
		} else if (toiminto.equals("2")) {
			System.out.println("Syötä tarvikkeen nimi:");
			String tarvike_nimi = input.nextLine();
			System.out.println("Syötä tarvikkeen sisäänostohinta:");
			String sisaanostohinta = input.nextLine();
			System.out.println("Syötä tarvikkeen myyntihinta:");
			String myyntihinta = input.nextLine();
			System.out.println("Syötä tarvikkeen ALV-prosentti:");
			String alv_p = input.nextLine();
			System.out.println("Syötä tarvikkeen yksikkö:");
			String yksikko = input.nextLine();
			System.out.println("Syötä tarvikkeen varastosaldo:");
			String varastosaldo = input.nextLine();
			try {
				stmt.executeUpdate("INSERT INTO tarvike (tarvike_nimi, sisaanostohinta, myyntihinta, alv_p, yksikko, varastosaldo) VALUES ('" + tarvike_nimi + "', '" 
					+ sisaanostohinta + "', '" + myyntihinta + "', '" + alv_p + "', '" + yksikko + "', '" + varastosaldo + "')");							
			} catch (SQLException poikkeus) {				
				System.out.println("Tapahtui seuraava virhe: " + poikkeus.getMessage());     
			}	
		} else if (toiminto.equals("3")) {
			System.out.println("HUOM! tarviketta ei voi poistaa mikäli siihen viitataan muissa tauluissa");
			System.out.println("Syötä poistettavan tarvikkeen id:");
			String id = input.nextLine();
			try {
				stmt.executeUpdate("DELETE FROM tarvike WHERE tarvike_id = " + id);
			} catch (SQLException poikkeus) {				
				System.out.println("Tapahtui seuraava virhe: " + poikkeus.getMessage());     
			}  				
		} else {
			System.out.println("Virheellinen valinta.");
		}
	}

	public static void alustus(Statement stmt) {
		try {
			stmt.executeUpdate("DROP TABLE IF EXISTS tunnit, tarvikeluettelo, lasku, projekti, tyokohde, asiakas, tuntityyppi, tarvike");

			stmt.executeUpdate("CREATE TABLE asiakas (asiakas_id SERIAL, asiakas_nimi VARCHAR(60) NOT NULL, asiakas_osoite VARCHAR(60) NOT NULL, PRIMARY KEY (asiakas_id));");
			stmt.executeUpdate("CREATE TABLE tyokohde (kohde_id SERIAL, asiakas_id INT NOT NULL, kohde_nimi VARCHAR(60) NOT NULL,kohde_osoite VARCHAR(60) NOT NULL, "
			 + "PRIMARY KEY (kohde_id), FOREIGN KEY (asiakas_id) REFERENCES asiakas);");
			stmt.executeUpdate("CREATE TABLE projekti (projekti_id SERIAL, kohde_id INT NOT NULL, projektinimi VARCHAR(60), PRIMARY KEY (projekti_id), FOREIGN KEY (kohde_id) REFERENCES tyokohde);");
			stmt.executeUpdate("CREATE TABLE tuntityyppi (tyyppi_id SERIAL, tyyppi_nimi VARCHAR(20) NOT NULL, tyyppi_hinta INT NOT NULL, PRIMARY KEY (tyyppi_id));");
			stmt.executeUpdate("CREATE TABLE tunnit (tunnit_id SERIAL, projekti_id INT NOT NULL, tyyppi_id INT NOT NULL, maara INT NOT NULL, ale_p DECIMAL (5, 2), "
				+ "PRIMARY KEY (tunnit_id), FOREIGN KEY (projekti_id) REFERENCES projekti, FOREIGN KEY (tyyppi_id) REFERENCES tuntityyppi);");
			stmt.executeUpdate("CREATE TABLE lasku (lasku_id SERIAL, projekti_id INT NOT NULL, edeltava INT, viivastyskorko  DECIMAL (5, 2), tyyppi VARCHAR(20) NOT NULL, "
				+ "paivamaara DATE, erapaiva DATE, maksupaiva DATE, laskutuslisa DECIMAL (5, 2), loppusumma DECIMAL (7, 2) NOT NULL, tila VARCHAR(20) NOT NULL, "
				+ "kotitalousvah DECIMAL (5, 2) NOT NULL,PRIMARY KEY (lasku_id),FOREIGN KEY (projekti_id) REFERENCES projekti,FOREIGN KEY (edeltava) REFERENCES lasku(lasku_id));");
			stmt.executeUpdate("CREATE TABLE tarvike (tarvike_nimi VARCHAR(60), alv_p DECIMAL (5, 2) NOT NULL, varastosaldo DECIMAL (5, 2) NOT NULL, yksikko VARCHAR(20) NOT NULL, "
				+ "sisaanostohinta DECIMAL (6, 2) NOT NULL, myyntihinta DECIMAL (6, 2) NOT NULL,PRIMARY KEY (tarvike_nimi));");
			stmt.executeUpdate("CREATE TABLE tarvikeluettelo (tluettelo_id SERIAL, projekti_id INT NOT NULL, tarvike_nimi VARCHAR(60) NOT NULL, ale_p DECIMAL (5, 2), "
				+ "maara INT  NOT NULL, PRIMARY KEY (tluettelo_id), FOREIGN KEY (projekti_id) REFERENCES projekti, FOREIGN KEY (tarvike_nimi) REFERENCES tarvike);");
						
			stmt.executeUpdate("INSERT INTO asiakas (asiakas_nimi, asiakas_osoite) VALUES ('Terhi Jorkko', 'Koulutie 3'), ('Masa Mainio', 'Tehdaskatu 43'), ('Pentti Ponteva', 'Puutie 8');");
			stmt.executeUpdate("INSERT INTO tyokohde (asiakas_id, kohde_nimi, kohde_osoite) VALUES (1, 'Kesähuvila', 'Meritie 185'), (1, 'Kotitalo', 'Koulutie 3'), (2, 'Verstas', 'Tehdaskatu 10');");
			stmt.executeUpdate("INSERT INTO projekti (kohde_id, projektinimi) VALUES (2, 'Putkiremontti'), (2, 'Uusi terassi'), (2, 'Julkisivuremppa');");
			stmt.executeUpdate("INSERT INTO tuntityyppi (tyyppi_nimi, tyyppi_hinta) VALUES ('Suunnittelu', 55), ('Työ', 45), ('Aputyö', 35);");
			stmt.executeUpdate("INSERT INTO tunnit (projekti_id, tyyppi_id, maara, ale_p) VALUES (1, 2, 20, null), (1, 3, 10, null), (2, 1, 5, 10.00);");
			stmt.executeUpdate("INSERT INTO tarvike (tarvike_nimi, alv_p, varastosaldo, yksikko, sisaanostohinta, myyntihinta) VALUES ('Viemäriputki', 24.00, 30, 'metri', 5.00, 12.00), ('Naulat', 24.00, 2, 'kg', 3.50, 5.00);");
			stmt.executeUpdate("INSERT INTO tarvikeluettelo (projekti_id, tarvike_nimi, ale_p, maara) VALUES (1, 'Viemäriputki', null, 3), (2, 'Naulat', null, 1), (2, 'Naulat', null, 2);");
			stmt.executeUpdate("INSERT INTO lasku (projekti_id, edeltava, viivastyskorko, tyyppi, paivamaara, erapaiva, maksupaiva, laskutuslisa, loppusumma, tila, kotitalousvah) VALUES (1, null, null, 'lasku', '2020-02-15', '2020-03-05', null, null, 165.00, 'valmis', 100.00);");
		} catch (SQLException poikkeus) {				
			System.out.println("Tapahtui seuraava virhe: " + poikkeus.getMessage());     
		}			

	}


	public static void main(String args[]) {

		// Vaihe 2: yhteyden ottaminen tietokantaan

		Connection con = null;
		try {
			con = DriverManager.getConnection(PROTOKOLLA + "//" + PALVELIN + ":" + PORTTI + "/" + TIETOKANTA, KAYTTAJA,
					SALASANA);

			Statement stmt;
			ResultSet rset;

			// Vaihe 3.1: tähän tehtäväkohtainen koodi

			System.out.println("Tervetuloa tmi Sähkötärskyn tietokantaohjelmaan.\n");

			boolean kysyValinta = true;
			boolean kysyToiminto = true;
			String valinta;
			String toiminto;

			while (kysyValinta) {
				System.out.println("Valitse ryhmä:");
				System.out.println("1. Asiakkaat");
				System.out.println("2. Kohteet");
				System.out.println("3. Projektit");
				System.out.println("4. Laskut");
				System.out.println("5. Tarjoukset");
				System.out.println("6. Tarvikkeet (varasto)");
				System.out.println("7. Tapahtumat");
				System.out.println("8. Raportit");
				System.out.println("9. Lopeta");
				System.out.println("");
				System.out.println("00. Alusta tietokanta testidatalla (poistaa kaikki tallettamasi tiedot)");
				System.out.println("");
				System.out.println("Syötä ryhmän numero:");
				valinta = input.nextLine();
				if (valinta.equals("9")) {
					kysyValinta = false;
				} else if (valinta.equals("1")) {
					stmt = con.createStatement();
					asiakas(stmt);
					stmt.close();
				} else if (valinta.equals("2")) {
					stmt = con.createStatement();
					kohde(stmt);
					stmt.close();
				} else if (valinta.equals("3")) {
					stmt = con.createStatement();
					projekti(stmt);
					stmt.close();
				} else if (valinta.equals("4")) {
					stmt = con.createStatement();
					lasku(stmt);
					stmt.close();
				} else if (valinta.equals("5")) {
					stmt = con.createStatement();
					tarjous(stmt);
					stmt.close();
				} else if (valinta.equals("6")) {
					stmt = con.createStatement();
					tarvike(stmt);
					stmt.close();
				} else if (valinta.equals("7")) {
					stmt = con.createStatement();
					tapahtumat(stmt);
					stmt.close();
				} else if (valinta.equals("8")) {
					stmt = con.createStatement();
					raportit(stmt);
					stmt.close();
				} else if (valinta.equals("00")) {
					stmt = con.createStatement();
					alustus(stmt);
					stmt.close();
				} else {
					System.out.println("Virheellinen valinta.");
				}
				System.out.println("");
			}

		} catch (SQLException poikkeus) {

			// Vaihe 3.2: tähän toiminta mahdollisessa virhetilanteessa

			System.out.println("Tapahtui seuraava virhe: " + poikkeus.getMessage());     
		}       

		// Vaihe 4: yhteyden sulkeminen 
		System.out.println(con);
		if (con != null) try {     // jos yhteyden luominen ei onnistunut, con == null
			con.close();
		} catch(SQLException poikkeus) {
			System.out.println("Yhteyden sulkeminen tietokantaan ei onnistunut. Lopetetaan ohjelman suoritus.");
			return;
		}
	}
  
}
