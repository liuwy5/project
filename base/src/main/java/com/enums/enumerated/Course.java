package com.enums.enumerated;

import com.enums.random.Enums;

public enum Course {
    APPETIZER(Food.Appetizer.class),
    MAINCOURSE(Food.MainCourse.class),
    DESSERT(Food.Dessert.class)
    ;

    private Food[] values;

    Course(Class<? extends Food> kind) {
        values = kind.getEnumConstants();
    }

    public Food randomSelect() {
        return Enums.random(values);
    }
}
