package tests;

import static org.junit.Assert.*;

import java.awt.Point;
import org.junit.Test;
import v0.*;

public class tests {
	
	Defender ourShip;
	Invaders inv;
	Missile mis;
	
	@Test
	public void CollideTest1() {
		ourShip = new Defender(new Point(50,100));
		inv  = new FireInvaders(new Point(60,80));
		assertEquals(ourShip.collideWith(inv), true);
	}
	
	@Test
	public void CollideTest2() {
		ourShip = new Defender(new Point(200,100));
		inv  = new FireInvaders(new Point(10,100));
		assertEquals(ourShip.collideWith(inv), false);
	}
	
	@Test
	public void CollideTest3() {
		ourShip = new Defender(new Point(100,100));
		inv  = new FireInvaders(new Point(137,100));
		assertEquals(ourShip.collideWith(inv), true);
	}
	
	@Test
	public void CollideTest4() {
		mis = new Missile(new Point(100,125), Element.movement.TOP, false);
		inv  = new FireInvaders(new Point(100,100));
		assertEquals(mis.collideWith(inv), true);
	}

}
