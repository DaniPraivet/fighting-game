package dev.danipraivet.juegodelucha.math;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class VelocityTest {
    private Velocity velocity;
    
    @BeforeEach
    void setUp() {
        velocity = new Velocity(0, 0);
    }

    @Test
    void testGravity() {
        velocity.setGravity(0.9);
        Assertions.assertEquals(0.9, velocity.getGravity());
        
        velocity.setDefaultGravity();
        Assertions.assertEquals(0.5, velocity.getGravity());
    }
}