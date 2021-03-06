package com.github.manasmods.unordinary_basics.utils;

public class UBUtils {

    /**
     * An "instance of" check for multiple classes
     *
     * @param target Target Object
     * @param types  Array of Classes
     */
    public static boolean isInstanceOf(Object target, Class<?>... types) {
        for (Class<?> type : types) {
            if (type.isInstance(target)) {
                return true;
            }
        }

        return false;
    }

    public static int getXpPointsForLevel(int level) {
        if (level <= 15) {
            return 2 * level + 7;
        } else if (level <= 30) {
            return 5 * level - 38;
        }
        return 9 * level - 158;
    }
}
