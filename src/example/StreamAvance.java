package example;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class PersonneVille {
    private final String nom;
    private final int age;
    private final String ville;

    public PersonneVille(String nom, int age, String ville) {
        this.nom = nom;
        this.age = age;
        this.ville = ville;
    }

    public String getNom() { return nom; }
    public int getAge() { return age; }
    public String getVille() { return ville; }

    @Override
    public String toString() {
        return nom + " (" + age + ") - " + ville;
    }
}

public class StreamAvance {
	
	 public static void main(String[] args) {
	        List<PersonneVille> personnes = Arrays.asList(
	                new PersonneVille("Jean", 25, "Paris"),
	                new PersonneVille("Marie", 30, "Lyon"),
	                new PersonneVille("Pierre", 20, "Paris"),
	                new PersonneVille("Sophie", 35, "Lyon"),
	                new PersonneVille("Paul", 40, "Marseille")
	        );

	        Map<String, List<PersonneVille>> parVille = personnes.stream()
	                .collect(Collectors.groupingBy(PersonneVille::getVille));
	        System.out.println("Personnes par ville: " + parVille);

	        double ageMoyen = personnes.stream()
	                .mapToInt(PersonneVille::getAge)
	                .average()
	                .orElse(0);
	        System.out.println("Âge moyen: " + ageMoyen);

	        PersonneVille plusAgee = personnes.stream()
	                .max((p1, p2) -> Integer.compare(p1.getAge(), p2.getAge()))
	                .orElse(null);
	        System.out.println("Personne la plus âgée: " + plusAgee);

	        List<String> parisiens = personnes.stream()
	                .filter(p -> p.getVille().equals("Paris"))
	                .map(PersonneVille::getNom)
	                .map(String::toUpperCase)
	                .collect(Collectors.toList());
	        System.out.println("Parisiens: " + parisiens);
	    }

}
