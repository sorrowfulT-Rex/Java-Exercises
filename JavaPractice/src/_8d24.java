import java.util.Random;

public class _8d24 {

	public static void main(String[] args) {
		var f1 = new LuckyFighter("Mike", "Dwarf", 14, 20, 10, BattleStyle.AVERAGE);
		var f2 = new LuckyFighter("Jason", "Goblin", 14, 20, 10, BattleStyle.AGGRESSIVE);
		var gm = new GameEngine();
		gm.simulateLuckyBattle(f1, f2);
	}
}

enum BattleStyle {
	AGGRESSIVE, DEFENSIVE, AVERAGE
}

class LuckyFighter {

	private final String name;
	private final String type;
	private final int skill;
	private int stamina;
	private int luck;
	private final BattleStyle bs;
	private final Random gen = new Random();

	public LuckyFighter(String name, String type, int skill, int stamina, int luck, BattleStyle bs) {
		this.name = name;
		this.type = type;
		this.skill = Math.min(Math.max(skill, 1), 18);
		this.stamina = Math.min(Math.max(stamina, 1), 24);
		this.luck = Math.min(Math.max(luck, 1), 18);
		this.bs = bs;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name + " - " + bs + " " + type + " "
				+ "skill: " + skill + "; stamina: " + stamina + "; luck: " + luck;
	}

	// Return the number of damage points to be inflicted on opponent
	public int calculateDamage() {
		int damage;
		if (bs == BattleStyle.DEFENSIVE) {
			damage = 2;
		} else {
			if (luck >= 7) {
				System.out.println(name + " goes for an aggressive hit!");
				if (2 + gen.nextInt(6) + gen.nextInt(6) <= luck) {
					System.out.println(name + " is lucky!");
					System.out.println("The hit is aggressive!");
					damage = 4;
				} else {
					System.out.println(name + " is unlucky!");
					System.out.println("The hit is reduced!");
					damage = 1;
				}

				luck--;
			} else {
				damage = 2;
			}
		}

		return damage;
	}

	public void takeDamage(int dmg) {
		if (bs == BattleStyle.AGGRESSIVE) {
			stamina = Math.max(0, stamina - dmg);
		} else {
			if (luck >= 7) {
				System.out.println(name + " tries to resist the damage!");
				if (2 + gen.nextInt(6) + gen.nextInt(6) <= luck) {
					System.out.println(name + " is lucky!");
					System.out.println("The damage is partially resisted!");
					stamina = Math.max(0, stamina - dmg + 1);
				} else {
					System.out.println(name + " is unlucky!");
					System.out.println("The damage is increased!");
					stamina = Math.max(0, stamina - dmg - 1);
				}

				luck--;
			} else {
				stamina = Math.max(0, stamina - dmg);
			}
		}
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
