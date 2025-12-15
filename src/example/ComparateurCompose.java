package example;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class PersonneNom {
	private final String nom;
	private final String prenom;
	private final int age;

	public PersonneNom(String prenom, String nom, int age) {
		this.prenom = prenom;
		this.nom = nom;
		this.age = age;
	}

	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public int getAge() {
		return age;
	}

	@Override
	public String toString() {
		return prenom + " " + nom + " (" + age + ")";
	}
}

public class ComparateurCompose {
	public static void main(String[] args) {
		List<PersonneNom> personnes = Arrays.asList(new PersonneNom("Jean", "Dupont", 30),
				new PersonneNom("Marie", "Martin", 25), new PersonneNom("Pierre", "Dupont", 40),
				new PersonneNom("Sophie", "Martin", 35), new PersonneNom("Paul", "Dupont", 20));

		Comparator<PersonneNom> comparateur = Comparator.comparing(PersonneNom::getNom)
				.thenComparing(PersonneNom::getPrenom).thenComparingInt(PersonneNom::getAge);

		System.out.println("Liste triée:");
		personnes.stream().sorted(comparateur).forEach(System.out::println);

		Comparator<PersonneNom> comparateurVariante = Comparator.comparing(PersonneNom::getNom)
				.thenComparing(PersonneNom::getAge, Comparator.reverseOrder());

		System.out.println("\nListe triée (variante):");
		personnes.stream().sorted(comparateurVariante).forEach(System.out::println);
	}
}
