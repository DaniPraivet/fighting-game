package dev.danipraivet.juegodelucha.entities;

import java.awt.*;

public enum DamageColors {
    DAMAGE_0(0, new Color(255,255,255)),
    DAMAGE_25(25, new Color(255, 255,150)),
    DAMAGE_100(100, new Color(255,255,0)),
    DAMAGE_125(125, new Color(255, 128,0)),
    DAMAGE_150(150, new Color(255,85,0)),
    DAMAGE_175(175, new Color(255,0,0)),
    DAMAGE_225(225, new Color(100, 0,0));

    private final int damage;
    private final Color color;

    DamageColors(int damage, Color color) {
        this.damage = damage;
        this.color = color;
    }

    public static Color getColor(int damage) {
        if (damage > 225) return Color.BLACK;

        Color color = DAMAGE_0.color;

        for (DamageColors damageColor : DamageColors.values()) {
            if (damage <= damageColor.damage) {
                color = damageColor.color;
                break;
            }
        }

        return color;
    }
}
