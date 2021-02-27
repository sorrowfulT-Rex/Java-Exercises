package tutorialquestion;// 0378: Comparing people

/* Full description:
 * https://github.com/afd/ProgrammingIITutorialQuestions/blob/master/questions/0378.md
 */

import java.util.HashSet;
import java.util.Set;

interface PersonComparator {
  int compareTo(Person p1, Person p2);
}

class TwoTieredComparator implements PersonComparator {
  private final PersonComparator primary;
  private final PersonComparator secondary;

  public TwoTieredComparator(PersonComparator primary, PersonComparator secondary) {
    this.primary = primary;
    this.secondary = secondary;
  }

  @Override
  public int compareTo(Person p1, Person p2) {
    var primaryResult = primary.compareTo(p1, p2);
    if (primaryResult == 0) {
      return secondary.compareTo(p1, p2);
    } else {
      return primaryResult;
    }
  }
}

public class _0378 {

  public static void main(String[] args) {
    Person mike = new Person("Jin", "Mike", "9527");
    Person jason = new Person("Dai", "Jason", "4532");
    Person paul = new Person("Pan", "Paul", "3492");

    Person one = new Person("1", "1", "1");
    Person two = new Person("1", "1", "2");
    Person three = new Person("1", "1", "3");

    var pSet = new HashSet<>(Set.of(mike, jason, paul));
    var pppp = new HashSet<>(Set.of(one, two, three));

    System.out.println(findMin(pSet, new SurnameComparator()));
    System.out.println(findMin(pSet, new ForenameComparator()));
    System.out.println(findMin(pSet, new TelephoneComparator()));
    System.out.println(
        findMin(pppp, new TwoTieredComparator(new SurnameComparator(), new ForenameComparator())));
    System.out.println(
        findMin(
            pppp,
            new TwoTieredComparator(
                new SurnameComparator(),
                new TwoTieredComparator(new ForenameComparator(), new TelephoneComparator()))));
  }

  public static Person findMin(Set<Person> people, PersonComparator comparator) {
    if (people.isEmpty()) {
      throw new IllegalArgumentException("The set of people is empty!");
    }
    var minPerson = people.iterator().next();

    for (var p : people) {
      if (comparator.compareTo(p, minPerson) < 0) {
        minPerson = p;
      }
    }

    return minPerson;
  }
}

class Person {

  private final String surname;
  private final String forename;
  private final String telephone;

  public Person(String surname, String forename, String telephone) {
    this.surname = surname;
    this.forename = forename;
    this.telephone = telephone;
  }

  public String getSurname() {
    return surname;
  }

  public String getForename() {
    return forename;
  }

  public String getTelephone() {
    return telephone;
  }

  @Override
  public String toString() {
    return this.forename + " " + this.surname + ": " + this.telephone;
  }
}

class SurnameComparator implements PersonComparator {

  @Override
  public int compareTo(Person p1, Person p2) {
    return (int) Math.signum(p1.getSurname().compareTo(p2.getSurname()));
  }
}

class ForenameComparator implements PersonComparator {

  @Override
  public int compareTo(Person p1, Person p2) {
    return (int) Math.signum(p1.getForename().compareTo(p2.getForename()));
  }
}

class TelephoneComparator implements PersonComparator {

  @Override
  public int compareTo(Person p1, Person p2) {
    return (int) Math.signum(p1.getTelephone().compareTo(p2.getTelephone()));
  }
}
