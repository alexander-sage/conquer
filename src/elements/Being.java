package elements;

public class Being {
	
	int health;
	int maxHealth;
	final String name;
	String race;
	
	public Being(String name){
		this.name = name;
		this.health = 1;
		this.maxHealth = 1;
	}

	public Being reduceHealth(int i){
		health-=i;
		return this;
	}
	
	public Being addHealth(int i){
		health+=i;
		if(health>maxHealth){
			health = maxHealth;
		}
		return this;
	}

	public int getHealth() {
		return health;
	}

	public Being setHealth(int health) {
		this.health = health;
		return this;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public Being setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
		return this;
	}

	public String getName() {
		return name;
	}
	
	public static Being ofWolfman(String name){
		return new Being(name).setMaxHealth(100).fillHealth();
	}
	
	public Being setRace(String race){
		this.race = race;
		return this;
	}
	
	public String getRace(){
		return race;
	}

	private Being fillHealth() {
		if(health<maxHealth){
			health = maxHealth;
		}
		return this;
	}

}
