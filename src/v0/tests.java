package v0;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;

public class tests {
	
	Defender ourShip = new Defender(new Point(50,100));
	Invaders inv  = new FireInvaders(new Point(200,300));
	
	@Test
	public void CollideTest1() {
		ourShip = new Defender(new Point(50,100));
		inv  = new FireInvaders(new Point(60,80));
		assertEquals(ourShip.collide(inv), true);
	}
	
	@Test
	public void CollideTest2() {
		ourShip = new Defender(new Point(100,100));
		inv  = new FireInvaders(new Point(10,100));
		assertEquals(ourShip.collide(inv), false);
	}
	
	@Test
	public void CollideTest3() {
		ourShip = new Defender(new Point(100,100));
		inv  = new FireInvaders(new Point(137,100));
		assertEquals(ourShip.collide(inv), true);
	}

}
