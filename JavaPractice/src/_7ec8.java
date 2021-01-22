// 7ec8: Battling fighters

// In this question you will simulate a battle between fighters using rules based on the
// Fighting Fantasy adventure game books.
//
// A fighter has a name (e.g. "Joe"), a type (e.g. "Elf Lord"), and two further attributes: skill,
// an integer initialised to a value between 1 and 18 that remains fixed, and stamina,
// an integer initialised to a value between 1 and 24.
//
// A battle between two fighters consists of a series of rounds.
// In a round, each fighter computes an attack score. This is obtained by rolling two dice,
// and adding the result to the fighter's skill. If both fighters compute the same attack score,
// the round results in a draw. Otherwise the fighter with the higher attack score hits the fighter
// with the lower attack score, whose stamina is reduced by two
// (or set to zero if it is currently set to one).
// The battle ends when one of the fighters' stamina becomes zero.

import java.util.Random;

public class _7ec8 {

  public static void main(String[] args) {
	  var f1 = new Fighter("Mike", "Dwarf", 10, 40);
	  var f2 = new Fighter("Jason", "Goblin", 14, 10);
	  var gm = new GameEngine();
	  gm.simulateBattle(f1, f2);
  }
}

class Fighter {

	private final String name;
	private final String type;
	private final int skill;
	private int stamina;
	private final Random gen = new Random();

	public Fighter(String name, String type, int skill, int stamina) {
		this.name = name;
		this.type = type;
		this.skill = Math.min(Math.max(skill, 1), 18);
		this.stamina = Math.min(Math.max(stamina, 1), 24);
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name + " - " + type + " " + "skill: " + skill + "; stamina: " + stamina;
	}

	// Return the number of damage points to be inflicted on opponent
	public void updateDamage() {
		stamina = Math.max(stamina - 2, 0);
	}

	// Calculate an attack score for the fighter using the procedure described above
	public int calculateAttackScore() {
		return skill + 2 + gen.nextInt(6) + gen.nextInt(6);
	}

	// Determine whether fighter is still alive
	public boolean isDead() {
		return stamina == 0;
	}
}

class GameEngine {

	// Simulate battle between two fighters, displaying how the battle progresses and who wins
	public void simulateBattle(Fighter first, Fighter second) {
		System.out.println("At start of battle, stats are:");
		System.out.println(first);
		System.out.println(second);
		System.out.println("------------------------------");

		while (true) {
			var scoreF = first.calculateAttackScore();
			var scoreS = second.calculateAttackScore();

			if (scoreF > scoreS) {
				second.updateDamage();
				System.out.println(first.getName() + " hits " + second.getName() + ", stats are:");
				System.out.println(first);
				System.out.println(second);
				System.out.println("------------------------------");

				if (second.isDead()) {
          System.out.println("End of battle, " + first + " wins!");
          break;
				}
			} else if (scoreS > scoreF) {
				first.updateDamage();
				System.out.println(second.getName() + " hits " + first.getName() + ", stats are:");
				System.out.println(first);
				System.out.println(second);
				System.out.println("------------------------------");

				if (first.isDead()) {
					System.out.println("End of battle, " + second + " wins!");
					break;
				}
			} else {
        System.out.println(first.getName() + " draws with " + second.getName());
				System.out.println("------------------------------");
			}
		}
	}

	public void simulateLuckyBattle(LuckyFighter first, LuckyFighter second) {
		System.out.println("At start of battle, stats are:");
		System.out.println(first);
		System.out.println(second);
		System.out.println("------------------------------");

		while (true) {
			var scoreF = first.calculateAttackScore();
			var scoreS = second.calculateAttackScore();

			if (scoreF > scoreS) {
				second.takeDamage(first.calculateDamage());
				System.out.println(first.getName() + " hits " + second.getName() + ", stats are:");
				System.out.println(first);
				System.out.println(second);
				System.out.println("------------------------------");

				if (second.isDead()) {
					System.out.println("End of battle, " + first + " wins!");
					break;
				}
			} else if (scoreS > scoreF) {
				first.takeDamage(second.calculateDamage());
				System.out.println(second.getName() + " hits " + first.getName() + ", stats are:");
				System.out.println(first);
				System.out.println(second);
				System.out.println("------------------------------");

				if (first.isDead()) {
					System.out.println("End of battle, " + second + " wins!");
					break;
				}
			} else {
				System.out.println(first.getName() + " draws with " + second.getName());
				System.out.println("------------------------------");
			}
		}
	}
}
